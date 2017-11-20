package com.semanticsquare.thrillio.constants;

public enum KidFriendlyStatus {
	UNKNOWN("unknown"),
	APPROVED("approved"),
	REJECTED("rejected");
	
	private String status;
	private KidFriendlyStatus(String status) {
		this.status = status;
	}
}
