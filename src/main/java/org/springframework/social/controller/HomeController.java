package org.springframework.social.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.Group;
import org.springframework.social.facebook.api.GroupMembership;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.json.AjaxResponse;
import org.springframework.social.json.NotificationResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.social.model.*;
import org.springframework.social.service.Facebook4JService;
import org.springframework.social.service.FacebookService;
import org.springframework.social.service.GroupFaceService;
import org.springframework.social.service.BigGroupFaceService;
import org.springframework.social.service.PostFacebookService;
import org.springframework.social.service.SystemService;
import org.springframework.social.service.UserService;
import org.springframework.social.user.SecurityContext;
import org.springframework.social.user.SimpleSignInAdapter;

import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.PostUpdate;
import facebook4j.RawAPIResponse;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONObject;

/**
 * Simple little @Controller that invokes Facebook and renders the result. The
 * injected {@link Facebook} reference is configured with the required
 * authorization credentials for the current user behind the scenes.
 * 
 * @author Minh Anh Nguyen & Hiep Xuan Nguyen
 */
@Controller
@SessionAttributes("facebookSession")
public class HomeController {

	
	@Autowired
	PostFacebookService postFacebookService;
	@Autowired
	FacebookService facebookService;
	@Autowired
	BigGroupFaceService bigGroupFaceService;
	@Autowired
	GroupFaceService groupFaceService;
	@Autowired
	Facebook4JService facebook4J;
	@Autowired
	UserService userService;

	/**
	 * NOTE: IN FUTURE THE PAGE WILL USED TO INTRUDUCE ABOUT OUR APP redirect to
	 * intruduce page
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "intruduce", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String redirectIntruduce(Model model, HttpServletRequest req,
			HttpServletResponse res, HttpSession session) {
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			return "redirect:/signin/facebook";
		}
		return "intruduce";
	}

	/**
	 * NOTE: THE PAGE ONLY USER FOR TEST FUNCTION THAT SEARCHER FROM WEBSITE SO
	 * IN FURTURE THE PAGE WILL REMOVED ) redirect to test page
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "test", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String redirectTest(Model model, HttpServletRequest req,
			HttpServletResponse res) {

		model.addAttribute("name", facebookService.getUserProfile()
				.getName());
		model.addAttribute("id", facebookService.getUserProfile()
				.getId());
		return "Test";
	}
	/**
	 * NOTE: THE PAGE ONLY USER FOR TEST FUNCTION THAT SEARCHER FROM WEBSITE SO
	 * IN FURTURE THE PAGE WILL REMOVED ) redirect to login Page
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String redirectLoginPage(HttpServletRequest req) {

		return "signin";
	}
	
	@RequestMapping(value = "/register", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String redirectRegister(HttpServletRequest req,HttpSession session,Model model) {
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			return "redirect:/signin/facebook";
		}
		FacebookProfile facebookProfile=(FacebookProfile) session.getAttribute("facebookSession");
		String clientId=facebookProfile.getId();
		User user=this.userService.getUserByUserId(clientId);
		model.addAttribute("user", user);
		return "register";
	}
	
	@RequestMapping(value = "/applogin", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String redirectAppLogin(HttpServletRequest req,HttpSession session,Model model) {
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			return "redirect:/signin/facebook";
		}
		FacebookProfile facebookProfile=(FacebookProfile) session.getAttribute("facebookSession");
		User user = userService.getUserByUserId(facebookProfile.getId());
		boolean register = false;
		if (user == null || StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword())) {
			register = false;;
		} else {
			register = true;
		}
		model.addAttribute("register", register);
		return "login";
	}
	/**
	 * redirect to authorizationUrl to find accesstoken
	 * 
	 * @param model
	 * @param req
	 * @param group
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String redirectToCalBack(Model model, HttpServletRequest req, HttpServletResponse res) {

		if (!facebookService.getFacebook().isAuthorized()) {
			return "redirect:/signin/facebook";
		}
		
		StringBuffer callbackURL = req.getRequestURL();
		int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index, callbackURL.length(), "").append("/callback&method=POST");
	
		return "redirect:"+facebook4J.getFacebook().getOAuthAuthorizationURL(callbackURL.toString());
	}
	@RequestMapping(value = "/callback", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView redirectCallBack(Model model, HttpServletRequest req, HttpServletResponse res) throws FacebookException {

		if (!facebookService.getFacebook().isAuthorized()) {
			return new ModelAndView("redirect:/signin/facebook");
		}
		ModelAndView modelView = new ModelAndView("redirect:/applogin");
		String code = req.getParameter("code");
		try {
			// Get User Profile
			FacebookProfile facebookProfile = facebookService.getUserProfile();
			modelView.addObject("facebookSession", facebookProfile);
			AccessToken accessToken = facebook4J.getFacebook()
					.getOAuthAccessToken(code);

			facebook4J.setAccessToken(accessToken);
			req.getSession().setAttribute("facebook4JSession", facebook4J);
			// facebook4J.getFacebook().setOAuthAccessToken(accessToken);
			// Check if user in db or not, if not insert to db
			if (!userService.isExistUserId(facebookProfile.getId())) {
				userService.createUser(new User(0, facebookProfile.getId(),
						null, null, accessToken.getToken(),facebookProfile.getName()));
			}
			User user = this.userService.getUserByUserId(facebookProfile
					.getId());
			HttpSession session = req.getSession();
			session.setAttribute("USER", user);
			addAllGroup(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelView;
	}
	
	/**
	 * return to Home Page
	 * 
	 * @param model
	 * @param req
	 * @param group
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/home", method = {RequestMethod.GET,
			RequestMethod.POST })
	public String redirectHome(Model model, HttpServletRequest req,
			@ModelAttribute("group") MyGroup group, HttpServletResponse res,HttpSession session, NativeWebRequest request) throws FacebookException {

		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			return "redirect:/signin/facebook";
		}
		//Get User Profile
		FacebookProfile facebookProfile=(FacebookProfile) session.getAttribute("facebookSession");
		String clientId=facebookProfile.getId();
		
		List<GroupFace> groupFaces=new ArrayList<GroupFace>();
		String id=req.getParameter("id");
		String nameBigGroup = "";
		if(id==null) {
			groupFaces = this.groupFaceService.getAllGroupFaceOfClient(clientId);
		}
		else {
			BigGroupFace bigGroupFace=this.bigGroupFaceService.getBigGroupFace(Integer.parseInt(id));
			nameBigGroup = bigGroupFace.getNameBigGroupFace();
			Set<String> idGroup=new HashSet<String>();
			for (GroupFace g : bigGroupFace.getListGroupFaces()) {
				if (idGroup.add(g.getIdGroupFace())) {
					groupFaces.add(g);
				}
			}
		}
		
		List<BigGroupFace> bigGroupFaces=bigGroupFaceService.getAllBigGroupFaceOfClient(clientId);
		for (BigGroupFace biggroup : bigGroupFaces) {
			UploadedFileResponse file = facebook4J.getUploadService().getFileByFileId(biggroup.getNameBigGroupFace());
			biggroup.setUploadedFile(file);
		}
		model.addAttribute("user_name", facebookProfile.getName());
		model.addAttribute("user_id", facebookProfile.getId());
		model.addAttribute("bigGroupFaces", bigGroupFaces);	
		model.addAttribute("groupFaces", groupFaces);
		model.addAttribute("idBigGroup", id);
		model.addAttribute("nameBigGroup", nameBigGroup);
		return "my_home";
	}
	
	/**
	 * return to Home Page
	 * 
	 * @param model
	 * @param req
	 * @param group
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "yourPost", method = {RequestMethod.GET,
			RequestMethod.POST })
	public String redirectYourPost(Model model, HttpServletRequest req,
			 HttpServletResponse res,HttpSession session, NativeWebRequest request) throws FacebookException {

		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			return "redirect:/signin/facebook";
		}
		//Get User Profile
		FacebookProfile facebookProfile=(FacebookProfile) session.getAttribute("facebookSession");
		String clientId=facebookProfile.getId();
		
		List<GroupFace> groupFaces=new ArrayList<GroupFace>();
		List<BigGroupFace> bigGroupFaces=bigGroupFaceService.getAllBigGroupFaceOfClient(clientId);
		for (BigGroupFace biggroup : bigGroupFaces) {
			UploadedFileResponse file = facebook4J.getUploadService().getFileByFileId(biggroup.getNameBigGroupFace());
			biggroup.setUploadedFile(file);
		}
		
		List<PostFacebook> postFacebookTemps=this.postFacebookService.getPostFacebookbyIdClient(clientId);
		List<PostFacebook> postFacebooks=new ArrayList<PostFacebook>();
		for(int i=postFacebookTemps.size()-1;i>=0;i--)
		{
			postFacebooks.add(postFacebookTemps.get(i));
		}
		
		model.addAttribute("postFacebooks", postFacebooks);
		model.addAttribute("user_name", facebookProfile.getName());
		model.addAttribute("user_id", facebookProfile.getId());
		model.addAttribute("bigGroupFaces", bigGroupFaces);	
		model.addAttribute("groupFaces", groupFaces);

		return "all_post";
	}
	
	@RequestMapping(value = "yourNotifications", method = {RequestMethod.GET,
			RequestMethod.POST })
	public String redirectYourNotifications(Model model, HttpServletRequest req,
			 HttpServletResponse res,HttpSession session, NativeWebRequest request) throws FacebookException {

		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			return "redirect:/signin/facebook";
		}
		//Get User Profile
		FacebookProfile facebookProfile=(FacebookProfile) session.getAttribute("facebookSession");
		String clientId=facebookProfile.getId();
		
		List<GroupFace> groupFaces=new ArrayList<GroupFace>();
		List<BigGroupFace> bigGroupFaces=bigGroupFaceService.getAllBigGroupFaceOfClient(clientId);
		for (BigGroupFace biggroup : bigGroupFaces) {
			UploadedFileResponse file = facebook4J.getUploadService().getFileByFileId(biggroup.getNameBigGroupFace());
			biggroup.setUploadedFile(file);
		}
		User user =  userService.getUserByUserId(facebookProfile.getId());
		List<Notification> notis = user.getListNotis(); 
		List<Notification> notisTemp = new ArrayList<Notification>();
		for(int i=notis.size()-1;i>=0;i--)
		{
			notisTemp.add(notis.get(i));
		}
		
		model.addAttribute("notifications", notisTemp);
		model.addAttribute("user_name", facebookProfile.getName());
		model.addAttribute("user_id", facebookProfile.getId());
		model.addAttribute("bigGroupFaces", bigGroupFaces);	
		model.addAttribute("groupFaces", groupFaces);

		return "all_notifications";
	}
	@RequestMapping(value = "/logout", method = {RequestMethod.GET,
			RequestMethod.POST })
	public String redirectLogout(Model model, HttpServletRequest req,
			HttpServletResponse res,HttpSession session) throws FacebookException {
		String accessToken= null;
		accessToken=facebook4J.getFacebook().getOAuthAccessToken().getToken();
		req.removeAttribute("facebookSession");
		req.removeAttribute("facebook4JSession");
		
		req.getSession().invalidate();
		// Log Out of the Facebook
        StringBuffer next = req.getRequestURL();
        int index = next.lastIndexOf("/");
        //Signout Security will automatic remove current user and cookie in UserInterceptor
        next.replace(index+1, next.length(), "signout" );
       return "redirect:http://www.facebook.com/logout.php?next=" + next.toString()+ "&access_token=" + accessToken;
	}
	
	@RequestMapping(value = "/subRegister", method = {RequestMethod.GET,RequestMethod.POST })
	public String subRegister(Model model, HttpServletRequest req,@ModelAttribute("user") User user, HttpServletResponse res,HttpSession session, NativeWebRequest request){
		String page="";
		String rePassword=req.getParameter("rePassword");
		boolean check=true;
		String msg="";
		if (this.userService.isExistUsename(user.getUserName())) {
			msg += "Username already exist!";
			check = false;
		} else {
			if (StringUtils.isBlank(user.getFirstName())) {
				msg += "First Name not empty, ";
				check = false;
			}
			if (StringUtils.isBlank(user.getLastName())) {
				msg += "Last Name not empty, ";
				check = false;
			}
			if (StringUtils.isBlank(user.getUserName())) {
				msg += "Username not empty, ";
				check = false;
			}
			if (StringUtils.isBlank(user.getPassword())) {
				msg += "Password not empty, ";
				check = false;
			} else if (!user.getPassword().equals(rePassword)) {
				msg += "Password and Confirm Password not same, ";
				check = false;
			}
		}

		if (check) {
			this.userService.updateUserLogin(user);
			page = "login";
		} else {
			page = "register";
			model.addAttribute("msg", msg);
		}

		return page;
	}
	
	@RequestMapping(value = "/subLogin", method = {RequestMethod.GET,RequestMethod.POST })
	public String subLogin(Model model, HttpServletRequest req,HttpSession session){
		
		FacebookProfile facebookProfile=(FacebookProfile) session.getAttribute("facebookSession");
		String clientId=facebookProfile.getId();
		String page="";
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String msg="";
		if (this.userService.isExistUsenameAndPassword(username, password)) {
			if (this.userService.isExistUsenameAndPasswordUserId(username,
					password, clientId)) {
				page = "redirect:/home";
				User user = this.userService.getUserByUserId(facebookProfile
						.getId());
				session.setAttribute("USER", user);
			} else {
				page = "login";
				model.addAttribute("register", false);
				model.addAttribute("msg",
						"Please create a account for this facebook!");
			}
		} else {
			page = "login";
			model.addAttribute("register", false);
			model.addAttribute("msg", "Username or password enter incorrect!");
		}
	
		return page;
	}
	
	public void addAllGroup(User user) {
		try{
			//add all group of user in database
			ResponseList<facebook4j.Group> groups=facebook4J.getFacebook().getGroups();
			if (groups != null) {
				for(facebook4j.Group groupAddDB : groups) {				
					if(!facebook4J.getGroupDbService().isExistIdGroupFace(groupAddDB.getId(),user.getUserId())) {
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
						facebook4J.getGroupDbService().createGroupFace(groupFace);
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

}
