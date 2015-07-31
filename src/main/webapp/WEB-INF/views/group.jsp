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
<title>Group Detail</title>

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

.panel-body{
padding-top: 1px;}

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
#mytablePosts th, #mytablePosts td {
	padding-left: 2px;
	padding-top: 2px;
}
</style>
<script>
	$(document).ready(function() {
		$(".showme").click(function() {
			$(".my_show").slideToggle();
		});
		$("#mytablePosts").dataTable();
		$("#mytableMember").dataTable();
		$("#mytableDocs").dataTable();
		$("#mytableFiles").dataTable();
		
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
					<div class="panel-heading" style="padding:2px;overflow: hidden;color: white;">
						<h3 class="panel-title" style="color: black;">
							<a href="#" class="btn" style="color: black;background: transparent;border: 0px solid white;"><b style="font-size: 18">${groupName }</b></a>
							<a class="btn btn-info" style="float: right;margin-left: 2px;color: white" href="#" data-toggle="modal" data-target="#getListPost"><span
									class="glyphicon glyphicon-th-list"></span> Your Posts</a>
							<a class="btn btn-info" style="float: right;margin-left: 2px;color: white" href="#" data-toggle="modal" data-target="#getListMember"><span
									class="glyphicon glyphicon-user"></span> Group's Member</a>
							 <a class="btn btn-info" style="float: right;margin-left: 2px;color: white" href="#" data-toggle="modal" data-target="#getListDocs"><span
									class="glyphicon glyphicon-duplicate"></span> Group's Documents</a> 
							<a class="btn btn-info" style="float: right;color: white;" href="#" data-toggle="modal" data-target="#getListFiles"><span
									class="glyphicon glyphicon-open-file"></span> Group's Files</a>
						</h3>
					</div>
					<div class="panel-body" style="min-height: 600px;">
						<form:form id="formPostToGroup" action=""
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
														aria-hidden="true"></span> <a href="#" style="color: black;" data-toggle="modal" data-target="#uploadFile">
														Add Photo/Video</a></li>
													<li> <span class="glyphicon glyphicon-refresh"></span><a href="#" style="color: black;" onclick="refreshValuePost('')"
														aria-hidden="true"> Refresh Value </a></li>


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
								<!-- begin while for post-->

								<c:if test="${postFacebooks.size()<=5}">
									<jsp:include page="include/less_five.jsp" />
								</c:if>
								<c:if test="${postFacebooks.size()>5}">
									<jsp:include page="include/more_five.jsp" />
								</c:if>
								<!-- end while for post-->
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
	<!-- Get List of Files -->
	<div class="modal fade" id="getListFiles" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document" style="width: 80%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">${groupName}'s Files</h4>
			</div>
			
				<div class="modal-body"  style="overflow: scroll;" >
					<div class="form-group">
						<div class="panel-body">
								<table id="mytableFiles" class=" display cell-border compact">
								<thead>
									<tr align="left">
										<th>From</th>
										<th>Message</th>
										<th>Name</th>
										<th>DownLoad</th>
										<th>Updated Time</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="file" items="${files}">
										<tr>
										<td>
											<img src='http://graph.facebook.com/${file.fromId }/picture' align='middle' width='50px' />
										 </td>
										<td>${file.message}</td>
										<td>${file.name}</td>
										<td><a href="${file.downloadLink}">Download</a></td>
										<td>${file.updatedTime}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
		</div>
	</div>
</div>
		<!-- Popup for list of Document -->
	<div class="modal fade" id="getListDocs" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document" style="width: 80%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">${groupName}'s Documents</h4>
			</div>
			
				<div class="modal-body"  style="overflow: scroll;" >
					<div class="form-group">
						<div class="panel-body">
								<table id="mytableDocs" class=" display cell-border compact">
								<thead>
									<tr align="left">
										<th>Icon</th>
										<th>Subject</th>
										<th>Message</th>
										<th>Created Date</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="doc" items="${documents}">
										<tr>
										<td>
											<img  src="${doc.icon }">
										 </td>
										<td>${doc.subject}</td>
										<td>${doc.message}</td>
										<td>${doc.createdTime}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
		</div>
	</div>
</div>
	<!-- Popup for list of Post -->
	<div class="modal fade" id="getListPost" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document" style="width: 80%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">${groupName}'s  Posts</h4>
			</div>
			
				<div class="modal-body"  style="overflow: scroll;" >
					<div class="form-group">
						<div class="panel-body">
								<table id="mytablePosts" class=" display cell-border compact">
								<thead>
									<tr align="left">
										<th>Message</th>
										<th>Created Date</th>
										<th>Likes</th>
										<th>Comments</th>
										<th>Shared</th>
										<th>Type</th>
										<!-- <th>Delete</th> -->
									</tr>
								</thead>
								<tbody>
									<c:forEach var="post" items="${postFacebooks}">
										<tr>
										<td>
											<a href="detaiAPost?id=${post.id}&idGroup=${post.idGroup}" style="font-size: 20px;">
												${post.message}
												<c:if test="${not empty post.videoId }">
													${post.name}
												</c:if>
											</a>	
										 </td>
										<td>${post.createdDate}</td>
										<td>${post.like_count}</td>
										<td>${post.listComments.size()}</td>
										<td>${post.shareCount }</td>
										<td>${post.type }</td>
										<%-- <td><button type="button" class="deleteRow_${post.idPost} btn btn-primary" value="Submit" 
										onclick="deletePostId('${post.idPost}')">Delete</button></td> --%>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
		</div>
	</div>
</div>
<div class="modal fade" id="getListMember" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document" style="width: 80%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">${groupName}'s Members</h4>
			</div>
			
				<div class="modal-body"  style="overflow: scroll;" >
					<div class="form-group">
						<div class="panel-body">
								<table id="mytableMember" class=" display cell-border compact">
								<thead>
									<tr align="left">
										<th>STT</th>
										<th>Name</th>
										
									</tr>
								</thead>
								<tbody>
									<c:forEach var="member" items="${members}">
										<tr>
											<td>${member.id} </td>
											<td>${member.name}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
		</div>
	</div>
</div>
			<!-- /st-container -->
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
							<label for="file2" class="control-label">Choose your Video/Picture:</label>
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