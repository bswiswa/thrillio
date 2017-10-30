// TODO make this into an abstract class
package com.semanticsquare.thrillio.entities;

public abstract class Bookmark {
	private long id;
	private String title;
	private String profileUrl;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	
	public abstract boolean isKidFriendlyEligible();
}
