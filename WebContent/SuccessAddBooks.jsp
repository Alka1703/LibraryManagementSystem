<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success Books Add</title>
</head>
<body bgcolor='white'>
	 <table border='1' cellpadding='5' cellspacing='0' width='100%'>
        <tr bgcolor='#CCCCFA' align='center' valign='center' height='20'>
        	<th><h1>LIBRARY MANAGEMENT SYSTEM</h1><h2>Books Added Successfully</h2></th>
        </tr>
     </table>
     <br><br>
	<center>
	<% 
		String flag= request.getAttribute("flag").toString();
		if(flag.equals("file")){
			if( request.getAttribute("errorMsgs") != null){%>
				<p>Books not added from file: </p>
				<% ArrayList<String> errorMsgs = (ArrayList<String>)request.getAttribute("errorMsgs");
				for(String errorMsg: errorMsgs){	
		        %>
		            <center>
		                <p style="color:red"><%= errorMsg%></p>
		            </center>
		        <% 
		        }
			}
		}
	%>
	
        <h3> Books have been successfully added to our database.</h3><br><br>
        <center><p>Click<a href="Admin.jsp"> here </a>to go back to Menu Page</p></center> 
        <center><p>Click<a href="index.jsp"> here </a>to Log Out</p></center> 
        
</body>
</html>
</html>