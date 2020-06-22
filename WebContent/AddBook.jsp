<%@page import="java.util.ArrayList"%>
<%@page import="SignInProject.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Book</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='100%'>
<tr bgcolor='#CCCCFA' align='center' valign='center' height='20'>
<td><h1>LIBRARY MANAGEMENT SYSTEM</h1><h2>Add a New Book</h2></td>
</tr>
</table>


<%  Book book = new Book();
	
    if( request.getAttribute("errorMsgs") != null){
		ArrayList<String> errorMsgs = (ArrayList<String>)request.getAttribute("errorMsgs");
		for(String errorMsg: errorMsgs){	
        %>
            <center>
                <p style="color:red"><%= errorMsg%></p>
            </center>
        <%	}
		book=(Book)request.getAttribute("book");
		System.out.println(book); 
	}
    
%>
<center>
    <p>
    <h4>Please enter the details of the new Book.</h4>
</p>
<form action='AddBookServlet' method='POST'>

<label>Book Name</label><br><input type='text' name='bookname' pattern='[A-Za-z0-9-.\s]+' title ='Only letters allowed.' required value="<%=book.getBookName()%>"><br/><br/>
<label>Author Name</label><br><input type='text' name='authorname' pattern='[A-Za-z.\s]+' title ='Only letters allowed.' required value="<%=book.getAuthorName()%>"><br/><br/>
<label>Publisher</label><br><input type='text' name='publisher' pattern='[A-Za-z.\s]+' title ='Only letters allowed.' required value="<%=book.getPublisher() %>"><br/><br/>
<label>ISBN</label><br><input type='text' name='ISBN' pattern='[A-Za-z0-9]+' title ='Only letters allowed.' required value="<%=book.getISBN() %>"><br/><br/>
<label>Total Copies</label><br><input type='text' name='totalCopies' pattern='[0-9]+' title ='Only numbers allowed.' required ><br/><br/>
<label>Rack Number</label><br><input type='text' name='rackNumber' pattern='[0-9]+' title ='Only numbers allowed.' required ><br/><br/>
<br/><br/>


<input type='submit' value='Add Book'/><br><br>
</form>

<center><p>Click<a href="Admin.jsp"> here </a>to go back to Menu Page</p></center> 
        <center><p>Click<a href="index.jsp"> here </a>to Log Out</p></center> 

</body>
</html>