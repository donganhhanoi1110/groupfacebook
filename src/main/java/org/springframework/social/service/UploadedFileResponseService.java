package org.springframework.social.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.social.dao.UploadedFileResponseDAO;
import org.springframework.social.model.*;

/**
 * UploadedFileResponse Service class call method from DAO for controller to do get Data or business logic
 * 
 * @author Anh Minh Nguyen
 *
 */
@Service("uploadedFileResponseService")
public class UploadedFileResponseService {
	
	UploadedFileResponseDAO uploadedFileResponseDAO;
	
	
	
	public UploadedFileResponseDAO getUploadedFileResponseDAO() {
		return uploadedFileResponseDAO;
	}


	@Autowired
	public void setUploadedFileResponseDAO(UploadedFileResponseDAO UploadedFileResponseDAO) {
		this.uploadedFileResponseDAO = UploadedFileResponseDAO;
	}



	public UploadedFileResponse getUploadedFileResponse(int id) {
		return this.getUploadedFileResponseDAO().getUploadedFileResponse(id);
	}
	
	public List<UploadedFileResponse> getAllUploadedFileResponse() {
		return this.getUploadedFileResponseDAO().getAllUploadedFileResponse();
	}
	public boolean createUploadedFileResponse(UploadedFileResponse UploadedFileResponse) {
		return this.getUploadedFileResponseDAO().createUploadedFileResponse(UploadedFileResponse);
	}
	
	 public boolean updateUploadedFileResponse(UploadedFileResponse UploadedFileResponse) {
	    	return this.getUploadedFileResponseDAO().updateUploadedFileResponse(UploadedFileResponse);
	    }
	 
	    /**
	     * Delete UploadedFileResponse by their Id.
	     *
	     * @param UploadedFileResponseId the UploadedFileResponse Id.
	     */
	    public boolean deleteUploadedFileResponseById(int UploadedFileResponseId) {
	       return this.getUploadedFileResponseDAO().deleteUploadedFileResponseById(UploadedFileResponseId);
	    }
	 
	    /**
	     * Delete UploadedFileResponse entity.
	     *
	     * @param UploadedFileResponse the object to be deleted.
	     */
	    public boolean deleteUploadedFileResponse(UploadedFileResponse UploadedFileResponse) {
	    	return this.getUploadedFileResponseDAO().deleteUploadedFileResponse(UploadedFileResponse);
	    }

	    /**
	     * check idUploadedFileResponse already exist ??? 
	     * @param idUploadedFileResponse
	     * @return
	     */
		public boolean isExistIdUploadedFileResponse(String name) {
			return this.uploadedFileResponseDAO.isExistIdUploadedFileResponse(name);
		}
		
		/**
		 * 
		 * get UploadedFileResponse by name and type
		 * @param String name
		 * @param String type
		 * @return
		 */
		public UploadedFileResponse getFileByNameAndType(String name, String type) {
			return this.uploadedFileResponseDAO.getFileByNameAndType(name, type);
		}
		/**
		 * 
		 * get UploadedFileResponse by fileId
		 * @param String type
		 * @return
		 */
		public UploadedFileResponse getFileByFileId(String idFile) {
			return this.uploadedFileResponseDAO.getFileByFileId( idFile);
		}
}
