package org.springframework.social.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.http.params.HttpParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.dao.UploadedFileResponseDAO;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.GroupMembership;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.json.AjaxResponse;
import org.springframework.social.json.GroupFiles;
import org.springframework.social.json.NotificationResponse;
import org.springframework.social.json.NotificationsResponse;
import org.springframework.social.json.SearchResponse;
import org.springframework.social.json.UploadedFile;
import org.springframework.social.model.BigGroupFace;
import org.springframework.social.model.CommentFacebook;
import org.springframework.social.model.GroupFace;
import org.springframework.social.model.MemberGroup;
import org.springframework.social.model.MyGroup;
import org.springframework.social.model.Notification;
import org.springframework.social.model.PostFacebook;
import org.springframework.social.model.Schedule;
import org.springframework.social.model.UploadedFileResponse;
import org.springframework.social.model.User;
import org.springframework.social.service.BigGroupFaceService;
import org.springframework.social.service.CommentFacebookService;
import org.springframework.social.service.Facebook4JService;
import org.springframework.social.service.FacebookService;
import org.springframework.social.service.GroupFaceService;
import org.springframework.social.service.MemberGroupService;
import org.springframework.social.service.NotificationService;
import org.springframework.social.service.PostFacebookService;
import org.springframework.social.service.ScheduleService;
import org.springframework.social.service.SystemService;
import org.springframework.social.service.UploadedFileResponseService;
import org.springframework.social.service.UserService;
import org.springframework.social.user.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Group;
import facebook4j.GroupDoc;
import facebook4j.Media;
import facebook4j.PhotoUpdate;
import facebook4j.RawAPIResponse;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import facebook4j.internal.http.HttpParameter;
import facebook4j.internal.org.json.JSONObject;



/**
 * Simple little @Controller that invokes Facebook and renders the result. The
 * injected {@link Facebook} reference is configured with the required
 * authorization credentials for the current user behind the scenes.
 * 
 * @author Minh Anh Nguyen & Hiep Xuan Nguyen
 */
@Controller
public class GroupController {


	SystemService systemService=new SystemService();
	@Autowired
	PostFacebookService postFacebookService;
	@Autowired
	FacebookService facebookService;
	
	//Hiep add 9:04 PM date 20/04/2015
	@Autowired
	BigGroupFaceService bigGroupFaceService;
	
	//Hiep add 9:04 PM date 20/04/2015
	@Autowired
	GroupFaceService groupFaceService;
	
	@Autowired
	CommentFacebookService commentFacebookService;
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	Facebook4JService facebook4J;
	
	@Autowired
	UserService userService;
	
	@Autowired
	NotificationService notisService;
	
	@Autowired
	UploadedFileResponseService uploadService;
	
	@Autowired
	MemberGroupService memberGroupServ;
	/**
	 * redirect to option_group page
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "optionGroup", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String redirectOptionGroup(Model model, HttpServletRequest req,
			HttpServletResponse res, @ModelAttribute("group") MyGroup group, HttpSession session) {

		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			return "redirect:/signin/facebook";
		}

		// Get Friend List
		List<Reference> friends = facebookService.getFacebookFriends();
		

		// Get Groups of user
		List<GroupMembership> groups =facebookService.getFacebookGroups();

		//Get User Profile
		FacebookProfile facebookProfile=(FacebookProfile) session.getAttribute("facebookSession");
		
		
	

		model.addAttribute("user_name", facebookProfile.getName());
		model.addAttribute("user_id", facebookProfile.getId());
		model.addAttribute("groups", groups);
		model.addAttribute("friends", friends);

		return "option_group";
	}
	
	/**
	 * Action for Post to Wall with much group that use Json
	 * 
	 * @param group
	 * @param request
	 * @return
	 * @throws FacebookException 
	 */
	@RequestMapping(value = "/addUserRole", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody AjaxResponse addUserRole(
			 HttpServletRequest request, HttpSession session) throws FacebookException {

		// Define AjaxResponse
		AjaxResponse response = new AjaxResponse();
		String message = "";
		boolean success = false;
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			success = false;
			message = "Please login before Post To Group!!!";
		}
		FacebookProfile fbProfile=(FacebookProfile) session.getAttribute("facebookSession");
		Configuration conf=facebook4J.getFacebook().getConfiguration();
		Map<String, String> params = new HashMap<String, String>();
		params.put("user", "1617179881832546");
		params.put("role", "administrators");
		params.put("access_token", facebook4J.getFacebook().getOAuthAccessToken().getToken());
		params.put("permissions", conf.getOAuthPermissions());
		String url=conf.getRestBaseURL()+conf.getOAuthAppId()+"/roles?user="+ fbProfile.getId()+
				"&role=testers&access_token="+facebook4J.getFacebook().getOAuthAccessToken().getToken();
		RawAPIResponse res =facebook4J.getFacebook().callPostAPI("/"+conf.getOAuthAppId()+"/roles", params);
		//RawAPIResponse res =facebook4J.getFacebook().callPostAPI(url);
		String obj=res.asString();
		if(res.isBoolean()){
		System.out.println(res);
		}
		return response;
	}
	/**
	 * Action for Post to Wall with much group that use Json
	 * 
	 * @param group
	 * @param request
	 * @return
	 * @throws FacebookException 
	 */
	@RequestMapping(value = "/postToWallGroupJson", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody AjaxResponse postToWallGroupJson(
			@ModelAttribute("group") MyGroup group, HttpServletRequest request, HttpSession session) throws FacebookException {

		// Define AjaxResponse
		AjaxResponse response = new AjaxResponse();
		String message = "";
		boolean success = false;
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			success = false;
			message = "Please login before Post To Group!!!";
		}
		FacebookProfile fbProfile=(FacebookProfile) session.getAttribute("facebookSession");
		//Set current user to MyGroup group
		group.setClientId(fbProfile.getId());
		
		User userDB= userService.getUserByUserId(fbProfile.getId());
		facebook4J.setAccessToken(new AccessToken(userDB.getAccessToken()));
		if(StringUtils.isEmpty(group.getGroupMessage().trim())){
			success = false;
			message = "Please input Message";
		}
		if(group.getListGroup() == null || group.getListGroup().length<=0){
			success=false;
			message="Please select at least one group";
		} else{
			List<String> errors = null;
			//Post to Wall Groups Facebook
			UploadedFile file= FileUploadController.getUfile().get("post");
			if (file != null && file.checkElement()== true) {
				String[] contentType = file.getType().split("/");
				String type = contentType[0];
				if (type.contains("image")) {
					errors= facebook4J.postToWallPhoto(group, file);
				} else {
					if(type.contains("video")) {
						errors	= facebook4J.postToWallVideo(group, file);
					} 
				}
			} else {
				//Post to wall and catch exception
				errors = facebook4J.postToWall(group);
			}
			// ==null || empty that mean has no exception
			if (errors == null || errors.isEmpty()) {
				success = true;
				message = "Success to Post to Group!!!";
			} else {
				success = false;
				message = "Your post has failed!!!";
				//Set exception error to ajax
				response.setExceptions(errors);
			}
		}//end else
		response.setMessage(message);
		response.setSuccess(success);
		return response;
	}
	
	
	//Hiep add 9:04 PM date 20/04/2015
	/**
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * @param bigGroupFace
	 * @return
	 */
	@RequestMapping(value = "addBigGroup", method = { RequestMethod.GET,RequestMethod.POST })
	public String redirectAddBigGroup(Model model, HttpServletRequest req,
			HttpServletResponse res, @ModelAttribute("bigGroupFace") BigGroupFace bigGroupFace, HttpSession session) {

		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			return "redirect:/signin/facebook";
		}	
		//Get User Profile
		FacebookProfile facebookProfile=(FacebookProfile) session.getAttribute("facebookSession");
		// Get Groups of user
		List<GroupMembership> groups =facebookService.getFacebookGroups();
		
		//Get bigGroupFace of client.
		String clientId=facebookProfile.getId();
		List<BigGroupFace> bigGroupFaces=bigGroupFaceService.getAllBigGroupFaceOfClient(clientId);
		for (BigGroupFace biggroup : bigGroupFaces) {
			UploadedFileResponse file = facebook4J.getUploadService().getFileByFileId(biggroup.getNameBigGroupFace());
			biggroup.setUploadedFile(file);
		}
		model.addAttribute("user_name", facebookProfile.getName());
		model.addAttribute("user_id", facebookProfile.getId());
		model.addAttribute("bigGroupFaces", bigGroupFaces);
		model.addAttribute("bigGroupFace", new BigGroupFace());
		model.addAttribute("groups", groups);

		return "add_big_group";
	}
	
	/**
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * @param bigGroupFace
	 * @return
	 */
	@RequestMapping(value = "editBigGroup", method = { RequestMethod.GET,RequestMethod.POST })
	public String redirectEditBigGroup(Model model, HttpServletRequest req,
			HttpServletResponse res, @ModelAttribute("bigGroupFace") BigGroupFace bigGroupFace, HttpSession session) {

		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			return "redirect:/signin/facebook";
		}	
		//Get User Profile
		FacebookProfile facebookProfile= (FacebookProfile) session.getAttribute("facebookSession");

		// Get Groups of user
		List<GroupMembership> groups =facebookService.getFacebookGroups();
		
		//Get bigGroupFace of client.
		String idBigGroup = req.getParameter("id");
		BigGroupFace biggroup = bigGroupFaceService.getBigGroupFace(Integer.parseInt(idBigGroup));
		LinkedHashSet<GroupFace> groupFaces = new LinkedHashSet<GroupFace>();
		ArrayList<String> listInBigGroup = new ArrayList<String>();
		for (GroupFace groupFace : biggroup.getListGroupFaces()) {
			groupFace.setEdited(true);
			listInBigGroup.add(groupFace.getIdGroupFace());
		}
		//set List in biggroup
		groupFaces.addAll(biggroup.getListGroupFaces());
		for(GroupMembership groupF : groups) {
			GroupFace groupInDB = groupFaceService.getGroupFaceWithidGroupFaceAndClientId(groupF.getId(), facebookProfile.getId());
			groupInDB.setEdited(false);
			if (!listInBigGroup.contains(groupInDB.getIdGroupFace())) {
				//set list not in biggroup
				groupFaces.add(groupInDB);
			}
		}
		model.addAttribute("user_name", facebookProfile.getName());
		model.addAttribute("user_id", facebookProfile.getId());
		model.addAttribute("bigGroupFace", biggroup);
		model.addAttribute("groups", new ArrayList<GroupFace>(groupFaces));

		return "edit_big_group";
	}
	@RequestMapping(value = "subDeleteBigGroupFace", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody AjaxResponse subDeleteBigGroupFace(Model model, HttpServletRequest req,HttpServletResponse res, HttpSession session) {

		
		AjaxResponse response = new AjaxResponse();
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			response.setSuccess(false);
			response.setMessage("Please Login");
		}
		String biggroupId = req.getParameter("id");
		if (StringUtils.isBlank(biggroupId)) {
			response.setMessage("Please input ID!!!");
			response.setSuccess(false);
		}
		
		boolean check = this.bigGroupFaceService.deleteBigGroupFaceById(Integer.parseInt(biggroupId));
		if (check == true){
			response.setMessage("Successful to Delete Biggroup!!!");
			response.setSuccess(true);
		} else {
			response.setMessage("Fail to Delete Biggroup!!!");
			response.setSuccess(false);
		}
		
		return response;
	}
	
	//Hiep add 9:04 PM date 20/04/2015
	
	/**
	 * create BigGroupFace
	 * @param bigGroupFace
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/postAddBigGroupJson", method = {RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody AjaxResponse postAddBigGroupJson(@ModelAttribute("bigGroupFace") BigGroupFace bigGroupFace,
			HttpServletRequest request, HttpSession session) {
		
		AjaxResponse response = new AjaxResponse();
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			response.setSuccess(false);
			response.setMessage("Please Login");
		}
		if (StringUtils.isBlank(bigGroupFace.getNameBigGroupFace())) {
			response.setMessage("Please input Name of you Big Group!!!");
			response.setSuccess(false);
		}
		FacebookProfile fbProfile=(FacebookProfile) session.getAttribute("facebookSession");
		if(!this.bigGroupFaceService.isExistNameBigGroupFace(bigGroupFace.getNameBigGroupFace())) {
				String[] groupIds = request.getParameterValues("names");
				//check if groupIds < 0
				if( groupIds != null && groupIds.length > 0) {
				List<GroupFace> groupFaces=new ArrayList<GroupFace>();
				for (String groupId:groupIds) {
					GroupFace groupFace=this.groupFaceService.getGroupFaceWithidGroupFaceAndClientId(groupId,fbProfile.getId());
					if (groupFace != null) {
					groupFace.setListBigGroups(null);
					groupFaces.add(groupFace);
					//this.groupFaceService.createGroupFace(groupFace);
					}
				}
				
				bigGroupFace.setClientId(fbProfile.getId());
				bigGroupFace.setListGroupFaces(groupFaces);
				//set UploadedFile
				UploadedFile uploadedFile = FileUploadController.getUfile().get("biggroup");
				UploadedFileResponse uploadFile = null;
				if (uploadedFile != null && uploadedFile.checkElement()== true) {
					String[] contentType = uploadedFile.getType().split("/");
					String type = contentType[0];
					if (type.contains("image")) {
						 uploadFile = new UploadedFileResponse(
								 0,bigGroupFace.getNameBigGroupFace(), uploadedFile.getLength(), uploadedFile.getName(), uploadedFile.getType(),uploadedFile.isSuccess() , uploadedFile.getMessage(), request.getContextPath()+"/resources/files/"+uploadedFile.getName());
						uploadService.createUploadedFileResponse(uploadFile);
					} else {
						response.setSuccess(false);
						response.setMessage("Please Upload a Picture!!!");
						return response;
					}
				} else {
					response.setMessage("Please select one picture!!!");
					response.setSuccess(false);
					return response;
				}
				bigGroupFace.setUploadedFile(uploadFile);
				boolean check=this.bigGroupFaceService.createBigGroupFace(bigGroupFace);
				if(check) {
					FileUploadController.setBiggroupFileToNull();
					response.setSuccess(true);
					bigGroupFace.setListGroupFaces(null);
					response.setBigGroupFace(bigGroupFace);
					response.setMessage("Create Group Successfull!!!");
				} else {
					response.setSuccess(false);
					response.setMessage("Create Group Fail!!!");
				}
			}//end check if groupid <= 0 
			else {
				response.setSuccess(false);
				response.setMessage("Please select at least one group!!!");
			}
		}
		else {
			response.setSuccess(false);
			response.setMessage("Group's Name already exist!!!");
		}
		
		return response;
	}
	
	/**
	 * create BigGroupFace
	 * @param bigGroupFace
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/postEditBigGroupJson", method = {RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody AjaxResponse postEditBigGroupJson(@ModelAttribute("bigGroupFace") BigGroupFace bigGroupFace,
			HttpServletRequest request, HttpSession session) {
		
		AjaxResponse response = new AjaxResponse();
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			response.setSuccess(false);
			response.setMessage("Please Login");
		}
		if (StringUtils.isBlank(bigGroupFace.getNameBigGroupFace())) {
			response.setMessage("Please input Name of your Big Group!!!");
			response.setSuccess(false);
			return response;
		}
		
		// original used to check whether biggroup's name have changed or not
		BigGroupFace bigGroupNeedToSave = bigGroupFaceService.getBigGroupFace(bigGroupFace.getIdBigGroupFace());
		if(this.bigGroupFaceService.isExistNameBigGroupFace(bigGroupFace.getNameBigGroupFace())&&!bigGroupNeedToSave.getNameBigGroupFace().equals(bigGroupFace.getNameBigGroupFace())) {
			response.setMessage("Another BigGroup are using this Name. Please input new one!!!");
			response.setSuccess(false);
			return response;
		}
		FacebookProfile fbProfile=(FacebookProfile) session.getAttribute("facebookSession");
		//Get File of old biggroup before set new name
		UploadedFileResponse uploadFile = uploadService.getFileByFileId(bigGroupNeedToSave.getNameBigGroupFace());
		
				String[] groupIds = request.getParameterValues("names");
				//check if groupIds < 0
				if( groupIds != null && groupIds.length > 0) {
				List<GroupFace> groupFaces=new ArrayList<GroupFace>();
				for (String groupId:groupIds) {
					GroupFace groupFace=this.groupFaceService.getGroupFaceWithidGroupFaceAndClientId(groupId,fbProfile.getId());
					if (groupFace != null) {
					groupFace.setListBigGroups(null);
					groupFaces.add(groupFace);
					//this.groupFaceService.createGroupFace(groupFace);
					}
				}
				//set new list Group to biggroup
				bigGroupNeedToSave.setListGroupFaces(groupFaces);
				//set new Name
				bigGroupNeedToSave.setNameBigGroupFace(bigGroupFace.getNameBigGroupFace());
				//set UploadedFile
				UploadedFile uploadedFile = FileUploadController.getUfile().get("biggroup");
				
				if (uploadedFile != null && uploadedFile.checkElement()== true) {
					String[] contentType = uploadedFile.getType().split("/");
					String type = contentType[0];
					if (type.contains("image")) {
						uploadFile.setIdFile(bigGroupNeedToSave.getNameBigGroupFace());
						uploadFile.setLength(uploadedFile.getLength());
						uploadFile.setName(uploadedFile.getName());
						uploadFile.setType(uploadedFile.getType());
						uploadFile.setSuccess(uploadedFile.isSuccess());
						uploadFile.setPath(request.getContextPath()+"/resources/files/"+uploadedFile.getName());
						uploadService.updateUploadedFileResponse(uploadFile);
					} else {
						response.setSuccess(false);
						response.setMessage("Please Upload a Picture!!!");
						return response;
					}
					bigGroupNeedToSave.setUploadedFile(uploadFile);
				} else {
					uploadFile.setIdFile(bigGroupNeedToSave.getNameBigGroupFace());
					uploadService.updateUploadedFileResponse(uploadFile);
				}
				
				boolean check=this.bigGroupFaceService.updateBigGroupFace(bigGroupNeedToSave);
				if(check) {
					FileUploadController.setBiggroupFileToNull();
					response.setSuccess(true);
					bigGroupFace.setListGroupFaces(null);
					response.setBigGroupFace(bigGroupFace);
					response.setMessage("Edit Group Successfull!!!");
				} else {
					response.setSuccess(false);
					response.setMessage("Edit Group Fail!!!");
				}
			}//end check if groupid <= 0 
			else {
				response.setSuccess(false);
				response.setMessage("Please select at least one group!!!");
			}
		
		return response;
	}
	
	@RequestMapping(value = "/group", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String redirectGroup(Model model, HttpServletRequest req,
			@ModelAttribute("group") MyGroup group, HttpServletResponse res, HttpSession session) {

		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			return "redirect:/signin/facebook";
		}
		
		FacebookProfile facebookProfile= (FacebookProfile) session.getAttribute("facebookSession");
		String clientId=facebookProfile.getId();
		
		String idGroup=req.getParameter("id");
		
		//get all member of group in db
		facebook4J.getMembersGroup(idGroup, clientId);
		
		
		List<MemberGroup> listMembers = memberGroupServ.getListMemberFroupByIdGroup(idGroup);
		List<PostFacebook> postFacebooks= null;
		/*List<PostFacebook> postFacebooks=this.postFacebookService.getPostFacebookbyIdGroup(idGroup,clientId);
		
		for(PostFacebook postFacebook:postFacebooks)
		{
			org.springframework.social.facebook.api.Post post = (this.facebookService.getFacebook().feedOperations().getPost(postFacebook.getIdPost()));
			for(org.springframework.social.facebook.api.Comment com: post.getComments())
			{
				if(!this.commentFacebookService.isExistIdComment(com.getId()))
				{
					FacebookProfile facebookProfileClientId=facebookService.getUserProfileWithClientId(com.getFrom().getId());
					CommentFacebook comF=new CommentFacebook(0, com.getId(), com.getMessage(), com.getFrom().getId(), com.getLikesCount(), postFacebook,facebookProfileClientId.getName(),com.getCreatedTime());
					commentFacebookService.createCommentFacebook(comF);
					
				}
				else
				{
					CommentFacebook comF=commentFacebookService.getCommentFacebookbyIdPost(com.getId());
					comF.setLike_count(com.getLikesCount());
					commentFacebookService.updateLikeCountOfCommentFacebook(comF);
				}
			}
			int likes=post.getLikeCount();
			postFacebook.setLike_count(likes);
			postFacebook.setMessage(post.getMessage());
			this.postFacebookService.updateLikeCountAndMessageOfPostFacebook(postFacebook);
		}*/
		
		List<PostFacebook> postFacebookTemps=this.postFacebookService.getPostFacebookbyIdGroup(idGroup,clientId);
		postFacebooks=new ArrayList<PostFacebook>();
		for(int i=postFacebookTemps.size()-1;i>=0;i--)
		{	PostFacebook post = postFacebookTemps.get(i);
			post.setListComments(facebook4J.updateCommentsOfPost(post));
			postFacebooks.add(post);
		}
		

		List<BigGroupFace> bigGroupFaces=bigGroupFaceService.getAllBigGroupFaceOfClient(clientId);
		for (BigGroupFace biggroup : bigGroupFaces) {
			UploadedFileResponse file = facebook4J.getUploadService().getFileByFileId(biggroup.getNameBigGroupFace());
			biggroup.setUploadedFile(file);
		}
		// Get Groups of user
		List<GroupMembership> groups =facebookService.getFacebookGroups();
		
		String groupName=this.groupFaceService.getGroupFaceWithidGroupFaceAndClientId(idGroup, clientId).getNameGroupFace();
		
		//Get Documents
		ResponseList<GroupDoc> docs = facebook4J.getDocumentsOfGroup(idGroup);
		//Get Files
		List<GroupFiles> files = facebook4J.getGroupFiles(clientId, idGroup);
		
		model.addAttribute("groupName", groupName);
		model.addAttribute("user_name", facebookProfile.getName());
		model.addAttribute("user_id", facebookProfile.getId());
		model.addAttribute("bigGroupFaces", bigGroupFaces);	
		model.addAttribute("groups", groups);
		model.addAttribute("postFacebooks", postFacebooks);
		model.addAttribute("idGroupFace", idGroup);
		model.addAttribute("members", listMembers);
		model.addAttribute("documents", docs);
		model.addAttribute("files", files);
		return "group";

	}
	
	@RequestMapping(value = "/subPostToSingleGroup", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String subPostToSingleGroup(Model model, HttpServletRequest req,
			@ModelAttribute("group") MyGroup group, HttpServletResponse res, HttpSession session) {

		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			return "redirect:/signin/facebook";
		}
		
		facebookService.postToWallGroups(group);
		
		return "redirect:group?id="+group.getListGroup()[0];

	}
	
	/**
	 * create BigGroupFace
	 * @param bigGroupFace
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getPostOfGroupId", method = {RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody AjaxResponse getPostOfGroupId( HttpServletRequest request, HttpSession session) {

		AjaxResponse response = new AjaxResponse();
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			response.setSuccess(false);
			response.setMessage("Please Login");
		}
		try{
		List<org.springframework.social.facebook.api.Post> list=facebookService.getListPostOfGroupId("943562415694364");
		response.setMessage("Get Posts of Group Successfully!!!" );
		response.setSuccess(true);
		response.setListPosts(list);
		response.setBigGroupFace(null);
		
		}catch(Exception e)
		{
			response.setMessage("Get Posts of Group Fail!!!" );
			response.setSuccess(false);
			response.setListPosts(null);
			response.setBigGroupFace(null);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/schedule", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String redirectSchedule(Model model, HttpServletRequest req,
			HttpServletResponse res, @ModelAttribute("schedule") Schedule schedule, HttpSession session) {

		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			return "redirect:/signin/facebook";
		}

		// Get Groups of user
		List<GroupMembership> groups =facebookService.getFacebookGroups();

		//Get User Profile
		FacebookProfile facebookProfile= (FacebookProfile) session.getAttribute("facebookSession");
		
		List<Schedule> schedules=scheduleService.getAllScheduleOfClient(facebookProfile.getId());
		
		List<BigGroupFace> bigGroups=bigGroupFaceService.getAllBigGroupFaceOfClient(facebookProfile.getId());
		for (BigGroupFace biggroup : bigGroups) {
			UploadedFileResponse file = facebook4J.getUploadService().getFileByFileId(biggroup.getNameBigGroupFace());
			biggroup.setUploadedFile(file);
		}
		model.addAttribute("user_name", facebookProfile.getName());
		model.addAttribute("user_id", facebookProfile.getId());
		model.addAttribute("groups", groups);
		model.addAttribute("schedules",schedules);
		model.addAttribute("bigGroups",bigGroups);

		return "add_schedule_group";
	}
	
	
	@RequestMapping(value = "/postCreateScheduleJson", method = {RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody AjaxResponse postCreateScheduleJson(@ModelAttribute("schedule") Schedule schedule
			, HttpServletRequest request, HttpSession session) {
		FacebookProfile facebookProfile= (FacebookProfile) session.getAttribute("facebookSession");
		AjaxResponse response = new AjaxResponse();
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			response.setSuccess(false);
			response.setMessage("Please Login");
		}
		String[] groupIds = request.getParameterValues("names");
		String[] groupIdBigGroup = request.getParameterValues("namesBigGroup");
		String date=request.getParameter("myDate");
		String time=request.getParameter("myTime");
		boolean checkInfo=true;
		String msgCheckInfo="";

		Set<String> groupIdsTemp=new HashSet<String>();
		if(groupIds==null) {
			if(groupIdBigGroup!=null) {
				for(String idBigGroup:groupIdBigGroup) {
					BigGroupFace big=this.bigGroupFaceService.getBigGroupFace(Integer.parseInt(idBigGroup));
					for(GroupFace groupface1:big.getListGroupFaces()) {
						groupIdsTemp.add(groupface1.getIdGroupFace());
					}
				}
			}
		}
		else {
			for(String groupId:groupIds) {
					groupIdsTemp.add(groupId);
			}
			if(groupIdBigGroup!=null) {
				for(String idBigGroup:groupIdBigGroup) {
					BigGroupFace big=this.bigGroupFaceService.getBigGroupFace(Integer.parseInt(idBigGroup));
					for(GroupFace groupface1:big.getListGroupFaces()) {
						groupIdsTemp.add(groupface1.getIdGroupFace());
					}
				}
			}
		}
		
		
		if(schedule.getGroupMessage()==""||schedule.getGroupMessage()==null) {
			checkInfo=false;
			msgCheckInfo="Message not empty";
		}
		else if(date==""||time=="") {
			checkInfo=false;
			msgCheckInfo="Please enter date and time";
			
		}
 else if (groupIds == null && groupIdBigGroup == null) {
			checkInfo = false;
			msgCheckInfo = "Please select least a group";
		}

		if (checkInfo) {
			String datetime = request.getParameter("myDate") + " "
					+ request.getParameter("myTime");
			schedule.setClientId(facebookProfile.getId());
			
			Date systemDate =systemService
					.convertStringToDate(systemService
							.convertDateToString(new Date()));
			//check schedule time
			Date scheduleDate = systemService
					.convertStringToDate(systemService
							.formatStringToDDMMYYYYHHMMSS(datetime));
			if(systemDate.getTime() > scheduleDate.getTime()){
				response.setSuccess(false);
				response.setMessage("You cant scheduleDate less than systemDate!!!");
				return response;
			}
			schedule.setDatePost(systemService
					.formatStringToDDMMYYYYHHMMSS(datetime));
			schedule.setState("Pending");

			if (this.scheduleService.isExistMessageAndDatePost(
					facebookProfile.getId(), schedule.getGroupMessage(),
					schedule.getDatePost())) {
				Schedule scheduleE = this.scheduleService
						.getScheduleOfClientWithMessageAndDatePost(
								facebookProfile.getId(),
								schedule.getGroupMessage(),
								schedule.getDatePost());
				List<GroupFace> listgroupFaceE = scheduleE.getListGroupFaces();
				for (String groupId : groupIdsTemp) {
					boolean checkE = true;
					for (GroupFace groupFaceE : listgroupFaceE) {
						if (groupFaceE.getIdGroupFace().equals(groupId)) {
							checkE = false;
						}
					}
					if (checkE) {
						GroupFace groupFaceE = this.groupFaceService
								.getGroupFaceWithidGroupFaceAndClientId(
										groupId, facebookProfile.getId());
						listgroupFaceE.add(groupFaceE);
					}
				}
				scheduleE.setListGroupFaces(listgroupFaceE);
				boolean check = this.scheduleService.updateSchedule(scheduleE);
				if (check) {
					response.setSuccess(true);
					schedule.setListGroupFaces(null);
					response.setSchedule(schedule);
					response.setMessage("Create Schedule Successfull");
				} else {
					response.setSuccess(false);
					response.setMessage("Create Schedule Fail");
				}
			} else {
				List<GroupFace> listGroupFace = new ArrayList<GroupFace>();
				for (String groupId : groupIdsTemp) {
					GroupFace groupFace = this.groupFaceService
							.getGroupFaceWithidGroupFaceAndClientId(groupId,
									facebookProfile.getId());
					boolean check = true;
					for (GroupFace g : listGroupFace) {
						if (g.getIdGroupFace().equals(groupId)) {
							check = false;
						}
					}
					if (check) {
						listGroupFace.add(groupFace);
					}
				}
				schedule.setListGroupFaces(listGroupFace);
				//Check if schedule has File
				UploadedFile file = FileUploadController.getUfile().get("schedule");
				UploadedFileResponse uploadFile = null;
				if (file != null) {
					if (file != null && file.checkElement()== true) {
						String[] contentType = file.getType().split("/");
						String type = contentType[0];
						if (type.contains("image") || type.contains("video")) {
							 uploadFile = new UploadedFileResponse(
									 0,schedule.getGroupMessage()+"_"+schedule.getDatePost(), file.getLength(), file.getName(), file.getType(),file.isSuccess() , file.getMessage(), file.getPath());
							uploadService.createUploadedFileResponse(uploadFile);
						} else {
							response.setSuccess(false);
							response.setMessage("Please Upload a Picture or Video!!!");
							return response;
						}
					}
					
				}
				//set file to sschedule
				schedule.setUploadedFile(uploadFile);
				boolean check = scheduleService.createSchedule(schedule);
				if (check) {
					FileUploadController.setScheduleFileToNull();
					response.setSuccess(true);
					schedule.setListGroupFaces(null);
					response.setSchedule(schedule);
					response.setMessage("Create Schedule Successfull");
				} else {
					response.setSuccess(false);
					response.setMessage("Create Schedule Fail");
				}
			}
		} else {
			response.setSuccess(false);
			response.setMessage(msgCheckInfo);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/subDeleteSchedule", method = { RequestMethod.GET,RequestMethod.POST })
	public String subDeleteSchedule(Model model, HttpServletRequest req,HttpServletResponse res, HttpSession session) {

		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			return "redirect:/signin/facebook";
		}	
		
		this.scheduleService.deleteScheduleId(Integer.parseInt(req.getParameter("id")));

		return "redirect:schedule";
	}
	
	@RequestMapping(value = "/viewDetailScheduleJson", method = {	RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String viewDetailSchedule(HttpServletRequest request, HttpSession session) {
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			return null;
		}	
		Schedule schedule=this.scheduleService.getScheduleById(Integer.parseInt(request.getParameter("id")));
		String kq = "";
		
		if (schedule == null) {

		} else {
			Set<String> groupCheck = new HashSet<String>();
			kq += "<table class='table table-condensed' border='0' style=' width: 500px;margin: auto;'>";
			for (GroupFace group : schedule.getListGroupFaces()) {
				if (groupCheck.add(group.getIdGroupFace())) {
					kq += "<p>" + group.getNameGroupFace() + "</p>";
				}
			}
			kq += "</table>";
		}
		return kq;

	}

	@RequestMapping(value = "/postToWallGroupJsonInSchedulePage", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody AjaxResponse postToWallGroupJsonInSchedulePage(@ModelAttribute("schedule") Schedule schedule
			, HttpServletRequest request, HttpSession session) throws FacebookException {
		String[] groupIds = request.getParameterValues("names");
		String[] groupIdBigGroup = request.getParameterValues("namesBigGroup");
		
		Set<String> groupIdsTemp = new HashSet<String>();
		if (groupIds == null) {
			if (groupIdBigGroup != null) {
				for (String idBigGroup : groupIdBigGroup) {
					BigGroupFace big = this.bigGroupFaceService
							.getBigGroupFace(Integer.parseInt(idBigGroup));
					for (GroupFace groupface1 : big.getListGroupFaces()) {
						groupIdsTemp.add(groupface1.getIdGroupFace());
					}
				}
			}
		} else {
			for (String groupId : groupIds) {
				groupIdsTemp.add(groupId);
			}
			if (groupIdBigGroup != null) {
				for (String idBigGroup : groupIdBigGroup) {
					BigGroupFace big = this.bigGroupFaceService
							.getBigGroupFace(Integer.parseInt(idBigGroup));
					for (GroupFace groupface1 : big.getListGroupFaces()) {
						groupIdsTemp.add(groupface1.getIdGroupFace());
					}
				}
			}
		}
		String[] temp = new String[groupIdsTemp.size()];
		int i = 0;
		for (String t : groupIdsTemp) {
			temp[i++] = t;
		}
		FacebookProfile fbProfile=(FacebookProfile) session.getAttribute("facebookSession");
		MyGroup group=new MyGroup();
		group.setClientId(fbProfile.getId());
		group.setGroupCaption(schedule.getGroupCaption());
		group.setGroupDescription(schedule.getGroupDescription());
		group.setGroupImage(schedule.getGroupImage());
		group.setGroupLink(schedule.getGroupLink());
		group.setGroupMessage(schedule.getGroupMessage());
		group.setGroupName(schedule.getGroupName());
		group.setListGroup(temp);
		// Define AjaxResponse
		AjaxResponse response = new AjaxResponse();
		String message = "";
		boolean success = false;
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			success = false;
			message = "Please login before Post To Group!!!";
		}
		
		//Post to wall and catch exception
		List<String> errors = facebook4J.postToWall(group);
		// ==null || empty that mean has no exception
		if (errors == null || errors.isEmpty()) {
			success = true;
			message = "Success to Post to Group!!!";
		} else {
			success = false;
			message = "Your post has failed!!!";
			//Set exception error to ajax
			response.setExceptions(errors);
		}
		
		response.setMessage(message);
		response.setSuccess(success);
		return response;
	}
	
	/**
	 * Action for GetNOtifications group that use Json
	 * 
	 * @param request
	 * @param session
	 * @return NotificationsResponse
	 * @throws FacebookException 
	 */
	@RequestMapping(value = "/getNotificationsGroup", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody NotificationsResponse getNotifications(
			 HttpServletRequest request, HttpSession session) throws FacebookException {

		// Define AjaxResponse
		NotificationsResponse response = new NotificationsResponse();
		String message = "";
		boolean success = false;
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			success = false;
			message = "Please login before Post To Group!!!";
		}
		try{
		FacebookProfile fbProfile=(FacebookProfile) session.getAttribute("facebookSession");
		//Set current user to MyGroup group
		//Get Notification
		List<Notification> notis=null;
		notis= facebook4J.getNotificationsGroup(fbProfile.getId());
			if (notis == null || notis.isEmpty() || notis.size() <= 0) {
				success = false;
			} else {
				response.setListNotis(notis);
				success = true;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			success = false;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		response.setMessage(message);
		response.setSuccess(success);
		return response;
	}
	
	/**
	 * Check Notis is showed or not
	 * @param request
	 * @param session
	 * @return NotificationsResponse
	 * @throws FacebookException 
	 */
	@RequestMapping(value = "/checkNotisIsShow", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody NotificationsResponse checkNotisIsShow(
			 HttpServletRequest request, HttpSession session, @RequestParam(value = "notisId") String notisId) throws FacebookException {

		// Define AjaxResponse
		NotificationsResponse response = new NotificationsResponse();
		String message = "";
		boolean success = false;
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			success = false;
			message = "Please login before Post To Group!!!";
		}
		try{
		//Set current user to MyGroup group
		//Get Notification
		String [] list = notisId.split(",");
		
		if(list == null || list.length <= 0){
			success = false;
		}else {
			Notification notification = notisService.getNotisByNotisId(notisId);
			notification.setShow(true);
			notisService.updateNotification(notification);
			success = true;
		  }
		} catch( NullPointerException e){
			e.printStackTrace();
			success = false;
		} catch( Exception e) {
			e.printStackTrace();
			success = false;
		}
		response.setMessage(message);
		response.setSuccess(success);
		return response;
	}
	
	@RequestMapping(value = "/subChangePass", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody AjaxResponse subChangePass(HttpServletRequest request, HttpSession session){
		AjaxResponse ajax=new AjaxResponse();
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			ajax.setMessage("Please input field before edit!!!");
			ajax.setSuccess(false);
			return ajax;
		}
		FacebookProfile facebookProfile=(FacebookProfile) session.getAttribute("facebookSession");
		String clientId=facebookProfile.getId();
		User user=this.userService.getUserByUserId(clientId);
		String oldPassword=request.getParameter("oldPassword");
		String newPassword=request.getParameter("newPassword");
		String reNewPassword=request.getParameter("reNewPassword");
		if (user.getPassword().equals(oldPassword)) {
			if (StringUtils.isBlank(newPassword)) {
				ajax.setMessage("New Password not empty!");
				ajax.setSuccess(false);
			} else if (!newPassword.equals(reNewPassword)) {
				ajax.setMessage("New Password and Confirm New Password not same!");
				ajax.setSuccess(false);
			} else {
				ajax.setMessage("Change Password successful!");
				ajax.setSuccess(true);
				user.setPassword(newPassword);
				this.userService.updateUserLogin(user);
			}
		} else {
			ajax.setMessage("Old Password incorrect!");
			ajax.setSuccess(false);
		}

		return ajax;
	}
	
	@RequestMapping(value = "/subEditProfile", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody AjaxResponse subEditProfile(HttpServletRequest request, HttpSession session) {
		AjaxResponse ajax=new AjaxResponse();
		
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			ajax.setMessage("Please input field before edit!!!");
			ajax.setSuccess(false);
			return ajax;
		}
		FacebookProfile facebookProfile=(FacebookProfile) session.getAttribute("facebookSession");
		String clientId=facebookProfile.getId();
		User user=this.userService.getUserByUserId(clientId);
		
		String lastName=request.getParameter("lastName");
		String firstName=request.getParameter("firstName");
		String userName=request.getParameter("userName");
		//check null String
		if(StringUtils.isBlank(userName) || StringUtils.isBlank(lastName) || StringUtils.isBlank(firstName)) {
			ajax.setMessage("Please input field before edit!!!");
			ajax.setSuccess(false);
			return ajax;
		}
		if (userName.equals(user.getUserName())) {
			user.setLastName(lastName);
			user.setFirstName(firstName);
			this.userService.updateUserLogin(user);
			session.setAttribute("USER", user);
			ajax.setMessage("Edit successful!");
			ajax.setSuccess(true);
		} else if (StringUtils.isBlank(userName)) {
			ajax.setMessage("Please enter Username!");
			ajax.setSuccess(false);
		} else {
			if (this.userService.isExistUsenameWhenEdit(userName)) {
				ajax.setMessage("Username already exist!");
				ajax.setSuccess(false);
			} else {
				user.setLastName(lastName);
				user.setFirstName(firstName);
				user.setUserName(userName);
				this.userService.updateUserLogin(user);
				session.setAttribute("USER", user);
				ajax.setMessage("Edit successful!");
				ajax.setSuccess(true);
			}
		}
		
		return ajax;
	}
	
	@RequestMapping(value = "/subGetNotification", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody NotificationResponse subGetNotification(HttpServletRequest request, HttpSession session){
		// Define SearchResponse
		NotificationResponse response = new NotificationResponse();
		String message = "";
		boolean success = false;
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			success = false;
			message = "Please login before Post To Group!!!";
		}
		FacebookProfile facebookProfile=(FacebookProfile) session.getAttribute("facebookSession");
		String clientId=facebookProfile.getId();
		List<Notification> listNotifi = null;
		try {
			listNotifi = facebook4J.getNotificationsGroup(clientId);			
		//	listNotifi = notisService.getNotisByClientIdAndUnread(clientId, true);
		} catch( Exception e) {
			response.setMessage("Fail "+ e.getMessage());
			response.setSuccess(false);
			return response;
		}
		if(listNotifi != null) {
			for(Notification noti : listNotifi) {
				noti.getId_post().setListComments(null);
				noti.getId_post().setListNotis(null);
				noti.getId_post().setUploadedFile(null);
				noti.getId_post().setIdGroupFace(null);
				noti.setUserId(null);
			}
			response.setNotis(listNotifi);
			message = "Success!!!";
			success = true;
		} else {
			message = "Fail!!!";
			success = false;
		}
		response.setMessage(message);
		response.setSuccess(success);
		return response;
	}
	
	
	@RequestMapping(value = "/detaiAPost", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String redirectDetaiAPost(Model model, HttpServletRequest req,@ModelAttribute("group") MyGroup group, 
			HttpServletResponse res, HttpSession session) {
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			return "redirect:/signin/facebook";
		}
		
		FacebookProfile facebookProfile= (FacebookProfile) session.getAttribute("facebookSession");
		String clientId=facebookProfile.getId();
		
		String id=req.getParameter("id");
		//Get noti from id and set unread->false
		String idNoti =  req.getParameter("idNotis");
		
		if (!StringUtils.isBlank(idNoti)) {
		Notification noti = notisService.getNotification(Integer.parseInt(idNoti));
		noti.setUnread(false);
		try {
			facebook4J.getFacebook().notifications().markNotificationAsRead(noti.getNotificationId());
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//update noti
		notisService.updateNotification(noti);
		}//end if idNoti != null
		String idGroup=req.getParameter("idGroup");
		PostFacebook postFacebook=this.postFacebookService.getPostFacebookWithIdPostAndClientId(Integer.parseInt(id),clientId);
		postFacebook.setListComments(facebook4J.updateCommentsOfPost(postFacebook));
	/*		org.springframework.social.facebook.api.Post post = (this.facebookService.getFacebook().feedOperations().getPost(postFacebook.getIdPost()));
			for(org.springframework.social.facebook.api.Comment com: post.getComments())
			{
				if(!this.commentFacebookService.isExistIdComment(com.getId()))
				{
					FacebookProfile facebookProfileClientId=facebookService.getUserProfileWithClientId(com.getFrom().getId());
					CommentFacebook comF=new CommentFacebook(0, com.getId(), com.getMessage(), com.getFrom().getId(), com.getLikesCount(), postFacebook,facebookProfileClientId.getName(),com.getCreatedTime());
					commentFacebookService.createCommentFacebook(comF);
					
				}
				else
				{
					CommentFacebook comF=commentFacebookService.getCommentFacebookbyIdPost(com.getId());
					comF.setLike_count(com.getLikesCount());
					commentFacebookService.updateLikeCountOfCommentFacebook(comF);
				}
			}
			int likes=post.getLikeCount();
			postFacebook.setLike_count(likes);
			postFacebook.setMessage(post.getMessage());
			this.postFacebookService.updateLikeCountAndMessageOfPostFacebook(postFacebook);*/
		

		List<BigGroupFace> bigGroupFaces=bigGroupFaceService.getAllBigGroupFaceOfClient(clientId);
		for (BigGroupFace biggroup : bigGroupFaces) {
			UploadedFileResponse file = facebook4J.getUploadService().getFileByFileId(biggroup.getNameBigGroupFace());
			biggroup.setUploadedFile(file);
		}
		// Get Groups of user
		List<GroupMembership> groups =facebookService.getFacebookGroups();
		String groupName=this.groupFaceService.getGroupFaceWithidGroupFaceAndClientId(idGroup, clientId).getNameGroupFace();
		
		model.addAttribute("groupName", groupName);
		
		model.addAttribute("user_name", facebookProfile.getName());
		model.addAttribute("user_id", facebookProfile.getId());
		model.addAttribute("bigGroupFaces", bigGroupFaces);	
		model.addAttribute("groups", groups);
		model.addAttribute("postFacebook", postFacebook);
		model.addAttribute("idGroupFace", idGroup);
		return "detail_a_post";

	}
	
	/**
	 * Action for Post to Wall with much group that use Json
	 * 
	 * @param group
	 * @param request
	 * @return
	 * @throws FacebookException 
	 */
	@RequestMapping(value = "/deletePostId", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody AjaxResponse deletePostid(
			 HttpServletRequest request, HttpSession session) throws FacebookException {

		// Define AjaxResponse
		AjaxResponse response = new AjaxResponse();
		String message = "";
		boolean success = false;
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			success = false;
			message = "Please login before Post To Group!!!";
		}
		try {
		FacebookProfile fbProfile=(FacebookProfile) session.getAttribute("facebookSession");
		
		String idPost = (String) request.getParameter("idPost");
		if (StringUtils.isBlank(idPost)) {
			response.setMessage("You're getting error when deleting Post");
			response.setSuccess(false);
			return response;
		}
		User userDB= userService.getUserByUserId(fbProfile.getId());
		facebook4J.setAccessToken(new AccessToken(userDB.getAccessToken()));
		
		//boolean deleteSuccess = facebook4J.getFacebook().deletePost(idPost);
			PostFacebook post = postFacebookService.getPostFacebookbyPostId(idPost);
			if (post != null) {
				boolean check = postFacebookService.deletePostFacebook(post);
				success = check;
				message = "You successfully delete Post >"+post.getMessage()+"<"; 
			} else {
				success = false;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			message = e.getMessage();
		}
		response.setMessage(message);
		response.setSuccess(success);
		return response;
	}	
	
	@RequestMapping(value = "/subSearchGroup", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody SearchResponse subSearchGroups(HttpServletRequest request, HttpSession session){
		// Define SearchResponse
		SearchResponse response = new SearchResponse();
		String message = "";
		boolean success = false;
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			success = false;
			message = "Please login before Post To Group!!!";
		}
		String searchInfo = request.getParameter("searchInfo");
		if (!StringUtils.isBlank(searchInfo)) {
		List<Group> listGroups = null;
		try {
			listGroups = this.facebook4J.getFacebook().searchGroups(searchInfo);
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage("You have errors with searching Groups!!!");
			response.setSuccess(false);
			return response;
		}
		List<GroupFace> groups = new ArrayList<GroupFace>();
		if(listGroups!=null) {
			for(Group n:listGroups) {
				GroupFace groupFace=new GroupFace();
				groupFace.setNameGroupFace(n.getName());
				groupFace.setIdGroupFace(n.getId());
				groupFace.setUpdatedTime(n.getUpdatedTime() == null ? null :n.getUpdatedTime().toString());
				groupFace.setDescription(n.getDescription() == null ? null : n.getDescription());
				groups.add(groupFace);
			}
			success = true;
			response.setGroups(groups);
		}
		}//end if null
		else {
			message = "Please input search query!!!";
			success = false;
		}
		response.setSuccess(success);
		response.setMessage(message);
		return response;
	}
	
	@RequestMapping(value = "/like", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody boolean like(HttpServletRequest request, HttpSession session){
		boolean success = false;
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			success = false;
		}
		try {
			String postId = request.getParameter("postId");
			String commentId = request.getParameter("commentId");
			
			if (!StringUtils.isBlank(postId)) {
				success = facebook4J.likePostOrComment(postId, null);
			} else {
				success = facebook4J.likePostOrComment(null, commentId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}
	
	@RequestMapping(value = "/unlike", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody boolean unlike(HttpServletRequest request, HttpSession session){
		boolean success = false;
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			success = false;
		}
		try {
			String postId = request.getParameter("postId");
			String commentId = request.getParameter("commentId");
			
			if (!StringUtils.isBlank(postId)) {
				success = facebook4J.unlikePostOrComment(postId, null);
			} else {
				success = facebook4J.unlikePostOrComment(null, commentId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}
	
	@RequestMapping(value = "/comment", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody AjaxResponse comment(HttpServletRequest request, HttpSession session){
		AjaxResponse ajax=new AjaxResponse();
		ajax.setSuccess(false);
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			ajax.setSuccess(false);
		}
		try {
			String postId = request.getParameter("postId");
			String message = request.getParameter("message");
			FacebookProfile fbProfile=(FacebookProfile) session.getAttribute("facebookSession");
			//Set current user to MyGroup group
			User userDB= userService.getUserByUserId(fbProfile.getId());
			facebook4J.setAccessToken(new AccessToken(userDB.getAccessToken()));
			if (!StringUtils.isBlank(postId) && !StringUtils.isBlank(message)) {
				String id_Comment = facebook4J.commentFacebookReturnCommentId(postId, message);
				CommentFacebook c=commentFacebookService.getCommentFacebookbyIdComment(id_Comment);
				if(c!=null) {
					c.setId_post(null);
					ajax.setCommentFacebook(c);
					ajax.setSuccess(true);
				}
				else {
					ajax.setSuccess(false);
				}
				
			} else {
				ajax.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setSuccess(false);
		}
		return ajax;
	}
}
