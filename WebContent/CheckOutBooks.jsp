<%@page import="SignInProject.CheckOut"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="SignInProject.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Return/CheckOut Books</title>
</head>
<body bgcolor="white">
	<table border='1' cellpadding='5' cellspacing='0' width='100%'>
        <tr bgcolor='#CCCCFA' align='center' valign='center' height='20'>
        	<td><h1>LIBRARY MANAGEMENT SYSTEM</h1><h2>All Books</h2></td>
        </tr>
    </table>
    <br><br>
     <form action="CheckOutServlet" method="POST">
     
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
		    	<td><input type='radio' name='bkgroup1' value=<%=book.getBookId() %>></td>
	    	</tr>	
	    <%
	    }
	    %>
    </table>
    <br><br>
    
    <%
    SimpleDateFormat dm= new SimpleDateFormat("yyyy-MM-dd");
    Date date= new Date();
    String today= dm.format(date);
    Calendar c= new GregorianCalendar();
    c.add(Calendar.DAY_OF_YEAR, 10);
    Date rtDate= c.getTime();
    String returnDate= dm.format(rtDate);
    
    
    %>
    
    <%if( request.getAttribute("errorMsgs") != null){%>
		<% ArrayList<String> errorMsgs = (ArrayList<String>)request.getAttribute("errorMsgs");
		for(String errorMsg: errorMsgs){	
        %>
		<p style="color:red"><%= errorMsg%></p>
        <%	} } %>
 
	    <label>UserName: <input type='text' name='username'></label>
	    <label>Date Of Return: <input type='text' name='dateofreturn' value=<%=returnDate %>></label>
	    
	    <input type='submit' name='checkout' value='CheckOut Book'>
	    <input type='submit' name='return' value='Return Book'> 
            <input type='submit' name='renew' value='Renew Book'>
            <input type='submit' name='reserve' value='Reserve Book'>    

	</form>
	
	<center><p>Click<a href="Admin.jsp"> here </a>to go back to Menu Page</p></center> 
        <center><p>Click<a href="index.jsp"> here </a>to Log Out</p></center> 
</body>
</html>