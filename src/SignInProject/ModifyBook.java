package SignInProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyBook extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> errorMsgs=new ArrayList<String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			ArrayList<Book>books= new DataFetch().fetchBooks();
			request.setAttribute("books", books);
			String id= request.getParameter("bkId");
			if(id==null) {
				errorMsgs.add("Please select a book to modify");
				request.setAttribute("flag", "modify");
				RequestDispatcher view = request.getRequestDispatcher("BookList.jsp");
                view.forward(request,response);
                return;
			}
			else {
				RequestDispatcher view = request.getRequestDispatcher("ModifyBookForm.jsp");
				request.setAttribute("id", id);
                view.forward(request,response);
                return;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
