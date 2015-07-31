package org.springframework.social.dao;

import java.util.ArrayList;
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
 * DAO class for BigGroupFace entity. This class contains all methods that
 * inserts/updates/deletes BigGroupFace info
 * 
 * @author Anh Minh Nguyen
 *
 */
@Repository("bigGroupFaceDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class BigGroupFaceDAO {
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional
	public BigGroupFace getBigGroupFace(int id) {
		BigGroupFace bigGroupFace = null;
		try {
			bigGroupFace = entityManager.find(BigGroupFace.class, id);
			if (bigGroupFace == null) {
				throw new EntityNotFoundException("Can't find BigGroupFace for ID "
						+ id);
			}
			LinkedHashSet<GroupFace> groups= new LinkedHashSet<GroupFace >();
			for(GroupFace group : bigGroupFace.getListGroupFaces()) {
				groups.add(group);
			}
			bigGroupFace.setListGroupFaces(new ArrayList<GroupFace>(groups));
			System.out.println(bigGroupFace.toString() + "getBigGroupFace-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting BigGroupFace had Errors" + "*_"
					+ e.getMessage() + "*_");

		}
		return bigGroupFace;

	}

	// Minh Create but Hiep update because property of BigGroupFace is nameBigGroupFace ==> c.nameBigGroupFace not c.BigGroupFacename
	public BigGroupFace getBigGroupFacebyBigGroupFaceName(String BigGroupFacename) {
		BigGroupFace bigGroupFace = null;
		try {
			if(StringUtils.isBlank(BigGroupFacename)) return null;
			TypedQuery<BigGroupFace> query = entityManager.createQuery("SELECT c FROM "
					+ BigGroupFace.class.getName() + " c where c.nameBigGroupFace=:nameBigGroupFace",
					BigGroupFace.class);
			query.setParameter("nameBigGroupFace", BigGroupFacename);
			bigGroupFace = query.getSingleResult();
			if (bigGroupFace == null) {
				throw new EntityNotFoundException(
						"Can't find BigGroupFace for BigGroupFaceName " + BigGroupFacename);
			}
			System.out.println(bigGroupFace.toString() + "getBigGroupFace-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting BigGroupFace had Errors" + "*_"
					+ e.getMessage() + "*_");

		}
		return bigGroupFace;

	}

	public List<BigGroupFace> getAllBigGroupFace() {
		List<BigGroupFace> list = new ArrayList<BigGroupFace>();
		try {
			TypedQuery<BigGroupFace> query = entityManager.createQuery("SELECT c FROM "
					+ BigGroupFace.class.getName() + " c", BigGroupFace.class);

			list = query.getResultList();
			System.out.println("Get All BigGroupFaces");
		} catch (Exception e) {
			System.out.println("\nGet Error " + "*_" + e.getMessage() + "*_");

		}
		return list;

	}

	@Transactional

	public boolean createBigGroupFace(BigGroupFace bigGroupFace) {
		boolean check = false;

		try {
			if (bigGroupFace != null) {
			// Insert a row to BigGroupFace table
			entityManager.persist(bigGroupFace);
			check = true;
			}
		} catch (Exception e) {
			System.out.println("\nGet Error with Create BigGroupFace " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Update BigGroupFace information.
	 *
	 * @param BigGroupFace
	 *            an BigGroupFace to be updated.
	 */
	@Transactional

	public boolean updateBigGroupFace(BigGroupFace bigGroupFace) {

		boolean check = false;
		try {
			if (bigGroupFace != null) {
			entityManager.merge(bigGroupFace);
			check = true;
			System.out.println("BigGroupFace " + bigGroupFace.getNameBigGroupFace()+ "updated");
			}
		} catch (Exception e) {
			System.out.println("\nUpdate BigGroupFace get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Delete BigGroupFace by their Id.
	 *
	 * @param BigGroupFaceId
	 *            the BigGroupFace Id.
	 */
	@Transactional

	public boolean deleteBigGroupFaceById(int BigGroupFaceId) {
		boolean check = false;
		try {
			BigGroupFace bigGroupFace = entityManager.find(BigGroupFace.class, BigGroupFaceId);
			if (bigGroupFace == null) {
				check = false;
				throw new EntityNotFoundException("Can't find BigGroupFace for ID "
						+ BigGroupFaceId);
			}
			System.out.println(bigGroupFace.toString());
			if (bigGroupFace != null) {

				entityManager.remove(bigGroupFace);
				check = true;
				System.out.println("delete BigGroupFace by ID");
			}
		} catch (Exception e) {

			System.out.println("\nDelete BigGroupFace by ID get Error " + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

	/**
	 * Delete BigGroupFace entity.
	 *
	 * @param BigGroupFace
	 *            the object to be deleted.
	 */
	public boolean deleteBigGroupFace(BigGroupFace bigGroupFace) {
		boolean check = false;
		try {
			if (bigGroupFace != null) {
			entityManager.remove(bigGroupFace);
			check = true;
			System.out.println("delete BigGroupFace by BigGroupFace");
			}
		} catch (Exception e) {
			System.out.println("\nDelete BigGroupFace get Error" + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}


	 //Hiep add 9:04 PM date 20/04/2015
	  /**
	   * get list of BigGroupFace with clientId
	   * @param clientId
	   * @return
	   */
	public List<BigGroupFace> getAllBigGroupFaceOfClient(String clientId) {
		
		 List<BigGroupFace> bigGroupFaces = null;
		try {
			if(StringUtils.isBlank(clientId)) return null;
			TypedQuery<BigGroupFace> query = entityManager.createQuery("SELECT c FROM "
					+ BigGroupFace.class.getName() + " c where c.clientId=:clientId",
					BigGroupFace.class);
			query.setParameter("clientId", clientId);
			bigGroupFaces = query.getResultList();
			if (bigGroupFaces == null) {
				throw new EntityNotFoundException(
						"Can't find BigGroupFace for clientId " + clientId);
			}
		} catch (Exception e) {
			System.out.println("\nGetting BigGroupFace had Errors" + "*_"
					+ e.getMessage() + "*_");

		}
		return bigGroupFaces;
	}

	
	//Hiep add 9:04 PM date 20/04/2015
	/**
	 * check nameBigGroupFace already exist ????
	 * @param nameBigGroupFace
	 * @return
	 */
	public boolean isExistNameBigGroupFace(String nameBigGroupFace) {
		
		BigGroupFace bigGroupFace = null;
		try {
			if(StringUtils.isBlank(nameBigGroupFace)) return false;
			TypedQuery<BigGroupFace> query = entityManager.createQuery("SELECT c FROM "
					+ BigGroupFace.class.getName() + " c where c.nameBigGroupFace=:nameBigGroupFace",
					BigGroupFace.class);
			query.setParameter("nameBigGroupFace", nameBigGroupFace);
			bigGroupFace = query.getSingleResult();
			if(bigGroupFace==null) {
				return false;
			}
			else {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Cant find exist bigGroup = " +nameBigGroupFace);
			return false;
		}
	}
}
