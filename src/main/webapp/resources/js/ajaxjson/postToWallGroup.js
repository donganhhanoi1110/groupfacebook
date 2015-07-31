$(document)
		.ready(
				function() {
					$(function() {
						var token = $("meta[name='_csrf']").attr("content");
						var header = $("meta[name='_csrf_header']").attr("content");
						$(document).ajaxSend(function(e, xhr, options) {
							xhr.setRequestHeader(header, token);
						});
					});
					$(document)
							.on(
									"click",
									"#postToWallGroup",
									function(e) {
										e.preventDefault();
										 var n = noty({
									            text        : 'Do you want to Post this message to Facebook?',
									            type        : 'information',
									            dismissQueue: true,
									            layout      : 'topCenter',
									            theme       : 'defaultTheme',
									            animation: {
									                open: 'animated bounceInDown', // Animate.css class names
									                close: 'animated flipOutX'
									            },
									            buttons     : [
									                {addClass: 'btn btn-primary', text: 'Ok', onClick: function ($noty) {
									                    $noty.close();
									                    $(".loadingGif").show();
									                    postWall();
									                }
									                },
									                {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
									                    $noty.close();
									                }
									                }
									            ]
									        });

									});//end postToWall click
					function postWall() {
					
						var mydata=$("#formPostToGroup").serialize();
						console.log("mydata"+mydata);
							// if you Transaction ajax
							$.ajax({
										type : 'post',
										url : 'postToWallGroupJson',
										data : mydata,
										datatype : 'json',											
										success : function(data) {
											console.log(data);
											
												if (data.success == true) {
													 noty({dismissQueue: true, force: true, 
														 layout: 'topCenter', theme: 'defaultTheme', 
														 text:"Success! "+data.message, type: 'success'});
													 $(".loadingGif").hide();
													 //refresh value input
													 refreshValuePost(data.message);
												} else {
													if(data.exceptions.length > 0) {
													var errorInfos="Please correct following errors:";
													for(var i=0;i<data.exceptions.length;i++)
														{
														 errorInfos += "\n" + (i + 1) +". " + data.exceptions[i];

														}
													 noty({dismissQueue: true, force: true, 
														 layout: 'topCenter', theme: 'defaultTheme', 
														 text:errorInfos, type: 'error'});
													}
												}
											
										},
										error : function(a, b, c) {
											noty({dismissQueue: true, force: true, 
												 layout: 'topCenter', theme: 'defaultTheme', 
												 text:"Fail!!! Post to Wall Facebook Fail!!!" , type: 'error'});						
											console.log(a);
										}
									});
					}
	
	
});//end ready function

	function refreshValuePost(message) {
		var groupMessage = $(".groupMessage");
		var groupLink = $(".groupLink");
		var groupName= $(".groupName");
		var groupCaption = $(".groupCaption");
		var groupDescription = $(".groupDescription");
		var file2 = $(".file2");
		file2.val('');
		groupMessage.val('');
		groupLink.val('');
		groupName.val('');
		groupCaption.val('');
		groupDescription.val('');
		$.ajax({
			type : 'get',
			url : 'refreshValuePost',
			datatype : 'json',											
			success : function(data) {
				console.log(data);
				
					if (data == true) {
						$('#resultUpload').html(message);
					} else {
						noty({dismissQueue: true, force: true, 
							 layout: 'topCenter', theme: 'defaultTheme', 
							 text:"You are getting error when refresh!!!" , type: 'error'});						
					}
				
			},
			error : function(a, b, c) {
				noty({dismissQueue: true, force: true, 
					 layout: 'topCenter', theme: 'defaultTheme', 
					 text:"You are getting error when refresh!!!" , type: 'error'});						
				console.log(a);
			}
		});
		
	}//end if refresh value post
