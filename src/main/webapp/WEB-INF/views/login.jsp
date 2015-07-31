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
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="resources/css/buttons.css">
<link rel="stylesheet" type="text/css" href="resources/css/animate.css">
<script src="resources/js/jquery/jquery.noty.packaged.min.js"></script>
<script src="resources/js/jquery/jquery-form.js"></script>
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
<script src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resources/js/ajaxjson/menuMainPopup.js"></script>
<script src="resources/ckeditor/ckeditor.js"></script>
<style type="text/css">
body{
    background-color: #0B0B3B;
}
.centered-form{
	margin-top: 60px;
}

.centered-form .panel{
	background: rgba(255, 255, 255, 0.8);
	box-shadow: rgba(0, 0, 0, 0.3) 20px 20px 20px;
}
</style>
<script>
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
</script>
</head>
<body>
	<div class="container">    
        <div id="loginbox" style="margin-top:60px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title" >Sign In</div>
                        <div style="float:right; font-size: 80%; position: relative; top:-10px"><!-- <a href="#" data-toggle="modal"
								data-target="#changepass"><span
									class="glyphicon glyphicon-pencil"></span> Forgot Password?</a> --></div>
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            
                        <form id="loginform" class="form-horizontal" role="form" action="subLogin" method="post">
                                    
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="username or email">                                        
                                    </div>
                                
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password" type="password" class="form-control" name="password" placeholder="password">
                                    </div>
                                    

                                
                            <div class="input-group">
                                      <div class="checkbox">
                                        <label>
                                          <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                                        </label>
                                      </div>
                                    </div>


                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->

                                    <div class="col-sm-12 controls">
                                    <input type="submit" value="Login" class="btn btn-success">
                                  
<!--                                       <a id="btn-fblogin" href="#" class="btn btn-primary">Login with Facebook</a> -->

                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                        <c:if test="${register == false }">
                                            Don't have an account! 
                                        <a href="register">
                                            Sign Up Here
                                        </a>
                                        </c:if>
                                        </div>
                                    </div>
                                </div>    
                            </form>     

						<c:if test="${not empty msg}">
							<script type="text/javascript">
								alert("${msg}");
							</script>
						</c:if>

                        </div>                     
                    </div>  
        </div>
    </div>
    
<div class="modal fade" id="changepass" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">Form change Password</h4>
			</div>
			<form action="xyz" method="post" id="formChangePass">
				<div class="modal-body">
					<div class="form-group">
						<label for="recipient-name" class="control-label">Old Password:</label>
						<input type="password" class="form-control" id="oldPassword" name="oldPassword"/>
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">New Password:</label>
						<input type="password" class="form-control" id="newPassword" name="newPassword" />
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">Confirm New Password:</label>
						<input type="password" class="form-control" id="reNewPassword" name="reNewPassword" />
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="change">Change</button>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>