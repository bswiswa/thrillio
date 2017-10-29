package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.managers.BookmarkManager;
import com.semanticsquare.thrillio.managers.UserManager;

public class Launch {
	private static User[] users;
	private static Bookmark[][] bookmarks;
	
	private static void loadData() {	
		System.out.println("1. Loading data...");
		DataStore.loadData();
//		fetch the data from DataStore
		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();
//		print data
		System.out.println("2. Printing data ...");
		printUserData();
		printBookmarkData();
		}
	
	private static void printUserData() {
		for(User user: users) {
			System.out.println(user);
		}
		
	}
	
	private static void printBookmarkData() {
		for(Bookmark[] bookmark_group: bookmarks) {
			for(Bookmark bookmark: bookmark_group) {
				System.out.println(bookmark);
			}
		}
	}

	public static void main(String[] args) {
		loadData();
	}
}
