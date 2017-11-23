package com.semanticsquare.thrillio.managers;

import java.util.List;
import java.util.Map;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.dao.BookmarkDao;
import com.semanticsquare.thrillio.entities.Book;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.Movie;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.entities.WebLink;

public class BookmarkManager {
	// also implements Singleton pattern
	private static BookmarkManager instance = new BookmarkManager();
	private static BookmarkDao dao = new BookmarkDao();

	private BookmarkManager() {
	}

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast,
			String[] directors, MovieGenre genre, double imdbRating) {
		Movie movie = new Movie();
		movie.setId(id);
		movie.setTitle(title);
		movie.setProfileUrl(profileUrl);
		movie.setReleaseYear(releaseYear);
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setImdbRating(imdbRating);

		return movie;
	}

	public Book createBook(long id, String title, String profileUrl, int publicationYear, String publisher,
			String[] authors, BookGenre genre, double amazonRating) {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setProfileUrl(profileUrl);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setAuthors(authors);
		book.setGenre(genre);
		book.setAmazonRating(amazonRating);

		return book;
	}

	public WebLink createWebLink(long id, String title, String profileUrl, String url, String host) {
		WebLink weblink = new WebLink();
		weblink.setId(id);
		weblink.setTitle(title);
		weblink.setProfileUrl(profileUrl);
		weblink.setUrl(url);
		weblink.setHost(host);

		return weblink;
	}

	public Map<Integer, List<Bookmark>> getBookmarks() {
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);
		
		/*
		if (bookmark instanceof WebLink) {
			try {				
				String url = ((WebLink)bookmark).getUrl();
				if (!url.endsWith(".pdf")) {
					String webpage = HttpConnect.download(((WebLink)bookmark).getUrl());
					if (webpage != null) {
						IOUtil.write(webpage, bookmark.getId());
					}
				}				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
*/
		
		dao.saveUserBookmark(userBookmark);
	}

	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyChoice, Bookmark bookmark) {
		bookmark.setKidFriendlyStatus(kidFriendlyChoice);
		bookmark.setKidFriendlyMarkedBy(user);
		
		dao.updateKidFriendlyStatus(bookmark);
		System.out.println("Kid friendly status: "+ kidFriendlyChoice + ", Marked by: "+ user.getEmail() + ", "+ bookmark);
	}

	public static void share(User user, Bookmark bookmark) {
		bookmark.setSharedBy(user);
		System.out.println("Data to be shared: ");
//		we need to do downcasting so that we can use the proper getItemData() function
//		If we do not downcast Bookmark, at compile time, the compiler will look for an implementation of the getItemData() method
//		but since it does not exist in the Bookmark class, we will get an error
		if(!(bookmark instanceof Movie)) dao.updateSharedBy(bookmark);
		if(bookmark instanceof Book) {
			System.out.println(((Book)bookmark).getItemData());	
		}
		else if(bookmark instanceof WebLink) {
			System.out.println(((WebLink)bookmark).getItemData());	
		}
		
	}

}
