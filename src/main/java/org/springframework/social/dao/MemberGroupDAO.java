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
 * DAO class for MemberGroup entity. This class contains all methods that
 * inserts/updates/deletes MemberGroup info
 * 
 * @author Anh Minh Nguyen
 *
 */
@Repository("memberGroupDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberGroupDAO {
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional
	public MemberGroup getMemberGroup(int id) {
		MemberGroup MemberGroup = null;
		try {
			MemberGroup = entityManager.find(MemberGroup.class, id);
			if (MemberGroup == null) {
				throw new EntityNotFoundException("Can't find MemberGroup for ID "
						+ id);
			}
			System.out.println(MemberGroup.toString() + "getMemberGroup-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting MemberGroup had Errors" + "*_"
					+ e.getMessage() + "*_");
		}
		return MemberGroup;
	}

	public List<MemberGroup> getAllMemberGroup() {
		List<MemberGroup> list = new ArrayList<MemberGroup>();
		try {
			TypedQuery<MemberGroup> query = entityManager.createQuery("SELECT c FROM "
					+ MemberGroup.class.getName() + " c", MemberGroup.class);

			list = query.getResultList();
			System.out.println("Get All MemberGroups");
		} catch (Exception e) {
			System.out.println("\nGet Error " + "*_" + e.getMessage() + "*_");
			e.printStackTrace();
		}
		return list;

	}

	@Transactional

	public boolean createMemberGroup(MemberGroup memberGroup) {
		boolean check = false;

		try {
			if (memberGroup != null) {
			// Insert a row to MemberGroup table
			entityManager.persist(memberGroup);
			check = true;
			}
		} catch (Exception e) {
			System.out.println("\nGet Error with Create MemberGroup " + "*_"
					+ e.getMessage() + "*_");
			check=false;
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Update MemberGroup information.
	 *
	 * @param MemberGroup
	 *            an MemberGroup to be updated.
	 */
	@Transactional
	public boolean updateMemberGroup(MemberGroup memberGroup) {

		boolean check = false;
		try {
			if (memberGroup != null) {
			entityManager.merge(memberGroup);
			check = true;
			System.out.println("MemberGroup " + memberGroup.getId()+ "updated");
			}
		} catch (Exception e) {
			System.out.println("\nUpdate MemberGroup get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Delete MemberGroup by their Id.
	 *
	 * @param MemberGroupId
	 *            the MemberGroup Id.
	 */
	@Transactional
	public boolean deleteMemberGroupById(int MemberGroupId) {
		boolean check = false;
		try {
			MemberGroup MemberGroup = entityManager.find(MemberGroup.class, MemberGroupId);
			if (MemberGroup == null) {
				check = false;
				throw new EntityNotFoundException("Can't find MemberGroup for ID "
						+ MemberGroupId);
			}
			System.out.println(MemberGroup.toString());
			if (MemberGroup != null) {

				entityManager.remove(MemberGroup);
				check = true;
				System.out.println("delete MemberGroup by ID");
			}
		} catch (Exception e) {

			System.out.println("\nDelete MemberGroup by ID get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Delete MemberGroup entity.
	 *
	 * @param MemberGroup
	 *            the object to be deleted.
	 */
	public boolean deleteMemberGroup(MemberGroup memberGroup) {
		boolean check = false;
		try {
			if (memberGroup != null) {
			entityManager.remove(memberGroup);
			check = true;
			System.out.println("delete MemberGroup by MemberGroup");
			}
		} catch (Exception e) {
			System.out.println("\nDelete MemberGroup get Error" + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * 
	 * check idMemberGroup already exist ???
	 * @param idMemberGroup
	 * @return boolean
	 */
	public boolean isExistIdMemberGroup(String idMemberGroup) {
		MemberGroup MemberGroup = null;
		try {
			if(StringUtils.isBlank(idMemberGroup)) return false;
			TypedQuery<MemberGroup> query = entityManager.createQuery("SELECT c FROM "
					+ MemberGroup.class.getName() + " c where c.memberId =:idMemberGroup",
					MemberGroup.class);
			query.setParameter("idMemberGroup", idMemberGroup);
			MemberGroup = query.getSingleResult();
			if(MemberGroup==null){
				return false;
			}
			else{
				return true;
			}
		} catch (Exception e) {
			System.out.println("\nGetting MemberGroup had Errors" + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
			return false;

		}
		
	}
	

	/**
	 * 
	 *get MemberFroup
	 * @param idMemberGroup
	 * @return memberGroup
	 */
	public MemberGroup getMemberFroupByMemberId(String idMemberGroup) {
		MemberGroup memberGroup = null;
		try {
			if(StringUtils.isBlank(idMemberGroup)) return null;
			TypedQuery<MemberGroup> query = entityManager.createQuery("SELECT c FROM "
					+ MemberGroup.class.getName() + " c where c.memberId =:idMemberGroup",
					MemberGroup.class);
			query.setParameter("idMemberGroup", idMemberGroup);
			memberGroup  = query.getSingleResult();
			
		} catch (Exception e) {
			System.out.println("\nGetting MemberGroup had Errors" + "*_"
					+ e.getMessage() + "*_");
			return null;
		}
		return memberGroup;
	}
	
	/**
	 * 
	 *get list MemberFroup
	 * @param idMemberGroup
	 * @return memberGroups
	 */
	public List<MemberGroup> getListMemberFroupByIdGroup(String idGroup) {
		List<MemberGroup> memberGroups = null;
		try {
			if(StringUtils.isBlank(idGroup)) return null;
			TypedQuery<MemberGroup> query = entityManager.createQuery("SELECT c FROM "
					+ MemberGroup.class.getName() + " c where c.idGroup =:idGroup",
					MemberGroup.class);
			query.setParameter("idGroup", idGroup);
			memberGroups  = query.getResultList();
			
		} catch (Exception e) {
			System.out.println("\nGetting MemberGroups had Errors" + "*_"
					+ e.getMessage() + "*_");
			return null;
		}
		return memberGroups;
	}

}
