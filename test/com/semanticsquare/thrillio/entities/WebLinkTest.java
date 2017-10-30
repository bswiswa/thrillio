package com.semanticsquare.thrillio.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.semanticsquare.thrillio.managers.BookmarkManager;

class WebLinkTest {

	@Test
	void testIsKidFriendlyEligible() {
		// WebLinks have 5 fields:
		// id, title, profileUrl, url and host
		// for now let us look for keywords in these fields and if these words are
		// present in
		// some of these fields, we will return false. Otherwise we will return true
		// if "porn" is present anywhere in the url or title, return false
		// if "adult" is present in the host, return false
		WebLink link;
		// Test 1: "porn" in url -- false
		link = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.porn.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaworld.com");
		assertFalse("\"Porn\" is in url, should not be kid friendly", link.isKidFriendlyEligible());
		// Test 2: "porn" in title -- false
		link = BookmarkManager.getInstance().createWebLink(2000, "Taming Porn Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaworld.com");
		assertFalse("\"Porn\" is in title, should not be kid friendly", link.isKidFriendlyEligible());
		// Test 3: "adult" in host -- false
		link = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.adult.com");
		assertFalse("\"Adult\" is in host, should not be kid friendly", link.isKidFriendlyEligible());
		// Test 4: "adult" in url, but not in host part -- true
		link = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com/article/2072759/core-java/taming-adult-part-2.html", "http://www.javaworld.com");
		assertTrue("\"Adult\" is in url only, should be kid friendly", link.isKidFriendlyEligible());
		// Test 5: "adult" in title only -- true
		link = BookmarkManager.getInstance().createWebLink(2000, "Taming Adult Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger-part-2.html", "http://www.javaworld.com");
		assertTrue("\"Adult\" is in title only, should be kid friendly", link.isKidFriendlyEligible());
	}

}
