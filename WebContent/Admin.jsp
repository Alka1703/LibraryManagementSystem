<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
</head>

 <body bgcolor='white'>
	 <table border='1' cellpadding='5' cellspacing='0' width='100%'>
        <tr bgcolor='#CCCCFA' align='center' valign='center' height='20'>
        	<th><h1>LIBRARY MANAGEMENT SYSTEM</h1><h2>Welcome to Admin Page</h2></th>
        </tr>
     </table>
     <br><br>
     <div style="padding-left:5%">
     <form action='AdminSection' method='POST'>	
        <h4>This Page is for Admin Section</h4><br>
        <input type="radio" name="menuSelection" value="AddBook">Add Book<br>
        <input type="radio" name="menuSelection" value="AddBooksFromFile">Add Books From File<br>
        <input type="radio" name="menuSelection" value="ListBooks">List Books/Search Books<br>
        <input type="radio" name="menuSelection" value="ModifyBook">Modify Book<br>
        <input type="radio" name="menuSelection" value="ListBorrowedBooks">List Borrowed Books<br>
        <input type="radio" name="menuSelection" value="DeleteBooks">Delete Books<br>
        <input type="radio" name="menuSelection" value="AddUser">Add User<br>
        <input type="radio" name="menuSelection" value="AddUsersFromFile">Add Users From File<br>
        <input type="radio" name="menuSelection" value="ListUsers">List Users<br>
        <input type="radio" name="menuSelection" value="ModifyUser">Modify User<br>
        <input type="radio" name="menuSelection" value="DeleteUsers">Delete Users<br>
        
        <input type="radio" name="menuSelection" value="ReturnBooks">Checkout/Return/Renew/Reserve Books<br>
        <br>
        <input type= "submit" value="Submit"><br>
        <br>
       
     </form> 
     </div>      
     <center>
     	
     	<p>Click<a href="index.jsp"> here </a>to log out</p>
     </center>   
       
	 


</body>
</html>