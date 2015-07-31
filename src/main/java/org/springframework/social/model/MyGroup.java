package org.springframework.social.model;

import java.util.List;

public class MyGroup {

	private String groupMessage;
	private String groupLink;
	private String groupName;
	private String groupCaption;
	private String groupDescription;
	private String groupImage;
	private String clientId;// use clientId here to know what user's Posting to Wall
	private String[] listGroup;
	
	public String[] getListGroup() {
		return listGroup;
	}
	public void setListGroup(String[] listGroup) {
		this.listGroup = listGroup;
	}
	public String getGroupMessage() {
		return groupMessage;
	}
	public void setGroupMessage(String groupMessage) {
		this.groupMessage = groupMessage;
	}
	public String getGroupLink() {
		return groupLink;
	}
	public void setGroupLink(String groupLink) {
		this.groupLink = groupLink;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupCaption() {
		return groupCaption;
	}
	public void setGroupCaption(String groupCaption) {
		this.groupCaption = groupCaption;
	}
	public String getGroupDescription() {
		return groupDescription;
	}
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
	public String getGroupImage() {
		return groupImage;
	}
	public void setGroupImage(String groupImage) {
		this.groupImage = groupImage;
	}
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public MyGroup(String groupMessage, String groupLink, String groupName,
			String groupCaption, String groupDescription, String groupImage,
			String clientId, String[] listGroup) {
		super();
		this.groupMessage = groupMessage;
		this.groupLink = groupLink;
		this.groupName = groupName;
		this.groupCaption = groupCaption;
		this.groupDescription = groupDescription;
		this.groupImage = groupImage;
		this.clientId = clientId;
		this.listGroup = listGroup;
	}
	public MyGroup(){
		
	}
	
}
