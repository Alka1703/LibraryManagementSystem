<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reserve Book</title>
</head>

<body bgcolor="white">
	<table border='1' cellpadding='5' cellspacing='0' width='100%'>
        <tr bgcolor='#CCCCFA' align='center' valign='center' height='20'>
        	<td><h1>LIBRARY MANAGEMENT SYSTEM</h1><h2>Successful Reserve</h2></td>
        </tr>
    </table>
    <%
	String date = request.getAttribute("date").toString();
    %>
<center><p style="color:green">Successfully Reserved. You can pick the book on <%=date%>.</p></center>
    <br><br>
    <center><p>Click<a href="Admin.jsp"> here </a>to go back to Menu Page</p></center> 
        <center><p>Click<a href="index.jsp"> here </a>to Log Out</p></center> 
    

</body>
</html>