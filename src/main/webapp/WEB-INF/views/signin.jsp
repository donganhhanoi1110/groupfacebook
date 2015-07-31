<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Sign In</title>

<link rel="stylesheet"
	href="http://getbootstrap.com/dist/css/bootstrap.min.css">
<%-- <link rel="stylesheet" href="<c:url value='resources/css/boostrap/bootstrap.min.css'/>"> --%>
<link rel="stylesheet"
	href="<c:url value='resources/css/boostrap/bootstrap-theme.min.css'/>">
<link rel="stylesheet" href="<c:url value='resources/css/home.css'/>">
<link rel="stylesheet"
	href="<c:url value='resources/css/nagative_left.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/normalize.css'/>" />
<!--<link rel="stylesheet" type="text/css" href="css/demo.css" /> -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/icons.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/component.css'/>" />

<script src="<c:url value='resources/js/modernizr.custom.js'/>"></script>
<script src="<c:url value='resources/js/jquery/jquery-1.10.2.min.js'/>"></script>
<script src="<c:url value='resources/js/bootstrap.min.js'/>"></script>
<style type="text/css">
/* GLOBAL STYLES
-------------------------------------------------- */
/* Padding below the footer and lighter body text */
body {
	padding-bottom: 40px;
	color: #5a5a5a;
}

/* CUSTOMIZE THE NAVBAR
-------------------------------------------------- */

/* Special class on .container surrounding .navbar, used for positioning it into place. */
.navbar-wrapper {
	position: absolute;
	top: 0;
	right: 0;
	left: 0;
	z-index: 20;
}

/* Flip around the padding for proper display in narrow viewports */
.navbar-wrapper>.container {
	padding-right: 0;
	padding-left: 0;
}

.navbar-wrapper .navbar {
	padding-right: 15px;
	padding-left: 15px;
}

.navbar-wrapper .navbar .container {
	width: auto;
}

/* CUSTOMIZE THE CAROUSEL
-------------------------------------------------- */

/* Carousel base class */
.carousel {
	height: 500px;
	margin-bottom: 60px;
}
/* Since positioning the image, we need to help out the caption */
.carousel-caption {
	z-index: 10;
}

/* Declare heights because of positioning of img element */
.carousel .item {
	height: 500px;
	background-color: #777;
}

.carousel-inner>.item>img {
	position: absolute;
	top: 0;
	left: 0;
	min-width: 100%;
	height: 500px;
}

/* MARKETING CONTENT
-------------------------------------------------- */

/* Center align the text within the three columns below the carousel */
.marketing .col-lg-4 {
	margin-bottom: 20px;
	text-align: center;
}

.marketing h2 {
	font-weight: normal;
}

.marketing .col-lg-4 p {
	margin-right: 10px;
	margin-left: 10px;
}

/* Featurettes
------------------------- */
.featurette-divider {
	margin: 80px 0; /* Space out the Bootstrap <hr> more */
}

/* Thin out the marketing headings */
.featurette-heading {
	font-weight: 300;
	line-height: 1;
	letter-spacing: -1px;
}

/* RESPONSIVE CSS
-------------------------------------------------- */
@media ( min-width : 768px) {
	/* Navbar positioning foo */
	.navbar-wrapper {
		margin-top: 20px;
	}
	.navbar-wrapper .container {
		padding-right: 15px;
		padding-left: 15px;
	}
	.navbar-wrapper .navbar {
		padding-right: 0;
		padding-left: 0;
	}

	/* The navbar becomes detached from the top, so we round the corners */
	.navbar-wrapper .navbar {
		border-radius: 4px;
	}

	/* Bump up size of carousel content */
	.carousel-caption p {
		margin-bottom: 20px;
		font-size: 21px;
		line-height: 1.4;
	}
	.featurette-heading {
		font-size: 50px;
	}
}

@media ( min-width : 992px) {
	.featurette-heading {
		margin-top: 120px;
	}
}

.carousel-control.right, .carousel-control.left {
	/* background-color: white; */
	
}
</style>
</head>
<body>
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class=""></li>
			<li data-target="#myCarousel" data-slide-to="1" class=""></li>
			<li data-target="#myCarousel" data-slide-to="2" class="active"></li>
		</ol>
		<div class="carousel-inner" role="listbox">
			<div class="item">
				<img class="first-slide"
					src="http://www.wispresort.com/images/more_difficult.jpg"
					alt="First slide" />
				<div class="container">
					<div class="carousel-caption">
						<img class="img-circle"
							src="http://www.maherpost.com/images/icons/goal.svg"
							alt="Generic placeholder image" width="200" height="200">
						<h2>Post to All Groups</h2>
						<p>Post to Multiple Facebook Groups at once.</p>
						<form action="<c:url value="/signin/facebook" />" method="POST">
							<button type="submit" class="btn btn-lg btn-primary">Sign
								in with Facebook</button>
							<input type="hidden" name="scope"
								value="
								email,
							    publish_actions,
								manage_notifications,
							    public_profile, 
							    basic_info, 
							    user_likes,
							    manage_pages,
							    user_posts, 
							    user_groups, 
							    user_events, 
							    user_photos, 
							    user_friends, 
							    user_about_me,
							    user_managed_groups,
							    read_stream
							" />
						</form>
					</div>
				</div>
			</div>
			<div class="item">
				<img class="second-slide"
					src="http://www.wispresort.com/images/more_difficult.jpg"
					alt="Second slide" />
				<div class="container">
					<div class="carousel-caption">
						<img class="img-circle"
							src="http://www.maherpost.com/images/icons/clocks.svg"
							alt="Generic placeholder image" width="200" height="200">
						<h2>Schedule your Posts</h2>
						<p>You can now schedule your posts to be posted automatically
							at your desired time</p>
						<form action="<c:url value="/signin/facebook" />" method="POST">
							<button type="submit" class="btn btn-lg btn-primary">Sign
								in with Facebook</button>
							<input type="hidden" name="scope"
								value="
								email,
							    publish_actions,
							   	manage_notifications,
							    public_profile, 
							    basic_info, 
							    user_likes,
							    manage_pages,
							    user_posts, 
							    user_groups, 
							    user_events, 
							    user_photos, 
							    user_friends, 
							    user_about_me,
							    user_managed_groups,
							    read_stream
							" />
						</form>
					</div>
				</div>
			</div>
			<div class="item active">
				<img class="third-slide"
					src="http://www.wispresort.com/images/more_difficult.jpg"
					alt="Third slide" />
				<div class="container">
					<div class="carousel-caption">
						<img class="img-circle"
							src="http://www.maherpost.com/images/icons/rocket.svg"
							alt="Generic placeholder image" width="200" height="200">
						<h2>Fast, simple and easy to use</h2>
						<p>Our app is easy to use, fast and effective.</p>
						<form action="<c:url value="/signin/facebook" />" method="POST">
							<button type="submit" class="btn btn-lg btn-primary">Sign
								in with Facebook</button>
							<input type="hidden" name="scope"
								value="
								email,
							    publish_actions,
							    manage_notifications,
							    public_profile, 
							    basic_info, 
							    user_likes,
							    manage_pages,
							    user_posts, 
							    user_groups, 
							    user_events, 
							    user_photos, 
							    user_friends, 
							    user_about_me,
							    user_managed_groups,
							    read_stream
							" />
						</form>
					</div>
				</div>
			</div>
		</div>
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<div class="container marketing">

		<!-- Three columns of text below the carousel -->
		<div class="row">
			<div class="col-lg-4">
				<img class="img-circle"
					src="http://www.maherpost.com/images/icons/goal.svg"
					alt="Generic placeholder image" width="200" height="200">
				<h2>Post to All Groups</h2>
				<p>
					<!-- <a class="btn btn-default" href="#" role="button">View details
						»</a> -->
				</p>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-4">
				<img class="img-circle"
					src="http://www.maherpost.com/images/icons/clocks.svg"
					alt="Generic placeholder image" width="200" height="200">
				<h2>Schedule your Posts</h2>
				<p>
				<!-- 	<a class="btn btn-default" href="#" role="button">View details
						»</a> -->
				</p>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-4">
				<img class="img-circle"
					src="http://www.maherpost.com/images/icons/rocket.svg"
					alt="Generic placeholder image" width="200" height="200">
				<h2>simple and easy to use</h2>
				<p>
					<!-- <a class="btn btn-default" href="#" role="button">View details
						»</a> -->
				</p>
			</div>
			<!-- /.col-lg-4 -->
		</div>
		<!-- /.row -->


		<!-- START THE FEATURETTES -->

		<!-- /END THE FEATURETTES -->


		<!-- FOOTER -->
		<footer>
		<p class="pull-right">
			<a href="#">Back to top</a>
		</p>
		<p>
			© 2015 Thesis Minh & Hiep, Inc. · <a href="#">Privacy</a> · <a href="#">Terms</a>
		</p>
		</footer>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
	<script src="http://getbootstrap.com/assets/js/vendor/holder.js"></script>

</body>
</html>
