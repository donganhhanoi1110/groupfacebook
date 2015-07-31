package org.springframework.social.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.social.dao.MemberGroupDAO;
import org.springframework.social.model.*;

/**
 * MemberGroup Service class call method from DAO for controller to do get Data or business logic
 * 
 * @author Anh Minh Nguyen
 *
 */
@Service("memberGroupService")
public class MemberGroupService {
	
	MemberGroupDAO MemberGroupDAO;
	
	
	
	public MemberGroupDAO getMemberGroupDAO() {
		return MemberGroupDAO;
	}


	@Autowired
	public void setMemberGroupDAO(MemberGroupDAO MemberGroupDAO) {
		this.MemberGroupDAO = MemberGroupDAO;
	}



	public MemberGroup getMemberGroup(int id) {
		return this.getMemberGroupDAO().getMemberGroup(id);
	}
	
	public List<MemberGroup> getAllMemberGroup() {
		return this.getMemberGroupDAO().getAllMemberGroup();
	}
	public boolean createMemberGroup(MemberGroup MemberGroup) {
		return this.getMemberGroupDAO().createMemberGroup(MemberGroup);
	}
	
	 public boolean updateMemberGroup(MemberGroup MemberGroup) {
	    	return this.getMemberGroupDAO().updateMemberGroup(MemberGroup);
	    }
	 
	    /**
	     * Delete MemberGroup by their Id.
	     *
	     * @param MemberGroupId the MemberGroup Id.
	     */
	    public boolean deleteMemberGroupById(int MemberGroupId) {
	       return this.getMemberGroupDAO().deleteMemberGroupById(MemberGroupId);
	    }
	 
	    /**
	     * Delete MemberGroup entity.
	     *
	     * @param MemberGroup the object to be deleted.
	     */
	    public boolean deleteMemberGroup(MemberGroup MemberGroup) {
	    	return this.getMemberGroupDAO().deleteMemberGroup(MemberGroup);
	    }

	    /**
	     * check idMemberGroup already exist ??? 
	     * @param idMemberGroup
	     * @return
	     */
		public boolean isExistIdMemberGroup(String idMemberGroup) {
			return this.MemberGroupDAO.isExistIdMemberGroup(idMemberGroup);
		}

		/**
		 * 
		 *get MemberFroup
		 * @param idMemberGroup
		 * @return memberGroup
		 */
		public MemberGroup getMemberFroupByMemberId(String idMemberGroup) {
			return this.MemberGroupDAO.getMemberFroupByMemberId(idMemberGroup);
		}
		
		/**
		 * 
		 *get list MemberFroup
		 * @param idMemberGroup
		 * @return memberGroups
		 */
		public List<MemberGroup> getListMemberFroupByIdGroup(String idGroup) {
			return this.MemberGroupDAO.getListMemberFroupByIdGroup( idGroup);
		}
}
