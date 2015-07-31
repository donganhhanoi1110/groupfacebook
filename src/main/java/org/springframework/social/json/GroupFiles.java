package org.springframework.social.json;

import java.util.List;

import org.springframework.social.facebook.api.Group;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.model.BigGroupFace;
import org.springframework.social.model.CommentFacebook;
import org.springframework.social.model.Notification;
import org.springframework.social.model.Schedule;

public class GroupFiles {
	private String id;
	private String fromId;
	private String message;
	private String downloadLink;
	private String updatedTime;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public GroupFiles(String id, String fromId, String message,
			String downloadLink, String updatedTime,String name) {
		super();
		this.id = id;
		this.fromId = fromId;
		this.message = message;
		this.downloadLink = downloadLink;
		this.updatedTime = updatedTime;
		this.name = name;
	}
	
	public GroupFiles() {
		
	}
}
