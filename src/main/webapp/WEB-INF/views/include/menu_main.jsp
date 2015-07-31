<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" type="text/css" href="resources/css/buttons.css">
<link rel="stylesheet" type="text/css" href="resources/css/animate.css">
<script src="resources/js/jquery/jquery.noty.packaged.min.js"></script>
<script src="resources/js/jquery/jquery-form.js"></script>
<script type="text/javascript"
	src="resources/js/ajaxjson/getNotifications.js"></script>
<script  type="text/javascript"
	src="resources/js/ajaxjson/validateForm.js"></script>
<script type="text/javascript" charset="UTF-8" src="resources/js/ajaxjson/menuMainPopup.js">
</script>
<style type="text/css">
#notification_li {
	position: relative
}

#notificationContainer{
	background-color: #fff;
	border: 1px solid rgba(100, 100, 100, .4);
	-webkit-box-shadow: 0 3px 8px rgba(0, 0, 0, .25);
	overflow: visible;
	position: absolute;
	top: 50px;
	margin-left: -150px;
	width: 400px;
	z-index: -1;
	display: none;
}

#notificationContainer:before{
	content: '';
	display: block;
	position: absolute;
	width: 0;
	height: 0;
	color: transparent;
	border: 10px solid black;
	border-color: transparent transparent white;
	margin-top: -20px;
	margin-left: 188px;
}

#notificationTitle {
	font-weight: bold;
	padding: 8px;
	font-size: 13px;
	background-color: #ffffff;
	position: fixed;
	z-index: 1000;
	width: 384px;
	border-bottom: 1px solid #dddddd;
}

#notificationsBody{
	padding: 33px 0px 0px 0px !important;
	min-height: 300px;
}

#notificationFooter {
	background-color: #e9eaed;
	text-align: center;
	font-weight: bold;
	padding: 8px;
	font-size: 12px;
	border-top: 1px solid #dddddd;
}

#notification_count{
	padding: 3px 7px 3px 7px;
	background: #cc0000;
	color: #ffffff;
	font-weight: bold;
	margin-left: 77px;
	border-radius: 9px;
	-moz-border-radius: 9px;
	-webkit-border-radius: 9px;
	position: absolute;
	margin-top: -11px;
	font-size: 11px;
}
#notification_li1 {
	position: relative
}

#notificationContainer1 {
	background-color: #fff;
	border: 1px solid rgba(100, 100, 100, .4);
	-webkit-box-shadow: 0 3px 8px rgba(0, 0, 0, .25);
	overflow: visible;
	position: absolute;
	top: 50px;
	margin-left: -150px;
	width: 400px;
	z-index: -1;
	display: none;
}

#notificationContainer1:before {
	content: '';
	display: block;
	position: absolute;
	width: 0;
	height: 0;
	color: transparent;
	border: 10px solid black;
	border-color: transparent transparent white;
	margin-top: -20px;
	margin-left: 188px;
}

#notificationTitle1 {
	font-weight: bold;
	padding: 8px;
	font-size: 13px;
	background-color: #ffffff;
	position: fixed;
	z-index: 1000;
	width: 384px;
	border-bottom: 1px solid #dddddd;
}

#notificationsBody1 {
	padding: 33px 0px 0px 0px !important;
	min-height: 300px;
}

#notificationFooter1 {
	background-color: #e9eaed;
	text-align: center;
	font-weight: bold;
	padding: 8px;
	font-size: 12px;
	border-top: 1px solid #dddddd;
}

#notification_count1 {
	padding: 3px 7px 3px 7px;
	background: #cc0000;
	color: #ffffff;
	font-weight: bold;
	margin-right: 100px;
	border-radius: 9px;
	-moz-border-radius: 9px;
	-webkit-border-radius: 9px;
	position: absolute;
	margin-top: -11px;
	font-size: 11px;
}
</style>
<div id="menu_main" style="padding: 0px;">
	<nav class="navbar navbar-inverse navbar-fixed-top"
		style="padding: 0px; margin: 0px; margin-right: 15px;">
		<div class="container-fluid"
			style="background-color: #6281BF; color: white; padding: 0px 10%; border: 0px;">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" style="color: white;" href="#"></a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><div class="fb-send"
							data-href="http://localhost:8080/spring-social-quickstart-30x/signin"
							data-colorscheme="light"></div></li>
					<li role="presentation"><a style="color: white;" href="#">
							<img
							src="http://graph.facebook.com/<c:out value="${user_id}"/>/picture"
							align="middle" width="20px" /> ${user_name}
					</a></li>
					<li role="presentation"><a style="color: white;" href="home">Home
							
					</a></li>
					<li id="notification_li"><a href="#" id="notificationLink"
						style="color: white;">Notifications <span id="myNotification" class="badge"></span></a>
						<div id="notificationContainer"
							style="z-index: 100000000000000000000;">
							<div id="notificationTitle" style="color: black;">Notifications</div>
							<div id="notificationsBody" style="color: black;"
								class="notifications">
								<div id="listNotification"
									style="overflow: scroll; max-height: 400px; min-height: 400px; color: black;">
									<div class="activity-item" style="overflow: hidden;">
										<i class=" text-warning"><img
											src="http://graph.facebook.com/<c:out value="${user_id}"/>/picture"
											align="middle" width="50px" /></i>
										<div class="glyphicons glyphicons-newspaper activity">
											<a href="#"></a> <span></span>
										</div>
									</div>

									<hr style="margin: 0px; padding: 0px" />

								</div>
							</div>
							<div id="notificationFooter">
								<a href="yourNotifications">See All</a>
							</div>

						</div></li>
					<li role="presentation" class="dropdown navbar-right"
						style="margin-right: 20px;"><a
						style="color: white; background-color: #6281BF;" href="#"
						class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-expanded="false"><span class="glyphicon glyphicon-user"></span>
							Profile<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" data-toggle="modal"
								data-target="#editProfile"><span
									class="glyphicon glyphicon-edit"></span> Edit Profile</a></li>
							<li><a href="#" data-toggle="modal"
								data-target="#changepass"><span
									class="glyphicon glyphicon-pencil"></span> Change Password</a></li>
							<li class="divider"></li>
							<li><a href="logout"><span
									class="glyphicon glyphicon-off"></span> Log out</a></li>
						</ul></li>
				</ul>
				<div class="navbar-left navbar-form">
						<div class="form-group "  id="notification_li1">
							<input id="txtSearch" type="text" class="form-control" placeholder="Search">
						</div>
						<button id="notificationLink1" class="btn btn-default form-group">Search</button>
						<div id="notificationContainer1"
							style="z-index: 100000000000000000000;">
							<div id="notificationTitle1" style="color: black;">Result</div>
							<div id="notificationsBody1" style="color: black;"
								class="notifications">
								<div id="listNotification1"
									style="overflow: scroll; max-height: 400px; min-height: 400px; color: black;">
									<div class="activity-item" style="overflow: hidden;">
										<i class=" text-warning"><img
											src="http://graph.facebook.com/<c:out value="${user_id}"/>/picture"
											align="middle" width="50px" /></i>
										<div class="glyphicons glyphicons-newspaper activity">
											<a href="#"></a> <span></span>
										</div>
									</div>

									<hr style="margin: 0px; padding: 0px" />

								</div>
							</div>
						</div>
				</div>
			</div>
		</div>
	</nav>
</div>

	