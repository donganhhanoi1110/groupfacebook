<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Detail A Post</title>

<!--Import CSS  -->
<link rel="stylesheet" href="resources/css/boostrap/bootstrap.min.css">
<link rel="stylesheet"
	href="resources/css/boostrap/bootstrap-theme.min.css">
<link rel="stylesheet" href="resources/css/home.css">
<link rel="stylesheet" href="resources/css/nagative_left.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/normalize.css" />
<!-- 		<link rel="stylesheet" type="text/css" href="css/demo.css" /> -->
<link rel="stylesheet" type="text/css" href="resources/css/icons.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/component.css" />
<link rel="stylesheet" href="resources/css/jquery.dataTables.css"
	type="text/css" />
<link rel="stylesheet" href="resources/css/jquery-ui-min.css"
	type="text/css" />

<!--Import Jquery  -->
<script src="resources/js/jquery/jquery-ui.js"></script>
<script src="resources/js/modernizr.custom.js"></script>
<script src="resources/js/jquery/jquery-1.10.2.min.js"></script>
<script src="resources/js/jquery/jquery.dataTables.js"
	type="text/javascript"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resources/js/ajaxjson/postToWallGroup.js"></script>
<!-- <script  type="text/javascript" -->
<!-- 	src="resources/js/ajaxjson/getPostOfGroupId.js"></script> -->
<script  type="text/javascript"
	src="resources/js/ajaxjson/deletePostId.js"></script>
<script  type="text/javascript"
	src="resources/js/ajaxjson/likeOrUnlikeOrComment.js"></script>
<style type="text/css">
#menu_main {
	background-color: #3662B9;
	color: white;
	padding: 0px 100px;
	overflow: hidden;
}

.badge {
	background-color: red;
	color: white;
}

.notify_new {
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	background-color: red;
	color: white;
	padding: 2px 4px;
}

.placeholder {
	min-height: 300px;
}

.fb-send {
	margin: 15px 15px;
}

.my_show {
	display: none;
}

#negative_left {
	float: left;
	width: 24.4%;
	min-height: 500px;
}

#negative_right {
	float: right;
	width: 20%;
	min-height: 500px;
}

#content {
	padding: 0px 0px;
	float: left;
	width: 55.4%;
	/* 	border-left: .1em solid gray; */
	/* 	border-right: .1em solid gray; */
	min-height: 500px;
}

.sidebar_right {
	width: 20%;
	position: fixed;
	top: 55px;
	bottom: 0;
	right: 15px;
	z-index: 1000;
	display: block;
	padding-left: 20px;
	overflow-x: hidden;
	overflow-y: auto;
	background-color: #f5f5f5;
	border-right: 1px solid #eee;
}

.sidebar {
	padding-top: 0px;
	padding-left: 20px;
	width: 24%;
}

#cover_status_group {
	margin: 20px 16px;
}

.my_show {
	display: none;
}
</style>
<script>
	$(document).ready(function() {
		$(".showme").click(function() {
			$(".my_show").slideToggle();
		});
	});
</script>
</head>
<body>
	<jsp:include page="include/effect_header.jsp" />
	<div id="cover_all">
		<jsp:include page="include/menu_main.jsp" />
		<div id="cover_content">
			<div id="negative_left">
				<div class="col-sm-3 col-md-2 sidebar">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title" style="color: black;">
								<b>FAVORITE GROUPS</b>
							</h3>
						</div>
						<div class="panel-body" style="min-height: 500px;">
							<ul class="nav nav-sidebar">
								<c:forEach items="${bigGroupFaces}" var="bigGroupFace">
									<li><a
										href="<%=request.getContextPath() %>/home?id=<c:out value='${bigGroupFace.idBigGroupFace}'/>"><img
											src="${bigGroupFace.uploadedFile.path}" width="50px" height="50px"
											align="middle" /> <c:out
												value='${bigGroupFace.nameBigGroupFace}' /></a></li>

								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- end_negative_left -->
			<div id="content">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title" style="color: black;">
							<b>${groupName }</b>
						</h3>
					</div>
					<div class="panel-body" style="min-height: 600px;">
						<form:form id="formPostToGroup" action="subPostToSingleGroup"
							modelAttribute="group">
							<div class="col-lg-12">
								<div class="panel panel-info">
									<div class="panel-body">

										<div class="cover_post_status">
											<div class="post_status_top">
												<ul>
													<li><span class="glyphicon glyphicon-edit"
														aria-hidden="true"></span> Update Status</li>
													<li><span class="glyphicon glyphicon-film"
														aria-hidden="true"></span> <a style="color: black;" href="#" data-toggle="modal" data-target="#uploadFile">
														Add Photo/Video</a></li>
													<li><span class="glyphicon glyphicon-refresh"></span><a style="color: black;" href="#" onclick="refreshValuePost('')" 
														aria-hidden="true"> Refresh Value</a></li>


												</ul>
											</div>
											<div class="post_status_center">

												<div class="form-group" style="margin: 0px">
													<form:textarea path="groupMessage"
														style="border: .1em solid white;" class="groupMessage form-control"
														rows="3" />
												</div>

											</div>
											<div class="post_status_bottom">
												<div class="menu_float_left">
													<ul class="nav nav-pills">
														<li role="presentation" class="showme"><a
															href="#"><span class="glyphicon glyphicon-chevron-down"></span></a></li>
														<li><img src="resources/images/loading4.gif" alt="Loading" class="loading loadingGif" /></li>
													</ul>
												</div>
												<div class="menu_float_right">

													<ul class="nav nav-pills">
														<!-- <li class="dropdown"><a href="#"
															class="dropdown-toggle" data-toggle="dropdown"
															role="button" aria-expanded="false">Only Me <span
																class="caret"></span>
														</a>
															<ul class="dropdown-menu" role="menu">
																<li><a href="#">Action</a></li>
																<li><a href="#">Another action</a></li>
																<li><a href="#">Something else here</a></li>
																<li class="divider"></li>
																<li><a href="#">Separated link</a></li>
																<li class="divider"></li>
																<li><a href="#">One more separated link</a></li>
															</ul></li> -->
														<li role="presentation" class="active"><a href="#"
															id="postToWallGroup"><span class="glyphicon glyphicon-send"
														aria-hidden="true"></span> Post</a></li>
													</ul>
												</div>
											</div>
										</div>
									</div>
									<div class="col-lg-12">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
										<div class="my_show">
											<div class="form-group has-success">
												<form:input path="groupLink" type="text" placeholder="Link"
													class="groupLink form-control" />
											</div>
											<div class="form-group has-warning">
												<form:input path="groupName" type="text" placeholder="Name"
													class="groupName form-control" />
											</div>
											<div class="form-group has-error">
												<form:input path="groupCaption" type="text"
													placeholder="Caption" class="groupCaption form-control" />
											</div>
											<div class="form-group has-error">
												<form:input path="groupDescription"
													placeholder="Description" type="text" class="groupDescription form-control" />
											</div>
										</div>

									</div>
									<!-- end_cover_status -->
									<div style="display: none;">
										<form:checkbox checked="checked" class="checkbox1"
											path="listGroup" value="${idGroupFace}" />
									</div>
								</div>
							</div>
						</form:form>
						<div id="cover_status_group">
							<div class="col-lg-12" style="padding: 0px;">
								<!-- ID Group for Ajax automatic getting  -->
								<input id="idGroup" type="hidden" value="${idGroup}" />
								<div class="panel panel-info">
									<div class="panel-body">
										<div class="col-lg-12"
											style="padding: 0px 0px 0px 0px; background-color: white; margin: 0px 0px 0px 0px;">
											<div class="col-lg-1">
												<img
													src="http://graph.facebook.com/<c:out value="${user_id}"/>/picture"
													align="middle" width="50px" />

											</div>

											<div class="col-lg-11">
												<h4 style="color: #6281BF; margin: 0px; padding: 0px;">${user_name}</h4>
												${postFacebook.createdDate }
													<a href="${postFacebook.link }"
														target="target">Go to Facebook</a>
											</div>
											<div class="col-lg-12" style="margin: 10px 0px;">
										<a style="color: black;" href="detaiAPost?id=${postFacebook.id}&idGroup=${postFacebook.idGroup}">
											<c:if test="${empty postFacebook.photoId &&  empty postFacebook.videoId  }">
												${postFacebook.message }<br />
											</c:if>
											<c:if test="${not empty postFacebook.photoId }">
												${postFacebook.message }<br />
												<img src="${postFacebook.filePath }"  class="mypicture"/>
											</c:if>
											<c:if test="${not empty postFacebook.videoId }">
											${postFacebook.name} <br />
												<video width="400" controls>
												  <source src="${postFacebook.source }" type="video/mp4">
											</video>
											</c:if>
										</a>
											</div>
											<div class="col-lg-12" style="padding-bottom: 10px;">
												<c:if test="${postFacebook.userLike == true }">
												<a href="#" onclick="like('${postFacebook.idPost}','')" class="myHide postLike_${postFacebook.idPost}">Like</a> 
												<a href="#" onclick="unlike('${postFacebook.idPost}','')" class="postUnlike_${postFacebook.idPost}">Unlike</a>
												</c:if>
												<c:if test="${postFacebook.userLike == false }">
												<a href="#" onclick="like('${postFacebook.idPost}','')" class="postLike_${postFacebook.idPost}">Like</a> 
												<a href="#" onclick="unlike('${postFacebook.idPost}','')" class="myHide postUnlike_${postFacebook.idPost}">Unlike</a>
												</c:if>
												<span class="likeCount_${postFacebook.idPost}">${postFacebook.like_count }</span>
												  <a href="#">Comment</a>
												${postFacebook.listComments.size() }
												<a href="#">Shared</a>
												${postFacebook.shareCount }
												<a href="#"><span
													class="glyphicon glyphicon-hand-right"></span></a>
											</div>
										</div>
										<div class="col-lg-12"
											style="padding: 5px 0px 5px 0px; background-color: #FBFBEF; margin: 0px 0px 0px 0px; border-top: 1px solid #F2F2F2;">
											<div class="col-lg-12" style="color: #6281BF;">
												<span class="glyphicon glyphicon-chevron-down"></span>
											</div>
											<!-- begin while for comment of post-->
											<c:forEach items="${postFacebook.listComments}"
												var="commentFacebook">

												<div class="col-lg-1"
													style="padding-right: 0px; margin-top: 10px;">
													<img
														src="http://graph.facebook.com/<c:out value="${commentFacebook.from_client_id}"/>/picture"
														align="middle" width="35px" />
												</div>
												<div class="col-lg-11"
													style="padding-left: 0px; margin-top: 10px;">
													<div class="col-lg-12" style="padding-left: 0px;">
														<span style="color: #6281BF;">${commentFacebook.name_client}</span>
														${commentFacebook.message }
													</div>
													<div class="col-lg-12" style="padding-left: 0px;">
														<span style="color: #BDBDBD">${commentFacebook.date_create }</span>
															<c:if test="${commentFacebook.userLike == true }">
															<a href="#" onclick="like('',${commentFacebook.id_comment})" class="myHide commentLike_${commentFacebook.id_comment}">Like</a> 
															<a href="#" onclick="unlike('',${commentFacebook.id_comment})" class="commentUnlike_${commentFacebook.id_comment}">Unlike</a>
														</c:if>
														<c:if test="${commentFacebook.userLike == false }">
															<a href="#" onclick="like('',${commentFacebook.id_comment})" class="commentLike_${commentFacebook.id_comment}">Like</a> 
															<a href="#" onclick="unlike('',${commentFacebook.id_comment})" class="myHide commentUnlike_${commentFacebook.id_comment}">Unlike</a>
														</c:if>
														<span class="likeCount_${commentFacebook.id_comment}">${commentFacebook.like_count }</span>
													</div>
												</div>
											</c:forEach>
											<!-- end while for comment of post-->
											<span id="placeholder_${postFacebook.idPost}"></span>
											<div class="col-lg-12" style="padding-left: 0px;">
												<div class="col-lg-1"
													style="padding-right: 0px; margin-top: 10px;">
													<img
														src="http://graph.facebook.com/<c:out value="${user_id}"/>/picture"
														align="middle" width="35px" />

												</div>
												<div class="col-lg-11"
													style="padding-left: 0px; margin-top: 10px;">
													<div class="col-lg-10" style="padding-left: 0px;">
														<div class="form-group has-warning">
															<input type="text" class="form-control" name="writeComment_${postFacebook.idPost}" id="writeComment_${postFacebook.idPost}"
															placeholder="Write a comment ... " />
														</div>
													</div>
													<div class="col-lg-2" style="padding-left: 0px;">
														<button type="button" style="padding:7px 10px" class="btn btn-primary" onClick="clickComment('${postFacebook.idPost}')">Comment</button>	
													</div>	
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
							<!--end cover_status_group -->
						</div>
					</div>
				</div>
			</div>
			<!-- end content -->
			<div id="negative_right">
				<div class="col-sm-3 col-md-2 sidebar_right">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title" style="color: black;">
								<b>SINGLE GROUP</b>
							</h3>
						</div>
						<div class="panel-body" style="min-height: 500px;">
							<ul class="nav nav-sidebar">
								<c:forEach items="${groups}" var="group">
									<li><a href="group?id=${group.getId()}"> <img alt=""
											src="http://graph.facebook.com/<c:out value="${group.getId()}"/>/picture"
											align="middle" /> <c:out value="${group.getName() }" />
									</a></li>

								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- end nagetive_right -->
		</div>
		<!-- end cover_content -->
		<div id="footer"></div>
	</div>
	<!-- cover_all -->
	<jsp:include page="include/effect_footer.jsp" />
		<div class="modal fade" id="uploadFile" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">Upload your file.</h4>
				</div>
				
				<label id="resultUpload" class="myResult control-label"></label>
				<form id="form2" method="post" action="/uploadAjax" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label for="file2" class="control-label">Choose your Video/Photo:</label>
							  <!-- File input -->    
				 			 <input  class="file2 form-control" name="file2" id="file2" type="file"  onchange="validateFile(this);"/><br/>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" value="Submit" onclick="uploadFormData('post')">Upload</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="resources/js/classie.js"></script>
	<script src="resources/js/sidebarEffects.js"></script>
</body>
</html>