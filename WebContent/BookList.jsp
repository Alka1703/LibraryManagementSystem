<%@page import="java.util.ArrayList"%>
<%@page import="SignInProject.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%
String flag= (request.getAttribute("flag").toString());
if(flag.equals("Modify")){

%>
	<title>ModifyBook</title>
<%
}else if(flag.equals("List") || flag.equals("visitor")){	
%>
	<title>Book List</title>
<%
}
%>

</head>
<body bgcolor="white">
	<table border='1' cellpadding='5' cellspacing='0' width='100%'>
        <tr bgcolor='#CCCCFA' align='center' valign='center' height='20'>
        	<%
        	if(flag.equals("modify")){
        	%>
        		<td><h1>LIBRARY MANAGEMENT SYSTEM</h1><h2>Modify Book</h2></td>
        	<%
        	}else if(flag.equals("List") || flag.equals("visitor") ||flag.equals("search")){	
        	%>
        		<td><h1>LIBRARY MANAGEMENT SYSTEM</h1><h2>List Book</h2></td>
        	<%
        	}
        	%>
        </tr>
    </table>
    <br><br>
    <%
   	if(flag.equals("List")|| flag.equals("search") ){
	 %>
    <form action="SearchBook " method="POST">
	  <label for="books">Search a Book :</label>
	  <select id="books" name="books">
	    <option value="authorName">Author</option>
	    <option value="bookName">Title</option>
	    <option value="ISBN">ISBN</option>
	  </select>
	  <input type='text' name='book' required >
	  <input type='submit' name='search' value='Search Book'>
	  <br><br>
	</form>
    <%
   	}
    %>
    
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
    ArrayList<Book>books= (ArrayList<Book>)request.getAttribute("books");  
    %>
    
    
    <form action="ModifyBook" method="POST">
   
   
   
    <table width='100%' border='1'>
	    <thead align='center'>
	    	<th>Book Id </th>
	    	<th> Title</th>
	    	<th>Author Name</th>
	    	<th> ISBN </th>
	    	<th>Publisher </th>
	    	<th>RackNumber </th>
	    	<%
	    	if(flag.equals("List") || flag.equals("modify")){
			 %>
		    	 <th>Total Copies </th>
	    		<th> Available Copies </th>
			<%
		    }
			%>
	    	
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
		    	<td><%= book.getRackNumber() %></td>
		    	<%
		    	if(flag.equals("List") || flag.equals("modify")){
				 %>
			    	 <td><%= book.getTotalCopies() %></td>
		    		 <td><%= book.getAvailableCopies() %></td>
				<%
			    }
				%>
		    	<%
		    	if(flag.equals("modify")){
				%>
		    	<td><input type='radio' name='bkId' value=<%=book.getBookId() %>></td>
		    	<%
		    	}
		    	%>
	    	</tr>	
	    <%
	    }
	    %>
    </table>
    <br><br>
	 <%
	    if(flag.equals("List") || flag.equals("search")){
	 %>
	    	<center><p>Click<a href="Admin.jsp"> here </a>to go back to Menu Page</p></center> 
	        <center><p>Click<a href="index.jsp"> here </a>to Log Out</p></center> 
     <% 	
     }else if(flag.equals("modify")){
      %>
      		<input type='submit' name='modify' value='Modify Book'>    <br><br>
     		<center><p>Click<a href="Admin.jsp"> here </a>to go back to Menu Page</p></center> 
	        <center><p>Click<a href="index.jsp"> here </a>to Log Out</p></center>  
      <% 	
      }
 	 %>
 	 
 	 </form>
</body>
</html>