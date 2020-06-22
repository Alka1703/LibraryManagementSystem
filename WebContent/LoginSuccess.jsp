<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@page import = "SignInProject.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Login Success</title>
    </head>
    <body bgcolor="white">
	<table border='1' cellpadding='5' cellspacing='0' width='100%'>
        <tr bgcolor='#CCCCFA' align='center' valign='center' height='20'>
        	<td><h1>LIBRARY MANAGEMENT SYSTEM</h1></td>
        </tr>
    </table>
    <br><br>
	<%
    ArrayList<CheckOut>checkOut= (ArrayList<CheckOut>)request.getAttribute("checkedOutItems");
    String email = request.getAttribute("email").toString();
    System.out.println(email);
    String userName = request.getAttribute("userName").toString();
    
    for(CheckOut c: checkOut)
		System.out.println(c);
   
    %>
    
    <table width='100%' border='1'>
	    <thead align='center'>
	    	<th> Transaction Id</th>
	    	<th>Book Id </th>
	    	<th> User Name </th>
	    	<th> Return Date</th>
	    </thead>
	    <%
	    for(CheckOut c : checkOut){
	    %>
	    	<tr align='center'>
		    	<td><%= c.getTransactionId() %></td>
		    	<td><%= c.getBookId() %></td>
		    	<td><%= c.getUserName() %></td>
		    	<td><%=c.getReturnDate() %></td>
	    	</tr>	
	    <%
	    }
	    %>
    </table>
    <br>
    <center>
        <form action='ModifyUserSelf.jsp' method='POST'>
		     <input type='submit' name='modifyUser' value='Modify My Details'>
                     <input type='hidden' name='username' value=<%= userName %>>
                     <input type='hidden' name='email' value=<%= email %>>
                     
        </form>
        
        <center><p>Click<a href="index.jsp"> here </a>to Log Out</p></center> 
    </center>
    </body>
</html>
