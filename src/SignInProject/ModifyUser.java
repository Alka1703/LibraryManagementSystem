package SignInProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errorMsgs=new ArrayList<String>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		try {
			ArrayList<User>users= new DataFetch().fetchUsers();
			request.setAttribute("users",users);
			String data= request.getParameter("email");
            String[] str = data.split(",");
            String email = str[0];
            String username = str[1];
			if(email==null) {
				errorMsgs.add("Please select a user to modify");
				RequestDispatcher view = request.getRequestDispatcher("UserList.jsp");
                view.forward(request,response);
                return;
            }
			else {
				RequestDispatcher view = request.getRequestDispatcher("ModifyUserForm.jsp");
				request.setAttribute("username", username);
				request.setAttribute("email", email);
                view.forward(request,response);
                return;
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
