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
<title>Group Social</title>

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
<script src="resources/js/modernizr.custom.js"></script>
<script src="resources/js/jquery/jquery-1.10.2.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resources/js/ajaxjson/postToWallGroup.js"></script>
<script src="resources/ckeditor/ckeditor.js"></script>
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

.sidebar {
	padding-top: 0px;
	padding-left: 20px;
	width: 24%;
}

.checkbox1 {
	-ms-transform: scale(1.5); /* IE */
	-moz-transform: scale(1.5); /* FF */
	-webkit-transform: scale(1.5); /* Safari and Chrome */
	-o-transform: scale(1.5); /* Opera */
}
</style>
<script>
	$(document).ready(function() {
		$(".showme").click(function() {
			$(".my_show").slideToggle();
		});
		if (window.location.hash == '#_=_') {

			// Check if the browser supports history.replaceState.
			if (history.replaceState) {

				// Keep the exact URL up to the hash.
				var cleanHref = window.location.href.split('#')[0];

				// Replace the URL in the address bar without messing with the back button.
				history.replaceState(null, null, cleanHref);

			} else {

				// Well, you're on an old browser, we can get rid of the _=_ but not the #.
				window.location.hash = '';

			}

		}
		$("#selectall").click(function(event) { //on click 
			if (this.checked) { // check select status
				$(".checkbox1").each(function() { //loop through each checkbox
					this.checked = true; //select all checkboxes with class "checkbox1"               
				});
			} else {
				$(".checkbox1").each(function() { //loop through each checkbox
					this.checked = false; //deselect all checkboxes with class "checkbox1"                       
				});
			}
		});
	});
</script>
</head>
<body>
	<div id="fb-root"></div>
	<jsp:include page="include/effect_header.jsp" />
	<div id="cover_all">
		<jsp:include page="include/menu_main.jsp" />
		<div id="cover_content">
			<div id="negative_left">

				<div class="col-sm-3 col-md-2 sidebar">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title" style="color: black;">
								<b>FAVORITE GROUPS 
												<a  href="addBigGroup" style="color: black; float:right;" ><span class="glyphicon glyphicon-edit"
												aria-hidden="true"></span> New</a>
								</b>
							</h3>
						</div>
						<div class="panel-body" style="min-height: 500px;">
							<ul class="nav nav-sidebar">
								<c:forEach items="${bigGroupFaces}" var="bigGroupFace">
									<li><a
										href="<%=request.getContextPath() %>/home?id=<c:out value='${bigGroupFace.idBigGroupFace}'/>"><img
											src="${bigGroupFace.uploadedFile.path}" width="50px" height="50px"
											align="middle" /> <c:out
												value='${bigGroupFace.nameBigGroupFace}' /></a>
											</li>

								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- end_negative_left -->
			<div id="content">
				<form:form id="formPostToGroup" modelAttribute="group">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title" style="color: black;">
								<b>DETAIL OF FAVORITE GROUP</b>
							</h3>
						</div>
						<div class="panel-body" style="min-height: 600px;">
							<div class="col-lg-12">
								<div class="panel panel-info">
									<div class="panel-heading">
										<h3 class="panel-title">WRITTING A MESSAGE </h3>
										
									</div>
									<div class="panel-body">
										<div class="cover_post_status">
											<div class="post_status_top">
												<ul>
													<li><span class="glyphicon glyphicon-edit"
														aria-hidden="true"></span> Update Status</li>
													<li><span class="glyphicon glyphicon-film"
														aria-hidden="true"></span> <a style="color: black;" href="#" data-toggle="modal" data-target="#uploadFile">
														Add Photo/Video</a></li>
													<li><span class="glyphicon glyphicon-refresh"></span><a href="#" style="color: black;" onclick="refreshValuePost('')" 
														aria-hidden="true"> Refresh Value</a></li>
														
												</ul>
													 
											</div>
											<div class="post_status_center">

												<div class="form-group" style="margin: 0px">
													<form:textarea path="groupMessage"
														style="border: .1em solid white;" class="form-control groupMessage"
														rows="3" />
												</div>

											</div>
											<div class="post_status_bottom">
												<div class="menu_float_left">
													<ul class="nav nav-pills">
														<li role="presentation" class="showme"><a
															href="#"><span class="glyphicon glyphicon-chevron-down"></span> </a></li>
														<li><img src="resources/images/loading4.gif" alt="Loading" class="loading loadingGif" /></li>
													</ul>
													
												</div>
												<div class="menu_float_right">

													<ul class="nav nav-pills">
													<!-- 	<li class="dropdown"><a href="#"
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
								</div>
							</div>
							<div class="col-lg-12">
								<div class="col-lg-12">

									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<div class="my_show">
										<div class="form-group has-success">
											<form:input path="groupLink" type="text" placeholder="Link"
												class="form-control groupLink" />
										</div>
										<div class="form-group has-warning">
											<form:input path="groupName" type="text" placeholder="Name"
												class="form-control groupName" />
										</div>
										<div class="form-group has-error">
											<form:input path="groupCaption" type="text"
												placeholder="Caption" class="form-control groupCaption" />
										</div>
										<div class="form-group has-error">
											<form:input path="groupDescription" placeholder="Description"
												type="text" class="form-control groupDescription" />
										</div>
									</div>
								</div>
							</div>
							<!-- end_cover_status -->
							<div class="col-xs-12 col-sm-12 placeholder">
								<div class="panel panel-info">
									<div class="panel-heading">
										<h3 class="panel-title">
											<input type="checkbox" id="selectall" checked="checked" class="checkbox1" /> 
											<c:choose>
												<c:when test="${not empty idBigGroup }">${nameBigGroup}</c:when>
												<c:otherwise>All Groups</c:otherwise>
											</c:choose>
										</h3>
									</div>
									<div class="panel-body">
										<div class="col-sm-12">
											<div class="row placeholders">
												<c:forEach items="${groupFaces}" var="groupFace">
													<div class="col-xs-6 col-sm-3 placeholder">
														<div class="panel panel-info">
															<div class="panel-heading">
																<h3 class="panel-title">
																	<form:checkbox checked="checked" class="checkbox1"
																		path="listGroup" value="${groupFace.idGroupFace}" />
																</h3>
															</div>
															<div class="panel-body"
																style="text-align: center; max-height: 250px; min-height:250px; overflow: hidden;">
																<a href="group?id=${groupFace.idGroupFace}" title="${groupFace.nameGroupFace }">
																	<img data-src="holder.js/200x200/auto/sky" width="200px"
																		height="200px" class="img-responsive img-rectangle"
																		alt="200x200"
																		src="http://graph.facebook.com/<c:out value="${groupFace.idGroupFace}"/>/picture?type=normal"
																		data-holder-rendered="true">
																	<h4>
																		<c:out value="${groupFace.nameGroupFace }" />
																	</h4>
																</a>
																<!-- <span class="text-muted">Overview of Group</span> -->
															</div>
														</div>
													</div>
												</c:forEach>
											</div>
										</div>
										<!-- end cover_group_box -->
									</div>
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
			<!-- end content -->
			<div id="negative_right"></div>
			<!-- end nagetive_right -->
		</div>
		<!-- end cover_content -->
		<div id="footer"></div>
	</div>
	<!-- cover_all -->
	<jsp:include page="include/effect_footer.jsp" />
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