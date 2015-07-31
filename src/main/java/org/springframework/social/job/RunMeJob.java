package org.springframework.social.job;
import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Group;
import facebook4j.Like;
import facebook4j.PagableList;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import facebook4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.social.controller.FileUploadController;
import org.springframework.social.facebook.api.GroupMembership;
import org.springframework.social.json.UploadedFile;
import org.springframework.social.model.CommentFacebook;
import org.springframework.social.model.GroupFace;
import org.springframework.social.model.MyGroup;
import org.springframework.social.model.PostFacebook;
import org.springframework.social.model.Schedule;
import org.springframework.social.model.UploadedFileResponse;
import org.springframework.social.model.User;
import org.springframework.social.service.Facebook4JService;
import org.springframework.social.service.FacebookService;
import org.springframework.social.service.SystemService;
/**
 * Quartz Job Run in background for scheduling Post
 * @author Minh Anh Nguyen 
 */
public class RunMeJob extends QuartzJobBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	ApplicationContextProvider appContext ;
	Facebook4JService facebookService;
	SystemService sysService;
	public RunMeJob() {
		// TODO Auto-generated constructor stub
		this.appContext = new ApplicationContextProvider();
		this.facebookService = appContext.getApplicationContext().getBean("facebook4JService", Facebook4JService.class);
		this.sysService=facebookService.getSystemService();
	}
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		// Process @Autowired injection for the given target object, based on
		// the current web application context.

//		try {
//			//System.out.println("\n!!*****Quartz-Start-Job*****!!");
//			  //Priority #1 Post Schedule
//			try {
//				postToWallGroup();
//			} catch (FacebookException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			//Priority #2 Add data to DB
//			List<User> users = facebookService.getUserService().getAllUser();
//			if( users != null && !users.isEmpty()) {
//				for(User user : users) {
//					facebookService.setAccessToken(new AccessToken(user.getAccessToken()));
//					//addAllGroup( user );
//					updatePostFace(user);
//				}
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
	}
	
	/**
	 * NOTE: Add all Group to DB
	 * @param User user
	 * @return void
	 */
	private void addAllGroup(User user) {
		try{
			//add all group of user in database
			ResponseList<Group> groups=this.facebookService.getFacebook().getGroups();
			if (groups != null) {
				for(Group groupAddDB : groups) {
					if(!facebookService.getGroupDbService().isExistIdGroupFace(groupAddDB.getId(),user.getUserId())) {
						GroupFace groupFace=new GroupFace(
								0, 
								groupAddDB.getVersion() == null ? 0 : groupAddDB.getVersion(), 
								groupAddDB.getName(), 
								groupAddDB.getId(), 
								groupAddDB.isAdministrator(), 
								groupAddDB.getBookmarkOrder() == null ? 0 : groupAddDB.getBookmarkOrder(), 
								groupAddDB.getOwner() == null ? null : groupAddDB.getOwner().getId(), 
								user.getUserId(), 
								groupAddDB.getDescription() == null ? null : groupAddDB.getDescription(), 
								groupAddDB.getPrivacy() == null ? null : groupAddDB.getPrivacy().toString(), 
								groupAddDB.getIcon() == null ? null : groupAddDB.getIcon().toString(), 
								groupAddDB.getUpdatedTime() == null ? null :groupAddDB.getUpdatedTime().toString(), 
							    groupAddDB.getEmail() == null ? null :groupAddDB.getEmail(),
								groupAddDB.getVenue() == null ? null :groupAddDB.getVenue().toString()); 
						facebookService.getGroupDbService().createGroupFace(groupFace);
					}
				}
			}//end if == null
		}catch(FacebookException e) {
			e.printStackTrace();
		}
		catch( Exception e){
			e.printStackTrace();
		}
	}
	

	/**
	 * NOTE: Post to Wall Group base on Schedule
	 * @return boolean
	 */
	private boolean postToWallGroup() throws FacebookException{
		boolean check=true;
		try{
			
			//get all schedules
			List<Schedule> schedules= facebookService.getScheduleService()
					.getAllScheduleOfState("Pending");
			//loop all schedule
			if (schedules != null) {
			for(Schedule schedule: schedules) {
				
				//Get system current time and check with Time of schedule post
				Date date=new Date();
				Date systemDate =sysService
						.convertStringToDate(facebookService.getSystemService()
								.convertDateToString(date));
				//get Schedule Date
				Date scheduleDate = sysService
						.convertStringToDate(schedule.getDatePost());
				if(systemDate.getTime() == scheduleDate.getTime()){
					
				//get User base on userId
				User user = facebookService.getUserService().getUserByUserId(schedule.getClientId());	
				//get access Token of that user
				AccessToken accessToken=new AccessToken(user.getAccessToken());
				//Set it to facebook to know that which user need to post 
				facebookService.getFacebook().setOAuthAccessToken(accessToken);
				if("pending".equalsIgnoreCase(schedule.getState())){
				
				// use to check in list of groupfacebook not repeate.
				Set<String> groupIdTemps=new HashSet<String>();
				
				// loop all groups that need to post the message
				for(GroupFace group: schedule.getListGroupFaces()){
					//if group.getIdGroupFace() is true ==> allow to post else it have been repeated ==> don't allow to post
					groupIdTemps.add(group.getIdGroupFace());
				}//end group loop
				String[] stringGroupIdArray = Arrays.copyOf(groupIdTemps.toArray(), groupIdTemps.toArray().length, String[].class);
				
				MyGroup myGroupItem = new MyGroup(
						schedule.getGroupMessage(), 
						schedule.getGroupLink(), 
						schedule.getGroupName(), 
						schedule.getGroupCaption(), 
						schedule.getGroupDescription(), 
						schedule.getGroupImage(), schedule.getClientId(), stringGroupIdArray );
				//get file from db, not get directly from schedule
				UploadedFileResponse fileRes = facebookService.getUploadService().getFileByFileId(schedule.getGroupMessage()+"_"+schedule.getDatePost());
				if (fileRes != null) {
					UploadedFile file= new UploadedFile(fileRes.getLength(), null, fileRes.getName(), fileRes.getType(), fileRes.isSuccess(), fileRes.getMessage(), fileRes.getPath(), new File(fileRes.getPath()));
					if (file != null) {
						String[] contentType = file.getType().split("/");
						String type = contentType[0];
						if (type.contains("image")) {
							facebookService.postToWallPhoto(myGroupItem, file);
						} else {
							if(type.contains("video")) {
								facebookService.postToWallVideo(myGroupItem, file);
							} 
						}
					}
				}  else {
					//Post to wall and catch exception
					facebookService.postToWall(myGroupItem);
				}
				
			  }//close if it's pending
				schedule.setState("active");
				facebookService.getScheduleService().updateSchedule(schedule);
			}//end if compare sysDate and scheduleDate
		}//end schedule loop
		}//end if schedule != null
		}catch(Exception e){
			e.printStackTrace();
			check=false;
		}
		return check;
	}

		/**
		 * NOTE:Get all comments
		 * @param User user
		 * @return void
		 */
		private void updatePostFace(User user){
			try {
			List<GroupFace> listGroups= facebookService.getGroupDbService().getAllGroupFaceOfClient(user.getUserId());
			
			for (GroupFace group : listGroups) {
				//List Post from fb
				List<PostFacebook> listPost = facebookService.getPostFacebookService().getPostFacebookbyIdGroup(group.getIdGroupFace(), group.getClientId());
				
				if (listPost != null) {
					//List Post from Group Feed
					ResponseList<Post> postFeed = facebookService.getFacebook().getFeed(group.getIdGroupFace(), new Reading().limit(10));
					facebookService.getNotificationsGroup(user.getUserId());
					for(Post feed : postFeed) {
						for( PostFacebook post : listPost) {
							//update commentFacebook
//							facebookService.updateCommentsOfPost(post);
							if (!post.getIdPost().contains(post.getIdGroup()) ||  post.getIdPost().length() < 20) { ////=> update id for post
//							String feedCreatedDate = sysService.convertDateToString(feed.getCreatedTime());
//							String postCreatedDate = post.getCreatedDate();
//								if(feedCreatedDate.equals(postCreatedDate) && feed.getObjectId() != null) {
							if(feed.getObjectId() != null) {
									if (post.getVideoId() != null && post.getVideoId().equals(feed.getObjectId())) {
											post.setIdPost(feed.getId());
									}
									else if (post.getPhotoId() != null && post.getPhotoId().equals(feed.getObjectId())) {
										post.setIdPost(feed.getId());
									}
								}
							}
							//update new information
							if( feed.getId().equals(post.getIdPost())) {
								if (feed.getLikes() != null && feed.getLikes().size() >0) {
								for (Like like : feed.getLikes()) {
									if (like.getName().equals(user.getUserFacebookFullName())) {
									post.setUserLike(true);	
									} else {
										post.setUserLike(false);	
									}
								}
								}//end if check size 
								else {
									post.setUserLike(false);
								}
								post.setMessage(StringUtils.isBlank(feed.getMessage()) ? null : feed.getMessage()); //String message
								post.setLink(feed.getLink() == null ? "https://facebook.com/"+post.getIdPost().replace("_", "/posts/") : feed.getLink().toString());//String link
								post.setName(StringUtils.isBlank(feed.getName()) ? null : feed.getName()); // String name
								post.setCaption(StringUtils.isBlank(feed.getCaption()) ? null : feed.getCaption()); //String caption
								post.setDescription(StringUtils.isBlank(feed.getDescription()) ? null : feed.getDescription());//String description 
								post.setPicture(feed.getPicture() == null ? null : feed.getPicture().toString()); //String picture
								post.setUpdated_date(sysService.convertDateToString(feed.getUpdatedTime())); //String updated_date
								post.setSource(feed.getSource() == null ? null : feed.getSource().toString());
								post.setFullPicture(feed.getFullPicture() == null ? null : feed.getFullPicture().toString());
								post.setLike_count(feed.getLikes().size());
								post.setType(feed.getType());
								post.setShareCount(feed.getSharesCount() == null ? 0 : feed.getSharesCount().intValue());
							}
							facebookService.getPostFacebookService().updatePostFacebook(post);//update info
						}//end for PostFacebook
					}//end for feed
				}//end if listPost != null
			}//end for listGroups
			} catch (FacebookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
}
