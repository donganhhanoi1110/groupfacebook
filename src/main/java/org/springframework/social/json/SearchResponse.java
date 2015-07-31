package org.springframework.social.json;

import java.util.List;

import org.springframework.social.model.GroupFace;


public class SearchResponse {
	private boolean success;
	private String message;
	private List<GroupFace> groups;
	
	public List<GroupFace> getGroups() {
		return groups;
	}
	public void setGroups(List<GroupFace> groups) {
		this.groups = groups;
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
	public SearchResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	
	}
	public SearchResponse(){
		
	}
}
