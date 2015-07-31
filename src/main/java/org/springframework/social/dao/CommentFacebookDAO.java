package org.springframework.social.dao;

import java.util.ArrayList;
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
 * inserts/updates/deletes CommentFacebook info
 * 
 * @author Anh Minh Nguyen
 *
 */
@Repository("CommentFacebookDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class CommentFacebookDAO {
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional

	public CommentFacebook getCommentFacebook(int id) {
		CommentFacebook commentFacebook = null;
		try {
			commentFacebook  = entityManager.find(CommentFacebook.class, id);
			if (commentFacebook  == null) {
				throw new EntityNotFoundException("Can't find CommentFacebook for ID "
						+ id);
			}
			System.out.println(commentFacebook .toString() + "getCommentFacebook-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting CommentFacebook had Errors" + "*_"
					+ e.getMessage() + "*_");
		}
		return commentFacebook ;

	}


	public CommentFacebook getCommentFacebookbyCommentFacebookName(String CommentFacebookname) {
		CommentFacebook commentFacebook = null;
		try {
			if(StringUtils.isBlank(CommentFacebookname)) return null;
			TypedQuery<CommentFacebook> query = entityManager.createQuery("SELECT c FROM "
					+ CommentFacebook.class.getName() + " c where c.CommentFacebookName=:CommentFacebookname",
					CommentFacebook.class);
			query.setParameter("CommentFacebookname", CommentFacebookname);
			commentFacebook  = query.getSingleResult();
			if (commentFacebook  == null) {
				throw new EntityNotFoundException(
						"Can't find CommentFacebook for CommentFacebookName " + CommentFacebookname);
			}
			System.out.println(commentFacebook .toString() + "getCommentFacebook-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting CommentFacebook had Errors" + "*_"
					+ e.getMessage() +" "+CommentFacebookname+  " *_");

		}
		return commentFacebook ;

	}

	public List<CommentFacebook> getAllCommentFacebook() {
		List<CommentFacebook> list = new ArrayList<CommentFacebook>();
		try {
			TypedQuery<CommentFacebook> query = entityManager.createQuery("SELECT c FROM "
					+ CommentFacebook.class.getName() + " c", CommentFacebook.class);

			list = query.getResultList();
			System.out.println("Get All CommentFacebooks");
		} catch (Exception e) {
			System.out.println("\nGet Error " + "*_" + e.getMessage() + "*_");
			e.printStackTrace();
		}
		return list;

	}

	@Transactional

	public boolean createCommentFacebook(CommentFacebook commentFacebook) {
		boolean check = false;

		try {
			if ( commentFacebook != null) {	
			// Insert a row to CommentFacebook table
			entityManager.persist(commentFacebook);
			check = true;
			}
		} catch (Exception e) {
			System.out.println("\nGet Error with Create CommentFacebook " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Update CommentFacebook information.
	 *
	 * @param CommentFacebook
	 *            an CommentFacebook to be updated.
	 */
	@Transactional

	public boolean updateCommentFacebook(CommentFacebook commentFacebook) {

		boolean check = false;
		try {
			if ( commentFacebook != null) {	
			entityManager.merge(commentFacebook);
			check = true;
			System.out.println("CommentFacebook " + commentFacebook.getMessage()+ "updated");
			}
		} catch (Exception e) {
			System.out.println("\nUpdate CommentFacebook get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Delete CommentFacebook by their Id.
	 *
	 * @param CommentFacebookId
	 *            the CommentFacebook Id.
	 */
	@Transactional

	public boolean deleteCommentFacebookById(int CommentFacebookId) {
		boolean check = false;
		try {
			CommentFacebook commentFacebook  = entityManager.find(CommentFacebook.class, CommentFacebookId);
			if (commentFacebook  == null) {
				check = false;
				throw new EntityNotFoundException("Can't find CommentFacebook for ID "
						+ CommentFacebookId);
			}
			System.out.println(commentFacebook .toString());
			if (commentFacebook  != null) {

				entityManager.remove(commentFacebook );
				check = true;
				System.out.println("delete CommentFacebook by ID");
			}
		} catch (Exception e) {

			System.out.println("\nDelete CommentFacebook by ID get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Delete CommentFacebook entity.
	 *
	 * @param CommentFacebook
	 *            the object to be deleted.
	 */
	public boolean deleteCommentFacebook(CommentFacebook commentFacebook) {
		boolean check = false;
		try {
			if ( commentFacebook != null) {	
			entityManager.remove(commentFacebook);
			check = true;
			System.out.println("delete CommentFacebook by CommentFacebook");
			}
		} catch (Exception e) {
			System.out.println("\nDelete CommentFacebook get Error" + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}
	
	
	/**
	 * check exist of id_comment
	 * @param id_comment
	 * @return
	 */
	public boolean isExistIdComment(String id_comment)
	{
		CommentFacebook commentFacebook = null;
		try {
			if (StringUtils.isBlank(id_comment)) return false;
			TypedQuery<CommentFacebook> query = entityManager.createQuery("SELECT c FROM "
					+ CommentFacebook.class.getName() + " c where c.id_comment=:id_comment",
					CommentFacebook.class);
			query.setParameter("id_comment", id_comment);
			commentFacebook = query.getSingleResult();
			if(commentFacebook==null){
				return false;
			}
			else {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Cant find exist id_comment = " +id_comment);
			return false;
		}
	}
	
	@Transactional
	public boolean updateLikeCountOfCommentFacebook(CommentFacebook commentFacebook) {

		boolean check = false;
		try {
			if(commentFacebook == null) return false;
			CommentFacebook commentFacebookUpdate= entityManager.find(CommentFacebook.class, commentFacebook.getId());
			commentFacebookUpdate.setLike_count(commentFacebook.getLike_count());
			check = true;
			System.out.println("CommentFacebook " + commentFacebook.getMessage()+ "updated");
		} catch (Exception e) {
			System.out.println("\nUpdate CommentFacebook get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
			check = false;
		}
		return check;
	}
	
	public CommentFacebook getCommentFacebookbyIdComment(String id_comment) {
		CommentFacebook CommentFacebook = null;
		try {
			if(StringUtils.isBlank(id_comment)) return null;
			TypedQuery<CommentFacebook> query = entityManager.createQuery("SELECT c FROM "
					+ CommentFacebook.class.getName() + " c where c.id_comment=:id_comment",
					CommentFacebook.class);
			query.setParameter("id_comment", id_comment);
			CommentFacebook = query.getSingleResult();
			if (CommentFacebook == null) {
				throw new EntityNotFoundException(
						"Can't find CommentFacebook for CommentFacebookName " + id_comment);
			}
			System.out.println(CommentFacebook.toString() + "getCommentFacebook-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting CommentFacebook had Errors" + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
			return null;
		}
		return CommentFacebook;
	}
}
