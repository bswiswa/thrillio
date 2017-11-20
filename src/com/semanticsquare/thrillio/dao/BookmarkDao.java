package com.semanticsquare.thrillio.dao;

import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.entities.WebLink;

import java.util.ArrayList;
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
	
	public List<WebLink> getAllWebLinks(){
		List<WebLink> result = new ArrayList<>();
		Map<Integer, List<Bookmark>> bookmarks = getBookmarks();
		List<Bookmark> bk = bookmarks.get(0);
		for(Bookmark b: bk) {
			result.add((WebLink)b);
		}
		return result;
	}
	
	public List<WebLink> getWebLinks(WebLink.DownloadStatus downloadStatus){
		List<WebLink> weblinks = getAllWebLinks();
		List<WebLink> result = new ArrayList<>();
		for(WebLink w: weblinks) {
			if(w.getDownloadStatus().equals(downloadStatus)) {
				result.add(w);
			}
		}
		
		return result;
	}
	
}
