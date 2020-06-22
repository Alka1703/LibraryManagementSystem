package SignInProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteBooks
 */
public class DeleteBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errorMsgs=new ArrayList<String>();
		request.setAttribute("errorMsgs", errorMsgs);

		ArrayList<Book>books= new DataFetch().fetchBooks();
		request.setAttribute("books", books);
		
		String id= request.getParameter("bkId");
		if(id==null) {
			errorMsgs.add("Please select a book to delete");
			RequestDispatcher view = request.getRequestDispatcher("DeleteBooks.jsp");
            view.forward(request,response);
            return;
		}
		int bookId=Integer.parseInt(id);
		new DataFetch().deleteBook(bookId);
		
		RequestDispatcher view = request.getRequestDispatcher("SuccessDelete.jsp");
        view.forward(request,response);
        return; 
		
    
	}
}
