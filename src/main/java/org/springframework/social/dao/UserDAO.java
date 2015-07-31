package org.springframework.social.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.model.BigGroupFace;
import org.springframework.social.model.CommentFacebook;
import org.springframework.social.model.Notification;
import org.springframework.social.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class UserDAO {
	@PersistenceContext
	public EntityManager entityManager;

	public List<User> getAllUser() {
		List<User> list = new ArrayList<User>();
		try {
			TypedQuery<User> query = entityManager.createQuery(
					"SELECT c FROM " + User.class.getName() + " c",
					User.class);

			list = query.getResultList();
			System.out.println("Get All User");
		} catch (Exception e) {
			System.out.println("\nGet Error " + "*_" + e.getMessage() + "*_");
			e.printStackTrace();
		}
		return list;

	}

	@Transactional
	public boolean createUser(User User) {
		boolean check = false;

		try {
			if (User != null) {
			// Insert a row to User table
			entityManager.persist(User);
			check = true;
			}
		} catch (Exception e) {
			System.out.println("\nGet Error with Create User " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	@Transactional
	public boolean updateUser(User User) {

		boolean check = false;
		try {
			if (User != null) {
			entityManager.merge(User);
			check = true;
			}
		} catch (Exception e) {
			System.out.println("\nUpdate User get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	@Transactional
	public boolean deleteUserId(int id) {
		boolean check = false;
		try {
			User User = entityManager.find(User.class,
					id);
			if (User == null) {
				check = false;
				throw new EntityNotFoundException(
						"Can't find User for ID " + id);
			}
			if (User != null) {

				entityManager.remove(User);
				check = true;
				System.out.println("delete User by ID");
			}
		} catch (Exception e) {

			System.out.println("\nDelete User by ID get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}
	
	@Transactional
	public User getUserById(int id) {
		User User=null;
		try {
			User = entityManager.find(User.class,id);
			
		} catch (Exception e) {

			System.out.println("\nDelete User by ID get Error " + "*_"
					+ e.getMessage() + "*_");
		}
		return User;
	}
	
	@Transactional
	public User getUserByUserId(String clientId) {
		User user= null;
		try {
			if(StringUtils.isBlank(clientId)) return null;
			TypedQuery<User> query = entityManager.createQuery("SELECT c FROM "
					+ User.class.getName() + " c where c.userId=:userId",
					User.class);
			query.setParameter("userId", clientId);
			user= query.getSingleResult();
			LinkedHashSet<Notification> notis= new LinkedHashSet<Notification>();
			for(Notification noti: user.getListNotis()) {
				notis.add(noti);
			}
			user.setListNotis(new ArrayList<Notification>(notis));
		} catch (Exception e) {
			System.out.println("\nGetUserByUserId had Errors" + "*_"
					+ e.getMessage() + "*_");
		}
		return user;
	}
	
	//Minh add 2:20 AM date 10/06/2015
		/**
		 * check userID from facebook already exist ????
		 * @param userId
		 * @return boolean
		 */
		public boolean isExistUserId(String userId) {
			User user= null;
			try {
				if(StringUtils.isBlank(userId)) return false;
				TypedQuery<User> query = entityManager.createQuery("SELECT c FROM "
						+ User.class.getName() + " c where c.userId=:userId",
						User.class);
				query.setParameter("userId", userId);
				user= query.getSingleResult();
				if(user==null){
					return false;
				}
				else{
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		//Minh add 8:49 PM date 25/06/2015
				/**
				 * check username already exist ????
				 * @param username
				 * @return boolean
				 */
		public boolean isExistUsename(String username) {
			User user= null;
			try{
				TypedQuery<User> query = entityManager.createQuery("SELECT c FROM "
						+ User.class.getName() + " c where c.userName=:username",
						User.class);
				query.setParameter("username", username);
				user= query.getSingleResult();
			}catch(Exception ex)
			{
				return false;
			}
				if(user==null){
					return false;
				}
				else{
					return true;
				}
			
		}
		
		public boolean isExistUsenameAndPassword(String username, String password) {
			User user= null;
			try{
				TypedQuery<User> query = entityManager.createQuery("SELECT c FROM "
						+ User.class.getName() + " c where c.userName=:username and c.password=:password",
						User.class);
				query.setParameter("username", username);
				query.setParameter("password", password);
				user= query.getSingleResult();
			}catch(Exception ex)
			{
				return false;
			}
				if(user==null){
					return false;
				}
				else{
					return true;
				}
			
		}
		
		public boolean isExistUsenameAndPasswordUserId(String username, String password, String userId) {
			User user= null;
			try{
				TypedQuery<User> query = entityManager.createQuery("SELECT c FROM "
						+ User.class.getName() + " c where c.userName=:username and c.password=:password and userId=:userId",
						User.class);
				query.setParameter("username", username);
				query.setParameter("password", password);
				query.setParameter("userId", userId);
				user= query.getSingleResult();
			}catch(Exception ex)
			{
				return false;
			}
				if(user==null){
					return false;
				}
				else{
					return true;
				}
			
		}
		
		public boolean isExistUsenameWhenEdit(String username) {
			User user= null;
			try{
				TypedQuery<User> query = entityManager.createQuery("SELECT c FROM "
						+ User.class.getName() + " c where c.userName=:username",
						User.class);
				query.setParameter("username", username);
				user= query.getSingleResult();
			}catch(Exception ex)
			{
				return false;
			}
				if(user==null){
					return false;
				}
				else{
					return true;
				}
			
		}
		
		@Transactional
		public boolean updateUserLogin(User user) {
			
			try {
				
				User userUpdate = this.getUserById(user.getId());
				userUpdate.setFirstName(user.getFirstName());
				userUpdate.setLastName(user.getLastName());
				userUpdate.setPassword(user.getPassword());
				userUpdate.setUserName(user.getUserName());
				
			} catch (Exception e) {
				return false;
			}
			// -----------End transaction-----------
			
			return true;
		}
}
