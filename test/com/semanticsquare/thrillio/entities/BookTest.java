package com.semanticsquare.thrillio.entities;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.managers.BookmarkManager;

class BookTest {

	@Test
	void testIsKidFriendlyEligible() {
		// Books have 8 fields:
		// id, title, profileUrl, publicationYear, publisher, authors, genre,
		// amazonRating
		// for now let us look for keywords in these fields and if these words are
		// present in
		// some of these fields, we will return false. Otherwise we will return true
		// if "porn" is present anywhere in the url or title, return false
		// if "adult" is present in the host, return false
		Book book;
		// Test 1: "porn" in title -- false
		book = BookmarkManager.getInstance().createBook(4000, "porn", "-", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);
		assertFalse("\"porn\" in title, should return false", book.isKidFriendlyEligible());
		// Test 2: "porn" in publisher -- false
		book = BookmarkManager.getInstance().createBook(4000, "Walden", "-", 1854, "Porn Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);
		assertFalse("\"porn\" in publisher, should return false", book.isKidFriendlyEligible());
		// Test 3: "porn" in genre -- false
		book = BookmarkManager.getInstance().createBook(4000, "Walden", "-", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PORN, 4.3);
		assertFalse("\"porn\" in genre, should return false", book.isKidFriendlyEligible());
		// Test 4: "adult" in genre -- false
		book = BookmarkManager.getInstance().createBook(4000, "Walden", "-", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.ADULT, 4.3);
		assertFalse("\"adult\" in genre, should return false", book.isKidFriendlyEligible());
		// Test 4: "philosophy" in genre -- false
		book = BookmarkManager.getInstance().createBook(4000, "Walden", "-", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);
		assertFalse("\"Philosophy\" in genre, should return false", book.isKidFriendlyEligible());
		// Test 4: "Self help" in genre -- false
		book = BookmarkManager.getInstance().createBook(4000, "Walden", "-", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.SELF_HELP, 4.3);
		assertFalse("\"self help\" in genre, should return false", book.isKidFriendlyEligible());
	}

}
