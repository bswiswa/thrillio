package com.semanticsquare.thrillio.dao;

import com.semanticsquare.thrillio.entities.Book;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.entities.WebLink;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.semanticsquare.thrillio.DataStore;

public class BookmarkDao {
	public Map<Integer, List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false");
				Statement stmt = conn.createStatement();) {
			if(userBookmark.getBookmark() instanceof Book) {
				saveUserBook(userBookmark, stmt);
			}
			else if(userBookmark.getBookmark() instanceof WebLink) {
				saveUserWebLink(userBookmark, stmt);;
			}
			else{
				saveUserMovie(userBookmark, stmt);;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void saveUserMovie(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into User_Movie (user_id, movie_id) values ("+ 
				userBookmark.getUser().getId()+", "+ userBookmark.getBookmark().getId() + ")";
		stmt.executeUpdate(query);
		
	}

	private void saveUserWebLink(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into User_WebLink (user_id, weblink_id) values ("+ 
				userBookmark.getUser().getId()+", "+ userBookmark.getBookmark().getId() + ")";
		stmt.executeUpdate(query);	
	}

	private void saveUserBook(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into User_Book (user_id, book_id) values ("+ 
				userBookmark.getUser().getId()+", "+ userBookmark.getBookmark().getId() + ")";
		stmt.executeUpdate(query);
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
