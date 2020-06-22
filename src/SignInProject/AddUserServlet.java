/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SignInProject;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> errorMsgs=new ArrayList<String>();
		request.setAttribute("errorMsgs", errorMsgs);
        try  {
			String firstName=request.getParameter("firstname").trim();
			String lastname=request.getParameter("lastname").trim();
			String gender=request.getParameter("gender").trim();
	        String email=request.getParameter("email").trim();
			String userName=request.getParameter("username").trim();
			String password=request.getParameter("password").trim();
		
			User user =new User(firstName,lastname,gender,email,userName,password);
			request.setAttribute("user", user);	
            boolean insertUser= new DataFetch().insertUser(user, errorMsgs); 
            if(insertUser==true) {
                RequestDispatcher view = request.getRequestDispatcher("SuccessAddUser.jsp");
                view.forward(request, response);
                return;
            }
            else {
            	RequestDispatcher view = request.getRequestDispatcher("addUser.jsp");
    			view.forward(request, response);
    			return;
            }  
		}
		catch(RuntimeException e)  {
			errorMsgs.add(e.getMessage());
			RequestDispatcher view = request.getRequestDispatcher("addUser.jsp");
			view.forward(request, response);
	    }
    }
}
