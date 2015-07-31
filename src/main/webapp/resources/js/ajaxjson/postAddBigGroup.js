
$(document).ready(function() {
					$(function() {
						var token = $("meta[name='_csrf']").attr("content");
						var header = $("meta[name='_csrf_header']").attr("content");
						$(document).ajaxSend(function(e, xhr, options) {
							xhr.setRequestHeader(header, token);
						});
					});
					
					$("#mytable2").dataTable();
					var table=$("#mytable1").DataTable({
						"bFilter" : true,
						responsive : true
					});

					$("#selectall").click(function(event) { //on click 
						if (this.checked) { // check select status
							$(".checkbox1").each(function() { //loop through each checkbox
								this.checked = true; //select all checkboxes with class "checkbox1"               
							});
						} else {
							$(".checkbox1").each(function() { //loop through each checkbox
								this.checked = false; //deselect all checkboxes with class "checkbox1"                       
							});
						}
					});
					
					$(document).on("click","#postAddBigGroup",function(e) {
						e.preventDefault();
						 var n = noty({
					            text        : 'Do you want to Add this BigGroup?',
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
					                    addBiggroup();
					                }
					                },
					                {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
					                    $noty.close();
					                }
					                }
					            ]
					        });

		});//end if post AddbigGroup
					$(document).on("click","#postEditBigGroup",function(e) {
						e.preventDefault();
						 var n = noty({
					            text        : 'Do you want to Edit this BigGroup?',
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
					                    editBiggroup();
					                }
					                },
					                {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
					                    $noty.close();
					                }
					                }
					            ]
					        });

		});//end if post AddbigGroup
			function addBiggroup() {
						var mydata=$("#formAddBigGroup").serialize();
						console.log("mydata"+mydata);
							$.ajax({
						type : 'post',
						url : 'postAddBigGroupJson',
						data : mydata,
						datatype : 'json',											
						success : function(data) {
							console.log(data);
							
								if (data.success == true) {
									 noty({dismissQueue: true, force: true, 
										 layout: 'topCenter', theme: 'defaultTheme', 
										 text:"Success! "+data.message, type: 'success'});
									table.row.add( [
									          '<a class="btn btn-primary" onclick="deleteBiggroupId(\''+data.bigGroupFace.idBigGroupFace+'\')">Delete</a>',
									          '<a href="editBigGroup?id='+data.bigGroupFace.idBigGroupFace+'" class=" btn btn-primary">Edit</a>',
									          "<img src='"+data.bigGroupFace.uploadedFile.path+"' width='50px' height='50px'/>",
									          "<a href='home?id="+data.bigGroupFace.idBigGroupFace+"'>"+data.bigGroupFace.nameBigGroupFace+"</a>"
									        ] ).draw();
									//refresh value
									refreshValueBiggroup(data.message);
								} else {
									noty({dismissQueue: true, force: true, 
										 layout: 'topCenter', theme: 'defaultTheme', 
										 text:"Fail!!!" + data.message, type: 'error'});
								}
							
						},
						error : function(a, b, c) {
							noty({dismissQueue: true, force: true, 
								 layout: 'topCenter', theme: 'defaultTheme', 
								 text:"Fail!!! Add BigGroup Fail", type: 'error'});					
							console.log(a);
						}
					});
			}//end addbiggroup
			
			function editBiggroup() {
				var mydata=$("#formEditBigGroup").serialize();
				console.log("mydata"+mydata);
					$.ajax({
				type : 'post',
				url : 'postEditBigGroupJson',
				data : mydata,
				datatype : 'json',											
				success : function(data) {
					console.log(data);
					
						if (data.success == true) {
							 noty({dismissQueue: true, force: true, 
								 layout: 'topCenter', theme: 'defaultTheme', 
								 text:"Success! "+data.message, type: 'success'});
							 window.location.href = 'addBigGroup';	
						} else {
							noty({dismissQueue: true, force: true, 
								 layout: 'topCenter', theme: 'defaultTheme', 
								 text:"Fail!!!" + data.message, type: 'error'});
						}
					
				},
				error : function(a, b, c) {
					noty({dismissQueue: true, force: true, 
						 layout: 'topCenter', theme: 'defaultTheme', 
						 text:"Fail!!! Edit BigGroup Fail", type: 'error'});					
					console.log(a);
				}
			});
	}//end addbiggroup
});//end document

	function refreshValueBiggroup(message) {
		var nameBigGroupFace = $(".nameBigGroupFace");
		var file2 = $(".file2");
		file2.val('');
		nameBigGroupFace.val('');
		
		$.ajax({
			type : 'get',
			url : 'refreshValueBiggroup',
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

	function deleteBiggroupId(biggroupId) {
		 var n = noty({
	            text        : 'Do you want to Delete this Biggroup?',
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
	                    deleteMyBiggroupId(biggroupId);
	                }
	                },
	                {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
	                    $noty.close();
	                }
	                }
	            ]
	        });

}//end if post deleteBiggroup	

function deleteMyBiggroupId(biggroupId) {
		$.ajax({
	type : 'post',
	url : 'subDeleteBigGroupFace?id='+biggroupId,
	datatype : 'json',											
	success : function(data) {
		console.log(data);
		
			if (data.success == true) {
				 noty({dismissQueue: true, force: true, 
					 layout: 'topCenter', theme: 'defaultTheme', 
					 text:"Success! "+data.message, type: 'success'});

				 $('#mytable1').dataTable().fnDeleteRow($("#deleteRow_"+biggroupId));
				//refresh value
				refreshValueBiggroup(data.message);
			} else {
				noty({dismissQueue: true, force: true, 
					 layout: 'topCenter', theme: 'defaultTheme', 
					 text:"Fail!!!" + data.message, type: 'error'});
			}
		
	},
	error : function(a, b, c) {
		noty({dismissQueue: true, force: true, 
			 layout: 'topCenter', theme: 'defaultTheme', 
			 text:"Fail!!! Delete BigGroup Fail", type: 'error'});					
		console.log(a);
	}
});
}//end delteteBiggroup
