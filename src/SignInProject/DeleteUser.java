package SignInProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> errorMsgs=new ArrayList<String>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		ArrayList<User>users=new DataFetch().fetchUsers();
		request.setAttribute("users",users);
		String username= request.getParameter("uname");
		if(username==null) {
			errorMsgs.add("Please select a book to delete");
			RequestDispatcher view = request.getRequestDispatcher("DeleteUser.jsp");
		    view.forward(request,response);
		    return;
		}
		new DataFetch().deleteUser(username);
		RequestDispatcher view = request.getRequestDispatcher("SuccessDeleteUser.jsp");
		view.forward(request,response);
		return;
	}
}
