package com.hanbit.team05.core.vo;

public class ConsultVO {

	private int consultId;
	private String name;
	private String email;
	private String tel;
	private String message;
	private String reply;
	private String replyContent;


	@Override
	public String toString() {
		return "ConsultVO [consultId=" + consultId + ", name=" + name + ", email=" + email + ", tel=" + tel
				+ ", message=" + message + ", reply=" + reply + ", replyContent=" + replyContent + "]";
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getConsultId() {
		return consultId;
	}

	public void setConsultId(int consultId) {
		this.consultId = consultId;
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

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

}
