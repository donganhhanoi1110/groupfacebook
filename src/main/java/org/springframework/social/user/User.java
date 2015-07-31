package org.springframework.social.user;

/**
 * Simple little User model. 
 * Just stores the user's id for simplicity.
 * @author Minh Anh Nguyen & Hiep Xuan Nguyen
 */
public final class User {
	
	private final String id;
	
	public User(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
}
