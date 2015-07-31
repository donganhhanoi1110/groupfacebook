package org.springframework.social.dao;

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
import org.springframework.social.model.PostFacebook;
import org.springframework.social.model.Schedule;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("scheduleDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class ScheduleDAO {
	@PersistenceContext
	public EntityManager entityManager;

	public List<Schedule> getAllSchedule() {
		List<Schedule> list = new ArrayList<Schedule>();
		LinkedHashSet<Schedule> linkedHasset = new LinkedHashSet<Schedule>();
		try {
			TypedQuery<Schedule> query = entityManager.createQuery(
					"SELECT c FROM " + Schedule.class.getName() + " c",
					Schedule.class);

			list = query.getResultList();
			for( Schedule schedule : list ) {
				linkedHasset.add(schedule);
			}
			System.out.println("Get All Schedule");
		} catch (Exception e) {
			System.out.println("\nGet Error " + "*_" + e.getMessage() + "*_");

		}
		return new ArrayList<Schedule>(linkedHasset);

	}

	@Transactional
	public boolean createSchedule(Schedule Schedule) {
		boolean check = false;

		try {
			if (Schedule != null) {
			// Insert a row to Schedule table
			entityManager.persist(Schedule);
			check = true;
			}
		} catch (Exception e) {
			System.out.println("\nGet Error with Create Schedule " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	public boolean isExistMessageAndDatePost(String clientId, String message,
			String datePost) {
		if(StringUtils.isBlank(clientId) || StringUtils.isBlank(message) || StringUtils.isBlank(datePost)) return false;
		Schedule schedule = null;
		try {
			TypedQuery<Schedule> query = entityManager
					.createQuery(
							"SELECT c FROM "
									+ Schedule.class.getName()
									+ " c where c.clientId=:clientId and c.groupMessage=:groupMessage and c.datePost=:datePost",
							Schedule.class);
			query.setParameter("clientId", clientId);
			query.setParameter("groupMessage", message);
			query.setParameter("datePost", datePost);
			schedule = query.getSingleResult();
			if (schedule == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Schedule> getAllScheduleOfClient(String clientId) {
		List<Schedule> list = new ArrayList<Schedule>();
		try {
			if(StringUtils.isBlank(clientId)) return null;
			TypedQuery<Schedule> query = entityManager.createQuery(
					"SELECT c FROM " + Schedule.class.getName()
							+ " c where c.clientId=:clientId", Schedule.class);
			query.setParameter("clientId", clientId);
			list = query.getResultList();
		} catch (Exception e) {
			System.out.println("\nGetting Schedule had Errors" + "*_"
					+ e.getMessage() + "*_");
		}
		return list;
	}

	public List<Schedule> getAllScheduleOfState( String state) {
		List<Schedule> list = new ArrayList<Schedule>();
		LinkedHashSet<Schedule> linkedHasset = new LinkedHashSet<Schedule>();
		try {
			if(StringUtils.isBlank(state)) return null;
			TypedQuery<Schedule> query = entityManager.createQuery(
					"SELECT c FROM " + Schedule.class.getName()
							+ " c where c.state=:state", Schedule.class);
			query.setParameter("state", state);
			list = query.getResultList();
			for( Schedule schedule : list ) {
				linkedHasset.add(schedule);
			}
		} catch (Exception e) {
			System.out.println("\nGetting Schedule had Errors" + "*_"
					+ e.getMessage() + "*_");
		}
		return new ArrayList<Schedule>(linkedHasset);
	}
	public Schedule getScheduleOfClientWithMessageAndDatePost(String clientId,
			String groupMessage, String datePost) {
		Schedule schedule = null;
		try {
			if(StringUtils.isBlank(clientId) || StringUtils.isBlank(groupMessage) || StringUtils.isBlank(datePost)) return null;
			TypedQuery<Schedule> query = entityManager
					.createQuery(
							"SELECT c FROM "
									+ Schedule.class.getName()
									+ " c where c.clientId=:clientId and c.groupMessage=:groupMessage and c.datePost=:datePost",
							Schedule.class);
			query.setParameter("clientId", clientId);
			query.setParameter("groupMessage", groupMessage);
			query.setParameter("datePost", datePost);
			schedule = query.getSingleResult();
		} catch (Exception e) {
			System.out.println("\nGetting Schedule had Errors" + "*_"
					+ e.getMessage() + "*_");
		}
		return schedule;
	}

	@Transactional
	public boolean updateSchedule(Schedule schedule) {

		boolean check = false;
		try {
			if (schedule != null) {
			entityManager.merge(schedule);
			check = true;
			}
		} catch (Exception e) {
			System.out.println("\nUpdate Schedule get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	@Transactional
	public boolean deleteScheduleId(int id) {
		boolean check = false;
		try {
			Schedule schedule = entityManager.find(Schedule.class,
					id);
			if (schedule == null) {
				check = false;
				throw new EntityNotFoundException(
						"Can't find Schedule for ID " + id);
			}
			if (schedule != null) {

				entityManager.remove(schedule);
				check = true;
				System.out.println("delete schedule by ID");
			}
		} catch (Exception e) {

			System.out.println("\nDelete schedule by ID get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}
	
	@Transactional
	public Schedule getScheduleById(int id) {
		Schedule schedule=null;
		try {
			schedule = entityManager.find(Schedule.class,id);
		} catch (Exception e) {
			System.out.println("\nDelete schedule by ID get Error " + "*_"
					+ e.getMessage() + "*_");
			return null;
		}
		return schedule;
	}
	
	
	
}
