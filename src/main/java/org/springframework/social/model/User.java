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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entity class for user table. This is a simple POJO class with annotations
 * to define mapping with DB table
 * 
 * @author Anh Minh Nguyen
 *
 */
@Entity
@Table(name = "user", uniqueConstraints = {
		@UniqueConstraint(columnNames = "user_name"), @UniqueConstraint(columnNames = "user_id")})
public class User implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	//User Facebook Id
	@Column(name = "user_id", length=50)
	private String userId;
	
	@Column(name = "user_name",length=50)
	private String userName;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "password")
	private String password;

	@Column(name = "access_token",columnDefinition="text")
	private String accessToken;
	
	@Column(name = "user_facebook_fullname")
	private String userFacebookFullName;
	
	@OneToMany(mappedBy = "userId",fetch = FetchType.EAGER)
	private List<Notification> listNotis;
	
	
	public String getUserFacebookFullName() {
		return userFacebookFullName;
	}

	public void setUserFacebookFullName(String userFacebookFullName) {
		this.userFacebookFullName = userFacebookFullName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Notification> getListNotis() {
		return listNotis;
	}

	public void setListNotis(List<Notification> listNotis) {
		this.listNotis = listNotis;
	}

	public User(int id, String userId, String userName, String password,
			String accessToken, List<Notification> listNotis) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.accessToken = accessToken;
		this.listNotis = listNotis;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public User(int id, String userId, String userName, String password,
			String accessToken, String userFullName) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.accessToken = accessToken;
		this.userFacebookFullName = userFullName;
	}

	public User(){
		
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", userName="
				+ userName + ", password=" + password + ", accessToken="
				+ accessToken + "]";
	}
	
	
}
