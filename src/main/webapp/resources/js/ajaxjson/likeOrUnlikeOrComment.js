		function like(postId, commentId) {
				// if you Transaction ajax
				$.ajax({
							type : 'post',
							url : 'like?postId=' + postId +'&commentId=' + commentId,
							datatype : 'json',											
							success : function(data) {
								if (data == true) {
								//Do like
								if (postId == "" && commentId != null) {
									document.getElementsByClassName("commentLike_"+commentId)[0].style.display = "none";
									document.getElementsByClassName("commentUnlike_"+commentId)[0].style.display = "inline";
									var currentLike = document.getElementsByClassName("likeCount_"+commentId)[0];
									currentLike.innerHTML = parseInt(currentLike.innerHTML) + 1;	
								}//end if
								else {
									if (postId != null && commentId == "") {
										document.getElementsByClassName("postLike_"+postId)[0].style.display = "none";
										document.getElementsByClassName("postUnlike_"+postId)[0].style.display = "inline";
										var currentLike = document.getElementsByClassName("likeCount_"+postId)[0];
										currentLike.innerHTML = parseInt(currentLike.innerHTML) + 1;
									}
								}
								} else {
									//Do sth else
									console.log("Error like " +commentId );
								}
							},
							error : function(a, b, c) {
								noty({dismissQueue: true, force: true, 
									 layout: 'topCenter', theme: 'defaultTheme', 
									 text:"Fail!!! Cant like!!!" , type: 'error'});						
								console.log(a);
							}
						});
		}// end like
		
		function unlike(postId, commentId) {
			// if you Transaction ajax
			$.ajax({
						type : 'post',
						url : 'unlike?postId=' + postId +'&commentId=' + commentId,
						datatype : 'json',											
						success : function(data) {
							if (data == true) {
								if (postId == "" && commentId != null) {
									document.getElementsByClassName("commentLike_"+commentId)[0].style.display = "inline";
									document.getElementsByClassName("commentUnlike_"+commentId)[0].style.display = "none";
									var currentLike = document.getElementsByClassName("likeCount_"+commentId)[0];
									currentLike.innerHTML = parseInt(currentLike.innerHTML) - 1;
								}//end if
								else {
									if (postId != null && commentId == "") {
										document.getElementsByClassName("postLike_"+postId)[0].style.display = "inline";
										document.getElementsByClassName("postUnlike_"+postId)[0].style.display = "none";
										var currentLike = document.getElementsByClassName("likeCount_"+postId)[0];
										currentLike.innerHTML = parseInt(currentLike.innerHTML) - 1;
									}
								}
							} else {
								//Do sth else
								console.log("Error unlike " +commentId );
							}
						},
						error : function(a, b, c) {
							noty({dismissQueue: true, force: true, 
								 layout: 'topCenter', theme: 'defaultTheme', 
								 text:"Fail!!! Cant unlike!!!" , type: 'error'});						
							console.log(a);
						}
					});
		}// end unlike
		
		function comment(postId, message) {
			// if you Transaction ajax
			$.ajax({
						type : 'post',
						url : 'comment?postId=' + postId +'&message=' + message,
						datatype : 'json',											
						success : function(data) {
							if (data.success == true) {
								//Do like
								
								var kq="<div class='col-lg-1' style='padding-right: 0px; margin-top: 10px;'>";
								kq+="<img src='http://graph.facebook.com/"+data.commentFacebook.from_client_id+"/picture' align='middle' width='35px' />";
								kq+="</div>"
								kq+="<div class='col-lg-11' style='padding-left: 0px; margin-top: 10px;'>";
								kq+="<div class='col-lg-12' style='padding-left: 0px;'>";
								kq+="<span style='color: #6281BF;'>"+data.commentFacebook.name_client+"</span>&nbsp;"+data.commentFacebook.message+"</div>";
								kq+="<div class='col-lg-12' style='padding-left: 0px;'>";
								kq+="<span style='color: #BDBDBD'>"+data.commentFacebook.date_create+"</span>";
								if(data.commentFacebook.userLike == true)
								{
									kq+="<a href='#' onclick=like('',"+data.commentFacebook.id_comment+") class='myHide commentLike_"+data.commentFacebook.id_comment+"'>&nbsp;Like&nbsp;</a>"; 
									kq+="<a href='#' onclick=unlike('',"+data.commentFacebook.id_comment+") class='commentUnlike_"+data.commentFacebook.id_comment+"'>&nbsp;Unlike&nbsp;</a>";
								}else{
									kq+="<a href='#' onclick=like('',"+data.commentFacebook.id_comment+") class='commentLike_"+data.commentFacebook.id_comment+"'>&nbsp;Like&nbsp;</a>"; 
									kq+="<a href='#' onclick=unlike('',"+data.commentFacebook.id_comment+") class='myHide commentUnlike_"+data.commentFacebook.id_comment+"'>&nbsp;Unlike&nbsp;</a>";
								}
								kq+="<span class='likeCount_"+data.commentFacebook.id_comment+"'>"+data.commentFacebook.like_count+"</span>";
								kq+="</div>";
								kq+="</div>";
								$("#placeholder_"+postId).before(kq);
								
							
							} else {
								//Do sth else
								console.log("Error comment " +postId );
							}
						},
						error : function(a, b, c) {
							noty({dismissQueue: true, force: true, 
								 layout: 'topCenter', theme: 'defaultTheme', 
								 text:"Fail!!! Cant unlike!!!" , type: 'error'});						
							console.log(a);
						}
					});
		}// end comment

		function clickComment(postId) {
			var message = document.getElementById('writeComment_'+postId).value;
			if (message == null || message == "") {
				noty({dismissQueue: true, force: true, 
					 layout: 'center', theme: 'defaultTheme', 
					 text:"Please input your message!!!" , type: 'error'});
				return;
			}
			//call comment method
			comment(postId, message);
			document.getElementById('writeComment_'+postId).value="";
			
		}
		
