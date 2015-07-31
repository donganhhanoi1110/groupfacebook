package org.springframework.social.model;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Entity class for fileupload table. This is a simple POJO class with annotations
 * to define mapping with DB table
 * 
 * @author Anh Minh Nguyen
 *
 */
@Entity
@Table(name = "fileupload", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id_file")})
public class UploadedFileResponse implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "id_file")
	private String idFile;
	
	@Column(name = "length")
	private int length;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "success")
	private boolean success;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "path")
	private String path;
	
	@OneToOne
	@JoinColumn(name="id", referencedColumnName="id")
	@JsonIgnore
	private PostFacebook postFace;
	
	@OneToOne
	@JoinColumn(name="id", referencedColumnName="id")
	@JsonIgnore
	private Schedule schedule;
	
	@OneToOne
	@JoinColumn(name="id", referencedColumnName="id")
	@JsonIgnore
	private BigGroupFace biggroup;
	
	
	
	public int getId() {
		return id;
	}
	public String getIdFile() {
		return idFile;
	}
	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigGroupFace getBiggroup() {
		return biggroup;
	}
	public void setBiggroup(BigGroupFace biggroup) {
		this.biggroup = biggroup;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public PostFacebook getPostFace() {
		return postFace;
	}
	public void setPostFace(PostFacebook postFace) {
		this.postFace = postFace;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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

	public UploadedFileResponse(int id, String idFile, int length, String name,
			String type, boolean success, String message, String path) {
		super();
		this.id = id;
		this.idFile = idFile;
		this.length = length;
		this.name = name;
		this.type = type;
		this.success = success;
		this.message = message;
		this.path = path;
	}
	public UploadedFileResponse(){
		
	}
	
}
