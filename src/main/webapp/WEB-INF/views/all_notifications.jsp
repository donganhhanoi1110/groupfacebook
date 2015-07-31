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
<title>All Notification</title>

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
<script  type="text/javascript"
	src="resources/js/ajaxjson/getPostOfGroupId.js"></script>
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
#mytablePosts2 th, #mytablePosts2 td {
	padding-left: 2px;
	padding-top: 2px;
}
</style>
<script>
	$(document).ready(function() {
		$("#mytablePosts2").DataTable({
			"bFilter" : true,
			responsive : true
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
								<b>All Notifications</b>
							</h3>
						</div>
						<div class="panel-body" style="min-height: 600px;">
						<div class="modal-body">
					<div class="form-group">
						<div class="panel-body">
								<table id="mytablePosts2" class=" display cell-border compact">
								<thead>
									<tr align="left">
										<th>From</th>
										<th>Title</th>
										<th>Created Date</th>
										<th>Updated Date</th>
										<th>Unread</th>
										<th>Link</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="noti" items="${notifications}">
										<tr>
										<td><i class='text-warning'><img src='http://graph.facebook.com/${noti.fromObject }/picture' align='middle' width='50px' /></i></td>
										<td>
											<a href="detaiAPost?id=${noti.id_post.id}&idGroup=${noti.id_post.idGroup}&idNotis=${noti.id}">
												${noti.title}
											</a>	
										 </td>
										<td>${noti.dateCreatedTime}</td>
										<td>${noti.dateUpdatedTIme}</td>
										<td>
										<c:if test="${noti.unread == true}">
											unread
										</c:if>
										<c:if test="${noti.unread == false}">
											read
										</c:if>
										</td>
										<td><a href="${noti.link}">Go to Facebook</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							</div>
					</div>
				</div>
						</div>
					</div>
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
	<script src="resources/js/classie.js"></script>
	<script src="resources/js/sidebarEffects.js"></script>
</body>
</html>