

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
        <meta charset="windows-1252">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body bgcolor='white'>
    <center>
        <table border='1' cellpadding='5' cellspacing='0' width='100%'>
        <tr bgcolor='#CCCCFA' align='center' valign='center' height='20'>
        	<th><h1>LIBRARY MANAGEMENT SYSTEM</h1></th>
        </tr>
     </table>
     <br><br>


        <%if( request.getAttribute("errorMsgs") != null){
		List<String> errorMsgs = (List<String>)request.getAttribute("errorMsgs");
		for(String errorMsg: errorMsgs){	
        %>
		<p style="color:red"><%= errorMsg%></p>
                <br>
        <%	} } %>
        <form action="SignInServlet" method="POST">
            <label>User Name: </label><input type="text" name="username" size="20" ><br><br>
            <label>Password : </label><input type="password" name="password" size="20">
                <br/><br/>
                <input type="submit" value="Sign in">
        </form>
		<br>
		<form action="VisitorPage" method="POST">
		<input type="submit" value="Visitor's Page">
        <br><br>
        <p> Not registered yet? Click <a href="addUser.jsp" >here</a> to register.</p>
        
    </center>        
</body>
</html>
