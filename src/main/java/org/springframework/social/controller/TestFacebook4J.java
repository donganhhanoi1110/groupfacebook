package org.springframework.social.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Media;
import facebook4j.PagableList;
import facebook4j.PhotoUpdate;
import facebook4j.Post;
import facebook4j.RawAPIResponse;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.VideoUpdate;
import facebook4j.auth.AccessToken;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import facebook4j.internal.org.json.JSONObject;

public class TestFacebook4J {
	static Facebook facebook;
	public TestFacebook4J() {
		// TODO Auto-generated constructor stub
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthAppId("1438460799780610")
		  .setOAuthAppSecret("6993efa92ea971fb36e52c41fb316798")
		  .setOAuthAccessToken("1438460799780610|xdpOmnGZNAmAyJH2N59miDYWbso")
		  .setOAuthPermissions("email,publish_actions,public_profile, basic_info,  user_likes, "
		  		+ "manage_pages,user_posts,user_groups, user_events, user_photos, user_friends, user_about_me,read_stream");
		FacebookFactory ff = new FacebookFactory(cb.build());
		// Generate facebook instance.
		 this.facebook = ff.getInstance();
	}
/**
 * A simple Facebook4J client which
 * illustrates how to access group feeds / posts / comments.
 * 
 * @param args
 * @throws FacebookException 
 */
public static void main(String[] args) throws FacebookException {

	
    
  
    // Use default values for oauth app id.
  //  facebook.setOAuthAppId("", "");
    // Get an access token from: 
    // https://developers.facebook.com/tools/explorer
    // Copy and paste it below.
//    String accessTokenString = "PASTE_YOUR_ACCESS_TOKEN_HERE";
//    AccessToken at = new AccessToken(accessTokenString);
//    // Set access token.
//    facebook.setOAuthAccessToken(at);

    // We're done.
    // Access group feeds.
    // You can get the group ID from:
    // https://developers.facebook.com/tools/explorer

    // Set limit to 25 feeds.
    ResponseList<Post> feeds = facebook.getFeed("943562415694364",
            new Reading().limit(1));
    System.out.println(feeds.get(0).getMessage());
        // For all 25 feeds...
//        for (int i = 0; i < feeds.size(); i++) {
//            // Get post.
//            Post post = feeds.get(i);
//            // Get (string) message.
//            String message = post.getMessage();
//                            // Print out the message.
//            System.out.println(message);
//
//            // Get more stuff...
//            PagableList<Comment> comments = post.getComments();
//            String date = post.getCreatedTime().toString();
//            String name = post.getFrom().getName();
//            String id = post.getId();
//        }           
    }
private AccessToken refreshToken(Facebook facebook, AccessToken currentToken) throws Exception {
    String clientId = "1438460799780610";
    String clientSecret = "6993efa92ea971fb36e52c41fb316798";
    	
    Map<String, String> params = new HashMap<String, String>();
    params.put("client_id", clientId);
    params.put("client_secret", clientSecret);
    params.put("grant_type", "fb_exchange_token");
    params.put("fb_exchange_token", currentToken.getToken());

    RawAPIResponse apiResponse = facebook.callGetAPI("/oauth/access_token", params);

    String response = apiResponse.asString();
    AccessToken newAccessToken = new AccessToken(response);

    facebook.setOAuthAccessToken(newAccessToken);

    return newAccessToken;
}
	public void postVideoTest(ServletContext servletContext, String clientId, String groupId){
		try{
			String fileDirec = servletContext.getRealPath("/")
					+ File.separator + "resources" 
					+ File.separator + "files" + File.separator
					+"image.jpg";
			String videoDirec = servletContext.getRealPath("/")
					+ File.separator + "resources" 
					+ File.separator + "files" + File.separator
					+"video.mp4";
			
			Media media=new Media(new File(fileDirec));
			PhotoUpdate post= new PhotoUpdate(media)
			.message("Upload Picture")
			;
			facebook.postPhoto(groupId, post);
			facebook.postVideo(clientId, new VideoUpdate(new Media(new File(videoDirec))));
			facebook.postVideo(groupId, new VideoUpdate(new Media(new File(videoDirec))));
			}catch(Exception e){
				e.printStackTrace();
			}
		
	}

	public void addRole(String clientId) throws FacebookException{
		Configuration conf=facebook.getConfiguration();
		Map<String, String> params = new HashMap<String, String>();
		params.put("user",clientId);
		params.put("role", "testers");
		params.put("access_token", facebook.getOAuthAccessToken().getToken());
		String url=conf.getRestBaseURL()+conf.getOAuthAppId()+"/roles?user="+ clientId+
				"&role=testers&access_token="+facebook.getOAuthAccessToken().getToken();
		//RawAPIResponse res =facebook4J.getFacebook().callPostAPI(conf.getRestBaseURL()+conf.getOAuthAppId()+"/roles", params);
		RawAPIResponse res = null;
		try {
			res = facebook.callPostAPI(url);
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj=res.asJSONObject();
		if(res.isBoolean()){
		System.out.println(res);
		}
	}
}

