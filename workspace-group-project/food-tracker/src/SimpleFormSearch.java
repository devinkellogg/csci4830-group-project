import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpleFormSearch")
public class SimpleFormSearch extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SimpleFormSearch() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String food = request.getParameter("food");
      String restaurant = request.getParameter("restaurant");
      search(food, restaurant, response);
   }

   void search(String food, String restaurant, HttpServletResponse response) throws IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");

      Connection connection = null;
      PreparedStatement preparedStatement = null;
      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;

         if (food.isEmpty() && restaurant.isEmpty()) {
            String selectSQL = "SELECT * FROM FoodTrackerTable";
            preparedStatement = connection.prepareStatement(selectSQL);
         } else if(restaurant.isEmpty()){
            String selectSQL = "SELECT * FROM FoodTrackerTable WHERE FOOD_ORDERED LIKE ?";
            String theFoodName = food + "%";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, theFoodName);
         } else if(food.isEmpty()){
             String selectSQL = "SELECT * FROM FoodTrackerTable WHERE RESTAURANT_ORDERED_FROM LIKE ?";
             String theRestaurantName = restaurant + "%";
             preparedStatement = connection.prepareStatement(selectSQL);
             preparedStatement.setString(1, theRestaurantName);
         } else {
             String selectSQL = "SELECT * FROM FoodTrackerTable WHERE FOOD_ORDERED LIKE ? AND RESTAURANT_ORDERED_FROM LIKE ?";
             String theFoodName = restaurant + "%";
             String theRestaurantName = restaurant + "%";
             preparedStatement = connection.prepareStatement(selectSQL);
             preparedStatement.setString(1, theFoodName);
             preparedStatement.setString(2, theRestaurantName);
         }
         ResultSet rs = preparedStatement.executeQuery();

         while (rs.next()) {
            int id = rs.getInt("id");
            String foodname = rs.getString("food").trim();
            String price = rs.getString("price").trim();
            String restaurantname = rs.getString("restaurant").trim();
            String rating = rs.getString("rating").trim();

            if (food.isEmpty() || foodname.contains(food)) {
               out.println("ID: " + id + ", ");
               out.println("Food: " + foodname + ", ");
               out.println("Price: " + price + ", ");
               out.println("Restaurant: " + restaurantname + ", ");
               out.println("Rating: " + rating + ", ");
               out.println("<br>");
            }
         }
         out.println("<a href=/webproject/home.html>Food Tracker Home</a> <br>");
         out.println("<a href=/webproject/simpleFormInsert.html>Insert Data</a> <br>");
         out.println("<a href=/webproject/simpleFormSearch.html>Search Data</a> <br>");
         out.println("<a href=/webproject/simpleFormDelete.html>Delete Data</a> <br>");
         out.println("</body></html>");
         rs.close();
         preparedStatement.close();
         connection.close();
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (preparedStatement != null)
               preparedStatement.close();
         } catch (SQLException se2) {
         }
         try {
            if (connection != null)
               connection.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
