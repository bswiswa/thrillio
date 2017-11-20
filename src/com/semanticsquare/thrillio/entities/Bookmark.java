package com.semanticsquare.thrillio.entities;

import java.io.Serializable;

import com.semanticsquare.thrillio.constants.KidFriendlyStatus;

public abstract class Bookmark implements Serializable{
	private long id;
	private String title;
	private String profileUrl;
	private KidFriendlyStatus kidFriendlyStatus = KidFriendlyStatus.UNKNOWN;
	private User kidFriendlyMarkedBy;
	private User sharedBy;
//	kidFriendlyStatus' initial value is "unknown" but later it can be "approved" or "rejected"
//	so it has a range of values and we can use a constant exporting class
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
	
	public KidFriendlyStatus getKidFriendlyStatus() {
		return kidFriendlyStatus;
	}

	public void setKidFriendlyStatus(KidFriendlyStatus kidFriendlyStatus) {
		this.kidFriendlyStatus = kidFriendlyStatus;
	}

	public User getKidFriendlyMarkedBy() {
		return kidFriendlyMarkedBy;
	}

	public void setKidFriendlyMarkedBy(User kidFriendlyMarkedBy) {
		this.kidFriendlyMarkedBy = kidFriendlyMarkedBy;
	}

	public User getSharedBy() {
		return sharedBy;
	}

	public void setSharedBy(User sharedBy) {
		this.sharedBy = sharedBy;
	}

	public abstract boolean isKidFriendlyEligible();

	@Override
	public String toString() {
		return "Bookmark [id=" + id + ", title=" + title + ", profileUrl=" + profileUrl + ", kidFriendlyStatus="
				+ kidFriendlyStatus + "]";
	}
}
