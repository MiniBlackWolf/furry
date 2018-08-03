package com.example.wolf.chat;

import java.util.Date;

/**
 * DTO�࣬��������������Ϣ
 * @author BoBo
 *
 */
public class Message {

	public String from;
	public String fromName;
	public String to;
	public String text;
	public Date date;

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
