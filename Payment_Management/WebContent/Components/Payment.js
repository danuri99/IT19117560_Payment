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
var status = validatePaymentForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidpaymentIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
{
url : "PaymentAPI",
type : type,
data : $("#formPayment").serialize(),
dataType : "text",
complete : function(response, status)
{
onPaymentSaveComplete(response.responseText, status);
}
});
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidpaymentIDSave").val($(this).closest("tr").find('#hidpaymentIDUpdate').val());
 $("#paymentID").val($(this).closest("tr").find('td:eq(0)').text());
 $("#amount").val($(this).closest("tr").find('td:eq(1)').text());
 $("#payDate").val($(this).closest("tr").find('td:eq(2)').text());
 $("#cardHolder").val($(this).closest("tr").find('td:eq(3)').text());
 $("#cardNo").val($(this).closest("tr").find('td:eq(1)').text());
 $("#cvv").val($(this).closest("tr").find('td:eq(1)').text());
 $("#expDate").val($(this).closest("tr").find('td:eq(1)').text());
});
// CLIENT-MODEL================================================================
function validatePaymentForm()
{
// PAYEMENT ID
if ($("#paymentId").val().trim() == "")
 {
 return "Insert Payment ID.";
 }

// AMOUNT
if ($("#amount").val().trim() == "")
 {
 return "Insert Amount.";
 }

// is numerical value
var tmpAmount = $("#amount").val().trim();
if (!$.isNumeric(tmpAmount))
 {
 return "Insert a numerical value for Amount.";
 }
// convert to decimal price
 $("#amount").val(parseFloat(tmpAmount).toFixed(2));

// PAYMENT DATE
if ($("#payDate").val().trim() == "")
 {
 return "Insert Payment Date.";
 } 

// CARD HOLDER NAME
if ($("#cardHolder").val().trim() == "")
 {
 return "Insert Card Holder Name.";
 } 

// CARD NUMBER
if ($("#cardNo").val().trim() == "")
 {
 return "Insert Card Number.";
 } 

// CVV
if ($("#cvv").val().trim() == "")
 {
 return "Insert CVV.";
 } 
// EXPIRY DATE------------------------
if ($("#expDate").val().trim() == "")
 {
 return "Insert Card expiry date.";
 }
return true;
}

function onPaymentSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divPaymentGrid").html(resultSet.data);
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
14
 $("#hidpaymentIDSave").val("");
 $("#formPayment")[0].reset();
}
$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "PaymentAPI",
		 type : "DELETE",
		 data : "paymentId=" + $(this).data("paymentId"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onPaymentDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onPaymentDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divPaymentGrid").html(resultSet.data);
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