package com.hanbit.team05.core.vo;

public class ConsultVO {

	private int cosultId;
	private String name;
	private String email;
	private String tel;
	private String message;

	@Override
	public String toString() {
		return "ConsultVO [cosultId=" + cosultId + ", name=" + name + ", email=" + email + ", tel=" + tel + ", message="
				+ message + "]";
	}

	public int getCosultId() {
		return cosultId;
	}
	public void setCosultId(int cosultId) {
		this.cosultId = cosultId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
