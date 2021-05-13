<%@ page import="com.Payment" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>



<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Payment.js"></script>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<h1>Items Management</h1>
<form id="formPayment" name="formPayment">
 PaymentID:
 <input id="paymentId" name="paymentId" type="text"
 class="form-control form-control-sm">
 <br> Amount:
 <input id="amount" name="amount" type="text"
 class="form-control form-control-sm">
 <br> Payment Date:
 <input id="payDate" name="payDate" type="text"
 class="form-control form-control-sm">
 <br> Card Holder Name:
 <input id="cardHolder" name="cardHolder" type="text"
 class="form-control form-control-sm">
 <br> Card Number:
 <input id="cardNo" name="cardNo" type="text"
 class="form-control form-control-sm">
 <br> CVV:
 <input id="cvv" name="cvv" type="text"
 class="form-control form-control-sm">
 <br> Expiry Date:
 <input id="expDate" name="expDate" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidpaymentIDSave"
 name="hidpaymentIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
    <div id="alertError" class="alert alert-danger"></div>

<br>

<br><br>

<div id="divItemsGrid">
 <%
 Payment paymentObj = new Payment();
 out.print(paymentObj.readPayment());
 %>
</div>

</body>
</html>