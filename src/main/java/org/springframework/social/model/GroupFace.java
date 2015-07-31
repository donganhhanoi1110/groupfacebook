package org.springframework.social.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * store group's information
 * @author Administrator
 *
 */
@Entity
@Table(name="groupFace")
public class GroupFace implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="version")
	private int version;
	
	@Column(name="nameGroupFace")
	private String nameGroupFace;
	
	@Column(name="idGroupFace")
	private String idGroupFace;
	
	@Column(name="administrator")
	private boolean administrator;
	
	@Column(name="bookmark_order")
	private int bookmarkOrder;
	
	@Column(name="id_owner")
	private String idOwner;
	
	@Column(name="clientId")
	private String clientId;
	
	@Column(name="description",columnDefinition= "text")
	private String description;
	
	@Column(name="privacy")
	private String privacy;
	
	@Column(name="icon")
	private String icon;
	
	@Column(name="updated_time")
	private String updatedTime;
	
	@Column(name="email")
	private String email;
	
	@Column(name="venue")
	private String venue;
	
	@Transient
	private boolean edited;
	
	@ManyToMany( mappedBy = "listGroupFaces",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<BigGroupFace> listBigGroups;
	
	@ManyToMany( mappedBy = "listGroupFaces",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Schedule> listSchedule;
	
	@OneToMany(mappedBy = "idGroupFace",fetch = FetchType.EAGER)
	@JsonIgnore
	private List<PostFacebook> listPosts;

	@OneToMany(mappedBy = "groupFace",fetch = FetchType.EAGER)
	@JsonIgnore
	private List<MemberGroup> listMemberGroups;
	
	public GroupFace(int id, String idGroupFace, String nameGroupFace,
			String clientId, List<BigGroupFace> listBigGroups,
			List<PostFacebook> listPosts) {
		super();
		this.id = id;
		this.idGroupFace = idGroupFace;
		this.nameGroupFace = nameGroupFace;
		this.clientId = clientId;
		this.listBigGroups = listBigGroups;
		this.listPosts = listPosts;
	}


	public boolean isEdited() {
		return edited;
	}


	public void setEdited(boolean edited) {
		this.edited = edited;
	}


	public List<MemberGroup> getListMemberGroups() {
		return listMemberGroups;
	}

	public void setListMemberGroups(List<MemberGroup> listMemberGroups) {
		this.listMemberGroups = listMemberGroups;
	}

	public List<PostFacebook> getListPosts() {
		return listPosts;
	}

	public void setListPosts(List<PostFacebook> listPosts) {
		this.listPosts = listPosts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getIdGroupFace() {
		return idGroupFace;
	}

	public void setIdGroupFace(String idGroupFace) {
		this.idGroupFace = idGroupFace;
	}

	public String getNameGroupFace() {
		return nameGroupFace;
	}

	public void setNameGroupFace(String nameGroupFace) {
		this.nameGroupFace = nameGroupFace;
	}

	public List<BigGroupFace> getListBigGroups() {
		return listBigGroups;
	}

	public void setListBigGroups(List<BigGroupFace> listBigGroups) {
		this.listBigGroups = listBigGroups;
	}

	
	public List<Schedule> getListSchedule() {
		return listSchedule;
	}

	public void setListSchedule(List<Schedule> listSchedule) {
		this.listSchedule = listSchedule;
	}

	

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public int getBookmarkOrder() {
		return bookmarkOrder;
	}

	public void setBookmarkOrder(int bookmarkOrder) {
		this.bookmarkOrder = bookmarkOrder;
	}

	public String getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(String idOwner) {
		this.idOwner = idOwner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	
	public GroupFace(int id, int version, String nameGroupFace,
			String idGroupFace, boolean administrator, int bookmarkOrder,
			String idOwner, String clientId, String description,
			String privacy, String icon, String updatedTime, String email,
			String venue) {
		super();
		this.id = id;
		this.version = version;
		this.nameGroupFace = nameGroupFace;
		this.idGroupFace = idGroupFace;
		this.administrator = administrator;
		this.bookmarkOrder = bookmarkOrder;
		this.idOwner = idOwner;
		this.clientId = clientId;
		this.description = description;
		this.privacy = privacy;
		this.icon = icon;
		this.updatedTime = updatedTime;
		this.email = email;
		this.venue = venue;
	}

	public GroupFace() {
		// TODO Auto-generated constructor stub
	}
}
