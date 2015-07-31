<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Group Detail</title>
<script type="text/javascript">
	$(document).ready(function() {
		$(".show_post").click(function() {
			$(".show_all_post").slideToggle();
			$(".show_post").hide();
		});

	});
</script>
<style type="text/css">
.show_all_post {
	display: none;
}
</style>
</head>
<body>
	<%
		int i = 0;
	%>
	<c:forEach items="${postFacebooks}" var="postFacebook">
		<%
			i++;
				if (i <= 5) {
		%>
		<div class="panel panel-info">
			<div class="panel-body">
				<div class="col-lg-12"
					style="padding: 20px 0px 0px 0px; background-color: white; margin: 0px 0px 0px 0px;">
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
					<a href="detaiAPost?id=${postFacebook.id}&idGroup=${postFacebook.idGroup}" style="color: black;">
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
					<span id="placeholder_${postFacebook.idPost}"></span>
					<!-- end while for comment of post-->

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
		<%
			} else {
		%>
		<%
			if (i == 6) {
		%>
		<div class="show_post" style="text-align: center">More
			Information</div>
		<%
			}
		%>
		<div class="show_all_post">
			<div class="panel panel-info">
				<div class="panel-body">
					<div class="col-lg-12"
						style="padding: 20px 0px 0px 0px; background-color: white; margin: 0px 0px 0px 0px;">
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
						<a href="detaiAPost?id=${postFacebook.id}&idGroup=${postFacebook.idGroup}" style="color: black;">
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
						<span id="placeholder_${postFacebook.idPost}"></span>
						<!-- end while for comment of post-->

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
		<%
			}
		%>
	</c:forEach>
</body>
</html>