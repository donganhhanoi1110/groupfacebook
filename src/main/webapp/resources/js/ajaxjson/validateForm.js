function notyError(message) {
	noty({dismissQueue: true, force: true, 
			 layout: 'topCenter', theme: 'defaultTheme', 
			 text:message , type: 'error'});		
		}
	function validateFormRegister() {
		var  check = false;
		var firstName = document.getElementById("first_name");
		var lastName = document.getElementById("last_name");
		var userName = document.getElementById("username");
		var password = document.getElementById("password");
		var confirmPass = document.getElementById("password_confirmation");
		
		if (firstName.value == null || firstName.value == '') {
			notyError("Please input your First Name!!");
			firstName.focus();
			return false;
		} else {
			if (firstName.value.length < 5) {
			notyError("Minimum is 5 characters!!");
			firstName.focus();
			return false;
			}
		}
		
		if (lastName.value == null || lastName.value == '') {
			notyError("Please input your Last Name!!");
			lastName.focus();
			return false;
		} else {
			if (lastName.value.length < 5) {
			lastName.focus();
			notyError("Minimum is 5 characters!!"); return false;
			}
		}
		
		if (userName.value == null || userName.value == '') {
			notyError("Please input your User Name!!");
			userName.focus();
			return false;
		} else {
			if (userName.value.length < 5) {
			notyError("Minimum is 5 characters!!"); 
			userName.focus();
			return false;
			}
		}
		
		if (password.value == null || password.value == '') {
			notyError("Please input your Password!!");
			password.focus();
			return false;
		} else {
			if (password.value.length < 5) {
			notyError("Minimum is 5 characters!!"); 
			password.focus();
			return false;
			}
		}
		
		if (confirmPass.value == null || confirmPass.value == '') {
			notyError("Please input your Confirm Password!!"); 
			confirmPass.focus();
			return false;
		} else {
			if (confirmPass.value.length < 5) {
			notyError("Minimum is 5 characters!!"); 
			confirmPass.focus();
			return false;
			}
		}
		
		return true;
	}
	
	