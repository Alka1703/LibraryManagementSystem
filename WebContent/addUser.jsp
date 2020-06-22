

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@page import = "SignInProject.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Library Management System: Add a new user</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='100%'>
<tr bgcolor='#CCCCFA' align='center' valign='center' height='20'>
<td><h1>LIBRARY MANAGEMENT SYSTEM</h1><h2>Add a New User</h2></td>
</tr>
</table>


<%  User user = new User();
    if( request.getAttribute("errorMsgs") != null){
		java.util.List<String> errorMsgs = (java.util.List<String>)request.getAttribute("errorMsgs");
		for(String errorMsg: errorMsgs){	
        %>
                <center>
                    <p style="color:red"><%= errorMsg%></p>
                </center>
        <%	}		
		user = (User)request.getAttribute("user");	
	}
%>
<center>
    <p>
    <h4>Please enter the details of the new User.</h4>
</p>
<form action='AddUserServlet' method='POST'>

<label>First Name</label><br><input type='text' name='firstname' pattern='[A-Za-z]+' title ='Only letters allowed.' required value="<%=user.getFirstName()%>"><br/><br/>
<label>Last Name</label><br><input type='text' name='lastname' pattern='[A-Za-z]+' title ='Only letters allowed.' required value="<%=user.getLastName()%>"><br/><br/>
<label>Gender</label><br><input type='text' name='gender' pattern='[A-Za-z]+' title ='Only letters allowed.' required value="<%=user.getGender() %>"><br/><br/>
<label>E-mail</label><br><input type='text' name='email' pattern = '[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$' title = 'Please follow a valid email format.' required value="<%=user.getEmail() %>"><br/><br/>
<label>Username</label><br><input type='text' name='username' pattern='[A-Za-z0-9]+' title ='Only letters allowed.' required value="<%=user.getUsername() %>"><br/><br/>
<label>Password</label><br><input type='password' name='password' required value="<%=user.getPassword() %>"><br/><br/>


<input type='submit' value='Add User'/>
</form>
</body>
</html>

