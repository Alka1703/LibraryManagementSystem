<%@page import="SignInProject.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Library Management System : List Books</title>
</head>
<body bgcolor="white">
	<table border='1' cellpadding='5' cellspacing='0' width='100%'>
        <tr bgcolor='#CCCCFA' align='center' valign='center' height='20'>
        	<td><h1>LIBRARY MANAGEMENT SYSTEM</h1><h2>All Books</h2></td>
        </tr>
    </table>
    <br><br>
    
    <%
    ArrayList<Book>books= (ArrayList<Book>)request.getAttribute("books");
    %>
    
    <table width='100%' border='1'>
	    <thead align='center'>
	    	<th>Book Id </th>
	    	<th> Book Name</th>
	    	<th>Author Name</th>
	    	<th> ISBN </th>
	    	<th>Publisher </th>
	    	<th>Total Copies </th>
	    	<th> Available Copies </th>
	    </thead>
	    <%
	    for(Book book : books){
	    %>
	    	<tr align='center'>
		    	<td><%= book.getBookId() %></td>
		    	<td><%= book.getBookName() %></td>
		    	<td><%= book.getAuthorName() %></td>
		    	<td><%= book.getISBN() %></td>
		    	<td><%= book.getPublisher() %></td>
		    	<td><%= book.getTotalCopies() %></td>
		    	<td><%= book.getAvailableCopies() %></td>
	    	</tr>	
	    <%
	    }
	    %>
    </table>
    
    <center><p>Click<a href="Admin.jsp"> here </a>to go back to Menu Page</p></center> 
        <center><p>Click<a href="index.jsp"> here </a>to Log Out</p></center> 

</body>
</html>