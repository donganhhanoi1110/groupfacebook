package org.springframework.social.test;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.json.UploadedFile;
import org.springframework.social.model.MyGroup;
import org.springframework.social.model.PostFacebook;
import org.springframework.social.model.User;
import org.springframework.social.service.Facebook4JService;
import org.springframework.social.service.PostFacebookService;
import org.springframework.social.service.UserService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class PostTest {

	@Autowired
	Facebook4JService facebookServ;
	@Autowired
	UserService userServ;
	@Autowired
	PostFacebookService postFaceServ;
	
	@Test
	public void postToWallGroup() {
		boolean check = false;
		String[] groupId = {"943562415694364","1642294075990555"};
		MyGroup myGroup = new MyGroup("Hello World", "https://google.com", null,
				null, null, null, "445983705552033",groupId);

		List<String> errors = facebookServ.postToWall(myGroup);
		if (errors == null) {
			check = true;
		} else {
			check = false;
		}
		Assert.assertEquals(true, check);
	}

	@Test
	public void postToWallGroupPhoto() {
		boolean check = false;
		String filePath = "";
		File file = new File(filePath);
		UploadedFile uploadedFile = new UploadedFile((int)(file.length()), null, file.getName(), "photo", true, "", filePath, file);
		String[] groupId = {"943562415694364","1642294075990555"};
		MyGroup myGroup = new MyGroup("Post Photo", null, null,
				null, null, null, "445983705552033",groupId);

		List<String> errors = facebookServ.postToWallPhoto(myGroup, uploadedFile);
		if (errors == null) {
			check = true;
		} else {
			check = false;
		}
		Assert.assertEquals(true, check);
	}
	
	@Test
	public void checkMessage() {
		List<PostFacebook> posts = postFaceServ.getAllPostFacebook();
		for (PostFacebook post : posts) {
			Assert.assertEquals("Hello World", post.getMessage());
			Assert.assertEquals("Post Photo", post.getMessage());
		}
	}

}
