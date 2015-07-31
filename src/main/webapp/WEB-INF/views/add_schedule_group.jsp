
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Schedule Management</title>

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
<link rel="stylesheet"
	href="resources/css/boostrap/bootstrap-datetimepicker.min.css"
	type="text/css" />

<!--Import Jquery  -->
<script src="resources/js/jquery/jquery-ui.js"></script>
<script src="resources/js/modernizr.custom.js"></script>
<script src="resources/js/jquery/jquery-1.10.2.min.js"></script>
<script src="resources/js/jquery/jquery.dataTables.js"
	type="text/javascript"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/bootstrap-datetimepicker.min.js"></script>
<!-- Input Ajax PostToWall Group -->
<script type="text/javascript" charset="UTF-8"
	src="resources/js/ajaxjson/postAddSchedule.js"></script>

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

#mytable2 th, #mytable2 td {
	padding-left: 20px;
}
#mytable3 th, #mytable3 td {
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
#redirect_quickly a{
/* 	border-right: 2px solid white; */
/* 	padding: 5px 5px; */
}
</style>
<script type="text/javascript" charset="utf-8">
	
</script>
</head>
<body>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Groups in Schedule</h4>
				</div>
				<div class="modal-body" id="insert_my_detail">...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>

				</div>
			</div>
		</div>
	</div>
	<jsp:include page="include/effect_header.jsp" />
	<div id="cover_all">
		<jsp:include page="include/menu_main.jsp"/>
		<div id="cover_content">

			<!-- end_negative_left -->
			<div id="content">
				<form:form id="formCreateSchedule" modelAttribute="schedule">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="col-lg-12">
						<div class="panel panel-info">
							<div class="panel-heading" style="padding:2px;overflow: hidden;color: white;">
								<h3 class="panel-title" id="redirect_quickly"><a href="schedule" class="btn" style="color: black;background: transparent;border: 0px solid white;"><b style="font-size: 18" >SCHEDULE PAGE</b></a><a href="addBigGroup" class="btn btn-info" style="float: right;margin-left: 2px"><b>GROUP MANAGEMENT PAGE</b></a><a class="btn btn-info" style="float: right;margin-left: 2px" href="<%=request.getContextPath() %>/home"><b>HOME PAGE</b></a></h3>
							</div>
							<div class="panel-body">
								<div class="col-lg-12">
									<div class="form-group has-success">
										<form:textarea path="groupMessage" placeholder="Message"
											style="border: .1em solid white;" class="groupMessage form-control"
											rows="3" />
									</div>
								</div>
								<div class="col-lg-4">
									<div class="form-group has-success">
										<form:input path="groupLink" type="text" placeholder="Link"
											class="groupLink form-control" />
									</div>
								</div>
								<div class="col-lg-4">
									<div class="form-group has-warning">
										<form:input path="groupName" type="text" placeholder="Name"
											class="groupName form-control" />
									</div>
								</div>
								<div class="col-lg-4">
									<div class="form-group has-error">
										<form:input path="groupCaption" placeholder="Caption"
											type="text" class="groupCaption form-control" />
									</div>
								</div>
								<div class="col-lg-4">
									<div class="form-group has-error">
										<form:input path="groupDescription" placeholder="Description"
											type="text" class="groupDescription form-control" />
									</div>
								</div>
								<div class="col-lg-4">
									<div class="form-group has-success">
										<input type="date" class="form-control" name="myDate" class="myDate"/>
									</div>
								</div>


								<div id="datetimepicker3" class="input-append">
									<div class="col-lg-4">
										<div class="input-group">
											<input data-format="hh:mm:ss" type="text"
												class="form-control" name="myTime"></input> <span
												class="myTime add-on input-group-btn">
												<button class="btn btn-default" type="button">Timer!</button>
												<i data-time-icon="icon-time" data-date-icon="icon-calendar">
											</i>
											</span>

										</div>
									</div>
								</div>
								<div class="col-lg-12">
									<div class="form-group has-error">
											<a href="#" class="btn btn-info" data-toggle="modal" data-target="#uploadFile"><span
										class="glyphicon glyphicon-upload"></span> Upload Video/Picture</a>
										<a href="#" class="btn btn-info" onclick="refreshValueSchedule('')"><span
									class="glyphicon glyphicon-refresh"></span> Refresh Value</a>
										
										<p style="float:right;" class="btn btn-primary"
											style="width: 100%; padding: 20px 0px" role="button"
											id="post"><span
										class="glyphicon glyphicon-send"></span> Posted quickly</p>
										<p style="float:right; margin-right: 5px;" class="btn btn-primary"
											style="width: 100%; padding: 20px 0px" role="button"
											id="createSchedule"><span
										class="glyphicon glyphicon-plus"></span> Create Schedule</p>
									</div>
								</div>
								<script type="text/javascript">
									$(function() {
										$('#datetimepicker3').datetimepicker({
											pickDate : false
										});
									});
								</script>
								<div class="col-lg-6">
									<!-- <div class="form-group has-error">
										<p class="btn btn-primary"
											style="width: 100%; padding: 20px 0px" role="button"
											id="createSchedule">Create Schedule</p>
									</div> -->

									<div id="errorPane"></div>
								</div>
								<div class="col-lg-6">
									<!-- <div class="form-group has-error">
										<p class="btn btn-primary"
											style="width: 100%; padding: 20px 0px" role="button"
											id="post">Posted quickly</p>
									</div> -->

									<div id="errorPane"></div>
								</div>
								<div class="col-lg-6">
									<div class="panel panel-info">
										<div class="panel-heading">
											<h3 class="panel-title">SELECT A SINGLE GROUP TO SCHEDULE</h3>
										</div>
										<div class="panel-body">
											<table id="mytable2">
												<thead>
													<tr align="left">
														<th><input type="checkbox" id="selectall"
															class="checkbox1" /> All</th>
														<th>Image</th>
														<th>Group Name</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="group" items="${groups}">
														<tr>
															<td><input type="checkbox" class="checkbox1"
																name="names" value="${group.id}" /></td>
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
								<div class="col-lg-6">
									<div class="panel panel-info">
										<div class="panel-heading">
											<h3 class="panel-title">SELECT A BIG GROUP TO SCHEDULE</h3>
										</div>
										<div class="panel-body">
											<table id="mytable3">
												<thead>
													<tr align="left">
														<th><input type="checkbox" id="selectall3"
															class="checkbox3" /> All</th>
														<th>Image</th>
														<th>BigGroup's Name</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="bigGroup" items="${bigGroups}">
														<tr>
															<td><input type="checkbox" class="checkbox3"
																name="namesBigGroup" value="${bigGroup.idBigGroupFace}" /></td>
															<td><img src="${bigGroup.uploadedFile.path}"
																align="middle" width="50px" height="50px" /></td>
															<td><a href="<%=request.getContextPath() %>/home?id=<c:out value='${bigGroup.idBigGroupFace}'/>"><c:out value="${bigGroup.nameBigGroupFace}" /></a></td>
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
				</form:form>

				<div class="col-lg-12">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">LIST OF SCHEDULE HAVE BEEN SETUP</h3>
						</div>
						<div class="panel-body">
							<div class="col-lg-12">
								<table id="mytable1">
									<thead>
										<tr align="left">
											<th>STT</th>
											<th>Message</th>
											<th>Date</th>
											<th>Status</th>
											<th>Group</th>
											<th>Delete</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="schedule" items="${schedules}">
											<tr>
												<td>${schedule.idSchedule}</td>
												<td>${schedule.groupMessage}</td>
												<td>${schedule.datePost}</td>
												<td>${schedule.state}</td>
												<td><button type="button" style="padding: 5px 10xp;"
														class="btn btn-info btn-sm my_detail" data-toggle="modal"
														data-target="#myModal">Group</button></td>
												<td><a class="btn btn-info btn-sm"
													href="subDeleteSchedule?id=${schedule.idSchedule}">Delete</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
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
						<button type="button" class="btn btn-primary" value="Submit" onclick="uploadFormData('schedule')">Upload</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="resources/js/classie.js"></script>
	<script src="resources/js/sidebarEffects.js"></script>
</body>
</html>