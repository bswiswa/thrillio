package com.semanticsquare.thrillio;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.constants.Gender;
import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.entities.Book;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.Movie;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.entities.WebLink;
import com.semanticsquare.thrillio.managers.BookmarkManager;
import com.semanticsquare.thrillio.managers.UserManager;
import com.semanticsquare.thrillio.util.IOUtil;

public class DataStore {
	public static final int MAX_BOOKMARKS_PER_USER = 5;
	public static final int BOOKMARK_TYPE_NUMBER = 3;
	public static final int MAX_USERS = 5;
	public static final int MAX_NUMBER_PER_BOOKMARK_TYPE = 5;
	public static final int MAX_WEBLINKS = 5, MAX_MOVIES = 5, MAX_BOOKS = 5;
	// all our possible users
	private static User[] users = new User[MAX_USERS];
	// all our possible bookmarks
	private static Bookmark[][] bookmarks = new Bookmark[BOOKMARK_TYPE_NUMBER][MAX_NUMBER_PER_BOOKMARK_TYPE];
	// also creating a store for the bookmarks that users make
	// we have 5 users and each of them can make a maximum of 5 bookmarks
	private static UserBookmark[] userBookmarks = new UserBookmark[MAX_USERS * MAX_BOOKMARKS_PER_USER];
	private static int bookmarkIndex;
	// bookmarkIndex keeps track of where we are in the userBookmarks array

	// getters for users and bookmarks to be called on by Data Access Objects since
	// we have no database
	public static User[] getUsers() {
		return users;
	}

	public static Bookmark[][] getBookmarks() {
		return bookmarks;
	}

	public static void loadData() {
		loadUsers();
		loadWebLinks();
		loadMovies();
		loadBooks();
	}

	private static void loadUsers() {
		String[] data = new String[MAX_USERS];
		IOUtil.read(data, "User");
		users = new User[MAX_USERS];
		for (int i = 0; i < data.length; i++) {
			String[] words = data[i].split("\\s+");
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
			users[i] = UserManager.getInstance().createUser(id, email, password, firstName, lastName, gender, userType);
		}
		// users[0] = UserManager.getInstance().createUser(1000,
		// "user0@semanticsquare.com", "test", "John", "M",
		// Gender.MALE, UserType.USER);
		// users[1] = UserManager.getInstance().createUser(1001,
		// "user1@semanticsquare.com", "test", "Sam", "M",
		// Gender.MALE, UserType.USER);
		// users[2] = UserManager.getInstance().createUser(1002,
		// "user2@semanticsquare.com", "test", "Anita", "M",
		// Gender.FEMALE, UserType.EDITOR);
		// users[3] = UserManager.getInstance().createUser(1003,
		// "user3@semanticsquare.com", "test", "Sara", "M",
		// Gender.FEMALE, UserType.EDITOR);
		// users[4] = UserManager.getInstance().createUser(1004,
		// "user4@semanticsquare.com", "test", "Dheeru", "M",
		// Gender.MALE, UserType.CHIEF_EDITOR);
	}



	private static void loadWebLinks() {
		String[] data = new String[MAX_WEBLINKS];
		IOUtil.read(data, "WebLink");
		bookmarks[0] = new WebLink[MAX_WEBLINKS];
		for (int i = 0; i < data.length; i++) {
			String[] words = data[i].split("\t");
			long id = Long.parseLong(words[0].trim());
			String title = words[1];
			String profileUrl = words[2];
			String url = words[2];
			String host = words[3];
			bookmarks[0][i] = BookmarkManager.getInstance().createWebLink(id, title, profileUrl, url, host);
		}
//		bookmarks[0][0] = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger Part 2",
//				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
//				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
//				"http://www.javaworld.com");
//		bookmarks[0][1] = BookmarkManager.getInstance().createWebLink(2001,
//				"How do I import a pre-existing Java project into Eclipse and get up and running?",
//				"http://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running",
//				"http://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running",
//				"http://www.stackoverflow.com");
//		bookmarks[0][2] = BookmarkManager.getInstance().createWebLink(2002, "Interface vs Abstract Class",
//				"http://mindprod.com/jgloss/interfacevsabstract.html",
//				"http://mindprod.com/jgloss/interfacevsabstract.html", "http://mindprod.com");
//		bookmarks[0][3] = BookmarkManager.getInstance().createWebLink(2003, "NIO tutorial by Greg Travis",
//				"http://cs.brown.edu/courses/cs161/papers/j-nio-ltr.pdf",
//				"http://cs.brown.edu/courses/cs161/papers/j-nio-ltr.pdf", "http://cs.brown.edu");
//		bookmarks[0][4] = BookmarkManager.getInstance().createWebLink(2004, "Virtual Hosting and Tomcat",
//				"http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html",
//				"http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html", "http://tomcat.apache.org");
	}

	private static void loadMovies() {
		String[] data = new String[MAX_MOVIES];
		IOUtil.read(data, "Movie");
		bookmarks[1] = new Movie[MAX_MOVIES];
		for(int i = 0; i < data.length; i++) {
			String[] word = data[i].split("\t");
			System.out.println(data[i]);
			for(int j = 0; j < word.length; j++) {
				word[j] = word[j].trim();
			}
			System.out.println("Num of words: "+ word.length);
			for(int k = 0; k < word.length; k++) {
				System.out.println(word[k]);
			}
			long id = Long.parseLong(word[0]);
			String title = word[1];
			String profileUrl = "-";
			int releaseYear = Integer.parseInt(word[2]);
			String[] cast = word[3].split(",");
			String[] directors = word[4].split(",");
			String genre = word[5];
			double imdbRating = Double.parseDouble(word[6]);
			
			bookmarks[1][i] = BookmarkManager.getInstance().createMovie(id, title, profileUrl, releaseYear, cast, directors, genre, imdbRating);
		}
//		bookmarks[1][0] = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "-", 1941,
//				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.CLASSICS,
//				8.5);
//		bookmarks[1][1] = BookmarkManager.getInstance().createMovie(3001, "The Grapes of Wrath", "-", 1940,
//				new String[] { "Henry Fonda", "Jane Darwell" }, new String[] { "John Ford" }, MovieGenre.CLASSICS, 8.2);
//		bookmarks[1][2] = BookmarkManager.getInstance().createMovie(3002, "A Touch of Greatness", "-", 2004,
//				new String[] { "Albert Cullum" }, new String[] { "Leslie Sullivan" }, MovieGenre.DOCUMENTARIES, 7.3);
//		bookmarks[1][3] = BookmarkManager.getInstance().createMovie(3003, "The Big Bang Theory", "-", 2007,
//				new String[] { "Kaley Cuoco", "Jim Parsons" }, new String[] { "Chuck Lorre", "Bill Prady" },
//				MovieGenre.TV_SHOWS, 8.7);
//		bookmarks[1][4] = BookmarkManager.getInstance().createMovie(3004, "Ikiru", "-", 1952,
//				new String[] { "Takashi Shimura", "Minoru Chiaki" }, new String[] { "Akira Kurosawa" },
//				MovieGenre.FOREIGN_MOVIES, 8.4);
	}

	private static void loadBooks() {
		String[] data = new String[MAX_BOOKS];
		bookmarks[2] = new Book[MAX_BOOKS];
		IOUtil.read(data, "Book");
		for(int i = 0; i < data.length; i++) {
			String[] word = data[i].split("\t");
			for(int j = 0; j < word.length; j++) {
				word[j] = word[j].trim()	;
			}
			long id = Long.parseLong(word[0]);
			String title = word[1];
			String profileUrl = "-";
			int publicationYear = Integer.parseInt(word[2]);
			String publisher = word[3];
			String[] authors = word[4].split(",");
			String genre = word[5];
			double amazonRating = Double.parseDouble(word[6]);
			bookmarks[2][i] = BookmarkManager.getInstance().createBook(id, title, profileUrl, publicationYear, publisher, authors, genre, amazonRating);
		}
		
//		bookmarks[2][0] = BookmarkManager.getInstance().createBook(4000, "Walden", "-", 1854, "Wilder Publications",
//				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);
//		bookmarks[2][1] = BookmarkManager.getInstance().createBook(4001, "Self-Reliance and Other Essays", "-", 1993,
//				"Dover Publications", new String[] { "Ralph Waldo Emerson" }, BookGenre.PHILOSOPHY, 4.5);
//		bookmarks[2][2] = BookmarkManager.getInstance().createBook(4002, "Light From Many Lamps", "-", 1988,
//				"Touchstone", new String[] { "Lillian Eichler Watson" }, BookGenre.PHILOSOPHY, 5.0);
//		bookmarks[2][3] = BookmarkManager.getInstance().createBook(4003, "Head First Design Patterns	Technical", "-",
//				2004, "O'Reilly Media",
//				new String[] { "Eric Freeman", "Bert Bates", "Kathy Sierra", "Elisabeth Robson" }, BookGenre.TECHNICAL,
//				4.5);
//		bookmarks[2][4] = BookmarkManager.getInstance().createBook(4004, "Effective Java Programming Language Guide",
//				"-", 2007, "Prentice Hall", new String[] { "Joshua Bloch" }, BookGenre.TECHNICAL, 4.9);
	}

	public static void add(UserBookmark userBookmark) {
		userBookmarks[bookmarkIndex] = userBookmark;
		bookmarkIndex++;

	}

}
