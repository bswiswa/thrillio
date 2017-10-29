package com.semanticsquare.thrillio.dao;

import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.DataStore;

public class BookmarkDao {
	public Bookmark[][] getBookmarks() {
		return DataStore.getBookmarks();
	}
}
