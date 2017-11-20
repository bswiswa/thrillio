package com.semanticsquare.thrillio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.constants.Gender;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.managers.BookmarkManager;
import com.semanticsquare.thrillio.managers.UserManager;
import com.semanticsquare.thrillio.util.IOUtil;

public class DataStore {
	// all our possible users
	private static List<User> users = new ArrayList<>();
	// all our possible bookmarks
	private static Map<Integer, List<Bookmark>> bookmarks = new HashMap<>();
	private static List<UserBookmark> userBookmarks = new ArrayList<>();
	// bookmarkCount keeps track of the number of bookmarks a user has chosen

	// getters for users and bookmarks to be called on by Data Access Objects since
	// we have no database
	public static List<User> getUsers() {
		return users;
	}

	public static Map<Integer, List<Bookmark>> getBookmarks() {
		return bookmarks;
	}

	public static void loadData() {
		loadUsers();
		loadWebLinks();
		loadMovies();
		loadBooks();
	}

	private static void loadUsers() {

		List<String> data = new ArrayList<>();
		IOUtil.read(data, "User");
		for (String line : data) {
			String[] words = line.split("\\s+");
			int id = Integer.parseInt(words[0]);
			String email = words[1];
			String password = words[2];
			String firstName = words[3];
			String lastName = words[4];
			int gender = 0;
			String userType = words[6];

			if (words[5].equals("m"))
				gender = Gender.MALE;
			else if (words[5].equals("f"))
				gender = Gender.FEMALE;
			else
				gender = Gender.TRANSGENDER;
			User user = UserManager.getInstance().createUser(id, email, password, firstName, lastName, gender,
					userType);
			users.add(user);
		}
	}

	private static void loadWebLinks() {
		List<Bookmark> data = new ArrayList<>();
//		Bookmark[] weblinks = (Bookmark[]) IOUtil.deserialize("weblinks.ser");
//		for (Bookmark weblink : weblinks) {
//			data.add(weblink);
//		}
//		bookmarks.put(0, data);
//		
		//Bookmark[] books = (Bookmark[]) IOUtil.deserialize("books.ser");
		
//		createWebLink(long id, String title, String profileUrl, String url, String host)
		List<String> str = new ArrayList<>();
		IOUtil.read(str, "WebLink");
		for (String line : str) {
			String[] words = line.split("\t");
			Bookmark b = BookmarkManager.getInstance().createWebLink(Long.parseLong(words[0]), words[1], words[2], words[3], words[4]);
			data.add(b);
		}
		bookmarks.put(0, data);

	}

	private static void loadMovies() {
		// String[] data = new String[MAX_MOVIES];
		// IOUtil.read(data, "Movie");
		List<Bookmark> data = new ArrayList<>();
		Bookmark[] movies = (Bookmark[]) IOUtil.deserialize("movies.ser");
		for (Bookmark movie : movies) {
			data.add(movie);
		}
		bookmarks.put(1, data);
	}

	private static void loadBooks() {
		List<Bookmark> data = new ArrayList<>();
		//Bookmark[] books = (Bookmark[]) IOUtil.deserialize("books.ser");
		List<String> str = new ArrayList<>();
		IOUtil.read(str, "Book");
		for (String line : str) {
			String[] words = line.split("\t");
			BookGenre[] genres = BookGenre.values();
			BookGenre genre = BookGenre.ART;
			for(BookGenre bg: genres) {
				if(bg.toString().equals(words[5].trim().toUpperCase())){
					genre = bg;
					break;
				}
			}
			Bookmark b = BookmarkManager.getInstance().createBook(Long.parseLong(words[0]), words[1], "-", Integer.parseInt(words[2]), words[3], words[4].split(","), genre, Double.parseDouble(words[6]));
			data.add(b);
		}
		bookmarks.put(2, data);
	}

	public static void add(UserBookmark userBookmark) {
		userBookmarks.add(userBookmark);
	}

}
