package SignInProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBooksFromFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<String> errorMsgs=new ArrayList<String>(); 
		request.setAttribute("errorMsgs", errorMsgs);
		
		String fileName= request.getParameter("filename");
		if(fileName==null) {
			errorMsgs.add("Please provide the file name with full path");
			RequestDispatcher view = request.getRequestDispatcher("AddBooksFromFile.jsp");
			view.forward(request, response);
			return;
		}
		
		BufferedReader br= new BufferedReader(new FileReader(fileName));
		try {
			String st;
			while((st=br.readLine())!=null) {
				String str[]=st.split(",");
				boolean validData= isValidData(str);
				if(validData==true) {
					boolean bookExists=new DataFetch().isBookInDatabase(str[0]);
					if(bookExists==false) {
						Book book= new Book(0,str[0],str[1],str[2],str[3],Integer.parseInt(str[4]),Integer.parseInt(str[4]), Integer.parseInt(str[5]));
						new DataFetch().insertBooks(book,errorMsgs);	
					}
					else 
						errorMsgs.add(str[0] + " book already exists");
				}
				else
					errorMsgs.add("Data Validation failed for Book "+ str[0]);
			}
			request.setAttribute("flag", "file");
			RequestDispatcher view = request.getRequestDispatcher("SuccessAddBooks.jsp");
            view.forward(request, response);
            return;
		} catch (FileNotFoundException e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher view = request.getRequestDispatcher("AddBooksFromFile.jsp");
			view.forward(request, response);
			e.printStackTrace();
		}finally {
			if(br!=null) 
				br.close();
		}
	}
	private boolean isValidData(String[] str) {
		boolean bookName= Pattern.matches("[A-Za-z0-9-.\\s]+", str[0].trim());
		boolean authorName=Pattern.matches("[A-Za-z.\\s]+", str[1].trim());
		boolean publisher=Pattern.matches("[A-Za-z.\\s]+", str[2].trim());
		boolean ISBN=Pattern.matches("[A-Za-z0-9\\s]+", str[3].trim());
		boolean totalCopies=Pattern.matches("[0-9]+", str[4].trim());
		if(bookName && authorName && publisher && ISBN && totalCopies)
			return true;
		return false;
	}
	

}
