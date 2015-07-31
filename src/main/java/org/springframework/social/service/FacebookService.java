package org.springframework.social.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.repository.query.Parameter;
import org.springframework.stereotype.Service;
import org.springframework.social.dao.PostFacebookDAO;
import org.springframework.social.facebook.api.Comment;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.Group;
import org.springframework.social.facebook.api.GroupMembership;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.model.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Facebook Service class call method from Facebook API for controller to do get
 * Data.
 * 
 * @author Anh Minh Nguyen
 *
 */
@Service("facebookService")
@Scope(value = "request",  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FacebookService {

	private Facebook facebook;

	SystemService systemService = new SystemService();

	@Autowired
	PostFacebookService postFacebookService;
	@Autowired
	CommentFacebookService commentFacebookService;

	@Inject
	public FacebookService(Facebook facebook) {
		this.facebook = facebook;
	}

	public Facebook getFacebook() {
		return facebook;
	}

	public void setFacebook(Facebook facebook) {
		this.facebook = facebook;
	}

	public List<Reference> getFacebookFriends() {
		// Get Friend List
		return getFacebook().friendOperations().getFriends();
	}

	// Function of User Profile
	public FacebookProfile getUserProfile() {
		
		// Get User profile when logged in
		if (!facebook.isAuthorized()) {
			return null;
		}else
		return facebook.userOperations().getUserProfile();

		// Function of Groups
	}
	
	public FacebookProfile getUserProfileWithClientId(String clientId) {
		// Get User profile when logged in
		return facebook.userOperations().getUserProfile(clientId);

		// Function of Groups
	}
	

	public List<GroupMembership> getFacebookGroups() {
		// Get Groups of user
		return facebook.groupOperations().getMemberships();
	}

	public List<Group> searchGroups(String searchString) {
		// Search Group
		return facebook.groupOperations().search(searchString);
	}

	public boolean postToWallGroups(MyGroup postItem) {
		boolean success = false;
		
		try {
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.set("link", postItem.getGroupLink());
			map.set("name", postItem.getGroupName());
			map.set("caption", postItem.getGroupCaption());
			map.set("description", postItem.getGroupDescription());
			map.set("message", postItem.getGroupMessage());
			map.set("picture", postItem.getGroupImage()); // the image on the
															// left
			for (String groupItemId : postItem.getListGroup()) {

				// Post to Wall Groups Immediately
				facebook.post(groupItemId, "feed", map);

				Post post = (facebook
						.feedOperations().getFeed(groupItemId, 0, 1)).get(0);
				
//				if (post.getMessage().equalsIgnoreCase(
//						postItem.getGroupMessage()) ) {
//					List<CommentFacebook> listComments=new ArrayList<CommentFacebook>(); 
//					for(Comment com: post.getComments())
//					{
//						CommentFacebook comF=new CommentFacebook(0, com.getId(), com.getMessage(), com.getFrom().getId(), com.getLikesCount(), null);
//						commentFacebookService.createCommentFacebook(comF);
//						listComments.add(comF);
//					}
//					
//					PostFacebook postFacebook = new PostFacebook(0, post.getId(),groupItemId, post.getMessage(), post.getLink(), post.getName(), post.getCaption(), 
//							post.getDescription(), post.getPicture(), systemService.convertDateToString(post.getCreatedTime()), systemService.convertDateToString(post.getUpdatedTime()), 
//							post.getFrom().getId(), post.getLikeCount(), post.getType().getDeclaringClass().getName(), listComments);
//					
//					
//					try {
//						postFacebookService.createPostFacebook(postFacebook);
//					} catch (Exception e) {
//						e.printStackTrace();
//						success = false;
//					}
//				}
				PostFacebook postFacebook = new PostFacebook(0, post.getId(),groupItemId, post.getMessage(), post.getLink(), post.getName(), post.getCaption(), 
						post.getDescription(), post.getPicture(), systemService.convertDateToString(post.getCreatedTime()), systemService.convertDateToString(post.getUpdatedTime()), 
						post.getFrom().getId(), post.getLikeCount(), post.getType().getDeclaringClass().getName());
				postFacebookService.createPostFacebook(postFacebook);
			}
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}
	
	public List<Post> getListPostOfGroupId( String groupId)
	{	
		List<Post> listPost=new ArrayList<Post>();
		try{
		
			listPost=facebook.feedOperations().getFeed(groupId);
			
			//Get List Post from db and check if not exist in db and save to DB
			List<PostFacebook> listPostFBdb=postFacebookService.getAllPostFacebook();
			for(Post post:listPost)
			{
				if(!postFacebookService.checkPostFacebookExistInDB(post.getId(),post.getFrom().getId()))
				{
					PostFacebook postFB=new PostFacebook(0, post.getId(),post.getTo().iterator().next().getId(), post.getMessage(), post.getLink(), post.getName(), post.getCaption(), 
							post.getDescription(), post.getPicture(), systemService.convertDateToString(post.getCreatedTime()), systemService.convertDateToString(post.getUpdatedTime()), 
							post.getFrom().getId(), post.getLikeCount(), post.getType().getDeclaringClass().getName());
					postFacebookService.createPostFacebook(postFB);
				}
			}
			

		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return listPost;
		
	}
	
}
