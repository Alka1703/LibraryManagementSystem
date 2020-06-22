package SignInProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisitorPage
 */
public class VisitorPage extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Book>books= new DataFetch().fetchBooks();
		request.setAttribute("books", books);
		request.setAttribute("flag", "visitor");
		RequestDispatcher view = request.getRequestDispatcher("BookList.jsp");
		view.forward(request,response);
        return;
		
	}
	


}
