package com.semanticsquare.thrillio.managers;

import com.semanticsquare.thrillio.entities.User;

public class UserManager {
	private static UserManager instance = new UserManager();

	private UserManager() {
	}
	// private constructor because we do not want clients to create managers. Only
	// one object needed
	// for a Singleton. So object has to be created in this class.

	public static UserManager getInstance() {
		// has to be static since we cannot create an instance of UserManager eg
		// UserManager u = new UserManager()
		// provides global point of access.
		return instance;
	}

	public User createUser(long id, String email, String password, String firstName, String lastName, int gender,
			String userType) {
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setGender(gender);
		user.setUserType(userType);

		return user;
	}
}