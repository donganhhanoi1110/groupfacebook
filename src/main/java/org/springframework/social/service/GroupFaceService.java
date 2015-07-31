package org.springframework.social.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.social.dao.GroupFaceDAO;
import org.springframework.social.model.*;

/**
 * GroupFace Service class call method from DAO for controller to do get Data or business logic
 * 
 * @author Anh Minh Nguyen
 *
 */
@Service("groupFaceService")
public class GroupFaceService {
	
	GroupFaceDAO GroupFaceDAO;
	
	
	
	public GroupFaceDAO getGroupFaceDAO() {
		return GroupFaceDAO;
	}


	@Autowired
	public void setGroupFaceDAO(GroupFaceDAO GroupFaceDAO) {
		this.GroupFaceDAO = GroupFaceDAO;
	}



	public GroupFace getGroupFace(int id) {
		return this.getGroupFaceDAO().getGroupFace(id);
	}
	
	public List<GroupFace> getAllGroupFace() {
		return this.getGroupFaceDAO().getAllGroupFace();
	}
	public GroupFace getGroupFacebyGroupFaceName(String GroupFacename) {
		return this.getGroupFaceDAO().getGroupFacebyGroupFaceName(GroupFacename);
	}
	public boolean createGroupFace(GroupFace GroupFace) {
		return this.getGroupFaceDAO().createGroupFace(GroupFace);
	}
	
	 public boolean updateGroupFace(GroupFace GroupFace) {
	    	return this.getGroupFaceDAO().updateGroupFace(GroupFace);
	    }
	 
	    /**
	     * Delete GroupFace by their Id.
	     *
	     * @param GroupFaceId the GroupFace Id.
	     */
	    public boolean deleteGroupFaceById(int GroupFaceId) {
	       return this.getGroupFaceDAO().deleteGroupFaceById(GroupFaceId);
	    }
	 
	    /**
	     * Delete GroupFace entity.
	     *
	     * @param GroupFace the object to be deleted.
	     */
	    public boolean deleteGroupFace(GroupFace GroupFace) {
	    	return this.getGroupFaceDAO().deleteGroupFace(GroupFace);
	    }

	    // Hiep create 9:04 pm date 20/04/2015
	    /**
	     * check idGroupFace already exist ??? 
	     * @param idGroupFace
	     * @return
	     */
		public boolean isExistIdGroupFace(String idGroupFace,String clientId) {
			return this.GroupFaceDAO.isExistIdGroupFace(idGroupFace,clientId);
		}

		// Hiep create 9:04 pm date 20/04/2015
		/**
		 * get a GroupFace with groupId vs clientId
		 * @param groupId
		 * @param clientId
		 * @return
		 */
		public GroupFace getGroupFaceWithidGroupFaceAndClientId(String groupId,String clientId) {
			return this.GroupFaceDAO.getGroupFaceWithidGroupFaceAndClientId(groupId,clientId);
		}


		public List<GroupFace> getAllGroupFaceOfClient(String clientId) {
			
			return this.GroupFaceDAO.getAllGroupFaceOfClient(clientId);
		}
}
