package org.springframework.social.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.social.dao.NotificationDAO;
import org.springframework.social.model.*;

/**
 * Notification Service class call method from DAO for controller to do get Data or business logic
 * 
 * @author Anh Minh Nguyen
 *
 */
@Service("notificationService")
public class NotificationService {
	
	NotificationDAO NotificationDAO;
	
	
	
	public NotificationDAO getNotificationDAO() {
		return NotificationDAO;
	}


	@Autowired
	public void setNotificationDAO(NotificationDAO NotificationDAO) {
		this.NotificationDAO = NotificationDAO;
	}



	public Notification getNotification(int id) {
		return this.getNotificationDAO().getNotification(id);
	}
	
	public List<Notification> getAllNotification() {
		return this.getNotificationDAO().getAllNotification();
	}
	public boolean createNotification(Notification Notification) {
		return this.getNotificationDAO().createNotification(Notification);
	}
	
	 public boolean updateNotification(Notification Notification) {
	    	return this.getNotificationDAO().updateNotification(Notification);
	    }
	 
	    /**
	     * Delete Notification by their Id.
	     *
	     * @param NotificationId the Notification Id.
	     */
	    public boolean deleteNotificationById(int NotificationId) {
	       return this.getNotificationDAO().deleteNotificationById(NotificationId);
	    }
	 
	    /**
	     * Delete Notification entity.
	     *
	     * @param Notification the object to be deleted.
	     */
	    public boolean deleteNotification(Notification Notification) {
	    	return this.getNotificationDAO().deleteNotification(Notification);
	    }

	    /**
	     * check idNotification already exist ??? 
	     * @param idNotification
	     * @return
	     */
		public boolean isExistIdNotification(String idNotification) {
			return this.NotificationDAO.isExistIdNotification(idNotification);
		}

		/**
		 * 
		 * Get Notification
		 * @param idNotification
		 * @return
		 */
		public Notification getNotisByNotisId(String idNotification) {
			return this.NotificationDAO.getNotisByNotisId(idNotification);
		}

		/**
		 * 
		 * Get Notifications
		 * @param clientId
		 * @param unread
		 * @return
		 */
		public List<Notification> getNotisByClientIdAndUnread(String clientId, boolean unread) {
			return this.NotificationDAO.getNotisByClientIdAndUnread(clientId, unread);
		}
}
