<%@page import="java.util.ArrayList"%>
<%@page import="SignInProject.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Modification</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='100%'>
<tr bgcolor='#CCCCFA' align='center' valign='center' height='20'>
<td><h1>LIBRARY MANAGEMENT SYSTEM</h1><h2>Modify Details</h2></td>
</tr>
</table>


<% 
	String id1= request.getParameter("username");
	String id2= request.getParameter("email");
        
    if( request.getAttribute("errorMsgs") != null){
		ArrayList<String> errorMsgs = (ArrayList<String>)request.getAttribute("errorMsgs");
		for(String errorMsg: errorMsgs){	
        %>
            <center>
                <p style="color:red"><%= errorMsg%></p>
            </center>
        <%	}		
	}
%>
<center>
    <p>
    <h4>Please enter the details of the new User.</h4>
</p>
<form action='ModifyUserServlet' method='POST'>

<label>First Name</label><br><input type='text' name='firstname' pattern='[A-Za-z]+' title ='Only letters allowed.' required ><br/><br/>
<label>Last Name</label><br><input type='text' name='lastname' pattern='[A-Za-z]+' title ='Only letters allowed.' required ><br/><br/>
<label>Gender</label><br><input type='text' name='gender' pattern='[A-Za-z]+' title ='Only letters allowed.' required ><br/><br/>
<label>E-mail</label><br><input type='text' name='email' pattern = '[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$' title = 'Please follow a valid email format.' required readonly value =<%=id2 %>><br/><br/>
<label>Username</label><br><input type='text' name='username' pattern='[A-Za-z0-9]+' title ='Only letters allowed.' required readonly value =<%=id1 %>><br/><br/>
<label>Password</label><br><input type='password' name='password' required ><br/><br/>


<input type='submit' value='Modify User'/>
</form>
</body>
</html>