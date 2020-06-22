package SignInProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<String> errorMsgs=new ArrayList<String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			ArrayList<Book>books= new DataFetch().fetchBooks();
			request.setAttribute("books", books);
			String username= request.getParameter("username");
			String checkOutBook=request.getParameter("checkout");
			String returnBook=request.getParameter("return");
                        String renewBook=request.getParameter("renew");
                        String returnDate=request.getParameter("dateofreturn");
                        String reserveBook=request.getParameter("reserve");
			String id;
			id=request.getParameter("bkgroup1");
			if(username.trim().length()==0 || username==null) 
				errorMsgs.add("Please Enter the username");
			int bookId=0;
			if(id == null) 
				errorMsgs.add("Please click on the book you want to return/checkout/renew/reserve");
			else 
				 bookId=Integer.parseInt(id);
			if(!errorMsgs.isEmpty()){
                            RequestDispatcher view = request.getRequestDispatcher("CheckOutBooks.jsp");
                            view.forward(request,response);
                            return;
                        }
                        if(reserveBook != null){
                            int count = new DataFetch().countBooks(username);
                            boolean exists = new DataFetch().userExistsInReserve(bookId, username);
                            if (new DataFetch().userExists(username) == 0){
                                errorMsgs.add("User does not exist in the system");
                                RequestDispatcher view = request.getRequestDispatcher("CheckOutBooks.jsp");
                                view.forward(request,response);
                                return;
                            }
                                    
                            if(exists){
                                errorMsgs.add("You have already reserved this book.");
                                RequestDispatcher view = request.getRequestDispatcher("CheckOutBooks.jsp");
                                view.forward(request,response);
                                return;
                            }
                            if(count>=5){
                                errorMsgs.add("You have already borrowed 5 books. You cannot reserve anymore books.");
                                RequestDispatcher view = request.getRequestDispatcher("CheckOutBooks.jsp");
                                view.forward(request,response);
                                return;
                            }
                            int ac = new DataFetch().fetchAvailableCopies(bookId);
                            if(ac > 0){
                                errorMsgs.add("Book is already available. Please CheckOut the book.");
                                RequestDispatcher view = request.getRequestDispatcher("CheckOutBooks.jsp");
                                view.forward(request,response);
                                return;
                            }
                            else{
                                String date = new DataFetch().reserveBook(bookId, username);
                                RequestDispatcher view = request.getRequestDispatcher("SuccessReserveBook.jsp");
                                request.setAttribute("date", date);
                                view.forward(request,response);
                                return;
                            }
                                
                        }
                        else{
                            int user=0, book=0;
                            if(checkOutBook==null && returnBook==null && renewBook==null){
                                errorMsgs.add("Please select either Checkout/Return/Renew/Reserve option");
                                RequestDispatcher view = request.getRequestDispatcher("CheckOutBooks.jsp");
                                view.forward(request,response);
                                return;
                            }
                            else {
                                if(checkOutBook!=null || returnBook!=null || renewBook!=null)
                                    user=new DataFetch().userExists(username);
                                if(user==0) {
                                    errorMsgs.add("User does not exist in the system");
                                    RequestDispatcher view = request.getRequestDispatcher("CheckOutBooks.jsp");
                                    view.forward(request,response);
                                    return;
                                }
                                else {
                                    book=new DataFetch().bookAlreadyExists(username,bookId);
                                    if(returnBook==null && renewBook==null) {
                                            if(book==1) {
                                                errorMsgs.add("Book Already issued to the same user");
                                                RequestDispatcher view = request.getRequestDispatcher("CheckOutBooks.jsp");
                                                view.forward(request,response);
                                                return;   
                                            }
                                            else
                                                new DataFetch().checkOut(bookId,book,request,response,username,errorMsgs);
                                    }
                                    else if(renewBook==null) 
                                            new DataFetch().returnbook(bookId,book,errorMsgs,response,request,username);
                                    else{
                                        if(book==1){
                                            int result = new DataFetch().renewBook(bookId, username, returnDate);
                                            System.out.println(result);
                                            if(result == 0){
                                                RequestDispatcher view = request.getRequestDispatcher("SuccessRenew.jsp");
                                                view.forward(request,response);
                                                return;
                                            }
                                            else{                                               
                                                errorMsgs.add(String.format("Please pay the fine of Rs. %d to renew the book.",result));
                                                RequestDispatcher view = request.getRequestDispatcher("SuccessRenew.jsp");
                                                view.forward(request,response);
                                                return;
                                            }
                                        }
                                        else{
                                            errorMsgs.add("Book has not been issued by the user");
                                            RequestDispatcher view = request.getRequestDispatcher("CheckOutBooks.jsp");
                                            view.forward(request,response);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
}
