
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpleFormInsert")
public class SimpleFormInsert extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SimpleFormInsert() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String foodOrdered = request.getParameter("foodOrdered");
      String price = request.getParameter("price");
      String restaurantOrderedFrom = request.getParameter("restaurantOrderedFrom");
      String rating = request.getParameter("rating");

      Connection connection = null;
      String insertSql = " INSERT INTO FoodTrackerTable (id, FOOD_ORDERED, PRICE, RESTAURANT_ORDERED_FROM, RATING) values (default, ?, ?, ?, ?)";

      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, foodOrdered);
         preparedStmt.setString(2, price);
         preparedStmt.setString(3, restaurantOrderedFrom);
         preparedStmt.setString(4, rating);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Food Ordered</b>: " + foodOrdered + "\n" + //
            "  <li><b>Price</b>: " + price + "\n" + //
            "  <li><b>Restaurant</b>: " + restaurantOrderedFrom + "\n" + //
            "  <li><b>Rating</b>: " + rating + "\n" + //

            "</ul>\n");

      out.println("<a href=/webproject/simpleFormSearch.html>Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
