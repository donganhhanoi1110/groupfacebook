package org.springframework.social.json;

import java.util.List;

import org.springframework.social.facebook.api.Group;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.model.BigGroupFace;
import org.springframework.social.model.Notification;
import org.springframework.social.model.Schedule;

public class NotificationsResponse {
	private boolean success;
	private String message;
	private List<Notification> listNotis;
	private boolean isShow;
	
	
	public boolean isShow() {
		return isShow;
	}
	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}
	public List<Notification> getListNotis() {
		return listNotis;
	}
	public void setListNotis(List<Notification> listNotis) {
		this.listNotis = listNotis;
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
	public NotificationsResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	
	}
	public NotificationsResponse(){
		
	}
}
