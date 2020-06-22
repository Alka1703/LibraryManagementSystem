package SignInProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserFromFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> errorMsgs=new ArrayList<String>(); 
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			String fileName= request.getParameter("filename");
			if(fileName==null) {
				errorMsgs.add("Please provide the file name");
				RequestDispatcher view = request.getRequestDispatcher("AddUsersFromFile.jsp");
				view.forward(request, response);
				return;
			}
			BufferedReader br= new BufferedReader(new FileReader(fileName));
			try {
				String st;
				while((st=br.readLine())!=null) {
					String str[]=st.split(",");
					User user= new User(str[0],str[1],str[2],str[3], str[4],str[5]);
					boolean validData= isValidData(str);
					if(validData==true) {
						boolean userExists=new DataFetch().isBookInDatabase(str[4]);
						if(userExists==false)
							new DataFetch().insertUser(user,errorMsgs);	
						else 
							errorMsgs.add(str[4] + " username already exists");
					}
					else
						errorMsgs.add("Data Validation failed for User "+ str[4]);	
				}
				RequestDispatcher view = request.getRequestDispatcher("SuccessAddUsers.jsp");
                view.forward(request, response);
                return;
			} catch (FileNotFoundException e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher view = request.getRequestDispatcher("AddUsersFromFile.jsp");
				view.forward(request, response);
				e.printStackTrace();
			} finally {
				if(br!=null)
					br.close();
			}
		}catch(RuntimeException e)  {
			errorMsgs.add(e.getMessage());
			RequestDispatcher view = request.getRequestDispatcher("AddUsersFromFile.jsp");
			view.forward(request, response);
    	}
	}
	private boolean isValidData(String[] str) {
		boolean firstName= Pattern.matches("[A-Za-z]+", str[0].trim());
		boolean lastName=Pattern.matches("[A-Za-z]+", str[1].trim());
		boolean Gender=Pattern.matches("[A-Za-z]+", str[2].trim());
		boolean email=Pattern.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", str[3].trim());
		boolean userName=Pattern.matches("[A-Za-z0-9]+", str[4].trim());
		if(firstName && lastName && Gender && email && userName)
			return true;
		return false;
	}


}
