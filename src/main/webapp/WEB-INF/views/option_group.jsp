<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Option</title>

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
<link rel="stylesheet" href="resources/css/jquery-ui.min.css"
	type="text/css" />

<!--Import Jquery  -->
<script src="resources/js/jquery/jquery-ui.js"></script>
<script src="resources/js/modernizr.custom.js"></script>
<script src="resources/js/jquery/jquery-1.10.2.min.js"></script>
<script src="resources/js/jquery/jquery.dataTables.js"
	type="text/javascript"></script>
<script src="resources/js/bootstrap.min.js"></script>
<!-- Input Ajax PostToWall Group -->
<script language=javascript type="text/javascript"
	src="resources/js/ajaxjson/postToWallGroup.js"></script>
<style type="text/css">
#content {
	width: 100%;
}

#negative_right {
	width: 0%;
}

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

.button_general {
	padding: 7px 12px;
	margin-bottom: 20px;
	margin-top: 5px;
}

.button_general:hover {
	background: #FFCC00;
}

.checkbox1 {
	-ms-transform: scale(1.5); /* IE */
	-moz-transform: scale(1.5); /* FF */
	-webkit-transform: scale(1.5); /* Safari and Chrome */
	-o-transform: scale(1.5); /* Opera */
}

.checkbox3 {
	-ms-transform: scale(1.5); /* IE */
	-moz-transform: scale(1.5); /* FF */
	-webkit-transform: scale(1.5); /* Safari and Chrome */
	-o-transform: scale(1.5); /* Opera */
}

#mytable th, #mytable td {
	padding-left: 20px;
}


#cover_content {
	padding-left: 10px;
	padding-right: 10px;
}

.cover_all_box_group {
	padding: 0px;
}

.fb-send {
	margin: 15px 15px;
}

#redirect_quickly a {
	border-right: 2px solid white;
	padding: 5px 5px;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#mytable").dataTable();

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
	<jsp:include page="include/effect_header.jsp" />
	<div id="cover_all">
		<jsp:include page="include/menu_main.jsp" />
		<div id="cover_content">
			<!-- end_negative_left -->
			<div id="content">
				<form:form id="formPostToGroup" modelAttribute="group">
					<div class="col-lg-12">
						<div class="panel panel-info">
							<div class="panel-heading" style="padding:2px;overflow: hidden;color: white;">
								<h3 class="panel-title" id="redirect_quickly">
									<a href="schedule" class="btn btn-info" style="float: right;margin-left: 2px"><b>SCHEDULE PAGE</b></a><a
										href="addBigGroup" class="btn btn-info" style="float: right;margin-left: 2px"><b>GROUP MANAGEMENT PAGE</b></a> <a
										href="optionGroup" class="btn" style="color: black;background: transparent;border: 0px solid white;"><b style="font-size: 18">POST
											PAGE</b></a> <a href="<%=request.getContextPath()%>/home" class="btn btn-info" style="float: right;margin-left: 2px"><b>HOME
											PAGE</b></a>
								</h3>
							</div>
							<div class="panel-body">
								<div class="col-lg-12">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<div class="form-group has-success">
										<form:textarea path="groupMessage" placeholder="Message"
											style="border: .1em solid white;" class="groupMessage form-control"
											rows="3" />
									</div>
								</div>
								<div class="col-lg-6">
									<div class="form-group has-success">
										<form:input path="groupLink" placeholder="Link" type="text"
											class="groupLink form-control" />
									</div>
								</div>
								<div class="col-lg-6">
									<div class="form-group has-warning">
										<form:input path="groupName" type="text" placeholder="Name"
											class="groupName form-control" />
									</div>
								</div>
								<div class="col-lg-6">
									<div class="form-group has-error">
										<form:input path="groupCaption" placeholder="Caption"
											type="text" class="groupCaption form-control" />
									</div>
								</div>
								<div class="col-lg-6">
									<div class="form-group has-error">
										<form:input path="groupDescription" placeholder="Description"
											type="text" class="groupDescription form-control" />
									</div>
								</div>
								<div class="col-lg-12">
								<h4 style="margin-top: 0px">
									<a href="#" class="btn btn-info" data-toggle="modal" data-target="#uploadFile"><span
									class="glyphicon glyphicon-upload"></span> Upload Video/Picture</a>
									<a href="#" class="btn btn-info" onclick="refreshValuePost('')"><span
									class="glyphicon glyphicon-refresh"></span> Refresh Value</a>
									<a class="btn btn-primary"
											style="float:right; padding: 5px 25px" role="button"
											id="postToWallGroup"><span class="glyphicon glyphicon-send"
														aria-hidden="true"></span>    Post</a>
								</h4>
								</div>
									
								<div class="col-lg-12">
									<div class="form-group has-error">
										
									</div>
								</div>
								<div id="errorPane"></div>
								<div class="col-lg-12">
									<table id="mytable">
										<thead>
											<tr align="left">
												<th><input type="checkbox" id="selectall"
													class="checkbox1" />All</th>
												<th>Image</th>
												<th>Group Name</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="group" items="${groups}">
												<tr>
													<td><form:checkbox class="checkbox1" path="listGroup"
															value="${group.id}" /></td>
													<td><img
														src="http://graph.facebook.com/<c:out value="${group.id}"/>/picture"
														align="middle" /></td>
													<td><a href="group?id=${group.id}"><c:out value="${group.name}" /></a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<!-- end content -->
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
				 			 <input  class="file2 form-control" name="file2" id="file2" type="file" onchange="validateFile(this);"/><br/>
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