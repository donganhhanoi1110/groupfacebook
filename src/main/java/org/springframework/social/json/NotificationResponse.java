package org.springframework.social.json;

import java.util.List;

import org.springframework.social.model.GroupFace;
import org.springframework.social.model.Notification;


public class NotificationResponse {
	private boolean success;
	private String message;
	private List<Notification> notis;
	
	
	public List<Notification> getNotis() {
		return notis;
	}
	public void setNotis(List<Notification> notis) {
		this.notis = notis;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public NotificationResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	
	}
	public NotificationResponse(){
		
	}
}
