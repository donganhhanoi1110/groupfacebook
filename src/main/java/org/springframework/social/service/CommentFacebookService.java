package org.springframework.social.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.social.dao.CommentFacebookDAO;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Group;
import org.springframework.social.facebook.api.GroupMembership;
import org.springframework.social.model.*;

/**
 * CommentFacebook Service class call method from DAO for controller to do get Data or business logic
 * 
 * @author Anh Minh Nguyen
 *
 */
@Service("CommentFacebookService")
public class CommentFacebookService {
	
	CommentFacebookDAO CommentFacebookDAO;
	
	public CommentFacebookDAO getCommentFacebookDAO() {
		return CommentFacebookDAO;
	}


	@Autowired
	public void setCommentFacebookDAO(CommentFacebookDAO CommentFacebookDAO) {
		this.CommentFacebookDAO = CommentFacebookDAO;
	}



	public CommentFacebook getCommentFacebook(int id) {
		return this.getCommentFacebookDAO().getCommentFacebook(id);
	}
	
	public List<CommentFacebook> getAllCommentFacebook() {
		return this.getCommentFacebookDAO().getAllCommentFacebook();
	}
	public CommentFacebook getCommentFacebookbyCommentFacebookName(String CommentFacebookname) {
		return this.getCommentFacebookDAO().getCommentFacebookbyCommentFacebookName(CommentFacebookname);
	}
	public boolean createCommentFacebook(CommentFacebook CommentFacebook) {
		return this.getCommentFacebookDAO().createCommentFacebook(CommentFacebook);
	}
	
	 public boolean updateCommentFacebook(CommentFacebook CommentFacebook) {
	    	return this.getCommentFacebookDAO().updateCommentFacebook(CommentFacebook);
	    }
	 
	    /**
	     * Delete CommentFacebook by their Id.
	     *
	     * @param CommentFacebookId the CommentFacebook Id.
	     */
	    public boolean deleteCommentFacebookById(int CommentFacebookId) {
	       return this.getCommentFacebookDAO().deleteCommentFacebookById(CommentFacebookId);
	    }
	 
	    /**
	     * Delete CommentFacebook entity.
	     *
	     * @param CommentFacebook the object to be deleted.
	     */
	    public boolean deleteCommentFacebook(CommentFacebook CommentFacebook) {
	    	return this.getCommentFacebookDAO().deleteCommentFacebook(CommentFacebook);
	    }
	    
	    public boolean isExistIdComment(String id_comment)
		{
	    	return this.CommentFacebookDAO.isExistIdComment(id_comment);
		}
	    
	    public boolean updateLikeCountOfCommentFacebook(CommentFacebook commentFacebook) {
	    	return this.CommentFacebookDAO.updateLikeCountOfCommentFacebook(commentFacebook);
	    }
	    public CommentFacebook getCommentFacebookbyIdComment(String id_comment) {
	    	return this.CommentFacebookDAO.getCommentFacebookbyIdComment(id_comment);
	    }
	    
}
