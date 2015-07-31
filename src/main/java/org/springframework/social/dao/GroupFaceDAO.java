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
 * DAO class for GroupFace entity. This class contains all methods that
 * inserts/updates/deletes GroupFace info
 * 
 * @author Anh Minh Nguyen
 *
 */
@Repository("groupFaceDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class GroupFaceDAO {
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional

	public GroupFace getGroupFace(int id) {
		GroupFace GroupFace = null;
		try {
			GroupFace = entityManager.find(GroupFace.class, id);
			if (GroupFace == null) {
				throw new EntityNotFoundException("Can't find GroupFace for ID "
						+ id);
			}
			System.out.println(GroupFace.toString() + "getGroupFace-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting GroupFace had Errors" + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return GroupFace;

	}

	//Minh create but hiep update because property of GroupFace is nameGroupFace ==> c.nameGroupFace not c.GroupFacename
	public GroupFace getGroupFacebyGroupFaceName(String GroupFacename) {
		GroupFace GroupFace = null;
		try {
			if (StringUtils.isBlank(GroupFacename)) return null;
			TypedQuery<GroupFace> query = entityManager.createQuery("SELECT c FROM "
					+ GroupFace.class.getName() + " c where c.nameGroupFace=:nameGroupFace",
					GroupFace.class);
			query.setParameter("nameGroupFace", GroupFacename);
			GroupFace = query.getSingleResult();
			if (GroupFace == null) {
				throw new EntityNotFoundException(
						"Can't find GroupFace for GroupFaceName " + GroupFacename);
			}
			System.out.println(GroupFace.toString() + "getGroupFace-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting GroupFace had Errors" + "*_"
					+ e.getMessage() + "*_");
		}
		return GroupFace;

	}

	public List<GroupFace> getAllGroupFace() {
		List<GroupFace> list = new ArrayList<GroupFace>();
		try {
			TypedQuery<GroupFace> query = entityManager.createQuery("SELECT c FROM "
					+ GroupFace.class.getName() + " c", GroupFace.class);

			list = query.getResultList();
			System.out.println("Get All GroupFaces");
		} catch (Exception e) {
			System.out.println("\nGet Error " + "*_" + e.getMessage() + "*_");
			e.printStackTrace();
		}
		return list;

	}

	@Transactional

	public boolean createGroupFace(GroupFace groupFace) {
		boolean check = false;

		try {
			if (groupFace != null) {
			// Insert a row to GroupFace table
			entityManager.persist(groupFace);
			check = true;
			}

		} catch (Exception e) {
			System.out.println("\nGet Error with Create GroupFace " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Update GroupFace information.
	 *
	 * @param GroupFace
	 *            an GroupFace to be updated.
	 */
	@Transactional

	public boolean updateGroupFace(GroupFace groupFace) {

		boolean check = false;
		try {
			if (groupFace != null) {
			entityManager.merge(groupFace);
			check = true;
			System.out.println("GroupFace " + groupFace.getNameGroupFace()+ "updated");
			}
		} catch (Exception e) {
			System.out.println("\nUpdate GroupFace get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Delete GroupFace by their Id.
	 *
	 * @param GroupFaceId
	 *            the GroupFace Id.
	 */
	@Transactional

	public boolean deleteGroupFaceById(int GroupFaceId) {
		boolean check = false;
		try {
			GroupFace GroupFace = entityManager.find(GroupFace.class, GroupFaceId);
			if (GroupFace == null) {
				check = false;
				throw new EntityNotFoundException("Can't find GroupFace for ID "
						+ GroupFaceId);
			}
			System.out.println(GroupFace.toString());
			if (GroupFace != null) {

				entityManager.remove(GroupFace);
				check = true;
				System.out.println("delete GroupFace by ID");
			}
		} catch (Exception e) {

			System.out.println("\nDelete GroupFace by ID get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Delete GroupFace entity.
	 *
	 * @param GroupFace
	 *            the object to be deleted.
	 */
	public boolean deleteGroupFace(GroupFace groupFace) {
		boolean check = false;
		try {
			if (groupFace != null) {
			entityManager.remove(groupFace);
			check = true;
			System.out.println("delete GroupFace by GroupFace");
			}
		} catch (Exception e) {
			System.out.println("\nDelete GroupFace get Error" + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	//Hiep create 9:04 pm date 20/04/2015
	/**
	 * 
	 * check idGroupFace already exist ???
	 * @param idGroupFace
	 * @return
	 */
	public boolean isExistIdGroupFace(String idGroupFace,String clientId) {
		GroupFace groupFace = null;
		try {
			if(StringUtils.isBlank(idGroupFace) || StringUtils.isBlank(clientId)) return false;
			TypedQuery<GroupFace> query = entityManager.createQuery("SELECT c FROM "
					+ GroupFace.class.getName() + " c where c.idGroupFace = ?1 and c.clientId = ?2",
					GroupFace.class);
			query.setParameter(1, idGroupFace);
			query.setParameter(2, clientId);
			groupFace = query.getSingleResult();
			if(groupFace==null) {
				return false;
			}
			else {
				return true;
			}
		} catch (Exception e) {
			System.out.println("\nGetting GroupFace had Errors" + "*_"
					+ e.getMessage() + "*_");
			return false;

		}
	}
	
	// Hiep create 9:04 pm date 20/04/2015
	/**
	 * get a GroupFace with groupId vs clientId
	 * @param groupId
	 * @param clientId
	 * @return
	 */
	public GroupFace getGroupFaceWithidGroupFaceAndClientId(String groupId,String clientId) {
		GroupFace GroupFace = null;
		try {
			if(StringUtils.isBlank(groupId) || StringUtils.isBlank(clientId)) return null;
			TypedQuery<GroupFace> query = entityManager.createQuery("SELECT c FROM "
					+ GroupFace.class.getName() + " c where c.idGroupFace=:idGroupFace and c.clientId=:clientId",
					GroupFace.class);
			query.setParameter("idGroupFace", groupId);
			query.setParameter("clientId", clientId);
			GroupFace = query.getSingleResult();
		} catch (Exception e) {
			System.out.println("\n GetGroupFaceWithidGroupFaceAndClientId had Errors" + "*_"
					+ e.getMessage() + "*_");
		}
		return GroupFace;
	}

	public List<GroupFace> getAllGroupFaceOfClient(String clientId) {
		List<GroupFace> GroupFaces = null;
		try {
			if (StringUtils.isBlank(clientId)) return null;
			TypedQuery<GroupFace> query = entityManager.createQuery("SELECT c FROM "
					+ GroupFace.class.getName() + " c where c.clientId=:clientId",
					GroupFace.class);
			query.setParameter("clientId", clientId);
			GroupFaces = query.getResultList();
		} catch (Exception e) {
			System.out.println("\n GetAllGroupFaceOfClient had Errors" + "*_"
					+ e.getMessage() + "*_");
			return null;
		}
		return GroupFaces;
	}

}
