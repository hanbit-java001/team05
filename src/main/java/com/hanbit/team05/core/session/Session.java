package com.hanbit.team05.core.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class Session {

	private boolean loggedIn;
	private int memberId;
	private String email;
	private String name;

	@Override
	public String toString() {
		return "Session [loggedIn=" + loggedIn + ", memberId=" + memberId + ", email=" + email + ", name=" + name + "]";
	}

	public void logout(){
		loggedIn = false;
		memberId = 0;
		email = null;
		name = null;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
