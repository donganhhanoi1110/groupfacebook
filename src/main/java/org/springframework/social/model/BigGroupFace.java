package org.springframework.social.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * BigGroup will container a list group that user select to management groups
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "bigGroupFace", uniqueConstraints = {
		@UniqueConstraint(columnNames = "nameBigGroupFace")})
public class BigGroupFace implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "idBigGroupFace")
	private int idBigGroupFace;

	@Column(name = "nameBigGroupFace")
	private String nameBigGroupFace;
	
	@Column(name="image")
	private String image;
	
	@Column(name="clientId")
	private String clientId;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "biggroup_group", joinColumns = { @JoinColumn(name = "idBigGroupFace") }, inverseJoinColumns = { @JoinColumn(name = "idGroupFace") })
	private List<GroupFace> listGroupFaces = new ArrayList<GroupFace>();
	
	@OneToOne(fetch=FetchType.EAGER,mappedBy="biggroup")
	private UploadedFileResponse uploadedFile;
	
	public UploadedFileResponse getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFileResponse uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public int getIdBigGroupFace() {
		return idBigGroupFace;
	}

	public void setIdBigGroupFace(int idBigGroupFace) {
		this.idBigGroupFace = idBigGroupFace;
	}

	public String getNameBigGroupFace() {
		return nameBigGroupFace;
	}

	public void setNameBigGroupFace(String nameBigGroupFace) {
		this.nameBigGroupFace = nameBigGroupFace;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<GroupFace> getListGroupFaces() {
		return listGroupFaces;
	}

	public void setListGroupFaces(List<GroupFace> listGroupFaces) {
		this.listGroupFaces = listGroupFaces;
	}

	public BigGroupFace(int idBigGroupFace, String nameBigGroupFace,
			String image, String clientId, List<GroupFace> listGroupFaces) {
		super();
		this.idBigGroupFace = idBigGroupFace;
		this.nameBigGroupFace = nameBigGroupFace;
		this.image = image;
		this.clientId = clientId;
		this.listGroupFaces = listGroupFaces;
	}

	public BigGroupFace() {
		// TODO Auto-generated constructor stub
	}


}
