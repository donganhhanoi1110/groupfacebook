package org.springframework.social.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.social.model.*;

/**
 * DAO class for Account entity. This class contains all methods that
 * inserts/updates/deletes PostFacebook info
 * 
 * @author Anh Minh Nguyen
 *
 */
@Repository("PostFacebookDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class PostFacebookDAO {
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional

	public PostFacebook getPostFacebook(int id) {
		PostFacebook PostFacebook = null;
		try {
			PostFacebook = entityManager.find(PostFacebook.class, id);
			if (PostFacebook == null) {
				throw new EntityNotFoundException("Can't find PostFacebook for ID "
						+ id);
			}
			System.out.println(PostFacebook.toString() + "getPostFacebook-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting PostFacebook had Errors" + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return PostFacebook;

	}


	public PostFacebook getPostFacebookbyPostFacebookName(String PostFacebookname) {
		PostFacebook PostFacebook = null;
		try {
			if(StringUtils.isBlank(PostFacebookname)) return null;
			TypedQuery<PostFacebook> query = entityManager.createQuery("SELECT c FROM "
					+ PostFacebook.class.getName() + " c where c.PostFacebookName=:PostFacebookname",
					PostFacebook.class);
			query.setParameter("PostFacebookname", PostFacebookname);
			PostFacebook = query.getSingleResult();
			if (PostFacebook == null) {
				throw new EntityNotFoundException(
						"Can't find PostFacebook for PostFacebookName " + PostFacebookname);
			}
			System.out.println(PostFacebook.toString() + "getPostFacebook-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting PostFacebook had Errors" + "*_"
					+ e.getMessage() + "*_");
		}
		return PostFacebook;

	}

	public List<PostFacebook> getAllPostFacebook() {
		List<PostFacebook> list = new ArrayList<PostFacebook>();
		try {
			TypedQuery<PostFacebook> query = entityManager.createQuery("SELECT c FROM "
					+ PostFacebook.class.getName() + " c", PostFacebook.class);

			list = query.getResultList();
			System.out.println("Get All PostFacebooks");
		} catch (Exception e) {
			System.out.println("\nGet Error " + "*_" + e.getMessage() + "*_");
			e.printStackTrace();
		}
		return list;

	}

	@Transactional

	public boolean createPostFacebook(PostFacebook PostFacebook) {
		boolean check = false;

		try {
			if (PostFacebook != null) {
			// Insert a row to PostFacebook table
			entityManager.persist(PostFacebook);
			check = true;
			}
		} catch (Exception e) {
			System.out.println("\nGet Error with Create PostFacebook " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Update PostFacebook information.
	 *
	 * @param PostFacebook
	 *            an PostFacebook to be updated.
	 */
	@Transactional

	public boolean updatePostFacebook(PostFacebook PostFacebook) {

		boolean check = false;
		try {
			if (PostFacebook != null) {
			entityManager.merge(PostFacebook);
			check = true;
			System.out.println("PostFacebook " + PostFacebook.getName()+ "updated");
			}
		} catch (Exception e) {
			System.out.println("\nUpdate PostFacebook get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Delete PostFacebook by their Id.
	 *
	 * @param PostFacebookId
	 *            the PostFacebook Id.
	 */
	@Transactional

	public boolean deletePostFacebookById(int PostFacebookId) {
		boolean check = false;
		try {
			PostFacebook PostFacebook = entityManager.find(PostFacebook.class, PostFacebookId);
			if (PostFacebook == null) {
				check = false;
				throw new EntityNotFoundException("Can't find PostFacebook for ID "
						+ PostFacebookId);
			}
			System.out.println(PostFacebook.toString());
			if (PostFacebook != null) {

				entityManager.remove(PostFacebook);
				check = true;
				System.out.println("delete PostFacebook by ID");
			}
		} catch (Exception e) {

			System.out.println("\nDelete PostFacebook by ID get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Delete PostFacebook entity.
	 *
	 * @param PostFacebook
	 *            the object to be deleted.
	 */
	public boolean deletePostFacebook(PostFacebook PostFacebook) {
		boolean check = false;
		try {
			if (PostFacebook != null) {
			entityManager.remove(PostFacebook);
			check = true;
			System.out.println("delete PostFacebook by PostFacebook");
			}
		} catch (Exception e) {
			System.out.println("\nDelete PostFacebook get Error" + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}
	
	public List<PostFacebook> getPostFacebookbyIdGroup(String idGroup,String from_client_id) {
		List<PostFacebook> postFacebooks = null;
		try {
			if(StringUtils.isBlank(idGroup) || StringUtils.isBlank(from_client_id)) return null;
			TypedQuery<PostFacebook> query = entityManager.createQuery("SELECT c FROM "
					+ PostFacebook.class.getName() + " c where c.idGroup=:idGroup and c.from_client_id=:from_client_id",
					PostFacebook.class);
			query.setParameter("idGroup", idGroup);
			query.setParameter("from_client_id", from_client_id);
			postFacebooks = query.getResultList();
			if (postFacebooks  == null) {
				throw new EntityNotFoundException(
						"Can't find PostFacebook for PostFacebookName " + idGroup);
			} else {
			for( PostFacebook post : postFacebooks ) {
				LinkedHashSet<CommentFacebook> comments = new LinkedHashSet<CommentFacebook>();
				for(CommentFacebook com : post.getListComments()) {
					comments.add(com);
				}
				post.setListComments(new ArrayList<CommentFacebook>(comments));
			}
		}
		} catch (Exception e) {
			System.out.println("\nGetting PostFacebook had Errors" + "*_"
					+ e.getMessage() + "*_");
		}
		return postFacebooks;

	}
	
	public List<PostFacebook> getPostFacebookbyIdClient(String from_client_id) {
		List<PostFacebook> postFacebooks = null;
		try {
			if(StringUtils.isBlank(from_client_id)) return null;
			TypedQuery<PostFacebook> query = entityManager.createQuery("SELECT c FROM "
					+ PostFacebook.class.getName() + " c where c.from_client_id=:from_client_id",
					PostFacebook.class);
			query.setParameter("from_client_id", from_client_id);
			postFacebooks = query.getResultList();
			if (postFacebooks  == null) {
				throw new EntityNotFoundException(
						"Can't find PostFacebook for from_client_id " + from_client_id);
			} else {
			for (PostFacebook postFacebook : postFacebooks) {
				LinkedHashSet<CommentFacebook> comments = new LinkedHashSet<CommentFacebook>();
				for(CommentFacebook com : postFacebook.getListComments()) {
					comments.add(com);
				}
				postFacebook.setListComments(new ArrayList<CommentFacebook>(comments));
			}
		}
		} catch (Exception e) {
			System.out.println("\nGetting PostFacebook had Errors" + "*_"
					+ e.getMessage() + "*_");
		}
		return postFacebooks;

	}
	public PostFacebook getPostFacebookbyPostId(String idPost) {
		PostFacebook postFacebooks = null;
		try {
			if(StringUtils.isBlank(idPost)) return null;
			TypedQuery<PostFacebook> query = entityManager.createQuery("SELECT c FROM "
					+ PostFacebook.class.getName() + " c where c.idPost=:idPost",
					PostFacebook.class);
			query.setParameter("idPost", idPost);
			postFacebooks = query.getSingleResult();
			if (postFacebooks == null) {
				throw new EntityNotFoundException(
						"Can't find PostFacebook for PostFacebookName " + idPost);
			} else {
				LinkedHashSet<Notification> linkedNoti = new LinkedHashSet<Notification>();
				for (Notification com: postFacebooks.getListNotis()) {
					linkedNoti.add(com);
				}
				LinkedHashSet<CommentFacebook> linkedComment = new LinkedHashSet<CommentFacebook>();
				for (CommentFacebook com: postFacebooks.getListComments()) {
					linkedComment.add(com);
				}
				postFacebooks.setListComments(new ArrayList<CommentFacebook>(linkedComment));
				postFacebooks.setListNotis(new ArrayList<Notification>(linkedNoti));
			}
		} catch (Exception e) {
			System.out.println("\nGetting PostFacebook had Errors" + "*_"
					+ e.getMessage() + "*_");
		}
		return postFacebooks;

	}
	
	@Transactional

	public boolean updateLikeCountAndMessageOfPostFacebook(PostFacebook postFacebook) {

		boolean check = false;
		try {
			if (postFacebook != null) {
			PostFacebook postUpdate = entityManager.find(PostFacebook.class, postFacebook.getId());
			postUpdate.setLike_count(postFacebook.getLike_count());
			postUpdate.setMessage(postFacebook.getMessage());
			check = true;
			System.out.println("PostFacebook " + postFacebook.getName()+ "updated");
			}
		} catch (Exception e) {
			System.out.println("\nUpdate PostFacebook get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}
	
	public boolean checkPostFacebookExistInDB(String idPost, String customerId)
	{	boolean check=false;
		try{
			if(StringUtils.isBlank(idPost) || StringUtils.isBlank(customerId)) return false;
		TypedQuery<PostFacebook> query=entityManager.createQuery("SELECT c FROM "
					+ PostFacebook.class.getName() + " c where c.idPost=:idPost and c.from_client_id=:from_client_id",
					PostFacebook.class);
		query.setParameter("idPost", idPost);
		query.setParameter("from_client_id", customerId);
		PostFacebook post=new PostFacebook();
		post=query.getSingleResult();
		if (post != null){
			check=true;
		}else {
			check=false;
			throw new EntityNotFoundException(
					"Can't find PostFacebook for PostFacebookName " + idPost);
			
		}
	} catch (Exception e) {
		System.out.println("\nGetting PostFacebook had Errors" + "*_"
				+ e.getMessage() + "*_");
		e.printStackTrace();
	}
		return check;
	}
	
	public PostFacebook getPostFacebookWithIdPostAndClientId(int id, String customerId)
	{	
		PostFacebook postFacebook=null;
		try{
		TypedQuery<PostFacebook> query=entityManager.createQuery("SELECT c FROM "
					+ PostFacebook.class.getName() + " c where c.id=:id and c.from_client_id=:from_client_id",
					PostFacebook.class);
		query.setParameter("id", id);
		query.setParameter("from_client_id", customerId);
		postFacebook=query.getSingleResult();
		LinkedHashSet<CommentFacebook> comments = new LinkedHashSet<CommentFacebook>();
		for(CommentFacebook com : postFacebook.getListComments()) {
			comments.add(com);
		}
		postFacebook.setListComments(new ArrayList<CommentFacebook>(comments));
		} catch (Exception e) {
			System.out.println("\nGetting PostFacebook had Errors" + "*_"
					+ e.getMessage() + "*_");
		}
		return postFacebook;
	}
}
