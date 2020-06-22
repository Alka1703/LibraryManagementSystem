package SignInProject;

import java.io.IOException; 
import java.util.ArrayList; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminSection extends HttpServlet{
	
	private static final long serialVersionUID = -769644966138779864L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      	String menuSelection = request.getParameter("menuSelection");
    	
    	if(menuSelection.equals("ListBooks")) {
			ArrayList<Book>books= new DataFetch().fetchBooks();
			request.setAttribute("books", books);
			request.setAttribute("flag", "List");
			RequestDispatcher view = request.getRequestDispatcher("BookList.jsp");
            view.forward(request,response);
            return;  
    	}
    	else if(menuSelection.equals("ListBorrowedBooks")) {
    		ArrayList<CheckOut>checkOut= new DataFetch().listBooks();
			ArrayList<CheckOut>past_returnDate= new DataFetch().pastReturnDate();
			request.setAttribute("checkOut", checkOut);
			request.setAttribute("past_returnDate",past_returnDate);
			RequestDispatcher view = request.getRequestDispatcher("ListBorrowedBooks.jsp");
			view.forward(request,response);
			return;
    	}
    	else if(menuSelection.equals("ListUsers")) {
			ArrayList<User>users= new DataFetch().fetchUsers();
			request.setAttribute("users",users);
			RequestDispatcher view = request.getRequestDispatcher("ListUsers.jsp");
            view.forward(request,response);
            return;
    	}
    	else if(menuSelection.equals("ReturnBooks")) {
			ArrayList<Book>books= new DataFetch().fetchBooks();
			request.setAttribute("books",books);
			RequestDispatcher view = request.getRequestDispatcher("CheckOutBooks.jsp");
            view.forward(request,response);
            return;
    	}
    	else if(menuSelection.equals("DeleteBooks")) {
    		ArrayList<Book>books= new DataFetch().fetchBooks();
			request.setAttribute("books",books);
			RequestDispatcher view = request.getRequestDispatcher("DeleteBooks.jsp");
            view.forward(request,response);
            return;
    	}
    	else if(menuSelection.equals("DeleteUsers")) {
    		ArrayList<User>users= new DataFetch().fetchUsers();
			request.setAttribute("users",users);
			RequestDispatcher view = request.getRequestDispatcher("DeleteUser.jsp");
            view.forward(request,response);
            return;
    	}
    	else if(menuSelection.equals("AddUser")) {
    		RequestDispatcher view = request.getRequestDispatcher("addUser.jsp");
            view.forward(request,response);
            return;
    	}
    	else if(menuSelection.equals("AddBook")) {
    		RequestDispatcher view = request.getRequestDispatcher("AddBook.jsp");
            view.forward(request,response);
            return;
    	}else if(menuSelection.equals("AddUsersFromFile")) {
    		RequestDispatcher view = request.getRequestDispatcher("AddUsersFromFile.jsp");
            view.forward(request,response);
            return;
    	}else if(menuSelection.equals("AddBooksFromFile")) {
    		RequestDispatcher view = request.getRequestDispatcher("AddBooksFromFile.jsp");
            view.forward(request,response);
            return;
    	}else if(menuSelection.equals("ModifyUser")) {
    		ArrayList<User>users= new DataFetch().fetchUsers();
			request.setAttribute("users",users);
    		RequestDispatcher view = request.getRequestDispatcher("UserList.jsp");
            view.forward(request,response);
            return;
    	}else if(menuSelection.equals("ModifyBook")) {
    		ArrayList<Book>books= new DataFetch().fetchBooks();
			request.setAttribute("books",books);
			request.setAttribute("flag","modify");
    		RequestDispatcher view = request.getRequestDispatcher("BookList.jsp");
            view.forward(request,response);
            return;
    	}	
    }
}
