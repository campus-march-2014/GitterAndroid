package org.infobip.campus8;

public class Message {
	private String title;
	
	public Message(String title, String text) {
		this.title = title;
		this.text = text;
	}
	private String text;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
