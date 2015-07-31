package org.springframework.social.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Entity class for user table. This is a simple POJO class with annotations
 * to define mapping with DB table
 * 
 * @author Anh Minh Nguyen
 *
 */
@Entity
@Table(name = "notification", uniqueConstraints = {
		@UniqueConstraint(columnNames = "notification_id")})
public class Notification implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	//User Facebook Id
	@Column(name = "notification_id", length=50)
	private String notificationId;
	
	@Column(name = "from_object_id",length=20)
	private String fromObject;

	@Column(name = "to_object_id",length=20)
	private String toObject;
	
	@Column(name = "date_created_time")
	private String dateCreatedTime;
	
	@Column(name = "date_updated_time")
	private String dateUpdatedTIme;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "link")
	private String link;
	
	@Column(name = "application_id")
	private String applicationId;
	
	@Column(name = "unread")
	private boolean unread;
	
	@Column(name = "is_show")
	private boolean isShow;
	
	@ManyToOne
	@JoinColumn(name = "id_post")
	private PostFacebook id_post;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User userId;
	
	
	public Notification(int id, String notificationId, String fromObject,
			String toObject, String dateCreatedTime, String dateUpdatedTIme,
			String title, String link, String applicationId, boolean unread,
			PostFacebook id_post, User userId, boolean isShow) {
		super();
		this.id = id;
		this.notificationId = notificationId;
		this.fromObject = fromObject;
		this.toObject = toObject;
		this.dateCreatedTime = dateCreatedTime;
		this.dateUpdatedTIme = dateUpdatedTIme;
		this.title = title;
		this.link = link;
		this.applicationId = applicationId;
		this.unread = unread;
		this.id_post = id_post;
		this.userId = userId;
		this.isShow = isShow;
	}

	public PostFacebook getId_post() {
		return id_post;
	}

	
	public void setId_post(PostFacebook id_post) {
		this.id_post = id_post;
	}
	
	
	public boolean isShow() {
		return isShow;
	}
	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}
	@JsonIgnore
	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	public String getFromObject() {
		return fromObject;
	}
	public void setFromObject(String fromObject) {
		this.fromObject = fromObject;
	}
	public String getToObject() {
		return toObject;
	}
	public void setToObject(String toObject) {
		this.toObject = toObject;
	}
	public String getDateCreatedTime() {
		return dateCreatedTime;
	}
	public void setDateCreatedTime(String dateCreatedTime) {
		this.dateCreatedTime = dateCreatedTime;
	}
	public String getDateUpdatedTIme() {
		return dateUpdatedTIme;
	}
	public void setDateUpdatedTIme(String dateUpdatedTIme) {
		this.dateUpdatedTIme = dateUpdatedTIme;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public boolean isUnread() {
		return unread;
	}
	public void setUnread(boolean unread) {
		this.unread = unread;
	}
	public Notification(int id, String notificationId, String fromObject,
			String toObject, String dateCreatedTime, String dateUpdatedTIme,
			String title, String link, String applicationId, boolean unread) {
		super();
		this.id = id;
		this.notificationId = notificationId;
		this.fromObject = fromObject;
		this.toObject = toObject;
		this.dateCreatedTime = dateCreatedTime;
		this.dateUpdatedTIme = dateUpdatedTIme;
		this.title = title;
		this.link = link;
		this.applicationId = applicationId;
		this.unread = unread;
	}

	public Notification(){
		
	}
	@Override
	public String toString() {
		return "Notification [id=" + id + ", notificationId=" + notificationId
				+ ", fromObject=" + fromObject + ", toObject=" + toObject
				+ ", dateCreatedTime=" + dateCreatedTime + ", dateUpdatedTIme="
				+ dateUpdatedTIme + ", title=" + title + ", link=" + link
				+ ", applicationId=" + applicationId + ", unread=" + unread
				+ "]";
	}
	
}
