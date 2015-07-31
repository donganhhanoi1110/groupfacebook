package org.springframework.social.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.dao.ScheduleDAO;
import org.springframework.social.model.Schedule;
import org.springframework.stereotype.Service;

@Service("scheduleService")
public class ScheduleService {
	@Autowired
	ScheduleDAO scheduleDAO;
	
	public boolean isExistMessageAndDatePost(String clientId,String message,String datePost)
	{
		return this.scheduleDAO.isExistMessageAndDatePost(clientId,message, datePost);
	}
	public List<Schedule> getAllSchedule() {
		return this.scheduleDAO.getAllSchedule();
	}
	
	public boolean createSchedule(Schedule Schedule) {
		return this.scheduleDAO.createSchedule(Schedule);
	}
	public List<Schedule> getAllScheduleOfClient(String clientId) {
		return this.scheduleDAO.getAllScheduleOfClient(clientId);
	}
	public Schedule getScheduleOfClientWithMessageAndDatePost(String clientId,String groupMessage, String datePost) {
		return this.scheduleDAO.getScheduleOfClientWithMessageAndDatePost(clientId, groupMessage, datePost);
	}
	
	public boolean updateSchedule(Schedule schedule)
	{
		return this.scheduleDAO.updateSchedule(schedule);
	}
	public boolean deleteScheduleId(int id) {
		return this.scheduleDAO.deleteScheduleId(id);
	}
	
	public Schedule getScheduleById(int id) {
		return this.scheduleDAO.getScheduleById(id);
	}
	
	public List<Schedule> getAllScheduleOfState( String state) {
		return this.scheduleDAO.getAllScheduleOfState(state);
	}
}
