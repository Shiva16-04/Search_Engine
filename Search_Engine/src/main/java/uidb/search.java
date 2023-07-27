package uidb;
import uidb.SearchResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//establishing connection
@WebServlet("/search")
public class search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //receives the request to search from html page
        String keyword=request.getParameter("keyword");

        try {
            Connection connection= DatabaseConnection.getConnection();
            //storing keywords and their respective links in history table
            PreparedStatement preparedStatement=connection.prepareStatement("Insert into history values(?, ?);");
            preparedStatement.setString(1,keyword);
            preparedStatement.setString(2,"http://localhost:8080/Search_Engine/search?keyword="+keyword);
            preparedStatement.executeUpdate();
            //getting result after the ranking query which returns top 30 results from the database
            ResultSet resultSet = connection.createStatement().executeQuery("select pageTitle, pageLink, (length(lower(pageText))-length(replace(lower(pageText),'" + keyword.toLowerCase() + "', '')))/length('" + keyword.toLowerCase() + "') as countoccurence from webpages order by countoccurence desc limit 30;");
            ArrayList<SearchResult> results = new ArrayList<SearchResult>();
            while (resultSet.next()) {
                SearchResult searchResult = new SearchResult();
                searchResult.setTitle(resultSet.getString("pageTitle"));
                searchResult.setLink(resultSet.getString("pageLink"));
                results.add(searchResult);
            }
            for(SearchResult result: results) {
                System.out.println(result.getTitle() + "\n" + result.getLink() + "\n");
            }
            request.setAttribute("results",results);
            //sending the results to the search html page
            request.getRequestDispatcher("search.jsp").forward(request, response);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            //exception handling
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        catch(ServletException servletException){
            servletException.printStackTrace();
        }
    }
}
