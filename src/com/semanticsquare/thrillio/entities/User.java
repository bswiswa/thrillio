package com.semanticsquare.thrillio.entities;

import com.semanticsquare.thrillio.constants.Gender;
import com.semanticsquare.thrillio.constants.UserType;

public class User {
	private long id;
	// using a larger data type like long is good because in our database we may have millions of users
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Gender gender;
	private UserType userType;
	/* userType will differentiate between regular users or editors -> this is violating the recommendation
		of using class hierarchies but it is fine in this case because we are creating this project as a web
		application. There are not much differences between regular users and staff members
	*/
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender + ", userType=" + userType + "]";
	}
}
