$(document).ready(function()
	{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	});
	
// SAVE ============================================
	$(document).on("click", "#btnSave", function(event)
	{
		// Clear alerts---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
		
		// Form validation-------------------
	    var status = validateformPmanage();
		if (status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
		
		 // If valid------------------------
		 var type = ($("#hidpowerpIdSave").val() == "") ? "POST" : "PUT"; 
		 $.ajax( 
		 { 
		 url : "MonitorAPI", 
		 type : type, 
		 data : $("#formPmanage").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onSaveComplete(response.responseText, status); 
		 } 
 	}); 
});
		
// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
	$("#hidpowerpIdSave").val($(this).data("powerpId"));
	$("#areaId").val($(this).closest("tr").find('td:eq(0)').text());
	$("#pName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#pLocation").val($(this).closest("tr").find('td:eq(2)').text());
	$("#type").val($(this).closest("tr").find('td:eq(3)').text());
	$("#capacity").val($(this).closest("tr").find('td:eq(4)').text());
	$("#status").val($(this).closest("tr").find('td:eq(5)').text());
	});
	
// DELETE===========================================
	$(document).on("click", ".btnRemove", function(event)
	{ 
	 $.ajax( 
	 { 
	 url : "MonitorAPI", 
	 type : "DELETE", 
	 data : "powerpId=" + $(this).data("powerpId"),
	 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 onDeleteComplete(response.responseText, status); 
	 } 
	 }); 
});
// CLIENT-MODEL================================================================
function validateUserForm()
	{
	// Area ID
	if ($("#areaId").val().trim() == "")
	{
	return "Insert First Name.";
	}
	// is string value
	var tmpareaId = $("#areaId").val().trim();
	if ($.isNumeric(tmpareaId))
	{
	return "Area ID cannot be just a value.";
	}
	// Power Plant Name-------------------------------
	if ($("#pName").val().trim() == "")
	{
	return "Insert Power Plant Name.";
	}
	// is string value
	var tmppName = $("#areaId").val().trim();
	if ($.isNumeric(tmppName))
	{
	return "Power Plant Name cannot be just a value.";
	}
	
	//Location -------------------------------
	if ($("#pLocation").val().trim() == "")
	{
	return "Insert Location.";
	}
	// is string value
	var tmppLocation = $("#pLocation").val().trim();
	if ($.isNumeric(tmppLocation))
	{
	return "Location cannot be just a value.";
	}
	// Occupation-------------------------------
	if ($("#type").val().trim() == "")
	{
	return "Insert Occupation.";
	}
	// is string value
	var tmptype = $("#type").val().trim();
	if ($.isNumeric(tmptype))
	{
	return "Type cannot be just a value.";
	}
	// Phone-------------------------------
	if ($("#capacity").val().trim() == "")
	{
	return "Insert Capacity Value.";
	}
	// is numerical value
	var tmpCapacity = $("#capacity").val().trim();
	if (!$.isNumeric(tmpCapacity))
	{
	return "Capacity value cannot contain letters.";
	}
	
	return true;
}

function onSaveComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully saved."); 
	 $("#alertSuccess").show();
	 $("#divUsersGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while saving."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while saving.."); 
	 $("#alertError").show(); 
	 } 
	 $("#hiduIdSave").val(""); 
	 $("#formUser")[0].reset(); 
}

function onDeleteComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully deleted."); 
	 $("#alertSuccess").show();
	 $("#divUsersGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while deleting."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while deleting.."); 
	 $("#alertError").show(); 
 } 
}