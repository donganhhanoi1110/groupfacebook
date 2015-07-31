package org.springframework.social.model;


import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * Entity class for user table. This is a simple POJO class with annotations
 * to define mapping with DB table
 * @table post
 * @author Nguyen Anh Minh
 *
 */
@Entity
@Table(name = "postFacebook", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id_post")})
public class PostFacebook implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "id_post")
	private String idPost;
	
	@Column(name="id_group")
	private String idGroup;
	
	@Column(name = "message",columnDefinition="text")
	private String message;
	
	@Column(name = "link",columnDefinition="text")
	private String link;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "caption")
	private String caption;
	
	@Column(name = "description",columnDefinition="text")
	private String description;
	
	@Column(name = "picture")
	private String picture;
	
	@Column(name = "created_date")
	private String createdDate;
	
	@Column(name="updated_date")
	private String updated_date;
	
	@Column(name="from_client_id")
	private String from_client_id;
	
	@Column(name="like_count")
	private int like_count;
	
	@Column(name="type")
	private String type;
	
	@Column(name="file_path")
	private String filePath;
	
	@Column(name="full_picture",columnDefinition="text")
	private String fullPicture;
	
	@Column(name="source",columnDefinition="text")
	private String source;
	
	@Column(name="video_id")
	private String videoId;
	
	@Column(name="photo_id")
	private String photoId;
	
	@Column(name="user_like")
	private boolean userLike;
	
	@Column(name="share_count")
	private int shareCount;
	
	@ManyToOne
	@JoinColumn(name = "idGroupFace")
	private GroupFace idGroupFace;
	
	@OneToMany(mappedBy = "id_post",fetch = FetchType.EAGER)
	private List<CommentFacebook> listComments;

	@OneToMany(mappedBy = "id_post",fetch = FetchType.EAGER)
	private List<Notification> listNotis;
	
	@OneToOne(fetch=FetchType.EAGER,mappedBy="postFace")
	private UploadedFileResponse uploadedFile;
	
	public PostFacebook(int id, String idPost, String idGroup, String message,
			String link, String name, String caption, String description,
			String picture, String createdDate, String updated_date,
			String from_client_id, int like_count, String type,
			GroupFace idGroupFace, List<CommentFacebook> listComments, String fullPicture, String source, String photoId, String videoId) {
		super();
		this.id = id;
		this.idPost = idPost;
		this.idGroup = idGroup;
		this.message = message;
		this.link = link;
		this.name = name;
		this.caption = caption;
		this.description = description;
		this.picture = picture;
		this.createdDate = createdDate;
		this.updated_date = updated_date;
		this.from_client_id = from_client_id;
		this.like_count = like_count;
		this.type = type;
		this.idGroupFace = idGroupFace;
		this.listComments = listComments;
		this.fullPicture = fullPicture;
		this.source = source;
		this.photoId = photoId;
		this.videoId = videoId;
	}

	public PostFacebook(int id, String idPost, String idGroup, String message,
			String link, String name, String caption, String description,
			String picture, String createdDate, String updated_date,
			String from_client_id, int like_count, String type,
			GroupFace idGroupFace, List<CommentFacebook> listComments,
			List<Notification> listNotis) {
		super();
		this.id = id;
		this.idPost = idPost;
		this.idGroup = idGroup;
		this.message = message;
		this.link = link;
		this.name = name;
		this.caption = caption;
		this.description = description;
		this.picture = picture;
		this.createdDate = createdDate;
		this.updated_date = updated_date;
		this.from_client_id = from_client_id;
		this.like_count = like_count;
		this.type = type;
		this.idGroupFace = idGroupFace;
		this.listComments = listComments;
		this.listNotis = listNotis;
	}
	
	
	public PostFacebook(int id, String idPost, String idGroup, String message,
			String link, String name, String caption, String description,
			String picture, String createdDate, String updated_date,
			String from_client_id, int like_count, String type,
			String filePath, GroupFace idGroupFace,
			List<CommentFacebook> listComments, List<Notification> listNotis) {
		super();
		this.id = id;
		this.idPost = idPost;
		this.idGroup = idGroup;
		this.message = message;
		this.link = link;
		this.name = name;
		this.caption = caption;
		this.description = description;
		this.picture = picture;
		this.createdDate = createdDate;
		this.updated_date = updated_date;
		this.from_client_id = from_client_id;
		this.like_count = like_count;
		this.type = type;
		this.filePath = filePath;
		this.idGroupFace = idGroupFace;
		this.listComments = listComments;
		this.listNotis = listNotis;
	}

	
	public boolean isUserLike() {
		return userLike;
	}

	public void setUserLike(boolean userLike) {
		this.userLike = userLike;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public String getFullPicture() {
		return fullPicture;
	}

	public void setFullPicture(String fullPicture) {
		this.fullPicture = fullPicture;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public UploadedFileResponse getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFileResponse uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<Notification> getListNotis() {
		return listNotis;
	}

	public void setListNotis(List<Notification> listNotis) {
		this.listNotis = listNotis;
	}

	public GroupFace getIdGroupFace() {
		return idGroupFace;
	}

	public void setIdGroupFace(GroupFace idGroupFace) {
		this.idGroupFace = idGroupFace;
	}

	public PostFacebook(int id, String idPost,String idGroup, String message, String link,
			String name, String caption, String description, String picture,
			String createdDate, String updated_date, String from_client_id,
				int like_count, String type) {
		super();
		this.id = id;
		this.idPost = idPost;
		this.idGroup=idGroup;
		this.message = message;
		this.link = link;
		this.name = name;
		this.caption = caption;
		this.description = description;
		this.picture = picture;
		this.createdDate = createdDate;
		this.updated_date = updated_date;
		this.from_client_id = from_client_id;
		this.like_count = like_count;
		this.type = type;
		
	}

	public PostFacebook(int id, String idPost,String idGroup, String message, String link,
			String name, String caption, String description, String picture,
			String createdDate, String updated_date, String from_client_id,
			int like_count, String type,
			List<CommentFacebook> listComments) {
		super();
		this.id = id;
		this.idPost = idPost;
		this.idGroup=idGroup;
		this.message = message;
		this.link = link;
		this.name = name;
		this.caption = caption;
		this.description = description;
		this.picture = picture;
		this.createdDate = createdDate;
		this.updated_date = updated_date;
		this.from_client_id = from_client_id;
		this.like_count = like_count;
		this.type = type;
		this.listComments = listComments;
	}

	
	public String getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(String idGroup) {
		this.idGroup = idGroup;
	}

	public List<CommentFacebook> getListComments() {
		return listComments;
	}

	public void setListComments(List<CommentFacebook> listComments) {
		this.listComments = listComments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}

	public String getFrom_client_id() {
		return from_client_id;
	}

	public void setFrom_client_id(String from_client_id) {
		this.from_client_id = from_client_id;
	}

	public int getLike_count() {
		return like_count;
	}



	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public PostFacebook() {
		// TODO Auto-generated constructor stub
	}
}
