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
					
			var callRepeat=function(e) {
				// if you Transaction ajax
				var id_group= $("#idGroup").val();
				$.ajax({
				type : 'get',
				data: {"idGroup":id_group},
				url : 'getPostOfGroupId',
				datatype : 'json',											
				success : function(data) {
					console.log(data);
					
						if (data.success == true) {
							var count=data.listPosts.length;
							console.log("countPost: "+count);
							var newestMessage=data.listPosts[0].message;
							$("#newestPost").html(newestMessage);
								
						} 
					
				},
				error : function(a, b, c) {
									
					console.log(a);
				}//close error tag
			});//close ajax
		} //close var callRepeat
	setInterval(callRepeat,5000);
	});// close document.ready
				
		
