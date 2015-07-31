package org.springframework.social.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



/**
 * store Comment's information
 * @author Minh Nguyen
 *
 */
@Entity
@Table(name="comment", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id_comment")})
public class CommentFacebook implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="id_comment")
	private String id_comment;
	
	@Column(name="message")
	private String message;
	
	@Column(name="from_client_id")
	private String from_client_id;
	
	@Column(name="name_client")
	private String name_client;
	
	@Column(name="date_create")
	private String date_create;
	
	@Column(name="like_count")
	private int like_count;
	
	@Column(name="user_like")
	private boolean userLike;
	
	@ManyToOne
	@JoinColumn(name = "id_post")
	private PostFacebook id_post;
	
	public CommentFacebook(int id, String id_comment, String message,
			String from_client_id, int like_count,String name_client,String date_create) {
		super();
		this.id = id;
		this.id_comment = id_comment;
		this.message = message;
		this.from_client_id = from_client_id;
		this.like_count = like_count;
		this.name_client=name_client;
		this.date_create=date_create;
	}


	public CommentFacebook(int id, String id_comment, String message,
			String from_client_id, int like_count, PostFacebook id_post,String name_client,String date_create, boolean userLike) {
		super();
		this.id = id;
		this.id_comment = id_comment;
		this.message = message;
		this.from_client_id = from_client_id;
		this.like_count = like_count;
		this.id_post = id_post;
		this.name_client=name_client;
		this.date_create=date_create;
		this.userLike= userLike;
	}

	

	public boolean isUserLike() {
		return userLike;
	}


	public void setUserLike(boolean userLike) {
		this.userLike = userLike;
	}


	public String getDate_create() {
		return date_create;
	}


	public void setDate_create(String date_create) {
		this.date_create = date_create;
	}


	public String getName_client() {
		return name_client;
	}


	public void setName_client(String name_client) {
		this.name_client = name_client;
	}


	public PostFacebook getId_post() {
		return id_post;
	}


	public void setId_post(PostFacebook id_post) {
		this.id_post = id_post;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getId_comment() {
		return id_comment;
	}


	public void setId_comment(String id_comment) {
		this.id_comment = id_comment;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public CommentFacebook() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
