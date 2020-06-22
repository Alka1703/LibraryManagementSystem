<%@page import="SignInProject.CheckOut"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Borrowed Books</title>
</head>
<body bgcolor='white'>
	<table border='1' cellpadding='5' cellspacing='0' width='100%'>
        <tr bgcolor='#CCCCFA' align='center' valign='center' height='20'>
        	<td><h1>LIBRARY MANAGEMENT SYSTEM</h1><h2>All Borrowed Books</h2></td>
        </tr>
    </table>
    <br><br>
    
    <%
    ArrayList<CheckOut>checkOut= (ArrayList<CheckOut>)request.getAttribute("checkOut");
    %>
    <table width='100%' border='1'>
	    <thead align='center'>
	    	<th> Transaction Id</th>
	    	<th>Book Id </th>
	    	<th> User Name </th>
	    	<th> Return Date </th>
	    </thead>
	    <%
	    for(CheckOut c : checkOut){
	    %>
	    	<tr align='center'>
		    	<td><%= c.getTransactionId() %></td>
		    	<td><%= c.getBookId() %></td>
		    	<td><%= c.getUserName() %></td>
		    	<td><%= c.getReturnDate() %></td>
		    	
	    	</tr>	
	    <%
	    }
	    %>
    </table>
    <br><br>
    <br>
    <h4>Books past return date: </h4>
        <%
    ArrayList<CheckOut>past_returnDate= (ArrayList<CheckOut>)request.getAttribute("past_returnDate");
    %>
    <table width='100%' border='1'>
	    <thead align='center'>
	    	<th> Transaction Id</th>
	    	<th>Book Id </th>
	    	<th> User Name </th>
	    	<th> Return Date </th>
	    	<th> Fine </th>
	    </thead>
	    <%
	    for(CheckOut c : past_returnDate){
	    %>
	    	<tr align='center'>
		    	<td><%= c.getTransactionId() %></td>
		    	<td><%= c.getBookId() %></td>
		    	<td><%= c.getUserName() %></td>
		    	<td><%= c.getReturnDate() %></td>
		    	<td><%= c.getFine() %></td>
	    	</tr>	
	    <%
	    }
	    %>
    </table>

    <center><p>Click<a href="Admin.jsp"> here </a>to go back to Menu Page</p></center> 
        <center><p>Click<a href="index.jsp"> here </a>to Log Out</p></center> 

</body>
</html>