var table = $("#mytablePosts").DataTable({
							"bFilter" : true,
							responsive : true
						}); 
function deletePostId(idPost) {
	        var n = noty({
	            text        : 'Do you want to delete this post?',
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
	                    $noty.close();
	                    deletePostAjax(idPost);	
	                }
	                },
	                {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
	                    $noty.close();
	                }
	                }
	            ]
	        });
	        console.log('html: ' + n.options.id);
	    }
			function deletePostAjax(idPost)	{		 
			// if you Transaction ajax
			$.ajax({
			type : 'get',
			url : 'deletePostId?idPost='+idPost,
			datatype : 'json',
			success : function(data) {
			console.log(data);
				
			if (data.success == true) {
				 noty({dismissQueue: true, force: true, 
					 layout: 'center', theme: 'defaultTheme', 
					 text:"Success! "+data.message, type: 'success'});
				  table.row('.deleteRow_'+idPost).remove().draw( false );
			} else {
				 noty({dismissQueue: true, force: true, 
					 layout: 'center', theme: 'defaultTheme', 
					 text:"Fail! " +data.message, type: 'error'});
			}
			},
			error : function(a, b, c) {
			console.log(a);
			}//close error tag
			});//close ajax
			}