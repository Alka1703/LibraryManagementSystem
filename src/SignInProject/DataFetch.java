package SignInProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DataFetch {
    Connection connection =new DataBaseConnection().createConnection();
    public ArrayList<Book> fetchBooks(){
            try {
                    Statement statement= connection.createStatement();
                    String sql= String.format("Select * from Books");
                    ResultSet rs= statement.executeQuery(sql);
                    ArrayList<Book>books= new ArrayList<Book>();
                    while(rs.next()) {
                            Book b= new Book();
                            b.setAuthorName(rs.getString("AuthorName"));
                            b.setBookName(rs.getString("BookName"));
                            b.setISBN(rs.getString("ISBN"));
                            b.setPublisher(rs.getString("Publisher"));
                            b.setBookId(rs.getInt("BookId"));
                            b.setAvailableCopies(rs.getInt("AvailableCopies"));
                            b.setTotalCopies(rs.getInt("TotalCopies"));
                            b.setRackNumber(rs.getInt("RackNumber"));
                            books.add(b);
                    }
                    return books;
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            return null;
    }
    public ArrayList<User> fetchUsers(){
            try {
                    Statement statement= connection.createStatement();
                    String sql= String.format("Select * from Users");
                    ResultSet rs= statement.executeQuery(sql);
                    ArrayList<User>users= new ArrayList<User>();
                    while(rs.next()) {
                            User user= new User();
                            user.setFirstName(rs.getString("FirstName"));
                            user.setLastName(rs.getString("LastName"));
                            user.setGender(rs.getString("Gender"));
                            user.setEmail(rs.getString("email"));
                            user.setUsername(rs.getString("UserName"));
                            users.add(user);
                    }
                    return users;
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            return null;

    }
    public ArrayList<CheckOut> listBooks(){
            try {
                    Statement statement= connection.createStatement();
                    String sql= String.format("Select * from CheckOut order by TransactionId");
                    ResultSet rs= statement.executeQuery(sql);
                    ArrayList<CheckOut>checkOut= new ArrayList<CheckOut>();
                    while(rs.next()) {
                            CheckOut c= new CheckOut();
                            c.setBookId(rs.getInt("BookId"));
                            c.setTransactionId(rs.getInt("TransactionId"));
                            c.setUserName(rs.getString("UserName"));
                            c.setReturnDate(rs.getDate("ReturnDate").toString());
                            c.setFine(0);
                            checkOut.add(c);
                    }
                    return checkOut;
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            return null;
    }
    public ArrayList<CheckOut> pastReturnDate(){
            try {
                    Statement statement = connection.createStatement();
                    String sql= String.format("SELECT CURDATE()");
                    ResultSet rs= statement.executeQuery(sql);
                    String now ="";
                    if(rs.next())
                            now = rs.getString(1);

                    ArrayList<CheckOut>past_returnDate= new ArrayList<CheckOut>();
                    sql=String.format("Select * from CheckOut where ReturnDate < '%s' order by TransactionId",now);
                    rs= statement.executeQuery(sql);

                    while(rs.next()) {
                            CheckOut c= new CheckOut();
                            c.setBookId(rs.getInt("BookId"));
                            c.setTransactionId(rs.getInt("TransactionId"));
                            c.setUserName(rs.getString("UserName"));
                            c.setReturnDate(rs.getDate("ReturnDate").toString());

                            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date dateBefore = (java.util.Date) myFormat.parse(c.getReturnDate());
                        java.util.Date dateAfter = (java.util.Date) myFormat.parse(now);
                        long difference = dateAfter.getTime() - dateBefore.getTime();
                        int daysBetween = (int) (difference / (1000*60*60*24));

                        c.setFine(daysBetween*2);
                        past_returnDate.add(c);
                    }   
                    return past_returnDate;
            } catch (SQLException e) {
                    e.printStackTrace();
            } catch (ParseException e) {
                    e.printStackTrace();
            }
            return null;
    }
    public boolean insertBooks(Book book, List<String> errorMsgs) {
            try {
        Statement statement=connection.createStatement();
        String sql=String.format("Insert into Books(BookName, AuthorName, Publisher, ISBN, TotalCopies, AvailableCopies, RackNumber)values ('%s','%s','%s','%s','%d','%d','%d')",
                    book.getBookName(), book.getAuthorName(), book.getPublisher(), book.getISBN(),book.getTotalCopies(),book.getAvailableCopies(),book.getRackNumber());
        statement.execute(sql);
        return true;
    }
    catch(SQLException e) {
        e.printStackTrace();
        errorMsgs.add(e.getMessage());
        return false;
    }
    }
    public boolean isBookInDatabase(String string) {
            try {
            Statement statement=connection.createStatement();
            String sql=String.format("Select BookName from Books where BookName ='%s'",string);
            ResultSet rs= statement.executeQuery(sql);
            if(rs.next())
                    return true;
            return false;
            } catch (SQLException e) {
                     e.printStackTrace();
            }
            return false;
    }
    public void deleteBook(int bookId) {
            try {
                    Statement statement= connection.createStatement();
                    String sql= String.format("Delete from Books where bookId='%d'",bookId);
                    statement.execute(sql); 
            } catch (SQLException e) {
                    e.printStackTrace();
            }
    }
    public void modifyBook(Book book) {
            try {
        Statement statement=connection.createStatement();
        String sql= String.format("Select TotalCopies,AvailableCopies From Books where BookId='%d'", book.getBookId());
        ResultSet rs= statement.executeQuery(sql);
        if(rs.next()){
            int tcp=rs.getInt(1);
            int acp=rs.getInt(2);
            int totalCopies=book.getTotalCopies();
            if(totalCopies<tcp) {
                    int dif=tcp-totalCopies;
                    acp=acp-dif;
            }
            else {
                    int dif=totalCopies-tcp;
                    acp=acp+dif;
            }
            book.setAvailableCopies(acp);
            sql=String.format("Update Books set BookName='%s', AuthorName= '%s', Publisher='%s', ISBN='%s', totalCopies='%d', availableCopies='%d', RackNumber='%d' where bookId='%d'",
                            book.getBookName(),book.getAuthorName(),book.getPublisher(),book.getISBN(), book.getTotalCopies(),book.getAvailableCopies(),book.getRackNumber(),book.getBookId());
            statement.execute(sql);
        }
    }
    catch(SQLException e) {
        e.printStackTrace();
    }
    }
    public boolean insertUser(User user, List<String> errorMsgs) {
            PreparedStatement pstmt;
            try {
        pstmt = connection.prepareStatement("insert into Users values(?,?,?,?,?,?)");
        pstmt.setString(1,user.getFirstName());
        pstmt.setString(2,user.getLastName());
        pstmt.setString(3,user.getGender());
        pstmt.setString(4,user.getEmail());
        pstmt.setString(5,user.getUsername());
        pstmt.setString(6,user.getPassword());
        pstmt.executeUpdate();	
        return true;

    }
    catch(SQLException e) {
        errorMsgs.add(e.getMessage());
        e.printStackTrace();
        return false;					 
    }


    }
    public boolean isUserInDatabase(String string) {
            try {
            Statement statement=connection.createStatement();
            String sql=String.format("Select FirstName from Users where username='%s'",string);
            ResultSet rs=statement.executeQuery(sql);
            if(rs.next())
                    return true;
            return false;

            } catch (SQLException e) {
                    e.printStackTrace();
            }
            return false;
    }
    public void deleteUser(String username) {
            try {
                    Statement statement = connection.createStatement();
                    String sql= String.format("Delete from Users where username='%s'",username);
                    statement.execute(sql);
            } catch (SQLException e) {
                    e.printStackTrace();
            }	
    }
    public boolean modifyUser(User user, List<String>errorMsgs) {
            try {
        Connection connection = new DataBaseConnection().createConnection();
        Statement statement=connection.createStatement();
        String sql=String.format("Update Users set firstName='%s', lastName='%s' ,gender ='%s',password ='%s' where email ='%s' ",user.getFirstName(), user.getLastName(), user.getGender(), user.getPassword(), user.getEmail());
        statement.execute(sql); 
        return true;
   }
    catch(SQLException e) {
        errorMsgs.add(e.getMessage());
        e.printStackTrace();	
        return false;
    }
    }
    public void returnbook(int bookId, int book, List<String> errorMsgs, HttpServletResponse response,HttpServletRequest request, String username) {
            try {
                    Statement s= connection.createStatement();
                    String sql= String.format("Select availablecopies from books where bookId ='%d'",bookId);
                    ResultSet rs= s.executeQuery(sql);
                    int ac=0;
                    if(rs.next()){
                            ac=rs.getInt(1);
                    }
                    int fine =0;
                    if(bookAlreadyExists(username, bookId)==1) {
                                    fine = removeFromCheckOut(username,bookId);
                                    incrementAvailableCopies(ac,bookId);
                                    setReturnIntoRequest(bookId,username,request);
                    }
                    else {
                            errorMsgs.add("The specified book is not borrowed by the given user ");
                    }
                    if(!errorMsgs.isEmpty()){
            RequestDispatcher view = request.getRequestDispatcher("CheckOutBooks.jsp");
            view.forward(request,response);
            return;
        }
                    if(fine>0) {
                            errorMsgs.add(String.format("Please collect a fine of Rs %d ", fine));
                    }
                    RequestDispatcher view = request.getRequestDispatcher("SuccessReturn.jsp");
        view.forward(request,response);
            } catch (SQLException e) {
                    e.printStackTrace();
            } catch (ServletException e) {
                    e.printStackTrace();
            } catch (IOException e) {
                    e.printStackTrace();
            }
    }
    public void checkOut(int bookId, int book, HttpServletRequest request, HttpServletResponse response,String username, List<String> errorMsgs) {
            try {
                    Statement s= connection.createStatement();

                    String sql= String.format("Select Count(transactionId) from CheckOut where username='%s'", username);
                    ResultSet rs=s.executeQuery(sql);
                    int count=0;
                    if(rs.next())
                            count=rs.getInt(1);
                    if(count<=4) {
                            sql= String.format("Select availableCopies from books where bookId ='%d'",bookId);
                            rs= s.executeQuery(sql);
                            int ac=0;
                            if(rs.next()) {
                                    ac=rs.getInt(1);
                                    if(ac>0) {
                                            if(book==0) {
                                                    String returnDate= request.getParameter("dateofreturn");
                                                    insertIntoCheckOut(bookId,username,returnDate);
                                                    decrementAvailableCopies(ac,bookId);
                                                    setCheckOutIntoRequest(bookId, username,request);
                                            }
                                    }
                                    else
                                            errorMsgs.add("No copies of the selected book left");
                            }
                    }
                    else 
                            errorMsgs.add("Cannot collect more than 5 books at once.");
                    if(!errorMsgs.isEmpty()){
            RequestDispatcher view = request.getRequestDispatcher("CheckOutBooks.jsp");
            view.forward(request,response);
            return;
        }
                    RequestDispatcher view = request.getRequestDispatcher("SuccessCheckOut.jsp");
        view.forward(request,response);
            } catch (SQLException e) {
                    e.printStackTrace();
            } catch (ServletException e) {
                    e.printStackTrace();
            } catch (IOException e) {
                    e.printStackTrace();
            }	
    }
    public void setReturnIntoRequest(int bookId, String username, HttpServletRequest request) {
            CheckOut checkout= new CheckOut();
            checkout.setBookId(bookId);
            checkout.setUserName(username);
            request.setAttribute("checkout", checkout);
    }
    public void incrementAvailableCopies(int ac, int bookId) {
            try {
                    Statement s= connection.createStatement();
                    String sql= String.format("Update books set availableCopies='%d' where bookId = '%d'",(ac+1),bookId);
                    s.execute(sql);
            } catch (SQLException e) {
                    e.printStackTrace();
            }

    }
    public int  removeFromCheckOut( String username, int bookId) {
            try {
                    Statement s= connection.createStatement();
                    String st= String.format("Select returnDate from CheckOut where bookId='%d' and UserName='%s'",bookId,username);
                    ResultSet rs= s.executeQuery(st);
                    String date="";
                    if(rs.next())
                            date=rs.getDate(1).toString();

                    String sql= String.format("SELECT CURDATE()");
                    rs= s.executeQuery(sql);
                    String now ="";
                    if(rs.next())
                            now = rs.getString(1);

                    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date dateBefore = (java.util.Date) myFormat.parse(date);
                java.util.Date dateAfter = (java.util.Date) myFormat.parse(now);
                long difference = dateAfter.getTime() - dateBefore.getTime();
                int fine = (int) (difference / (1000*60*60*24));
                    fine=fine*2;
                    sql= String.format("Delete from CheckOut where bookId='%d' and UserName='%s'",bookId,username);
                    s.execute(sql);
                    return fine;
            } catch (SQLException e) {
                    e.printStackTrace();
            } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            return 0;
    }
    public void setCheckOutIntoRequest(int bookId, String username, HttpServletRequest request) {
            CheckOut checkout= new CheckOut();
            checkout.setBookId(bookId);
            checkout.setUserName(username);
            request.setAttribute("checkout", checkout);
    }
    public void decrementAvailableCopies(int ac,int bookId) {
            try {
                    Statement s= connection.createStatement();
                    String sql= String.format("Update books set availableCopies='%d' where bookId = '%d'",(ac-1),bookId);
                    s.execute(sql);
            } catch (SQLException e) {
                    e.printStackTrace();
            }	
    }
    public void insertIntoCheckOut(int bookId, String username, String returnDate) {
            try {
                    Statement s= connection.createStatement();
                    String sql= String.format("insert into CheckOut(BookId, UserName, ReturnDate) values('%d', '%s','%s')",bookId,username,Date.valueOf(returnDate));
                    s.execute(sql);
            } catch (SQLException e) {
                    e.printStackTrace();
            }
    }
    public int bookAlreadyExists(String username, int bookId) {
            try {
                    Statement s= connection.createStatement();
                    String sql= String.format("Select bookId from CheckOut where Username= '%s' and bookId ='%d'",username,bookId);
                    ResultSet rs= s.executeQuery(sql);
                    if(rs.next())
                            return 1;
                    return 0;
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            return 0;
    }
    public int userExists( String username) {
            try {
                    Statement s= connection.createStatement();
                    String sql= String.format("Select username from users where Username= '%s'",username);
                    ResultSet rs= s.executeQuery(sql);
                    if(rs.next())
                            return 1;
                    return 0;
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            return 0;
    }

    public int renewBook(int bookId, String username, String returnDate){
        Statement s;
        try {
            s = connection.createStatement();
            String st= String.format("Select returnDate from CheckOut where bookId='%d' and UserName='%s'",bookId,username);
            ResultSet rs= s.executeQuery(st);
            String date="";
            if(rs.next())
                    date=rs.getDate(1).toString();

            String sql= String.format("SELECT CURDATE()");
            rs= s.executeQuery(sql);
            String now ="";
            if(rs.next())
                    now = rs.getString(1);

            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateBefore = (java.util.Date) myFormat.parse(date);
            java.util.Date dateAfter = (java.util.Date) myFormat.parse(now);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            int fine = (int) (difference / (1000*60*60*24));
            fine=fine*2;

            st=String.format("Update checkout set returndate='%s' where bookId = '%d'",returnDate,bookId);
            s.execute(st);
            System.out.println(fine);
            if(fine > 0){
                System.out.println(fine);
                return fine;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int fetchAvailableCopies(int bookId){
        int copies=0;
        try{
            Statement s = connection.createStatement();
            String st= String.format("Select availableCopies from Books where bookId='%d'",bookId);
            ResultSet rs= s.executeQuery(st);
            if(rs.next())
                copies=rs.getInt(1);

        }catch(Exception e){
            e.printStackTrace();
        }
        return copies;
    }

    public int countBooks(String username){
        int count=0;
        try{
            Statement s = connection.createStatement();
            String sql= String.format("Select Count(transactionId) from CheckOut where username='%s'", username);
            ResultSet rs=s.executeQuery(sql); 
            if(rs.next())
                count=rs.getInt(1);

        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public String reserveBook(int bookId, String username){
        int priority=0;
        String date="";
        try{
            Statement s = connection.createStatement();
            String st= String.format("Select Count(*) from reserve where bookId = '%d'",bookId);
            ResultSet rs= s.executeQuery(st);
            if(rs.next())
                priority=rs.getInt(1)+1;
            System.out.println("P: "+priority);
            st= String.format("SELECT returnDate FROM checkout where bookId = %d ORDER BY returnDate LIMIT 1,%d",bookId,priority);
            rs= s.executeQuery(st);
            if(rs.next())
                date=rs.getDate(1).toString();
            System.out.println(date);
            st= String.format("Insert into reserve values('%d','%d','%s','%s')",priority, bookId, username, date);
            s.execute(st);
        }catch(Exception e){
            e.printStackTrace();
        }
        return date;
    }
    
    public boolean userExistsInReserve(int bookId, String username){
        try{
            Statement s = connection.createStatement();
            String st= String.format("Select * from reserve where bookId = '%d' and username = '%s'",bookId,username);
            ResultSet rs= s.executeQuery(st);
            if(rs.next())
                return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }



}
