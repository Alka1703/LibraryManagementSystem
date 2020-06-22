
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Add User Success</title>
    </head>
    <body bgcolor='white'>
	 <table border='1' cellpadding='5' cellspacing='0' width='100%'>
        <tr bgcolor='#CCCCFA' align='center' valign='center' height='20'>
        	<th><h1>LIBRARY MANAGEMENT SYSTEM</h1><h2>User Added Successfully</h2></th>
        </tr>
     </table>
     <br><br>
     <%
   
    	int flag= Integer.parseInt(request.getAttribute("flag").toString());
    
    %>  
    <center>
        <h3> User has been successfully added to our database.</h3><br>
        
    </center>
    
     <%
	    if(flag==0){
	 %>
    	<center><p>Click<a href="Admin.jsp"> here </a>to go back to Menu Page</p></center> 
        <center><p>Click<a href="index.jsp"> here </a>to Log Out</p></center> 
	<%
	    }else if(flag==1){
	%>
	    	<center><p>Click<a href="index.jsp"> here </a>to go back</p></center> 
    <% 	
	    }
	%>
    </body>
</html>
