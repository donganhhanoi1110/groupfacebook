var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png", ".mp4", ".wmv", ".mvi", ".mkv"];
var _validFilePhoto = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];   
function validateFile(oInput) {
    if (oInput.type == "file") {
        var sFileName = oInput.value;
        var fileLength = oInput.files[0].size;
        if (fileLength > 15000000) {
        	noty({dismissQueue: true, force: true, 
				 layout: 'center', theme: 'defaultTheme', 
				 text:"Maximum file size is 15Mb " , type: 'error'});
           oInput.value = "";
           return false;
        }
         if (sFileName.length > 0) {
            var blnValid = false;
            for (var j = 0; j < _validFileExtensions.length; j++) {
                var sCurExtension = _validFileExtensions[j];
                if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                    blnValid = true;
                    break;
                }
            }
             
            if (!blnValid) {
            	 noty({dismissQueue: true, force: true, 
					 layout: 'center', theme: 'defaultTheme', 
					 text:"Sorry, " + sFileName + " is invalid, allowed extensions are: " + _validFilePhoto.join(", "), type: 'error'});
                oInput.value = "";
                return false;
            }
        }
    }
    return true;
}

function validateFilePicture(oInput) {
    if (oInput.type == "file") {
        var sFileName = oInput.value;
        var fileLength = oInput.files[0].size;
        if (fileLength > 5000000) {
        	noty({dismissQueue: true, force: true, 
				 layout: 'center', theme: 'defaultTheme', 
				 text:"Maximum picture size is 5Mb " , type: 'error'});
           oInput.value = "";
           return false;
        }
         if (sFileName.length > 0) {
            var blnValid = false;
            for (var j = 0; j < _validFilePhoto.length; j++) {
                var sCurExtension = _validFilePhoto[j];
                if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                    blnValid = true;
                    break;
                }
            }
             
            if (!blnValid) {
            	 noty({dismissQueue: true, force: true, 
					 layout: 'center', theme: 'defaultTheme', 
					 text:"Sorry, " + sFileName + " is invalid, allowed extensions are: " + _validFilePhoto.join(", "), type: 'error'});
                oInput.value = "";
                return false;
            }
        }
    }
    return true;
}
//using FormData() object
	function uploadFormData(myFileType) {
		$('#result').html('');

		var oMyForm = new FormData();
		oMyForm.append("file", file2.files[0]);
		$.ajax({
			url : 'uploadAjax?fileType='+ myFileType,
			data : oMyForm,
			dataType : 'json',
			processData : false,
			contentType : false,
			type : 'POST',
			success : function(data) {
				console.log(data);

				if (data.success == true) {
					$('#resultUpload').html(data.message);
				} else {
					 noty({dismissQueue: true, force: true, 
						 layout: 'topRight', theme: 'defaultTheme', 
						 text:"Upload file has failed!!!", type: 'error'});
	                
				}
			},
			error : function(a, b, c) {
				noty({dismissQueue: true, force: true, 
					 layout: 'topCenter', theme: 'defaultTheme', 
					 text:"Upload file has failed!!!", type: 'error'});

				console.log(a);
			}
		});
	}
	$(document).on("click", "#change", function(e) {
		e.preventDefault();
		 var n = noty({
	            text        : 'Do you want to change Password?',
	            type        : 'information',
	            dismissQueue: true,
	            layout      : 'center',
	            theme       : 'defaultTheme',
	            animation: {
	                open: 'animated bounceInDown', // Animate.css class names
	                close: 'animated flipOutX'
	            },
	            buttons     : [
	                {addClass: 'btn btn-primary', text: 'Ok', onClick: function ($noty) {
	                	if(validateFormChangePassWord()== true) {
	                    $noty.close();
	                    changePass();
	                	}
	                }
	                },
	                {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
	                    $noty.close();
	                }
	                }
	            ]
	        });
	});
	function changePass() {
		var mydata = $("#formChangePass").serialize();
		console.log("mydata" + mydata);
		$.ajax({
			type : 'post',
			url : 'subChangePass',
			data : mydata,
			datatype : 'json',
			success : function(data) {
				console.log(data);

				if (data.success == true) {
					noty({dismissQueue: true, force: true, 
						 layout: 'center', theme: 'defaultTheme', 
						 text:data.message, type: 'success'});

				} else {
					noty({dismissQueue: true, force: true, 
						 layout: 'center', theme: 'defaultTheme', 
						 text:data.message, type: 'error'});
				}

			},
			error : function(a, b, c) {
				noty({dismissQueue: true, force: true, 
					 layout: 'center', theme: 'defaultTheme', 
					 text:"Fail change Password!!!", type: 'error'});
				console.log(a);
			}
		});

	}
	$(document).on("click", "#edit", function(e) {
		e.preventDefault();
		 var n = noty({
	            text        : 'Do you want to edit your profile?',
	            type        : 'information',
	            dismissQueue: true,
	            layout      : 'center',
	            theme       : 'defaultTheme',
	            animation: {
	                open: 'animated bounceInDown', // Animate.css class names
	                close: 'animated flipOutX'
	            },
	            buttons     : [
	                {addClass: 'btn btn-primary', text: 'Ok', onClick: function ($noty) {
	                	if(validateFormEditProfile() == true){
	                    $noty.close();
	                    editProfile();
	                	}
	                }
	                },
	                {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
	                    $noty.close();
	                }
	                }
	            ]
	        });
	});
	function validateFormChangePassWord() {
		var  check = false;
		var oldPassword = document.getElementById("oldPassword");
		var newPassword = document.getElementById("newPassword");
		var reNewPassword = document.getElementById("reNewPassword");
		
		if (oldPassword.value == null || oldPassword.value == '') {
			notyError("Please input your Old Password!!");
			oldPassword.focus();
			return false;
		} else {
			if (oldPassword.value.length < 5) {
			notyError("Minimum is 5 characters!!");
			oldPassword.focus();
			return false;
			}
		}
		
		if (newPassword.value == null || newPassword.value == '') {
			notyError("Please input your New Password!!");
			newPassword.focus();
			return false;
		} else {
			if (newPassword.value.length < 5) {
			newPassword.focus();
			notyError("Minimum is 5 characters!!"); 
			return false;
			}
		}
		
		if (reNewPassword.value == null || reNewPassword.value == '') {
			notyError("Please input your Renew Password!!");
			reNewPassword.focus();
			return false;
		} else {
			if (reNewPassword.value.length < 5) {
			notyError("Minimum is 5 characters!!"); 
			reNewPassword.focus();
			return false;
			}
		}
		
		return true;
	}
	
	function validateFormEditProfile() {
		var  check = false;
		var editFirstName = document.getElementById("editFirstName");
		var editLastName = document.getElementById("editLastName");
		var editUsername = document.getElementById("editUsername");
		
		if (editFirstName.value == null || editFirstName.value == '') {
			notyError("Please input your First Name!!");
			editFirstName.focus();
			return false;
		} else {
			if (editFirstName.value.length < 5) {
			notyError("Minimum is 5 characters!!");
			editFirstName.focus();
			return false;
			}
		}
		
		if (editLastName.value == null || editLastName.value == '') {
			notyError("Please input your Last Name!!");
			editLastName.focus();
			return false;
		} else {
			if (editLastName.value.length < 5) {
			editLastName.focus();
			notyError("Minimum is 5 characters!!"); 
			return false;
			}
		}
		
		if (editUsername.value == null || editUsername.value == '') {
			notyError("Please input your User Name!!");
			editUsername.focus();
			return false;
		} else {
			if (editUsername.value.length < 5) {
			notyError("Minimum is 5 characters!!"); 
			editUsername.focus();
			return false;
			}
		}
		
		return true;
	}
	
	function notyError(message) {
		noty({dismissQueue: true, force: true, 
				 layout: 'topCenter', theme: 'defaultTheme', 
				 text:message , type: 'error'});		
	}
	
	function editProfile() {
		var mydata = $("#formEditProfile").serialize();
		console.log("mydata" + mydata);
		$.ajax({
			type : 'post',
			url : 'subEditProfile',
			data : mydata,
			datatype : 'json',
			success : function(data) {
				console.log(data);

				if (data.success == true) {
					noty({dismissQueue: true, force: true, 
						 layout: 'center', theme: 'defaultTheme', 
						 text:data.message, type: 'success'});

				} else {
					noty({dismissQueue: true, force: true, 
						 layout: 'center', theme: 'defaultTheme', 
						 text:data.message, type: 'error'});
				}

			},
			error : function(a, b, c) {
				noty({dismissQueue: true, force: true, 
					 layout: 'center', theme: 'defaultTheme', 
					 text:"Fail edit Profile!!!", type: 'error'});
				console.log(a);
			}
		});
	}
	$(document)
			.ready(
					function() {
						$("#notificationLink").click(function() {
							$("#notificationContainer").fadeToggle(300);
							$("#notification_count").fadeOut("slow");
							return false;
						});

						//Document Click hiding the popup 
						$(document).click(function() {
							$("#notificationContainer").hide();
						});

						//Popup on click
						$("#notificationContainer").click(function() {
							return true;
						});

						$("#notificationLink")
								.click(
										function() {
											$
													.ajax({
														type : 'get',
														url : 'subGetNotification',
														datatype : 'html',
														success : function(data) {
															if (data.success == true) {
																var result = '';
																for(var i=0; i<data.notis.length ; i++){
																	    result+="<div class='activity-item' style='overflow: hidden;'>";
																		result+="<i class='text-warning'><img src='http://graph.facebook.com/"+data.notis[i].fromObject+"/picture' align='middle' width='50px' /></i>";
																	    result+="<div class='glyphicons glyphicons-newspaper activity'>";
																	    result+="<a style='margin-left:3px;' href='detaiAPost?id="+data.notis[i].id_post.id+"&idGroup="+data.notis[i].id_post.idGroup+"&idNotis="+data.notis[i].id+"'>"+data.notis[i].title
																	    if (data.notis[i].id_post.picture != null) {
																	    result+="<img class='pictureNotis' src='"+data.notis[i].id_post.picture+"' width='50px' height ='50px'></img>"
																	    }
																	    result+="</a> <span>"+data.notis[i].dateUpdatedTIme+"</span>";
																	    result+="</div></div><hr style='margin: 0px;padding: 0px' />";
																}	
																	document
																	.getElementById("listNotification").innerHTML = result;
																} else{
																	document
																	.getElementById("listNotification").innerHTML = '';
																}
														},
														error : function(a, b,
																c) {
															console.log(a);
														}
													});

										});//end notificationLink
					$("#notificationLink1").click(function() {
							$("#notificationContainer1").fadeToggle(300);
							$("#notification_count1").fadeOut("slow");
							return false;
						});

						//Document Click hiding the popup 
						$(document).click(function() {
							$("#notificationContainer1").hide();
						});

						//Popup on click
						$("#notificationContainer1").click(function() {
							return true;
						});
						$("#notificationLink1")
						.click(
								function() {
									var searchInfo = $("#txtSearch").val();
									$
											.ajax({
												type : 'get',
												url : 'subSearchGroup?searchInfo='+searchInfo,
												datatype : 'html',
												success : function(data) {
													if (data.success == true) {
													var result = '';
													for(var i=0; i<data.groups.length ; i++){
														  var description = '';
														    if(data.groups[i].description == null)  description = '';
														    else description = data.groups[i].description;
														result+="<div class='activity-item' style='overflow: hidden;'>";
														result+="<i class='text-warning'><img src='http://graph.facebook.com/"+data.groups[i].idGroupFace+"/picture' align='middle' width='50px' /></i>";
													    result+="<div class='glyphicons glyphicons-newspaper activity'>";
													    result+="<a href='https://facebook.com/"+data.groups[i].idGroupFace+"'>"+data.groups[i].nameGroupFace+"</a> <span>"
													    +description +"</span>";
													    result+="</div></div><hr style='margin: 0px;padding: 0px' />";
													}	
														document
														.getElementById("listNotification1").innerHTML = result;
													} else{
														document
														.getElementById("listNotification1").innerHTML = '';
													}
													
												},
												error : function(a, b,
														c) {
													console.log(a);
												}
											});

								});//end notificationLink				
										

					});