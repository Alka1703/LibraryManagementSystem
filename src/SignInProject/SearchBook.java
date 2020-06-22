package SignInProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errorMsgs=new ArrayList<String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			ArrayList<Book>books= new DataFetch().fetchBooks();
			
			String option= request.getParameter("books");
			if(option.equals("")) {
				errorMsgs.add("Please select an option");
				request.setAttribute("flag", "search");
				request.setAttribute("books", books);
				RequestDispatcher view = request.getRequestDispatcher("BookList.jsp");
                view.forward(request,response);
                return;
			}
			else {
				Boolean found=false;
				ArrayList<Book>list=new ArrayList<Book>();
				String data= request.getParameter("book");
				if(option.equals("bookName")) {
					for (Book book : books) {
						if(book.getBookName().equals(data)) {
							list.add(book);
							found=true;
						}
					}
				}
				else if(option.equals("authorName")) {
					for (Book book : books) {
						if(book.getAuthorName().equals(data)) {
							list.add(book);
							found=true;
						}
					}
				}
				else if(option.equals("ISBN")) {
					for (Book book : books) {
						if(book.getISBN().equals(data)) {
							list.add(book);
							found=true;
						}
					}
				}
				if(found==true) {
					request.setAttribute("books", list);
					request.setAttribute("flag", "search");
					RequestDispatcher view = request.getRequestDispatcher("BookList.jsp");
	                view.forward(request,response);
	                return;
				}
				else {
					errorMsgs.add("No book found with the given details.");
					request.setAttribute("flag", "search");
					request.setAttribute("books", books);
					RequestDispatcher view = request.getRequestDispatcher("BookList.jsp");
	                view.forward(request,response);
	                return;
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
