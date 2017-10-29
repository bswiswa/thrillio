package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.controllers.BookmarkController;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;

public class View {
	public static void bookmark(User user, Bookmark[][] bookmarks) {
//		we will simulate a user being presented with all the possible bookmarks on
//		the website and randomly selecting 5 of them
		System.out.println("\n" + user.getEmail() + " is bookmarking");
		for(int i = 0; i < DataStore.MAX_BOOKMARKS_PER_USER; i++) {
			int typeOffset = (int)(Math.random()*DataStore.BOOKMARK_TYPE_NUMBER);
			int bookmarkOffset = (int)(Math.random()*DataStore.MAX_NUMBER_PER_BOOKMARK_TYPE);
			Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
//			send this information to a controller
			BookmarkController.getInstance().saveUserBookmark(user, bookmark);
			System.out.println(bookmark);
		}
	}
}
