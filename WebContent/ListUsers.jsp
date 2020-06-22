<%@page import="SignInProject.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Users</title>
</head>
<body bgcolor="white">
	<table border='1' cellpadding='5' cellspacing='0' width='100%'>
        <tr bgcolor='#CCCCFA' align='center' valign='center' height='20'>
        	<td><h1>LIBRARY MANAGEMENT SYSTEM</h1><h2>All Users</h2></td>
        </tr>
    </table>
    <br><br>
    
    <%
    ArrayList<User>users= (ArrayList<User>)request.getAttribute("users");
    %>
    
    <table width='100%' border='1'>
	    <thead align='center'>
	    	<th>Fisrt Name</th>
	    	<th>Last Name</th>
	    	<th>Gender</th>
	    	<th> Username</th>
	    	
	    </thead>
	    <%
	    for(User user: users){
	    %>
	    	<tr align='center'>
		    	<td><%= user.getFirstName()%></td>
		    	<td><%= user.getLastName()%></td>
		    	<td><%= user.getGender() %></td>
		    	<td><%= user.getUsername() %></td>
	    	</tr>	
	    <%
	    }
	    %>
    </table>
    <center><p>Click<a href="Admin.jsp"> here </a>to go back to Menu Page</p></center> 
        <center><p>Click<a href="index.jsp"> here </a>to Log Out</p></center> 
    </body>
</html>