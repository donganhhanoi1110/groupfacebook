$(document)
			.ready(
					function() {
						$(function() {
							var token = $("meta[name='_csrf']").attr("content");
							var header = $("meta[name='_csrf_header']").attr(
									"content");
							$(document).ajaxSend(function(e, xhr, options) {
								xhr.setRequestHeader(header, token);
							});
						});

						$("#mytable2").dataTable();
						$("#mytable3").dataTable();
						var table = $("#mytable1").DataTable({
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
						$("#selectall3").click(function(event) { //on click 
							if (this.checked) { // check select status
								$(".checkbox3").each(function() { //loop through each checkbox
									this.checked = true; //select all checkboxes with class "checkbox1"               
								});
							} else {
								$(".checkbox3").each(function() { //loop through each checkbox
									this.checked = false; //deselect all checkboxes with class "checkbox1"                       
								});
							}
						});
						
						$(document).on("click", "#createSchedule", function(e) {
							e.preventDefault();
							var mydata = $("#formCreateSchedule").serialize();
							console.log("mydata" + mydata);
							$.ajax({
								type : 'post',
								url : 'postCreateScheduleJson',
								data : mydata,
								datatype : 'json',
								success : function(data) {
									console.log(data);

									if (data.success == true) {
										 noty({dismissQueue: true, force: true, 
											 layout: 'topCenter', theme: 'defaultTheme', 
											 text:"Successful!!! "+data.message, type: 'success'});
										table.row.add( [
												          data.schedule.idSchedule,
												          data.schedule.groupMessage,
												          data.schedule.datePost,
												          data.schedule.state,
												          "<button type='button' style='padding: 5px 10xp;' class='btn btn-info btn-sm my_detail' data-toggle='modal' data-target='#myModal'>Group</button>",
												          "<a class='btn btn-info btn-sm' href='subDeleteSchedule?id="+data.schedule.idSchedule+"'>Delete</a>"
												        ] ).draw();
										refreshValueSchedule(data.message);
									} else {
										noty({dismissQueue: true, force: true, 
											 layout: 'topCenter', theme: 'defaultTheme', 
											 text:"Fail!!!" + data.message, type: 'error'});
									}

								},
								error : function(a, b, c) {
									noty({dismissQueue: true, force: true, 
										 layout: 'topCenter', theme: 'defaultTheme', 
										 text:"Fail!!! You're getting error!!!", type: 'error'});
									console.log(a);
								}
							});

						});
						
						$(document).on("click", "#post", function(e) {
							e.preventDefault();
							var mydata = $("#formCreateSchedule").serialize();
							console.log("mydata" + mydata);
							$.ajax({
								type : 'post',
								url : 'postToWallGroupJsonInSchedulePage',
								data : mydata,
								datatype : 'json',
								success : function(data) {
									console.log(data);

									if (data.success == true) {
										 noty({dismissQueue: true, force: true, 
											 layout: 'topCenter', theme: 'defaultTheme', 
											 text:"Successful!!! "+data.message, type: 'success'});
									} else {
										if(data.exceptions.length > 0) {
										var errorInfos="Please correct following errors:";
										for(var i=0;i<data.exceptions.length;i++) {
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
										 text:"Fail!!! Cant post.", type: 'error'});
									console.log(a);
								}
							});

						});

						$('#mytable1 tbody')
								.on(
										'click',
										'tr',
										function() {
											var detail = table.row(this).data();
											console.log(detail[2]);
											$
													.ajax({
														url : "viewDetailScheduleJson?id="
																+ detail[0],
														type : "GET",
														datatype : "html",
														timeout : 3000,
														success : function(data) {
															console.log(data);
															document
																	.getElementById("insert_my_detail").innerHTML = data;
														},
														error : function(data) {
															//alert("error loading page");
															console.log(data);

														}
													});

										});

});//end all document

	function refreshValueSchedule(message) {
		var groupMessage = $(".groupMessage");
		var groupLink = $(".groupLink");
		var groupName= $(".groupName");
		var groupCaption = $(".groupCaption");
		var groupDescription = $(".groupDescription");
		var myDate = $(".myDate");
		var myTime = $(".myTime");
		var file2 = $(".file2");
		file2.val('');
		groupMessage.val('');
		groupLink.val('');
		groupName.val('');
		groupCaption.val('');
		groupDescription.val('');
		myDate.val('');
		myTime.val('');
		
		$.ajax({
			type : 'get',
			url : 'refreshValueSchedule',
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