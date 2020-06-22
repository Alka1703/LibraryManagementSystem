<%@page import="SignInProject.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify Book Form</title>
</head>
<body>
	<%
		//String id= ""+(request.getAttribute("id"));
		String id1= request.getAttribute("id").toString();
		//System.out.println(id1);	
		
	%>
	<center>
	<p>
    <h4>Please Enter the details of the Book.</h4>
	</p>
	<form action='ModifyBookServlet' method='POST'>
	
	<label>Book Id</label><br><input type='text' name='bid' required  readonly value=<%=id1 %>><br/><br/>
	<label>Book Name</label><br><input type='text' name='bookname' pattern='[A-Za-z0-9\s]+' title ='Only letters, numbers and space allowed.' required ><br/><br/>
	<label>Author Name</label><br><input type='text' name='authorname' pattern='[A-Za-z\s]+' title ='Only letters and space allowed.' required ><br/><br/>
	<label>Publisher</label><br><input type='text' name='publisher' pattern='[A-Za-z\s]+' title ='Only letters allowed.' required ><br/><br/>
	<label>ISBN</label><br><input type='text' name='ISBN' pattern='[A-Za-z0-9]+' title ='Only letters, numbers and space allowed.' required ><br/><br/>
	<label>Total Copies</label><br><input type='text' name='totalCopies' pattern='[0-9]+' title ='Only numbers allowed.' required><br/><br/>
	<label>Rack Number</label><br><input type='text' name='rackNumber' pattern='[0-9]+' title ='Only numbers allowed.' required ><br/><br/>
	<br/><br/>
	
	
	<input type='submit' value='Modify Book'/><br><br>
	</form>
	
	<center><p>Click<a href="Admin.jsp"> here </a>to go back to Menu Page</p></center> 
	        <center><p>Click<a href="index.jsp"> here </a>to Log Out</p></center> 
</center>
</body>
</html>