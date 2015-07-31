package org.springframework.social.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.json.UploadedFile;
import org.springframework.social.service.FacebookService;
import org.springframework.social.service.UploadedFileResponseService;
import org.springframework.social.user.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Handles requests for the application file upload requests
 * Author: Minh ANh Nguyen
 */
@Controller
public class FileUploadController {

    // Constants ----------------------------------------------------------------------------------

	@Autowired
	ServletContext servletContext;
	@Autowired
	FacebookService facebookService;
	@Autowired
	UploadedFileResponseService uploadService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);
	
	public static  Map<String, UploadedFile> ufile;
	
    // Actions ------------------------------------------------------------------------------------

    public static Map<String, UploadedFile> getUfile() {
		return ufile;
	}
	public static void setUfile(Map<String, UploadedFile> file) {
		ufile = file;
	}
	public static void setPostFileToNull() {
		ufile.put("post",null);
	}
	public static void setScheduleFileToNull() {
		ufile.put("schedule",null);
	}
	public static void setBiggroupFileToNull() {
		ufile.put("biggroup",null);
	}
	/**
     * Initialize the servlet.
     * @see HttpServlet#init().
     */
    public boolean checkFile(String basePath) throws ServletException {
    	boolean check = true;

        // Validate base path.
        if (basePath == null) {
        	check = false;
            throw new ServletException("FileServlet init param 'basePath' is required.");
        } else {
            File path = new File(basePath);
            if (!path.exists()) {
            	check = false;
                throw new ServletException("FileServlet init param 'basePath' value '"
                    + basePath + "' does actually not exist in file system.");
            } else if (!path.isDirectory()) {
            	check = false;
                throw new ServletException("FileServlet init param 'basePath' value '"
                    + basePath + "' is actually not a directory in file system.");
            } else if (!path.canRead()) {
            	check = false;
                throw new ServletException("FileServlet init param 'basePath' value '"
                    + basePath + "' is actually not readable in file system.");
            }
        }
        return check;
    }
	  public FileUploadController(){
	    ufile = new HashMap<String, UploadedFile>();
	  }
	 
	  @RequestMapping(value = "uploadAjax/get/{value}", method = RequestMethod.GET)
	  public void get(HttpServletRequest request, HttpServletResponse response,@PathVariable String value) throws IOException{
	        try {
	        	  //we are using getTimeInMillis to avoid server cached image 
	        	UploadedFile uploadFile = null;
	        	String fileType = request.getParameter("fileType");
 			
 				if (ufile != null) {
	 				if (fileType.equals("biggroup")) {
	 					uploadFile = ufile.get("biggroup");
	 				} else {
	 					if (fileType.equals("post")) {
	 						uploadFile = ufile.get("post");
	 					} else {
	 						if (fileType.equals("schedule")) {
	 							uploadFile = ufile.get("schedule");
	 						}
	 					}
	 				}//end else
 				}//end if map!= null
 				
 				String[] types = uploadFile.getType().split("/");
				String type = types[0];
				if (type.equalsIgnoreCase("image")) {
					 response.setContentType(uploadFile.getType());
			            response.setContentLength(uploadFile.getLength());
			            FileCopyUtils.copy(uploadFile.getBytes(), response.getOutputStream());
				} else {
					if(type.equalsIgnoreCase("video")) {
						 response.setContentType(uploadFile.getType());
					        response.setHeader("Content-Disposition", "attachment; filename="+uploadFile.getName().replace(" ", "_"));
					        response.setContentLength(uploadFile.getLength());
					        InputStream iStream = new FileInputStream(uploadFile.getFile());
					        IOUtils.copy(iStream, response.getOutputStream());
					        response.flushBuffer();
					}
				}
				
				
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	  }
	 
	   @RequestMapping(value = "/uploadAjax", method = RequestMethod.POST)
	   public @ResponseBody UploadedFile upload(HttpServletRequest req, MultipartHttpServletRequest request
			   , HttpServletResponse response, HttpSession session) {                 
		   boolean success = false;
		   String message = "";
		   UploadedFile uploadFile= null;
			if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
				success = false;
				message = "Please Login !!!";
			}
		   try {
	     //0. notice, we have used MultipartHttpServletRequest
	     //1. get the files from the request object
	     Iterator<String> itr =  request.getFileNames();
	     //get Type to determine upload Post, or schedule, biggroup
	     String fileType= req.getParameter("fileType");
	     MultipartFile file = request.getFile(itr.next());
	     System.out.println(file.getOriginalFilename() +" uploaded!");
	     String fileName = "";
	     
	    	 if (!file.isEmpty()) {
	 			
	 				byte[] bytes = file.getBytes();
	 				String oriName = file.getOriginalFilename();
	 				String contentType =  file.getContentType();
	 				int length = file.getBytes().length;
	 				
	 				// Creating the directory to store file
	 				String webappRoot =  servletContext.getRealPath("/");
	 				String relativeFolder = "resources" + File.separator + "files" + File.separator;
	 				String serverRoot = webappRoot + relativeFolder;
	 				 fileName= serverRoot + oriName;
	 				File serverFile = null;
	 				File dir = new File(serverRoot);
	 				if (!dir.exists())
	 					dir.mkdirs();

	 				// Create the file on server
	 				serverFile = new File(fileName);
	 				BufferedOutputStream stream = new BufferedOutputStream(
	 						new FileOutputStream(serverFile));
	 				stream.write(bytes);
	 				stream.close();
	 			
	 		        //2. send it back to the client as <img> that calls get method
	 		        //we are using getTimeInMillis to avoid server cached image 
	 				String[] types = contentType.split("/");
					String type = types[0];
					if (type.contains("image")) {
						 message="<img class=\"img-responsive img-rectangle mypicture\" src='"
					 		        +req.getContextPath()
					 		        +"/uploadAjax/get/"+Calendar.getInstance().getTimeInMillis()+"?fileType="+fileType+"' />";
					} else {
						if(type.contains("video")) {
							message = "<h1>&nbsp;&nbsp;Successful UpLoad!!! You can watch your video after post to Facebook!!!</h1>";
						}
					}
	 		       
	 		        success = true;
	 		   	//Send back to Html Ajax
	 			 uploadFile = new UploadedFile(length, bytes, oriName, contentType, success, message, fileName, serverFile);
	 			    
	 				if (!StringUtils.isBlank(fileType)) {
		 				if (fileType.equals("biggroup")) {
		 					ufile.put("biggroup", uploadFile);
		 				} else {
		 					if (fileType.equals("post")) {
		 						ufile.put("post", uploadFile);
		 					} else {
		 						if (fileType.equals("schedule")) {
		 							ufile.put("schedule", uploadFile);
		 						}
		 					}
		 				}
	 				}//end if file Type is Blank
	 		} else {
	 				success = false;
	 				message = "Please select a file";
	 		}
	     
	     } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        success = false;
				message = "You are getting error!!!";
	    }
	     catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
	    	 success = false;
				message = "You are getting error!!!";
		}
		   uploadFile.setSuccess(success);
		   uploadFile.setMessage(message);
	     return uploadFile;
	 
	  }
	   
	   @RequestMapping(value = "/refreshValuePost", method = RequestMethod.GET)
	   public @ResponseBody boolean refreshValue(HttpServletRequest req, HttpServletResponse response, HttpSession session) {                 
	   boolean success = false;
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			success = false;
		}
		try {
			//reset File
			
			UploadedFile file= FileUploadController.getUfile().get("post");
			if (file == null) {
				success = true;
			}
			else if(file != null && file.checkElement()== true) {
			FileUploadController.setPostFileToNull();
			success = true;
			} 
			
			
	    }
	     catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
	    	 success = false;
		}
	     return success;
	 
	  }
	   
	   @RequestMapping(value = "/refreshValueSchedule", method = RequestMethod.GET)
	   public @ResponseBody boolean refreshValueSchedule(HttpServletRequest req, HttpServletResponse response, HttpSession session) {                 
	   boolean success = false;
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			success = false;
		}
		try {
			//reset File
			
			UploadedFile file= FileUploadController.getUfile().get("schedule");
			if (file == null) {
				success = true;
			}
			else if(file != null && file.checkElement()== true) {
			FileUploadController.setScheduleFileToNull();
			success = true;
			} 
			
			
	    }
	     catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
	    	 success = false;
		}
	     return success;
	 
	  }
	   
	   @RequestMapping(value = "/refreshValueBiggroup", method = RequestMethod.GET)
	   public @ResponseBody boolean refreshValueBiggroup(HttpServletRequest req, HttpServletResponse response, HttpSession session) {                 
	   boolean success = false;
		if (!facebookService.getFacebook().isAuthorized() || session.getAttribute("facebookSession") == null) {
			success = false;
		}
		try {
			//reset File
			
			UploadedFile file= FileUploadController.getUfile().get("biggroup");
			if (file == null) {
				success = true;
			}
			else if(file != null && file.checkElement()== true) {
			FileUploadController.setBiggroupFileToNull();
			success = true;
			} 
			
			
	    }
	     catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
	    	 success = false;
		}
	     return success;
	 
	  }
	@RequestMapping(value = "upload", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String redirectIntruduce(Model model, HttpServletRequest req,
			HttpServletResponse res, HttpSession session) {
		
		return "upload";
	}
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadFileHandler(
			@RequestParam("file") MultipartFile file) {
		String fileName="";
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				
				// Creating the directory to store file
				String webappRoot =  servletContext.getRealPath("/");
				String relativeFolder = "resources" + File.separator + "files" + File.separator;
				String serverRoot = webappRoot + relativeFolder;
				 fileName= serverRoot + file.getOriginalFilename();
				File dir = new File(serverRoot);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(fileName);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				return "You successfully uploaded file=" + fileName;
			} catch (Exception e) {
				return "You failed to upload " + fileName + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + fileName
					+ " because the file was empty.";
		}
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadMultipleFileHandler(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {

		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name
						+ "<br />";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
    @ResponseBody
    @RequestMapping(value = "/save")
    public String handleUpload(
    		@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file,
            HttpServletResponse httpServletResponse,
            HttpServletRequest req) {

        String orgName = file.getOriginalFilename();
        
        String filePath = req.getContextPath()+"/files/" + orgName;
        File dest = new File(filePath);
        try {
        	file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "File uploaded failed:" + orgName;
        } catch (IOException e) {
            e.printStackTrace();
            return "File uploaded failed:" + orgName;
        }
        return "File uploaded:" + orgName;
    }

}

