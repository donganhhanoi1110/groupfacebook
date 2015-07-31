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
 * DAO class for Notification entity. This class contains all methods that
 * inserts/updates/deletes Notification info
 * 
 * @author Anh Minh Nguyen
 *
 */
@Repository("notificationDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class NotificationDAO {
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional

	public Notification getNotification(int id) {
		Notification Notification = null;
		try {
			Notification = entityManager.find(Notification.class, id);
			if (Notification == null) {
				throw new EntityNotFoundException("Can't find Notification for ID "
						+ id);
			}
			System.out.println(Notification.toString() + "getNotification-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting Notification had Errors" + "*_"
					+ e.getMessage() + "*_");
		}
		return Notification;
	}

	public List<Notification> getAllNotification() {
		List<Notification> list = new ArrayList<Notification>();
		try {
			TypedQuery<Notification> query = entityManager.createQuery("SELECT c FROM "
					+ Notification.class.getName() + " c", Notification.class);

			list = query.getResultList();
			System.out.println("Get All Notifications");
		} catch (Exception e) {
			System.out.println("\nGet Error " + "*_" + e.getMessage() + "*_");
			e.printStackTrace();
		}
		return list;

	}

	@Transactional

	public boolean createNotification(Notification notification) {
		boolean check = false;

		try {
			if (notification != null) {
			// Insert a row to Notification table
			entityManager.persist(notification);
			check = true;
			}
		} catch (Exception e) {
			System.out.println("\nGet Error with Create Notification " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
			check=false;
		}
		return check;
	}

	/**
	 * Update Notification information.
	 *
	 * @param Notification
	 *            an Notification to be updated.
	 */
	@Transactional
	public boolean updateNotification(Notification notification) {

		boolean check = false;
		try {
			if (notification != null) {
			entityManager.merge(notification);
			check = true;
			System.out.println("Notification " + notification.getId()+ "updated");
			}
		} catch (Exception e) {
			System.out.println("\nUpdate Notification get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Delete Notification by their Id.
	 *
	 * @param NotificationId
	 *            the Notification Id.
	 */
	@Transactional
	public boolean deleteNotificationById(int notificationId) {
		boolean check = false;
		try {
			Notification notification = entityManager.find(Notification.class, notificationId);
			if (notification == null) {
				check = false;
				throw new EntityNotFoundException("Can't find Notification for ID "
						+ notificationId);
			}
			System.out.println(notification.toString());
			if (notification != null) {

				entityManager.remove(notification);
				check = true;
				System.out.println("delete Notification by ID");
			}
		} catch (Exception e) {

			System.out.println("\nDelete Notification by ID get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Delete Notification entity.
	 *
	 * @param Notification
	 *            the object to be deleted.
	 */
	public boolean deleteNotification(Notification notification) {
		boolean check = false;
		try {
			if (notification != null) {
			entityManager.remove(notification);
			check = true;
			System.out.println("delete Notification by Notification");
			}
		} catch (Exception e) {
			System.out.println("\nDelete Notification get Error" + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * 
	 * check idNotification already exist ???
	 * @param idNotification
	 * @return
	 */
	public boolean isExistIdNotification(String idNotification) {
		Notification notification = null;
		try {
			if(StringUtils.isBlank(idNotification)) return false;
			TypedQuery<Notification> query = entityManager.createQuery("SELECT c FROM "
					+ Notification.class.getName() + " c where c.notificationId =:idNotification",
					Notification.class);
			query.setParameter("idNotification", idNotification);
			notification = query.getSingleResult();
			if(notification == null) {
				return false;
			}
			else {
				return true;
			}
		} catch (Exception e) {
			System.out.println("\nGetting Notification had Errors" + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
			return false;

		}
		
	}
	
	/**
	 * 
	 * Get Notification
	 * @param idNotification
	 * @return
	 */
	public Notification getNotisByNotisId(String idNotification) {
		Notification notification = null;
		try {
			if(StringUtils.isBlank(idNotification)) return null;
			TypedQuery<Notification> query = entityManager.createQuery("SELECT c FROM "
					+ Notification.class.getName() + " c where c.notificationId =:idNotification",
					Notification.class);
			query.setParameter("idNotification", idNotification);
			notification = query.getSingleResult();
		} catch (Exception e) {
			System.out.println("\nGetting Notification had Errors" + "*_"
					+ e.getMessage() + "*_");
			return null;

		}
		return notification;
	}

	/**
	 * 
	 * Get Notifications
	 * @param clientId
	 * @param unread
	 * @return
	 */
	public List<Notification> getNotisByClientIdAndUnread(String clientId, boolean unread) {
		List<Notification> notis = null;
		try {
			if(StringUtils.isBlank(clientId)) return null;
			TypedQuery<Notification> query = entityManager.createQuery("SELECT c FROM "
					+ Notification.class.getName() + " c where c.toObject =:toObject and c.unread =:unread",
					Notification.class);
			query.setParameter("toObject", clientId);
			query.setParameter("unread", unread);
			notis = query.getResultList();
		} catch (Exception e) {
			System.out.println("\nGetting Notification had Errors" + "*_"
					+ e.getMessage() + "*_");
			return null;

		}
		return notis;
	}
}
