<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Update teacher - University</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h2>
			<a href="."><span class="badge badge-secondary">Back to Home Page</span></a>
		</h2>
	</div>

<% Integer teacherID = Integer.parseInt(request.getParameter("id")); %>
<% if(teacherID != null) { %>
	<div class="container">
		<form id="TeacherData" method="post"  enctype="application/json">
			<div class="form-group">
				<label for="inputId">ID</label> <input type="text"
					class="form-control" id="inputId" placeholder="id number" name="id">
			</div>
			<div class="form-group">
				<label for="inputFName">First Name</label> <input type="text"
					class="form-control" id="inputFName" placeholder="firstname"
					name="fName">
			</div>
			<div class="form-group">
				<label for="inputLName">Last Name</label> <input type="text"
					class="form-control" id="inputLName" placeholder="lastname"
					name="lName">
			</div>
			<div class="form-group">
				<label for="inputAddress">Address</label> <input type="text"
					class="form-control" id="inputAddress" placeholder="department"
					name="address">
			</div>
			<button type="submit" class="btn btn-primary" id="UpdateTeacher">Update
				Teacher</button>
		</form>
	</div>
	
	<script>
		$(document).ready(function() {
			var url = "api/teachers/teacher/" + <%= teacherID %>;
			$.get(url, function(data, status) {
				$("#inputId").val(data.id);
				$("#inputFName").val(data.fName);
				$("#inputLName").val(data.lName);
				$("#inputAddress").val(data.address);
			});
		});
	</script>
	
	<script>
		$(document).ready(function() {
			$("#UpdateTeacher").on('click', function() {
				var params = $("#TeacherData").serializeArray();
				var param = {};
				for(i = 0 ; i < params.length ; i++) {
					var name = params[i].name;
					var value = params[i].value;
					param[name] = value;
				}
				
				$.ajax({
					type : "POST",
					url : "api/teachers/updteacher",
					data : JSON.stringify(param),
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					success : function(resp) {
						console.log(resp);
					},
					failure : function(errMsg) {
						console.log(errMsg);
					}
				});
			});
		});
	</script>
<% } %>

	<!--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>