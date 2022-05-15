<%@page import= "com.Monitor" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Power Consumption Monitoring</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6">

				<h2>Power Consumption Management</h2>
				<br>
				<form id="formPmanage" name="formPmanage" method="post" action="index.jsp">
				<div>
				<label for="AreaID" class="form-label text-dark">Area ID:-</label>
				<input id="areaId" name="areaId" type="text"
						class="input-group input-group-sm mb-3">
				</div>
					<br>
				<div>
				<label for="powerPlantName" class="form-label">Power Plant Name:-</label>
				<input id="pName" name="pName" type="text"
						class="input-group input-group-sm mb-3">
				</div>
					<br>
				<div>
				<label for="Location" class="form-label">Location:-</label>
				<input id="pLocation" name="pLocation" type="text"
						class="input-group input-group-sm mb-3">
				</div>
					<br>
				<div>
				<label for="type" class="form-label">Type</label>  
				<input id="type" name="type" type="text"
						class="input-group input-group-sm mb-3"> 
					</div>
					<br>
				<div>
				<label for="capacity" class="form-label">Capacity:-</label> 
				<input id="capacity" name="capacity" type="text"
						class="input-group input-group-sm mb-3"> 
				</div>
					<br> 
					<div>
				<label for="type" class="form-label">Status:-</label>  
				<input id="status" name="status" type="text"
						class="input-group input-group-sm mb-3"> 
					</div>
					<br>
				<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary">
						
					<input type="hidden" id="hidpowerpIdSave" name="hidpowerpIdSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>


				<br>
				<div id="divUsersGrid"></div>
				<%
					Monitor f = new Monitor();
					out.print(f.readPowerPlant());
				%>
			</div>
		</div>
	</div>

</body>
</html>