package SignInProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errorMsgs=new ArrayList<String>();
					
		request.setAttribute("errorMsgs", errorMsgs);
	    try{
	    	String bid=request.getParameter("bid");
			String bookName=request.getParameter("bookname");
			String authorName=request.getParameter("authorname");
			String publisher=request.getParameter("publisher");
	        String ISBN=request.getParameter("ISBN");
			String tc=request.getParameter("totalCopies");
			int rackNumber=Integer.parseInt(request.getParameter("rackNumber"));
			int totalCopies=0;
			int bookId=Integer.parseInt(bid);	
			totalCopies=Integer.parseInt(tc);
						
        	Book book= new Book(bookId,bookName,authorName,publisher,ISBN ,rackNumber, totalCopies,0);
            new DataFetch().modifyBook(book);
            RequestDispatcher view = request.getRequestDispatcher("SuccessModifyBook.jsp");
            view.forward(request, response);
            return;     
		} 
		catch(RuntimeException e)  {
			errorMsgs.add(e.getMessage());
	    }	
	}

}
