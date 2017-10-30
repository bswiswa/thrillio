package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.controllers.BookmarkController;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.partner.Shareable;

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
					//Mark as kid friendly
					if(bookmark.isKidFriendlyEligible() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {	
						if(bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {	
							String kidFriendlyChoice = getKidFriendlyDecision();
							BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyChoice, bookmark);
							}
					}
//				Sharing possible if Editor and kidFriendlyStatus has to be approved. AND SHOULD BE BOOK OR WEBLINK ONLY
					if(bookmark.isKidFriendlyEligible() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED) && bookmark instanceof Shareable) {
						boolean isShared = getShareDecision();
						if(isShared) {
							//share with third party
							BookmarkController.getInstance().share(user, bookmark);
						}
					}
				}
			}
		}
	}
//	TODO: Below methods simulate user input. After IO, we take input via console
	private static boolean getShareDecision() {
		return Math.random() > 0.5? true: false;	
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
