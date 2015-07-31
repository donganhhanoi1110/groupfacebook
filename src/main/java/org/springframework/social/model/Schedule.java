package org.springframework.social.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="schedule")
public class Schedule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="idSchedule")
	@GeneratedValue
	private int idSchedule;
	
	@Column(name="groupMessage",columnDefinition="text")
	private String groupMessage;
	
	@Column(name="groupLink")
	private String groupLink;
	
	@Column(name="groupName")
	private String groupName;
	
	@Column(name="groupCaption")
	private String groupCaption;
	
	@Column(name="groupDescription")
	private String groupDescription;
	
	@Column(name="groupImage")
	private String groupImage;
	
	@Column(name="datePost")
	private String datePost;
	
	@Column(name="clientId")
	private String clientId;
	
	@Column(name="state")
	private String state;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "schedule_group", joinColumns = { @JoinColumn(name = "idSchedule") }, inverseJoinColumns = { @JoinColumn(name = "idGroupFace") })
	private List<GroupFace> listGroupFaces = new ArrayList<GroupFace>();
	
	@OneToOne(fetch=FetchType.EAGER,mappedBy="schedule")
	private UploadedFileResponse uploadedFile;
	
	
	public UploadedFileResponse getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(UploadedFileResponse uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	public int getIdSchedule() {
		return idSchedule;
	}
	public void setIdSchedule(int idSchedule) {
		this.idSchedule = idSchedule;
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
	public String getDatePost() {
		return datePost;
	}
	public void setDatePost(String datePost) {
		this.datePost = datePost;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<GroupFace> getListGroupFaces() {
		return listGroupFaces;
	}
	public void setListGroupFaces(List<GroupFace> listGroupFaces) {
		this.listGroupFaces = listGroupFaces;
	}
	
	
	
	
}
