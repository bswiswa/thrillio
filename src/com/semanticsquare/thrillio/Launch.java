package com.semanticsquare.thrillio;

import java.util.List;
import java.util.Map;

import com.semanticsquare.thrillio.bgjobs.WebpageDownloaderTask;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.managers.BookmarkManager;
import com.semanticsquare.thrillio.managers.UserManager;

public class Launch {
	private static List<User> users;
	private static Map<Integer, List<Bookmark>> bookmarks;

	private static void loadData() {
		DataStore.loadData();
		// fetch the data from DataStore
		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();
		// print data
		// printUserData();
		// printBookmarkData();
	}

	private static void printUserData() {
		for (User user : users) {
			System.out.println(user);
		}

	}

	private static void printBookmarkData() {
		for (Integer bookmark_group : bookmarks.keySet()) {
			for (Bookmark bookmark : bookmarks.get(bookmark_group)) {
				System.out.println(bookmark);
			}
		}
	}

	private static void start() {
		for (User user : users) {
			// View.bookmark(user, bookmarks);
			View.browse(user, bookmarks);
		}
	}

	public static void main(String[] args) {
		loadData();
		printBookmarkData();
		start();
		//background jobs
		//runDownloaderJob();
	}

	private static void runDownloaderJob() {
		// TODO Auto-generated method stub
		WebpageDownloaderTask task = new WebpageDownloaderTask(true);
		(new Thread(task)).start();
	}
}
