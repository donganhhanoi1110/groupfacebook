package org.springframework.social.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.social.dao.PostFacebookDAO;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Group;
import org.springframework.social.facebook.api.GroupMembership;
import org.springframework.social.model.*;

/**
 * PostFacebook Service class call method from DAO for controller to do get Data or business logic
 * 
 * @author Anh Minh Nguyen
 *
 */
@Service("PostFacebookService")
public class PostFacebookService {
	
	PostFacebookDAO PostFacebookDAO;
	
	public PostFacebookDAO getPostFacebookDAO() {
		return PostFacebookDAO;
	}


	@Autowired
	public void setPostFacebookDAO(PostFacebookDAO PostFacebookDAO) {
		this.PostFacebookDAO = PostFacebookDAO;
	}



	public PostFacebook getPostFacebook(int id) {
		return this.getPostFacebookDAO().getPostFacebook(id);
	}
	
	public List<PostFacebook> getAllPostFacebook() {
		return this.getPostFacebookDAO().getAllPostFacebook();
	}
	public PostFacebook getPostFacebookbyPostFacebookName(String PostFacebookname) {
		return this.getPostFacebookDAO().getPostFacebookbyPostFacebookName(PostFacebookname);
	}
	public boolean createPostFacebook(PostFacebook PostFacebook) {
		return this.getPostFacebookDAO().createPostFacebook(PostFacebook);
	}
	
	 public boolean updatePostFacebook(PostFacebook PostFacebook) {
	    	return this.getPostFacebookDAO().updatePostFacebook(PostFacebook);
	    }
	 
	    /**
	     * Delete PostFacebook by their Id.
	     *
	     * @param PostFacebookId the PostFacebook Id.
	     */
	    public boolean deletePostFacebookById(int PostFacebookId) {
	       return this.getPostFacebookDAO().deletePostFacebookById(PostFacebookId);
	    }
	 
	    /**
	     * Delete PostFacebook entity.
	     *
	     * @param PostFacebook the object to be deleted.
	     */
	    public boolean deletePostFacebook(PostFacebook PostFacebook) {
	    	return this.getPostFacebookDAO().deletePostFacebook(PostFacebook);
	    }
	    
	    public List<PostFacebook> getPostFacebookbyIdGroup(String idGroup,String from_client_id) {
	    	return this.PostFacebookDAO.getPostFacebookbyIdGroup(idGroup,from_client_id);
	    }
	    
	    public boolean updateLikeCountAndMessageOfPostFacebook(PostFacebook postFacebook) {
	    	return this.PostFacebookDAO.updateLikeCountAndMessageOfPostFacebook(postFacebook);
	    }
	    public boolean checkPostFacebookExistInDB(String idPost,String cusID) {
	    	return this.PostFacebookDAO.checkPostFacebookExistInDB(idPost,cusID);
	    }
	    public PostFacebook getPostFacebookbyPostId(String idPost){
	    	return this.PostFacebookDAO.getPostFacebookbyPostId(idPost);
	    }
	    
	    public PostFacebook getPostFacebookWithIdPostAndClientId(int id, String customerId)
		{	
	    	return this.PostFacebookDAO.getPostFacebookWithIdPostAndClientId(id, customerId);
		}
	    public List<PostFacebook> getPostFacebookbyIdClient(String from_client_id) {
	    	return this.PostFacebookDAO.getPostFacebookbyIdClient(from_client_id);
	    }
}
