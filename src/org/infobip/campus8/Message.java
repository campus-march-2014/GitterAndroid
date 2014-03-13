package org.infobip.campus8;

public class Message {
	private String title;
	private String text;
	private String id;
	
	public Message(String title, String text, String id) {
		this.title = title;
		this.text = text;
		this.id = id;
	}
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
