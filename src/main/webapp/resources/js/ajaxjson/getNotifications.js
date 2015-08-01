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
			//for show notification
			function generateNoty(type, text) {
		         var n = noty({
		             text        : text,
		             type        : type,
		             dismissQueue: true,
		             layout      : 'bottomRight',
		             closeWith   : ['click'],
		             theme       : 'relax',
		             timeout	 : 20000,
		             maxVisible  : 5,
		             animation   : {
		                 open  : 'animated flipInX',
		                 close : 'animated flipOutX',
		                 easing: 'swing',
		                 speed : 550
		             }
		         });
		     }		
			var callRepeat=function(e) {
		// if you Transaction ajax
		$.ajax({
		type : 'get',
		url : 'subGetNotification',
		datatype : 'json',											
		success : function(data) {
			console.log(data);
					
		if (data.success == true) {
			var count=data.notis.length;
			var mynoty = $("#myNotification");
			if( count > 0){
				console.log("countNotis: "+count);
				mynoty.html('New '+count);
				for(var i = 0; i < count; i++) {
					if(data.notis[i].show == false) {
						var notisId = data.notis[i].notificationId;
							var item =
								'<div class="activity-item">'
								+'<i class=" text-warning"></i>'
								+'<div class="glyphicons glyphicons-newspaper activity">'
								+'<a href='+'"detaiAPost?id='+data.notis[i].id_post.id+'&idGroup='+data.notis[i].id_post.idGroup+'&idNotis='+data.notis[i].id+'">'+data.notis[i].title+'</a>'
								+'<span>'+data.notis[i].dateUpdatedTIme+'</span>'
								+'</div>'//end first div
								+'</div>';//end second div
						
							generateNoty('information', item);
							 //Call another ajax to set isShow to true
							 $.ajax({
								type : 'get',
								url : 'checkNotisIsShow',
								datatype : 'json',
								data :({notisId : notisId }),
								success : function(data2) {
									if (data.success == true) {
										console.log('Notification->'+notisId + 'isShowed');
									} else{
										console.log('Notification->'+notisId + 'is not Showed');
									}
								},
								error : function( a, b, c) {
									console.log(a);
								}
							 });
							}//end if isShow
						}//end for
					}//end if count>0
					else {
						mynoty.html('');
					}
				}// end if success
				},
				error : function(a, b, c) {
					console.log(a);
				}//close error tag
			});//close ajax
		} //close var callRepeat
			
	//setInterval(callRepeat,5000);
	 
});// close document.ready
//load page in first time
window.onload = function () {
	$.ajax({
		type : 'get',
		url : 'subGetNotification',
		datatype : 'json',											
		success : function(data) {
			console.log(data);
					
		if (data.success == true) {
			var count=data.notis.length;
			var mynoty = $("#myNotification");
			if( count > 0){
				console.log("countNotis: "+count);
				mynoty.html('New '+count);
				for(var i = 0; i < count; i++) {
					if(data.notis[i].show == false) {
						var notisId = data.notis[i].notificationId;
							var item =
								'<div class="activity-item">'
								+'<i class=" text-warning"></i>'
								+'<div class="glyphicons glyphicons-newspaper activity">'
								+'<a href='+'"detaiAPost?id='+data.notis[i].id_post.id+'&idGroup='+data.notis[i].id_post.idGroup+'&idNotis='+data.notis[i].id+'">'+data.notis[i].title+'</a>'
								+'<span>'+data.notis[i].dateUpdatedTIme+'</span>'
								+'</div>'//end first div
								+'</div>';//end second div
						
							generateNoty('information', item);
							 //Call another ajax to set isShow to true
							 $.ajax({
								type : 'get',
								url : 'checkNotisIsShow',
								datatype : 'json',
								data :({notisId : notisId }),
								success : function(data2) {
									if (data.success == true) {
										console.log('Notification->'+notisId + 'isShowed');
									} else{
										console.log('Notification->'+notisId + 'is not Showed');
									}
								},
								error : function( a, b, c) {
									console.log(a);
								}
							 });
							}//end if isShow
						}//end for
					}//end if count>0
					else {
						mynoty.html('');
					}
				}// end if success
				},
				error : function(a, b, c) {
					console.log(a);
				}//close error tag
			});//close ajax
}			
		
