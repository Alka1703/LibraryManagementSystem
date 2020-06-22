package SignInProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class SignInServlet extends HttpServlet {

	private static final long serialVersionUID = 7075499649125475885L;
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username.equals("admin") && password.equals("admin")) {
        	RequestDispatcher view = request.getRequestDispatcher("Admin.jsp");
            view.forward(request,response);
            return;
        }
     
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<String> errorMsgs = new ArrayList<>();
        request.setAttribute("errorMsgs", errorMsgs);
        try{
            con= new DataBaseConnection().createConnection();
            stmt= con.prepareStatement("Select * from users where username = ? and password = ?");
            stmt.setString(1,username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            String userName = null;
            
            if(rs.next()){
                userName= rs.getString("UserName");
            }
            else{
                errorMsgs.add("Invalid username or password");
            }
            
            if(!errorMsgs.isEmpty()){
                RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                view.forward(request,response);
                return;
            }
            String sql = String.format("Select email from Users where username='%s'", userName);
            
            Statement statement= con.createStatement();
            ResultSet rs1= statement.executeQuery(sql);
            String email="";
            if(rs1.next()){
                email=rs1.getString("email");
            }
            sql= String.format("Select * from CheckOut where UserName='%s' order by ReturnDate", userName);
            statement= con.createStatement();
            rs1= statement.executeQuery(sql);
            ArrayList<CheckOut>checkOut= new ArrayList<CheckOut>();
            while(rs1.next()) {
            	CheckOut c= new CheckOut();
				c.setBookId(rs1.getInt("BookId"));
				c.setTransactionId(rs1.getInt("TransactionId"));
				c.setUserName(rs1.getString("UserName"));
				c.setReturnDate(rs1.getDate("ReturnDate").toString());
				checkOut.add(c);
            }
            request.setAttribute("checkedOutItems",checkOut);
            request.setAttribute("email",email);
            request.setAttribute("userName",userName);
            RequestDispatcher view = request.getRequestDispatcher("LoginSuccess.jsp");
            view.forward(request,response);
            return;
        } 
        catch (SQLException e) {
            throw new ServletException("Servlet could not display records.", e);
        } 
        finally {
            try {
                if(rs != null) {
                rs.close();
                rs = null;
                }
                if(stmt != null) {
                stmt.close();
                stmt = null;
                }	
                if(con != null) {
                con.close();
                con = null;
                }
            } 
            catch (SQLException e) {}
        }
    } 

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
