
package org.springframework.social.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.controller.FileUploadController;
import org.springframework.social.json.GroupFiles;
import org.springframework.social.json.UploadedFile;
import org.springframework.social.model.CommentFacebook;
import org.springframework.social.model.GroupFace;
import org.springframework.social.model.MemberGroup;
import org.springframework.social.model.MyGroup;
import org.springframework.social.model.PostFacebook;
import org.springframework.social.model.UploadedFileResponse;
import org.springframework.social.model.User;
import org.springframework.stereotype.Service;

import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.GroupDoc;
import facebook4j.GroupMember;
import facebook4j.IdNameEntity;
import facebook4j.Like;
import facebook4j.Media;
import facebook4j.Notification;
import facebook4j.PhotoUpdate;
import facebook4j.Post;
import facebook4j.PostUpdate;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.User.Education;
import facebook4j.User.Work;
import facebook4j.VideoUpdate;
import facebook4j.auth.AccessToken;
import facebook4j.conf.ConfigurationBuilder;
import facebook4j.internal.http.HttpClientWrapper;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONObject;

/**
 * Facebook4J Service use Facebook4J jar
 * Data.
 * 
 * @author Anh Minh Nguyen
 *
 */
@Service("facebook4JService")
public class Facebook4JService {

	private Facebook facebook;
	SystemService systemService ;
	@Autowired
	ServletContext servletContext;
	@Autowired
	UserService userService;
	@Autowired
	PostFacebookService postFacebookService;
	@Autowired
	CommentFacebookService commentFacebookService;
	@Autowired
	GroupFaceService groupDbService;
	@Autowired
	ScheduleService scheduleService;
	@Autowired
	FacebookService facebookService;
	@Autowired
	NotificationService notificationService;
	@Autowired
	MemberGroupService memberGroupService;
	@Autowired
	NotificationService notisService;
	@Autowired
	UploadedFileResponseService uploadService;
	public Facebook4JService() {
		systemService= new SystemService();
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthAppId("1438460799780610")
		  .setOAuthAppSecret("6993efa92ea971fb36e52c41fb316798")
		//  .setOAuthAccessToken("1438460799780610|xdpOmnGZNAmAyJH2N59miDYWbso")
		  .setOAuthPermissions("email,"
		  		+ "publish_actions,"
		  		+ "public_profile,  "
		  		+ "user_likes, "
		  		+ "manage_pages,"
		  		+ "user_posts,user_groups, user_events, user_photos, user_friends, user_about_me,user_videos,user_managed_groups,"
		  		+ "read_stream, manage_notifications");
		FacebookFactory ff = new FacebookFactory(cb.build());
		// Generate facebook instance.
		this.facebook = ff.getInstance();
	}

	
	public UploadedFileResponseService getUploadService() {
		return uploadService;
	}


	public void setUploadService(UploadedFileResponseService uploadService) {
		this.uploadService = uploadService;
	}


	public FacebookService getFacebookService() {
		return facebookService;
	}


	public void setFacebookService(FacebookService facebookService) {
		this.facebookService = facebookService;
	}


	public ScheduleService getScheduleService() {
		return scheduleService;
	}


	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}


	public GroupFaceService getGroupDbService() {
		return groupDbService;
	}


	public void setGroupDbService(GroupFaceService groupDbService) {
		this.groupDbService = groupDbService;
	}


	public SystemService getSystemService() {
		return systemService;
	}


	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public PostFacebookService getPostFacebookService() {
		return postFacebookService;
	}


	public void setPostFacebookService(PostFacebookService postFacebookService) {
		this.postFacebookService = postFacebookService;
	}


	public CommentFacebookService getCommentFacebookService() {
		return commentFacebookService;
	}


	public void setCommentFacebookService(
			CommentFacebookService commentFacebookService) {
		this.commentFacebookService = commentFacebookService;
	}


	public Facebook getFacebook() {
		return facebook;
	}

	public void setFacebook(Facebook facebook) {
		this.facebook = facebook;
	}
	public void setAccessToken(AccessToken accessToken){
		this.facebook.setOAuthAccessToken(accessToken);
	}
	
	/**
	 * NOTE: Post To Wall Facebook
	 * @param MyGroup postItem
	 * @return List<String>
	 */
	public List<String> postToWall(MyGroup postItem)
	{
		PostUpdate update = null;
		List<String> listMessage = new ArrayList<String>();
		try {
			update = new PostUpdate("");
			
			if(!StringUtils.isBlank(postItem.getGroupMessage())){
				update.message(postItem.getGroupMessage());
			} else {
				listMessage.add("Please input your message!!!");
			}
			if(!StringUtils.isBlank(postItem.getGroupImage())){
				update.picture(new URL(postItem.getGroupImage()));
			}
			if(!StringUtils.isBlank(postItem.getGroupName())){
				update.name(postItem.getGroupName());
			}
			if(!StringUtils.isBlank(postItem.getGroupDescription())){
				update.description(postItem.getGroupDescription());
			}
			if(!StringUtils.isBlank(postItem.getGroupCaption())){
				update.caption(postItem.getGroupCaption());
			}
			if(!StringUtils.isBlank(postItem.getGroupLink())){
				update.link(new URL(postItem.getGroupLink()));
			}
			
			for (String idGroup : postItem.getListGroup()) {
				String idPost= facebook.postFeed(idGroup, update);
				
				//get GroupFace base on idGroup and current Id user
				GroupFace groupFace=groupDbService.getGroupFaceWithidGroupFaceAndClientId(idGroup, postItem.getClientId());
				if(StringUtils.isBlank(idPost)) {
					listMessage.add("Your post to Group: "+groupFace.getNameGroupFace()+" has failed!!!");
				} else {
				Post post= facebook.getPost(idPost);
			
				//Save PostFacebook to db
				PostFacebook postFacebook = new PostFacebook(
					0, //int id
					idPost,//String idPost from Facebook
					idGroup,//String idGroup on Facebook
					StringUtils.isBlank(post.getMessage()) ? null : post.getMessage() ,//String message
					post.getLink() == null ? null : post.getLink().toString(),//String link
					StringUtils.isBlank(post.getName()) ? null : post.getName(), // String name
					StringUtils.isBlank(post.getCaption()) ? null : post.getCaption(), //String caption
					StringUtils.isBlank(post.getDescription()) ? null : post.getDescription(),//String description 
					post.getPicture() == null ? null : post.getPicture().toString(), //String picture
					systemService.convertDateToString(post.getCreatedTime()), //String createdDate
					systemService.convertDateToString(post.getUpdatedTime()), //String updated_date
					postItem.getClientId(),//String from_client_id
					post.getLikes() == null || post.getLikes().isEmpty()  ? 0 : post.getLikes().getCount().intValue(), //int like_count
					post.getType()== null || post.getType().isEmpty() ? null :post.getType().toString(),// String type
					groupFace,//GroupFace idGroupFace
					new ArrayList<CommentFacebook>(),// List<CommentFacebook> listComments
					post.getFullPicture() == null ? null : post.getFullPicture().toString(),//full picture
					post.getSource() == null ? null : post.getSource().toString(),// full source/video
					null,//photo Id
					null);//video Id
				//save postFaceBook to db
				if(!postFacebookService.createPostFacebook(postFacebook)) {
					listMessage.add("Can not create new Post: "+ post.getMessage() +"!!!");
				}
			  }// end else check idPost		
			}
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
			listMessage.add("MalformedURLExceptione: "+e.getMessage());
		}
		catch (FacebookException e) {
			
			e.printStackTrace();
			listMessage.add("FacebookException: "+e.getErrorMessage()+", ErrorCode: "+e.getErrorCode());
		}catch (Exception e) {
			e.printStackTrace();
			listMessage.add("Exception: "+e.getMessage());
		}
		return listMessage;
	}
	
	/**
	 * NOTE: Get Comments of Post
	 * @param MyGroup postItem
	 * @return boolean
	 */
	public List<CommentFacebook>  updateCommentsOfPost(PostFacebook postFacebook) {
		List<CommentFacebook> listComs = null;
		try {
			listComs =new ArrayList<CommentFacebook>();
		for(Comment com: facebook.getPostComments(postFacebook.getIdPost())){
		
			//get comment from db
			CommentFacebook commentFace = commentFacebookService.getCommentFacebookbyIdComment(com.getId());
			//check if in db or not
			if(commentFace == null){
				CommentFacebook comment=new CommentFacebook(
						0,
						com.getId(),
						com.getMessage(), 
						com.getFrom().getId(), 
						com.getLikeCount().intValue(),
						postFacebook, 
						com.getFrom().getName(), 
						systemService.convertDateToString(com.getCreatedTime()),
						com.isUserLikes());
				//create new one
				commentFacebookService.createCommentFacebook(comment);
				listComs.add(comment);
				} else {
					//add old one to list
					commentFace.setLike_count(com.getLikeCount().intValue());
					commentFace.setUserLike(com.isUserLikes());
					commentFace.setDate_create(systemService.convertDateToString(com.getCreatedTime()));
					commentFacebookService.updateCommentFacebook(commentFace);
					listComs.add(commentFace);
				}
		}
		} catch( FacebookException e){
			e.printStackTrace();
			return null;
		}
		return listComs;
	}
	/**
	 * NOTE: Get Notifications of Group and show it in Detail Group Page
	 * @param String idGroup
	 * @param String idUser of current user
	 * @return List<Notification>
	 */
	public List<org.springframework.social.model.Notification> getNotificationsGroup(String idUser){
		ResponseList<Notification> notis=null;
		org.springframework.social.model.Notification notiFace = null;
		List<org.springframework.social.model.Notification> listNotis = null;
		try {
			//Get User base on idUser
			User user = userService.getUserByUserId(idUser);
			//Get Notis from facebook
			notis=facebook.getNotifications(idUser,false);
			if(notis != null && !notis.isEmpty()) {
				//Create new list unread Notifications
				listNotis=new ArrayList<org.springframework.social.model.Notification>();
				for(Notification noti: notis){ 
				//check if targetObject == null => continue
				if (noti.getTargetObject() == null) continue;
				PostFacebook postFace = postFacebookService.getPostFacebookbyPostId(noti.getTargetObject().getId());
				//Check postFace != null => notis relevant to Post
				if( postFace != null && postFace.getIdPost().equals(noti.getTargetObject().getId())) {
					org.springframework.social.model.Notification currentNotis= notificationService.getNotisByNotisId(noti.getId());
				if(currentNotis == null && noti.getTargetObject().getId().equals(postFace.getIdPost())) {
					notiFace = new org.springframework.social.model.Notification(
						0, 
						noti.getId() == null || noti.getId().isEmpty() ? null : noti.getId(), 
						noti.getFrom().getId() == null || noti.getFrom().getId().isEmpty() ? null : noti.getFrom().getId(), 
						noti.getTo().getId() == null || noti.getTo().getId().isEmpty() ? null : noti.getTo().getId(),
						noti.getCreatedTime() == null || noti.getCreatedTime().toString().isEmpty() ? null : systemService.convertDateToString(noti.getCreatedTime()), 
						noti.getUpdatedTime() == null || noti.getUpdatedTime().toString().isEmpty() ? null : systemService.convertDateToString(noti.getUpdatedTime()),
						noti.getTitle() == null || noti.getTitle().isEmpty() ? null : noti.getTitle(), 
						noti.getLink() == null || noti.getLink().toString().isEmpty() ? null : noti.getLink().toString(), 
						noti.getApplication() == null || noti.getApplication().getId().isEmpty() ? null : noti.getApplication().getId(),  
						noti.unread(), 
						postFace == null ? null : postFace , 
						user == null ? null : user,
						false);// isShow => false bcz this is first time
						//Create new and Save to db
						notificationService.createNotification(notiFace);
						if(noti.unread()) {
							notiFace.setUserId(null);
							listNotis.add(notiFace);
						}
				}//end if check currentNotis
				else {// here is currentNotis != null
						if(noti.unread()) {
							currentNotis.setUnread(true);
							currentNotis.setDateUpdatedTIme(systemService.convertDateToString(noti.getUpdatedTime()));
							notificationService.updateNotification(currentNotis);
							listNotis.add(currentNotis);
						}
					}//end else
				}//end if check postFace
			}//end for
		}//end if notis != null
	} catch (FacebookException e) {
		
		e.printStackTrace();
	}
	catch ( Exception e) {
		e.printStackTrace();
	}
	return listNotis;
	}
	
	/**
	 * NOTE: Get Notifications of Group and show it in Detail Group Page
	 * @param String idGroup
	 * @param String idUser of current user
	 * @return List<Notification>
	 */
	public List<org.springframework.social.model.Notification> getNotifications(String idGroup,String idUser){
		ResponseList<Notification> notis=null;
		org.springframework.social.model.Notification notiFace = null;
		List<org.springframework.social.model.Notification> listNotis = new ArrayList<org.springframework.social.model.Notification>();
		try {
			//Get User base on idUser
			User user = userService.getUserByUserId(idUser);
			facebook4j.Group group = facebook.getGroup(idGroup);
			//Get Notis from facebook
			notis=facebook.getNotifications(idUser, new Reading().equals(group));
		} catch (FacebookException e) {
			
			e.printStackTrace();
		}
		return listNotis;
	}
	
	/**
	 * NOTE: Comment to a Post
	 * @param String fromUser Id
	 * @param String postId
	 * @param String message
	 * @return boolean
	 */
	public boolean commentFacebook(String postId, String message) {
		boolean check = false;
		try {
			String commentId = facebook.commentPost(postId, message);
			Comment com  = facebook.getComment(commentId);
			PostFacebook postFacebook = postFacebookService.getPostFacebookbyPostId(postId);
			CommentFacebook comment=new CommentFacebook(
					0,
					com.getId(),
					com.getMessage(), 
					com.getFrom().getId(), 
					com.getLikeCount().intValue(),
					postFacebook, 
					com.getFrom().getName(), 
					systemService.convertDateToString(com.getCreatedTime()), com.isUserLikes());
			//create new one
			if(commentFacebookService.createCommentFacebook(comment))
				check = true;
			else check = false;
		} catch (FacebookException e) {
			
			e.printStackTrace();
			check= false;
		}
		return check;
	}
	
	/**
	 * NOTE: like Comment or Post
	 * @param String postId
	 * @param String commentId
	 * @return boolean
	 */
	public boolean likePostOrComment(String postId, String commentId) {
		boolean check = false;
		
		try {
			if(commentId != null && !commentId.isEmpty()) {
				//like a comment
				facebook.likeComment(commentId);
			}
			else if(postId != null && !postId.isEmpty()) {
				//like a post
				facebook.likePost(postId);
			}
			check = true;
		} catch (FacebookException e) {
			
			e.printStackTrace();
			check=false;
		}
		return check;
	}
	
	/**
	 * NOTE: unlike Comment or Post
	 * @param String postId
	 * @param String commentId
	 * @return boolean
	 */
	public boolean unlikePostOrComment(String postId, String commentId) {
		boolean check = false;
		
		try {
			if(commentId != null && !commentId.isEmpty()) {
				//like a comment
				facebook.unlikeComment(commentId);
			}
			else if(postId != null && !postId.isEmpty()) {
				//like a post
				facebook.unlikePost(postId);
			}
			check = true;
		} catch (FacebookException e) {
			
			e.printStackTrace();
			check=false;
		}
		return check;
	}
	
	/**
	 * NOTE: Get member Groups
	 * @param String groupId
	 * @return boolean
	 */
	public ResponseList<GroupMember> getMembersGroup(String groupFaceId, String clientId) {
		ResponseList<GroupMember> listGroup = null;
		List<MemberGroup> members = new ArrayList<MemberGroup>();
		try {
			GroupFace groupFace = groupDbService.getGroupFaceWithidGroupFaceAndClientId(groupFaceId, clientId);
			listGroup=facebook.groups().getGroupMembers(groupFaceId);
			for( GroupMember mem : listGroup) {
				MemberGroup memberGroup = memberGroupService.getMemberFroupByMemberId(mem.getId());
				if (memberGroup == null) {
					String languages = "";
					String educations = "";
					String interests = "";
					String favorAthletes = "";
					String favorTeams = "";
					String works = "";
					for (IdNameEntity lan : mem.getLanguages()){
						languages = languages + lan.getName() == null ? "" :lan.getName() + ",";
					}
					for (Education edu : mem.getEducation()) {
						educations = educations + edu == null ? "" : edu.toString() + ",";
					}
					for (String inres : mem.getInterestedIn()) {
						interests = interests + inres + ",";
					}
					for ( IdNameEntity favor : mem.getFavoriteAthletes()) {
						favorAthletes = favorAthletes + favor.getName() + ",";
					}
					for ( IdNameEntity favor : mem.getFavoriteTeams()) {
						favorTeams = favorTeams + favor.getName() + ",";
					}
					for ( Work wrk : mem.getWork()) {
						works = works + wrk.getEmployer().getName()+ ",";
					}
					MemberGroup member = new MemberGroup(
							0,
							groupFace.getIdGroupFace(),
							StringUtils.isBlank(mem.getId()) ? null : mem.getId(),
							StringUtils.isBlank(mem.getName()) ? null : mem.getName(), 
							StringUtils.isBlank(mem.getFirstName()) ? null : mem.getFirstName(), 
							StringUtils.isBlank(mem.getMiddleName()) ? null : mem.getMiddleName(), 
							StringUtils.isBlank(mem.getLastName()) ? null : mem.getLastName(), 
							StringUtils.isBlank(mem.getGender()) ? null : mem.getGender(), 
							mem.getLocale() == null ? null : mem.getLocale().getCountry(), 
							languages, 
							mem.getLink() == null? null : mem.getLink().toString(), 
							StringUtils.isBlank(mem.getUsername()) ? null :mem.getUsername(), 
							mem.getTimezone() ==null ? 0.0 : mem.getTimezone().doubleValue(), 
							mem.getUpdatedTime() ==null ? null : mem.getUpdatedTime().toString(), 
							mem.isVerified() == null ? false : mem.isVerified().booleanValue(), 
							StringUtils.isBlank(mem.getBio()) ? null : mem.getBio(), 
							StringUtils.isBlank(mem.getBirthday()) ? null : mem.getBirthday(), 
							educations, 
							StringUtils.isBlank(mem.getEmail()) ? null : mem.getEmail(), 
							mem.getHometown() == null? null : mem.getHometown().getName(), 
							interests, 
							mem.getLocation() ==null ? null : mem.getLocation().getName(), 
							StringUtils.isBlank(mem.getPolitical()) ? null : mem.getPolitical(), 
							favorAthletes, 
							favorTeams, 
							mem.getPicture() ==null? null : mem.getPicture().getURL().toString(), 
							StringUtils.isBlank(mem.getQuotes()) ? null : mem.getQuotes(), 
							StringUtils.isBlank(mem.getRelationshipStatus()) ? null : mem.getRelationshipStatus(),
						    StringUtils.isBlank(mem.getReligion()) ? null : mem.getReligion(), 
							mem.getSignificantOther() ==null ? null : mem.getSignificantOther().getName(),
							mem.getWebsite() ==null ? null :mem.getWebsite().toString(), 
							works,
							groupFace);
					memberGroupService.createMemberGroup(member);
					members.add(member);
				}//end if exist
				else {
					memberGroup.setName(StringUtils.isBlank(mem.getName()) ? null : mem.getName());
					memberGroup.setFirstName(StringUtils.isBlank(mem.getFirstName()) ? null : mem.getFirstName());
					memberGroup.setMiddleName(StringUtils.isBlank(mem.getMiddleName()) ? null : mem.getMiddleName());
					memberGroup.setLastName(StringUtils.isBlank(mem.getLastName()) ? null : mem.getLastName());
					memberGroupService.updateMemberGroup(memberGroup);
					members.add(memberGroup);
				}
				
			}//end for
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		return listGroup;
	}
	
	/**
	 * NOTE: Post Photo To Wall Facebook
	 * @param MyGroup postItem
	 * @param File filePhoto
	 * @return List<String>
	 */
	public List<String> postToWallPhoto(MyGroup postItem, UploadedFile uploadedFile) {
		boolean success=true;
		PhotoUpdate photoUpdate = null;
		List<String> listMessage = new ArrayList<String>();
		try {
			Media media=new Media(uploadedFile.getFile());
			photoUpdate = new PhotoUpdate(media);
			
			if(!StringUtils.isBlank(postItem.getGroupMessage())){
				photoUpdate.message(postItem.getGroupMessage());
			} else {
				listMessage.add("Please input your message!!!");
			}
			
			for (String idGroup : postItem.getListGroup()) {
				String idPost = facebook.postPhoto(idGroup, photoUpdate);
				//get GroupFace base on idGroup and current Id user
				GroupFace groupFace=groupDbService.getGroupFaceWithidGroupFaceAndClientId(idGroup, postItem.getClientId());
				if(StringUtils.isBlank(idPost)) {
					listMessage.add("Your post to Group: "+groupFace.getNameGroupFace()+" has failed!!!");
				} else {
				Post post= facebook.getPost(idPost);

				//Save PostFacebook to db
				PostFacebook postFacebook = new PostFacebook(
					0, //int id
					idPost,//String idPost from Facebook
					idGroup,//String idGroup on Facebook
					StringUtils.isBlank(post.getMessage()) ? post.getName() : post.getMessage() ,//String message
					post.getLink() == null ? null : post.getLink().toString(),//String link
					StringUtils.isBlank(post.getName()) ? null : post.getName(), // String name
					StringUtils.isBlank(post.getCaption()) ? null : post.getCaption(), //String caption
					StringUtils.isBlank(post.getDescription()) ? null : post.getDescription(),//String description 
					post.getPicture() == null ? null : post.getPicture().toString(), //String picture
					systemService.convertDateToString(post.getCreatedTime()), //String createdDate
					systemService.convertDateToString(post.getUpdatedTime()), //String updated_date
					postItem.getClientId(),//String from_client_id
					post.getLikes() == null || post.getLikes().isEmpty()  ? 0 : post.getLikes().getCount().intValue(), //int like_count
					post.getType()== null || post.getType().isEmpty() ? null :post.getType().toString(),// String type
					groupFace,//GroupFace idGroupFace
					new ArrayList<CommentFacebook>(),// List<CommentFacebook> listComments
					post.getFullPicture() == null ? null : post.getFullPicture().toString(),//full picture
					post.getSource() == null ? null : post.getSource().toString(),// full source/video
					idPost,//photo Id
					null);//video Id
					//save File to db
					UploadedFileResponse uploadedFileDb = new UploadedFileResponse(
							0, idPost, uploadedFile.getLength(), uploadedFile.getName(), uploadedFile.getType(), success, uploadedFile.getMessage(), servletContext.getContextPath()+"/resources/files/"+uploadedFile.getName());
						uploadService.createUploadedFileResponse(uploadedFileDb);
				//set File upload to Post
				postFacebook.setUploadedFile(uploadedFileDb);
				postFacebook.setFilePath(servletContext.getContextPath()+"/resources/files/"+uploadedFileDb.getName());
				//save postFaceBook to db
				if(!postFacebookService.createPostFacebook(postFacebook)) success=false;
				
				//reset File
				FileUploadController.setPostFileToNull();
			  }//end else check idPost			
			}
		} catch (FacebookException e) {
			
			e.printStackTrace();
			listMessage.add("FacebookException: "+e.getErrorMessage()+", ErrorCode: "+e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			listMessage.add("Exception: "+e.getMessage());
		}
		return listMessage;
	}
	
	/**
	 * NOTE: Post Video To Wall Facebook
	 * @param MyGroup postItem
	 * @param File filePhoto
	 * @return List<String>
	 */
	public List<String> postToWallVideo(MyGroup postItem, UploadedFile uploadedFile) {
		boolean success=true;
		VideoUpdate photoUpdate = null;
		List<String> listMessage = new ArrayList<String>();
		try {
			Media media=new Media(uploadedFile.getFile());
			photoUpdate = new VideoUpdate(media);
			
			if(!StringUtils.isBlank(postItem.getGroupMessage())){
				photoUpdate.title(postItem.getGroupMessage());
			} else {
				listMessage.add("Please input your message!!!");
			}
			if(!StringUtils.isBlank(postItem.getGroupDescription())){
				photoUpdate.description(postItem.getGroupDescription());
			}
			
			for (String idGroup : postItem.getListGroup()) {
				
				String idPost = facebook.postVideo(idGroup, photoUpdate);
				//get GroupFace base on idGroup and current Id user
				GroupFace groupFace=groupDbService.getGroupFaceWithidGroupFaceAndClientId(idGroup, postItem.getClientId());
				if(StringUtils.isBlank(idPost)) {
					listMessage.add("Your post to Group: "+groupFace.getNameGroupFace()+" has failed!!!");
				} else {
				Post post= facebook.getPost(idPost);
				//Save PostFacebook to db
				PostFacebook postFacebook = new PostFacebook(
					0, //int id
					idPost,//String idPost from Facebook
					idGroup,//String idGroup on Facebook
					StringUtils.isBlank(post.getMessage()) ? null : post.getMessage() ,//String message
					post.getLink() == null ? null : post.getLink().toString(),//String link
					StringUtils.isBlank(post.getName()) ? null : post.getName(), // String name
					StringUtils.isBlank(post.getCaption()) ? null : post.getCaption(), //String caption
					StringUtils.isBlank(post.getDescription()) ? null : post.getDescription(),//String description 
					post.getPicture() == null ? null : post.getPicture().toString(), //String picture
					systemService.convertDateToString(post.getCreatedTime()), //String createdDate
					systemService.convertDateToString(post.getUpdatedTime()), //String updated_date
					postItem.getClientId(),//String from_client_id
					post.getLikes() == null || post.getLikes().isEmpty()  ? 0 : post.getLikes().getCount().intValue(), //int like_count
					post.getType()== null || post.getType().isEmpty() ? null :post.getType().toString(),// String type
					groupFace,//GroupFace idGroupFace
					new ArrayList<CommentFacebook>(),// List<CommentFacebook> listComments
					post.getFullPicture() == null ? null : post.getFullPicture().toString(),//full picture
					post.getSource() == null ? null : post.getSource().toString(),// full source/video
					null,//photo Id
					idPost);//video Id
					//save File to db
				//save File to db
				UploadedFileResponse uploadedFileDb = new UploadedFileResponse(
							0, idPost, uploadedFile.getLength(), uploadedFile.getName(), uploadedFile.getType(), success, uploadedFile.getMessage(), uploadedFile.getPath());
					uploadService.createUploadedFileResponse(uploadedFileDb);
				//set File upload to Post
				postFacebook.setUploadedFile(uploadedFileDb);
				//save postFaceBook to db
				if(!postFacebookService.createPostFacebook(postFacebook)) success=false;
				//reset File
				FileUploadController.setPostFileToNull();
				}//end else check idPost == null			
			}
		} catch (FacebookException e) {
			
			e.printStackTrace();
			listMessage.add("FacebookException: "+e.getErrorMessage()+", ErrorCode: "+e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			listMessage.add("Exception: "+e.getMessage());
		}
		return listMessage;
	}

	/**
	 * NOTE: Post Video To Wall Facebook
	 * @param MyGroup postItem
	 * @param File filePhoto
	 * @return List<String>
	 */
	public ResponseList<GroupDoc> getDocumentsOfGroup(String groupId) {
		ResponseList<GroupDoc> groupDocs = null;
		try {
			groupDocs = facebook.groups().getGroupDocs(groupId);
			
		} catch (FacebookException e) {
			
			e.printStackTrace();
		}
		return groupDocs;
	}
	
	public String commentFacebookReturnCommentId(String postId, String message) {
		String commentId="";
		try {
			commentId = facebook.commentPost(postId, message);
			Comment com  = facebook.getComment(commentId);
			PostFacebook postFacebook = postFacebookService.getPostFacebookbyPostId(postId);
			CommentFacebook comment=new CommentFacebook(
					0,
					com.getId(),
					com.getMessage(), 
					com.getFrom().getId(), 
					com.getLikeCount().intValue(),
					postFacebook, 
					com.getFrom().getName(), 
					systemService.convertDateToString(com.getCreatedTime()), com.isUserLikes());
				//create new one
				if(commentFacebookService.createCommentFacebook(comment)) {
					
				}else{
					commentId="";
				}
			
		} catch (FacebookException e) {
			
			e.printStackTrace();
			commentId="";
		}
		return commentId;
	}
	
	/**
	 * NOTE: Get Notifications of Group and show it in Detail Group Page
	 * @param String idUser of current user
	 * @return List<Notification>
	 */
	public List<org.springframework.social.model.Notification> getNotificationsGroup2(User user){
		org.springframework.social.model.Notification notiFace = null;
		List<org.springframework.social.model.Notification> listNotis = null;
		
		try {
			String url=facebook.getConfiguration().getRestBaseURL()+user.getUserId()+"/notifications?access_token="+user.getAccessToken();
			HttpClientWrapper http = new HttpClientWrapper(facebook.getConfiguration());
			HttpResponse res2 = http.get(url, facebook.getAuthorization());
			 JSONObject json = res2.asJSONObject();
	         JSONArray list;
			list = json.getJSONArray("data");
			 final int size = list.length();
			 List<PostFacebook> listPostFace = postFacebookService.getPostFacebookbyIdClient(user.getUserId());
			 listNotis=new ArrayList<org.springframework.social.model.Notification>();
	         for (int i = 0; i < size; i++) {
	             JSONObject notiObj = list.getJSONObject(i);
	             String targetObjId = "";
	             if(!notiObj.isNull("object")) {
	             JSONObject targetObj = notiObj.getJSONObject("object");
	             targetObjId = targetObj.getString("id");
	             } else {
	            	  continue;
	             }
	             String notiObjId = notiObj.getString("id");
	             String updatedTime = notiObj.getString("updated_time");
	             String createdTime = notiObj.getString("created_time");
	             String title = notiObj.getString("title");
	             String link = notiObj.getString("link");
	             String fromId = notiObj.getJSONObject("from").getString("id");
	             String toId = notiObj.getJSONObject("to").getString("id");
	             String applicationId = notiObj.getJSONObject("application").getString("id");
	             String unread = notiObj.getString("unread");
				//check if targetObject == null => continue
				
				for (PostFacebook postFace : listPostFace) {
				//Check postFace != null => notis relevant to Post
				if( postFace != null && postFace.getIdPost().equals(targetObjId)) {
					org.springframework.social.model.Notification currentNotis= notificationService.getNotisByNotisId(notiObjId);
				if(currentNotis == null && targetObjId.equals(postFace.getIdPost())) {
					notiFace = new org.springframework.social.model.Notification(
							0,
							notiObjId == null || notiObjId.isEmpty() ? null : notiObjId, 
							fromId == null || fromId.isEmpty() ? null : fromId,
							toId== null || toId.isEmpty() ? null : toId,
							createdTime == null ||createdTime.isEmpty() ? null : systemService.convertStringDateFb(createdTime), 
							updatedTime == null || updatedTime.isEmpty() ? null : systemService.convertStringDateFb(updatedTime), 
							title == null || title.isEmpty() ? null : title, 
							link == null || link.isEmpty() ? null : link,
							applicationId, 
							unread.equals("1") ? true : false, 
							postFace == null ? null : postFace ,
							user == null ? null : user,
							false);// isShow => false bcz this is first time
					
							//Create new and Save to db
							notificationService.createNotification(notiFace);
						if(unread.equals("1") ? true : false) {
							notiFace.setUserId(null);
							listNotis.add(notiFace);
						}
				}//end if check currentNotis
				else {// here is currentNotis != null
						if(unread.equals("1") ? true : false) {
							currentNotis.setUnread(true);
							currentNotis.setDateUpdatedTIme(systemService.convertStringDateFb(updatedTime));
							notificationService.updateNotification(currentNotis);
							listNotis.add(currentNotis);
						}
					}//end else
				}//end if check postFace
				}//end for posstFace
			}//end for
	} catch (FacebookException e) {
		
		e.printStackTrace();
	}
	catch ( Exception e) {
		e.printStackTrace();
	}
	return listNotis;
	}
	
	/**
	 * NOTE: Get Files of Group and show it in Detail Group Page
	 * @param String idUser of current user
	 * @param String groupId
	 * @return List<Notification>
	 */
	public List<GroupFiles> getGroupFiles(String idUser, String groupId){
		GroupFiles groupFile = null;
		List<GroupFiles> listFiles = null;
		
		try {
			//Get User base on idUser
			User user = userService.getUserByUserId(idUser);
			String url=facebook.getConfiguration().getRestBaseURL()+groupId+"/files?access_token="+user.getAccessToken();
			HttpClientWrapper http = new HttpClientWrapper(facebook.getConfiguration());
			HttpResponse res2 = http.get(url, facebook.getAuthorization());
			 JSONObject json = res2.asJSONObject();
	         JSONArray list;
	         list = json.getJSONArray("data");
			 final int size = list.length();
			 listFiles=new ArrayList<GroupFiles>();
	         for (int i = 0; i < size; i++) {
	             JSONObject fileObj = list.getJSONObject(i);
	             String fileObjId = fileObj.getString("id");
	             String updatedTime = fileObj.getString("updated_time");
	             String downloadLink = fileObj.getString("download_link");
	             String message = fileObj.getString("message");
	             String fromId = fileObj.getJSONObject("from").getString("id");
	             String name = downloadLink.split("/")[5];
				//check if targetObject == null => continue
				//Check postFace != null => notis relevant to Post
	             groupFile = new GroupFiles(fileObjId, fromId, message, downloadLink, 
	            		 systemService.convertStringDateFb(updatedTime), name);
	             listFiles.add(groupFile);
			}//end for
	} catch (FacebookException e) {
		
		e.printStackTrace();
	}
	catch ( Exception e) {
		e.printStackTrace();
	}
	return listFiles;
	}

	/**
	 * NOTE:list Post
	 * @param User user
	 * @return void
	 */
	public List<PostFacebook>  updatePostFace(String idGroup, User user){
		 List<PostFacebook> returnList = new ArrayList<PostFacebook>();
		try {
			List<PostFacebook> listPost=this.postFacebookService.getPostFacebookbyIdGroup(idGroup,user.getUserId());
			
			if (listPost != null) {
			//List Post from Group Feed
			ResponseList<Post> postFeed = facebook.getFeed(idGroup, new Reading().limit(10));
			for(Post feed : postFeed) {
				for( PostFacebook post : listPost) {
					//update commentFacebook
					updateCommentsOfPost(post);
					if (!post.getIdPost().contains(post.getIdGroup()) ||  post.getIdPost().length() < 20) { ////=> update id for post
					String feedCreatedDate = systemService.convertDateToString(feed.getCreatedTime());
					String postCreatedDate = post.getCreatedDate();
					if (post.getVideoId() != null || post.getPhotoId() != null) {
							if(feedCreatedDate.equals(postCreatedDate)) {
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
						post.setUpdated_date(systemService.convertDateToString(feed.getUpdatedTime())); //String updated_date
						post.setSource(feed.getSource() == null ? null : feed.getSource().toString());
						post.setFullPicture(feed.getFullPicture() == null ? null : feed.getFullPicture().toString());
						post.setLike_count(feed.getLikes().size());
						post.setType(feed.getType());
						post.setShareCount(feed.getSharesCount() == null ? 0 : feed.getSharesCount().intValue());
					}
					postFacebookService.updatePostFacebook(post);//update info
				}//end for PostFacebook
			}//end for feed
		}//end if listPost != null
			List<PostFacebook> listPost2=this.postFacebookService.getPostFacebookbyIdGroup(idGroup,user.getUserId());
			for(int i=listPost2.size()-1;i>=0;i--)
			{
				returnList.add(listPost2.get(i));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return returnList;
	}
}
