package uidb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/History")
public class History extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

        try{
            Connection connection= DatabaseConnection.getConnection();
            //getting all the history results from database and storing it to the arraylist of type
            // History Result
            ResultSet resultSet = connection.createStatement().executeQuery("select * from history;");
            ArrayList<HistoryResult>results=new ArrayList<HistoryResult>();
            while(resultSet.next()){
                HistoryResult historyResult=new HistoryResult();
                historyResult.setKeyword(resultSet.getString("keyword"));
                historyResult.setLink(resultSet.getString("link"));
                results.add(historyResult);
            }
            //sending the results to history html page
            request.setAttribute("results",results);
            request.getRequestDispatcher("history.jsp").forward(request,response);
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
