package SignInProject;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<String> errorMsgs=new ArrayList<String>();
		request.setAttribute("errorMsgs", errorMsgs);
	   
		String bookName=request.getParameter("bookname");
		String authorName=request.getParameter("authorname");
		String publisher=request.getParameter("publisher");
        String ISBN=request.getParameter("ISBN");
		String tc=request.getParameter("totalCopies");
		int rackNumber=Integer.parseInt(request.getParameter("rackNumber"));
		System.out.println(rackNumber);
		int availableCopies=0, totalCopies=0;
		totalCopies=Integer.parseInt(tc);
		availableCopies=totalCopies;
		
		Book book= new Book(0,bookName, authorName, publisher, ISBN, rackNumber, totalCopies, availableCopies);
		System.out.println(book);
		request.setAttribute("book", book);  
		
        boolean insertBooks= new DataFetch().insertBooks(book,errorMsgs);
        if(insertBooks==true) {
        	request.setAttribute("flag", "form");
        	RequestDispatcher view = request.getRequestDispatcher("SuccessAddBook.jsp");
            view.forward(request, response);
            return;   
        }
        else {
            RequestDispatcher view = request.getRequestDispatcher("AddBook.jsp");
            view.forward(request, response);
            return;   
        }
	}
}
