<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE >
<html>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Register Page</title>

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
	<script type="text/javascript"
	src="resources/js/ajaxjson/validateForm.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/buttons.css">
<link rel="stylesheet" type="text/css" href="resources/css/animate.css">
<script src="resources/js/jquery/jquery.noty.packaged.min.js"></script>
<script src="resources/ckeditor/ckeditor.js"></script>
<style type="text/css">
body{
    background-color: #0B0B3B;
}
.centered-form{
	margin-top: 60px;
}


</style>
<script>
	
</script>
</head>
<body>
<div class="container">
        <div class="row centered-form">
        <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        	<div class="panel panel-info">
        		<div class="panel-heading">
			    		<h3 class="panel-title">PLEASE SIGN UP FOR A TRIAL <small>IT'S FREE!</small></h3>
			 			</div>
			 			<div class="panel-body">
			    		<form:form action="subRegister" modelAttribute="user" method="post" onsubmit="return validateFormRegister();">
			    		<form:input path="id" type="hidden" />
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <form:input path="firstName" type="text" name="first_name" id="first_name" class="form-control input-sm" placeholder="First Name"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<form:input path="lastName" type="text" name="last_name" id="last_name" class="form-control input-sm" placeholder="Last Name"/>
			    					</div>
			    				</div>
			    			</div>

			    			<div class="form-group">
			    				<form:input path="userName" type="text" name="username" id="username" class="form-control input-sm" placeholder="Username"/>
			    			</div>

			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<form:input path="password" type="password" name="password" id="password" class="form-control input-sm" placeholder="Password"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="password" name="rePassword" id="password_confirmation" class="form-control input-sm" placeholder="Confirm Password"/>
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<input type="submit" value="Register" class="btn btn-info btn-block">
			    		
			    		</form:form>
			    		<c:if test="${not empty msg}">
							<script type="text/javascript">
								alert("${msg}");
							</script>
<%-- 							<div class="alert alert-danger" role="alert">${msg }</div> --%>
						</c:if>
			    	</div>
	    		</div>
    		</div>
    	</div>
    </div>
</body>
</html>