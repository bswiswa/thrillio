package com.semanticsquare.thrillio.bgjobs;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.semanticsquare.thrillio.dao.BookmarkDao;
import com.semanticsquare.thrillio.entities.WebLink;
import com.semanticsquare.thrillio.entities.WebLink.DownloadStatus;
import com.semanticsquare.thrillio.util.HttpConnect;
import com.semanticsquare.thrillio.util.IOUtil;

public class WebpageDownloaderTask implements Runnable {
	private static BookmarkDao dao = new BookmarkDao();
	private static final long TIME_FRAME = 3000000000L;
	private boolean downloadAll = false;
	private ExecutorService downloadExecutor = Executors.newFixedThreadPool(5);

	private static class DownloaderTask<T extends WebLink> implements Callable<T> {
		T weblink;

		public DownloaderTask(T weblink) {
			this.weblink = weblink;
		}

		@Override
		public T call() {
			try {
				if (!weblink.getUrl().endsWith(".pdf")) {
					weblink.setDownloadStatus(DownloadStatus.FAILED);

					String htmlPage = HttpConnect.download(weblink.getUrl());
					weblink.setHtmlPage(htmlPage);
				} else {
					weblink.setDownloadStatus(DownloadStatus.NOT_ELIGIBLE);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return weblink;
		}

	}

	public WebpageDownloaderTask(boolean downloadAll) {
		this.downloadAll = downloadAll;
	}

	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			// get weblinks
			List<WebLink> webLinks = getWebLinks();

			// download concurrently
			if (webLinks.size() > 0) {
				download(webLinks);
			} else {
				System.out.println("No new Web Links to download");
			}
			// wait
			try {
				TimeUnit.SECONDS.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		downloadExecutor.shutdown();
	}

	private void download(List<WebLink> webLinks) {
		List<DownloaderTask<WebLink>> tasks = getTasks(webLinks);
		List<Future<WebLink>> futures;

		try {
			futures = downloadExecutor.invokeAll(tasks, TIME_FRAME, TimeUnit.NANOSECONDS);
			for (Future<WebLink> future : futures) {
				if (!future.isCancelled()) {
					WebLink weblink = future.get();
					String webPage = weblink.getHtmlPage();
					if (webPage != null) {
						IOUtil.write(webPage, weblink.getId());
						weblink.setDownloadStatus(WebLink.DownloadStatus.SUCCESS);
						System.out.println("Download Success: " + weblink.getUrl());
					} else {
						System.out.println("Webpage not downloaded: " + weblink.getUrl());
					}
				} else {
					future.cancel(true);
					System.out.println("\n\nThread is cancelled -->" + Thread.currentThread().getName());
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private List<DownloaderTask<WebLink>> getTasks(List<WebLink> webLinks) {
		List<DownloaderTask<WebLink>> tasks = new ArrayList<>();
		for (WebLink webLink : webLinks) {
			tasks.add(new DownloaderTask<WebLink>(webLink));
		}
		return tasks;
	}

	private List<WebLink> getWebLinks() {
		List<WebLink> webLinks = null;
		if (downloadAll) {
			webLinks = dao.getAllWebLinks();
			downloadAll = false;
		} else {
			webLinks = dao.getWebLinks(WebLink.DownloadStatus.NOT_ATTEMPTED);
		}
		return webLinks;
	}

}