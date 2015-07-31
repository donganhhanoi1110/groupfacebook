<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Insert title here</title>

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
				<img class="first-slide" src="resources/images/blue.png"
					alt="First slide" />
				<div class="container">
					<div class="carousel-caption">
						<img alt="" src="resources/images/hoathinh.jpg" height="200px;" />
						<h1>Example headline.</h1>
						<p>
							Note: If you're viewing this page via a
							<code>file://</code>
							URL, the "next" and "previous" Glyphicon buttons on the left and
							right might not load/display properly due to web browser security
							rules.
						</p>
						<form action="<c:url value="/signin/facebook" />" method="POST">
							<button type="submit" class="btn btn-lg btn-primary">Sign
								in with Facebook</button>
							<input type="hidden" name="scope"
								value="email,publish_stream,offline_access,user_groups,user_friends" />
						</form>
					</div>
				</div>
			</div>
			<div class="item">
				<img class="second-slide" src="resources/images/blue.png"
					alt="Second slide" />
				<div class="container">
					<div class="carousel-caption">
						<img alt="" src="resources/images/hoathinh.jpg" height="200px;" />
						<h1>Another example headline.</h1>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>
						<form action="<c:url value="/signin/facebook" />" method="POST">
							<button type="submit" class="btn btn-lg btn-primary">Sign
								in with Facebook</button>
							<input type="hidden" name="scope"
								value="email,publish_stream,offline_access,user_groups,user_friends" />
						</form>
					</div>
				</div>
			</div>
			<div class="item active">
				<img class="third-slide" src="resources/images/blue.png"
					alt="Third slide" />
				<div class="container">
					<div class="carousel-caption">
						<img alt="" src="resources/images/hoathinh.jpg" height="200px;" />
						<h1>One more for good measure.</h1>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>
						<form action="<c:url value="/signin/facebook" />" method="POST">
							<button type="submit" class="btn btn-lg btn-primary">Sign
								in with Facebook</button>
							<input type="hidden" name="scope"
								value="email,publish_stream,offline_access,user_groups,user_friends" />
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
				<img class="img-circle" src="resources/images/goal.svg"
					alt="Generic placeholder image" width="200" height="200">
				<h2>Heading</h2>
				<p>Donec sed odio dui. Etiam porta sem malesuada magna mollis
					euismod. Nullam id dolor id nibh ultricies vehicula ut id elit.
					Morbi leo risus, porta ac consectetur ac, vestibulum at eros.
					Praesent commodo cursus magna.</p>
				<p>
					<a class="btn btn-default" href="#" role="button">View details
						»</a>
				</p>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-4">
				<img class="img-circle" src="resources/images/clocks.svg"
					alt="Generic placeholder image" width="200" height="200">
				<h2>Heading</h2>
				<p>Duis mollis, est non commodo luctus, nisi erat porttitor
					ligula, eget lacinia odio sem nec elit. Cras mattis consectetur
					purus sit amet fermentum. Fusce dapibus, tellus ac cursus commodo,
					tortor mauris condimentum nibh.</p>
				<p>
					<a class="btn btn-default" href="#" role="button">View details
						»</a>
				</p>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-4">
				<img class="img-circle" src="resources/images/rocket.svg"
					alt="Generic placeholder image" width="200" height="200">
				<h2>Heading</h2>
				<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in,
					egestas eget quam. Vestibulum id ligula porta felis euismod semper.
					Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum
					nibh, ut fermentum massa justo sit amet risus.</p>
				<p>
					<a class="btn btn-default" href="#" role="button">View details
						»</a>
				</p>
			</div>
			<!-- /.col-lg-4 -->
		</div>
		<!-- /.row -->


		<!-- START THE FEATURETTES -->

		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading">
					First featurette heading. <span class="text-muted">It'll
						blow your mind.</span>
				</h2>
				<p class="lead">Donec ullamcorper nulla non metus auctor
					fringilla. Vestibulum id ligula porta felis euismod semper.
					Praesent commodo cursus magna, vel scelerisque nisl consectetur.
					Fusce dapibus, tellus ac cursus commodo.</p>
			</div>
			<div class="col-md-5">
				<img class="featurette-image img-responsive center-block"
					data-src="holder.js/500x500/auto" alt="500x500"
					src="resources/images/hoathinh.jpg" data-holder-rendered="true">
			</div>
		</div>

		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7 col-md-push-5">
				<h2 class="featurette-heading">
					Oh yeah, it's that good. <span class="text-muted">See for
						yourself.</span>
				</h2>
				<p class="lead">Donec ullamcorper nulla non metus auctor
					fringilla. Vestibulum id ligula porta felis euismod semper.
					Praesent commodo cursus magna, vel scelerisque nisl consectetur.
					Fusce dapibus, tellus ac cursus commodo.</p>
			</div>
			<div class="col-md-5 col-md-pull-7">
				<img class="featurette-image img-responsive center-block"
					data-src="holder.js/500x500/auto" alt="500x500"
					src="resources/images/hoathinh.jpg" data-holder-rendered="true">
			</div>
		</div>

		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading">
					And lastly, this one. <span class="text-muted">Checkmate.</span>
				</h2>
				<p class="lead">Donec ullamcorper nulla non metus auctor
					fringilla. Vestibulum id ligula porta felis euismod semper.
					Praesent commodo cursus magna, vel scelerisque nisl consectetur.
					Fusce dapibus, tellus ac cursus commodo.</p>
			</div>
			<div class="col-md-5">
				<img class="featurette-image img-responsive center-block"
					data-src="holder.js/500x500/auto" alt="500x500"
					src="resources/images/hoathinh.jpg" data-holder-rendered="true">
			</div>
		</div>

		<hr class="featurette-divider">

		<!-- /END THE FEATURETTES -->


		<!-- FOOTER -->
		<footer>
		<p class="pull-right">
			<a href="#">Back to top</a>
		</p>
		<p>
			© 2014 Company, Inc. · <a href="#">Privacy</a> · <a href="#">Terms</a>
		</p>
		</footer>

	</div>
	<script src="resources/js/classie.js"></script>
	<script src="resources/js/sidebarEffects.js"></script>
</body>
</html>
