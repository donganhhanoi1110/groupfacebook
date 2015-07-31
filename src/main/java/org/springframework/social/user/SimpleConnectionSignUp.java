package org.springframework.social.user;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

/**
 * Simple little {@link ConnectionSignUp} command that allocates new userIds in memory.
 * Doesn't bother storing a user record in any local database, since this quickstart just stores the user id in a cookie.
 * @author Minh Anh Nguyen & Hiep Xuan Nguyen
 */
public final class SimpleConnectionSignUp implements ConnectionSignUp {

	private final AtomicLong userIdSequence = new AtomicLong();
	
	public String execute(Connection<?> connection) {
		return Long.toString(userIdSequence.incrementAndGet());
	}

}
