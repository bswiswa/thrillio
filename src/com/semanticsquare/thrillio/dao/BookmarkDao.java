package com.semanticsquare.thrillio.dao;

import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.UserBookmark;

import java.util.List;
import java.util.Map;

import com.semanticsquare.thrillio.DataStore;

public class BookmarkDao {
	public Map<Integer, List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
//		add method adds userBookmark to the userBookmark array
	}
}
