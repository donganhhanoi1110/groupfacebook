package org.springframework.social.json;

import java.io.File;
import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
/**
 * Entity class for fileupload table. This is a simple POJO class with annotations
 * to define mapping with DB table
 * 
 * @author Anh Minh Nguyen
 *
 */
public class UploadedFile implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int length;
	
	private byte[] bytes;
	
	private String name;
	
	private String type;
	
	private boolean success;
	
	private String message;
	
	private String path;
	
	//For upload on facebook, dont need to create new file again when post to Group
	private File file;
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
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
	
	
	public UploadedFile(int length, byte[] bytes, String name, String type,
			boolean success, String message, String path, File file) {
		super();
		this.length = length;
		this.bytes = bytes;
		this.name = name;
		this.type = type;
		this.success = success;
		this.message = message;
		this.path = path;
		this.file = file;
	}
	public UploadedFile(){
		
	}
	
	  @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof UploadedFile)) return false;

	        UploadedFile that = (UploadedFile) o;
	        if (length != 0 ? !(length==that.length) : that.length != 0) return false;
	        if (bytes != null ? !bytes.equals(that.bytes) : that.bytes != null) return false;
	        if (name != null ? !name.equals(that.name) : that.name != null) return false;
	        if (type != null ? !type.equals(that.type) : that.type != null) return false;
	        if (success == true ? !success==that.success : that.success == true) return false;
	        if (path != null ? !path.equals(that.path) : that.path != null) return false;
	        if (file != null ? !file.equals(that.file) : that.file != null) return false;
	        if (message != null ? !message.equals(that.message) : that.message != null) return false;

	        return true;
	    }
	  
	  public boolean checkElement(){
		  boolean check = true;
		  if(this.length <= 0 
				  || this.bytes.length <= 0 
				  || StringUtils.isBlank(this.name)
				  || StringUtils.isBlank(this.type)
				  || StringUtils.isBlank(this.path)
				  || this.file == null
				  || StringUtils.isBlank(this.message)
				  || success == false) check = false;
		  return check;
	  }
}
