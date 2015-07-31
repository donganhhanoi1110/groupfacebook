<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<ul>
		<li><a href="<c:url value="/signout" />">Sign Out</a></li>
	</ul>
	<h3>Your Facebook Friends</h3>
	<ul>
		<c:forEach items="${friends}" var="friend">
			<li><img
				src="http://graph.facebook.com/<c:out value="${friend.id}"/>/picture"
				align="middle" /> <c:out value="${friend.name}" /></li>
				
		</c:forEach>
	</ul>

	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&appId=945485498803969&version=v2.3";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
	<div class="fb-like"
		data-href="https://developers.facebook.com/docs/plugins/"
		data-layout="standard" data-action="like" data-show-faces="true"
		data-share="true"></div>
	<h1>Please give me Comments</h1>
	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&appId=945485498803969&version=v2.3";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
	<div class="fb-comments"
		data-href="http://developers.facebook.com/docs/plugins/comments/"
		data-numposts="5" data-colorscheme="light"></div>
</body>
</html>