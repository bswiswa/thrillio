package com.semanticsquare.thrillio.constants;

public enum UserType {
	// no need to create an instance of this class so create a private constructor
	USER("user"),
	EDITOR("editor"),
	CHIEF_EDITOR("chiefeditor");
	String name;
	private UserType(String name) {
		this.name = name;
	}
}
