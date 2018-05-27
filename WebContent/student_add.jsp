<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Add new student - University</title>
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
	
	<div class="container">
		<form id="StudentData" method="post" enctype='application/json'>
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
				<label for="inputDept">Department</label> <input type="text"
					class="form-control" id="inputDept" placeholder="department"
					name="dept">
			</div>
			<div class="form-group col-md-4">
				<label for="inputTeacher">Supervisor</label> <select
					id="inputTeacher" class="form-control" name="teacher">
					<option selected>Choose...</option>
					<option>...</option>
				</select>
			</div>
			<button type="submit" class="btn btn-primary" id="AddStudent">Add
				Student</button>
		</form>
	</div>

	<script>
		$(document).ready(function(){
			$("#AddStudent").on('click', function(){
				var params = $("#StudentData").serializeArray();
				var param = {};
				for(i = 0 ; i < params.length - 1 ; i++) {
					var name = params[i].name;
					var value = params[i].value;
					param[name] = value;
				}
				var name = params[4].name;
				var value = params[4].value;
				param[name] = {};
				param[name]["id"] = value;
				
				$.ajax({
					type : "POST",
					url : "api/students/addstudent",
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

	<script>
		$(document).ready(function() {
			$.get("api/teachers/allteachers",function(data, status) {
				document.getElementById("inputTeacher").innerHTML = optionMaker(data);
			});
		});

		function optionMaker(data) {
			var o = "";
			for (i = 0; i < data.length; i++) {
				o += "<option value=\"" + data[i].id + "\">"
						+ data[i].fName + " " + data[i].lName + "</option>";
			}
			return o;
		}
	</script>

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