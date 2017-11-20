package com.semanticsquare.thrillio;

import java.util.List;
import java.util.Map;

import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.controllers.BookmarkController;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.partner.Shareable;

public class View {

	public static void browse(User user, Map<Integer, List<Bookmark>> bookmarks) {
		System.out.println("\n" + user.getEmail() + " is browsing items");
		for (Integer bookmarkList : bookmarks.keySet()) {
			for (Bookmark bookmark : bookmarks.get(bookmarkList)) {
					boolean isBookmarked = getBookMarkDecision(bookmark);
					if (isBookmarked) {
						BookmarkController.getInstance().saveUserBookmark(user, bookmark);
						System.out.println("New item bookmarked..." + bookmark);
					}
				UserType userType = user.getUserType();
				if(userType.equals(UserType.CHIEF_EDITOR)  || userType.equals(UserType.EDITOR)) {
					//Mark as kid friendly
					if(bookmark.isKidFriendlyEligible() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {	
						if(bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {	
							KidFriendlyStatus kidFriendlyChoice = getKidFriendlyDecision();
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
	
	private static KidFriendlyStatus getKidFriendlyDecision() {
		int choice = (int)(Math.random()*3);
		if(choice == 0) return KidFriendlyStatus.UNKNOWN;
		if(choice == 1) return KidFriendlyStatus.REJECTED;
		else return KidFriendlyStatus.APPROVED;
	}
}
