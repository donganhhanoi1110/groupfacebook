
</div>
<!-- /main -->
</div>
<!-- /st-content-inner -->
</div>
<!-- /st-content -->
</div>
<!-- /st-pusher -->
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
<div class="modal fade" id="editProfile" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">Form Edit Profile</h4>
			</div>
			<form action="xyz" method="post" id="formEditProfile">
				<div class="modal-body">
					<div class="form-group">
						<label for="recipient-name" class="control-label">First Name: </label>
						<input type="text" class="form-control" id="editFirstName" value="${sessionScope.USER.firstName }" name="firstName"/>
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">Last Name:</label>
						<input type="text" class="form-control" id="editLastName" value="${sessionScope.USER.lastName }" name="lastName" />
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">User Name:</label>
						<input type="text" class="form-control" id="editUsername" value="${sessionScope.USER.userName }" name="userName" />
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="edit">Edit</button>
				</div>
			</form>
		</div>
	</div>
</div>