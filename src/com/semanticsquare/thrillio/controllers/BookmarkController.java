package com.semanticsquare.thrillio.controllers;

import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.managers.BookmarkManager;

public class BookmarkController {
	// also a Singleton
	private static BookmarkController instance = new BookmarkController();

	private BookmarkController() {
	}

	public static BookmarkController getInstance() {
		return instance;
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
	}

	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyChoice, Bookmark bookmark) {
		BookmarkManager.getInstance().setKidFriendlyStatus(user, kidFriendlyChoice, bookmark);
		
	}

	public void share(User user, Bookmark bookmark) {
		BookmarkManager.share(user, bookmark);		
	}
}
