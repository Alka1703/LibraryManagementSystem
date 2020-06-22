<%@page import="SignInProject.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Users</title>
</head>
<body bgcolor="white">
	<table border='1' cellpadding='5' cellspacing='0' width='100%'>
        <tr bgcolor='#CCCCFA' align='center' valign='center' height='20'>
        	<td><h1>LIBRARY MANAGEMENT SYSTEM</h1><h2>Delete Users</h2></td>
        </tr>
    </table>
    <br><br>
    <form action="DeleteUser" method="POST">
    <%
    if( request.getAttribute("errorMsgs") != null)
    {
		ArrayList<String> errorMsgs = (ArrayList<String>)request.getAttribute("errorMsgs");
		for(String errorMsg: errorMsgs){	
		    %>
			<p style="color:red"><%= errorMsg%></p>
		    <%	
		} 
	} 
    ArrayList<User>users= (ArrayList<User>)request.getAttribute("users");
    %>
    
    <table width='100%' border='1'>
	    <thead align='center'>
	    	<th>First Name</th>
	    	<th>Last Name</th>
	    	<th>Gender</th>
	    	<th>Email</th>
	    	<th> Username</th>
	    	
	    	
	    </thead>
	    <%
	    for(User user: users){
	    %>
	    	<tr align='center'>
		    	<td><%= user.getFirstName()%></td>
		    	<td><%= user.getLastName()%></td>
		    	<td><%= user.getGender() %></td>
		    	<td><%= user.getEmail() %></td>
		    	<td><%= user.getUsername() %></td>
		    	<td><input type='radio' name='uname' value=<%=user.getUsername() %> ></td>
	    	</tr>	
	    <%
	    }
	    %>
    </table>
    <br><br>
    
	<input type='submit' name='delete' value='Delete User'>    
	</form>
	<center><p>Click<a href="Admin.jsp"> here </a>to go back to Menu Page</p></center> 
        <center><p>Click<a href="index.jsp"> here </a>to Log Out</p></center> 
    </body>
</html>