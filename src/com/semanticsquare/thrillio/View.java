package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.controllers.BookmarkController;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;

public class View {
	// public static void bookmark(User user, Bookmark[][] bookmarks) {
	//// we will simulate a user being presented with all the possible bookmarks on
	//// the website and randomly selecting 5 of them
	// System.out.println("\n" + user.getEmail() + " is bookmarking");
	// for(int i = 0; i < DataStore.MAX_BOOKMARKS_PER_USER; i++) {
	// int typeOffset = (int)(Math.random()*DataStore.BOOKMARK_TYPE_NUMBER);
	// int bookmarkOffset =
	// (int)(Math.random()*DataStore.MAX_NUMBER_PER_BOOKMARK_TYPE);
	// Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
	//// send this information to a controller
	// BookmarkController.getInstance().saveUserBookmark(user, bookmark);
	// System.out.println(bookmark);
	// }
	// }

	public static void browse(User user, Bookmark[][] bookmarks) {
		// we will simulate a user being presented with all the possible bookmarks on
		// the website and randomly selecting 5 of them
		System.out.println("\n" + user.getEmail() + " is browsing items");
		int bookmarkCount = 0;
		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				if (bookmarkCount < DataStore.MAX_BOOKMARKS_PER_USER) {
					boolean isBookmarked = getBookMarkDecision(bookmark);
					if (isBookmarked) {
						bookmarkCount++;
						BookmarkController.getInstance().saveUserBookmark(user, bookmark);
						System.out.println("New item bookmarked..." + bookmark);
					}
				}
				String userType = user.getUserType();
				if(userType.toLowerCase().equals(UserType.CHIEF_EDITOR)  || userType.toLowerCase().equals(UserType.EDITOR)) {
					if(bookmark.isKidFriendlyEligible() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {	
						if(bookmark.getKidFriendlyStatus().equals("unknown")) {
								String kidFriendlyChoice = getKidFriendlyDecision();
								bookmark.setKidFriendlyStatus(kidFriendlyChoice);
								System.out.println("Kid friendly status: "+ kidFriendlyChoice + ", "+ bookmark);
							}
					}
				}
			}
		}
	}

	private static boolean getBookMarkDecision(Bookmark bookmark) {
		return Math.random() > 0.5 ? false : true;
	}
	
	private static String getKidFriendlyDecision() {
		int choice = (int)(Math.random()*3);
		if(choice == 0) return KidFriendlyStatus.UNKNOWN;
		if(choice == 1) return KidFriendlyStatus.REJECTED;
		else return KidFriendlyStatus.APPROVED;
	}
}
