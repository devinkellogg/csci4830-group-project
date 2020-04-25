package main;
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
   
   protected ResultSet dbHelper(String food, String restaurant) throws Exception {
	   	Connection connection = null;
	   	PreparedStatement preparedStatement = null;
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
        return preparedStatement.executeQuery();
   }
   
   void search(String food, String restaurant, HttpServletResponse response) throws IOException {
      try {
         
         ResultSet rs = dbHelper(food, restaurant);
     
    	 response.setContentType("text/html");
	     PrintWriter out = response.getWriter();
	     String title = "There were no entries found for your search. Please try again.";
	     out.println("<html>\r\n" + 
	    		"<head>\r\n" + 
	      		"<style>\r\n" + 
	      		"header {\r\n" + 
	      		"    background-color:#D74A2C;\r\n" + 
	      		"    color:black;\r\n" + 
	      		"    text-align:center;\r\n" + 
	      		"    padding:5;	 \r\n" + 
	      		"}\r\n" + 
	      		"img.logo {\r\n" + 
	      		"	vertical-align: middle;\r\n" + 
	      		"}\r\n" + 
	      		"nav {\r\n" + 
	      		"    line-height:30px;\r\n" + 
	      		"    background-color:d47f08;\r\n" + 
	      		"    height:300px;\r\n" + 
	      		"    width:150px;\r\n" + 
	      		"    float:left;\r\n" + 
	      		"    padding:5px;	      \r\n" + 
	      		"}\r\n" + 
	      		"section {\r\n" + 
	      		"    width:1024px;\r\n" + 
	      		"    float:left;\r\n" + 
	      		"    padding:10px;\r\n" + 
	      		"    background-color:#e8a799;\r\n" + 
	      		"    color:black; \r\n" + 
	      		"    text-align:left;	 \r\n" + 
	      		"}\r\n" + 
	      		"footer {\r\n" + 
	      		"    background-color:#D74A2C;\r\n" + 
	      		"    color:black;\r\n" + 
	      		"    clear:both;\r\n" + 
	      		"    text-align:center;\r\n" + 
	      		"    padding:5px;	 	 \r\n" + 
	      		"}\r\n" + 
	      		"</style>\r\n" + 
	      		"</head>\r\n" + 
	      		"\r\n" + 
	      		"<body style=\"background-color:#e8a799;\">\r\n" + 
	      		"<header>\r\n" + 
	      		"<h1>Food Tracker<img class=\"logo\" src=\"cheemsburbger.png\" width=\"128\" height=\"128\"></h1>\r\n" + 
	      		"</header>\r\n" + 
	      		"\r\n" + 
	      		"<nav>\r\n" + 
	      		"<a href=\"/food-tracker/home.html\">Food Tracker Home</a> <br>\r\n" + 
	      		"<a href=\"/food-tracker/simpleFormInsert.html\">Insert Order</a> <br>\r\n" + 
	      		"<a href=\"/food-tracker/simpleFormSearch.html\">Search Order</a> <br>\r\n" + 
	      		"<a href=\"/food-tracker/simpleFormDelete.html\">Delete Order</a> <br>\r\n" + 
	      		"</nav>\r\n" + 
	      		"\r\n" + 
	      		"<section>\r\n");
	
	     boolean foundEntry = false;
         while (rs.next()) {
        	 if (!foundEntry) {
        		 foundEntry = true;
        		 title = "Orders found!";
        		 out.println("<h2 align=\"center\">" + title + "</h2>\n");
        	 }
            int id = rs.getInt("id");
            String foodname = rs.getString("food_ordered").trim();
            String price = rs.getString("price").trim();
            String restaurantname = rs.getString("restaurant_ordered_from").trim();
            String rating = rs.getString("rating").trim();

            if (food.isEmpty() || foodname.contains(food)) {
               out.println("ID: " + id + ", ");
               out.println("Food: " + foodname + ", ");
               out.println("Price: " + price + ", ");
               out.println("Restaurant: " + restaurantname + ", ");
               out.println("Rating: " + rating);
               out.println("<br>");
            }
         }
         if (!foundEntry) {
    		 out.println("<h2 align=\"center\">" + title + "</h2>\n");
         }
         out.println("</section>\r\n" + 
		      		"\r\n" + 
		      		"<footer>\r\n" + 
		      		"Food Tracker site created by Devin Kellogg, Logan Sortino, Conner Kennell, and Ben Jahnke\r\n" + 
		      		"</footer>\r\n" + 
		      		"</body>\r\n" + 
		      		"</html>");
         rs.close();
         
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } 
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
