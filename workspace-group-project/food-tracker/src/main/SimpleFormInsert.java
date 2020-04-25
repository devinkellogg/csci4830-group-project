package main;

/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
   
   protected void dbHelper(Connection connection, String foodOrdered, String price, String restaurantOrderedFrom, String rating) throws SQLException {
	   DBConnection.getDBConnection();
       connection = DBConnection.connection;
       String insertSql = " INSERT INTO FoodTrackerTable (id, FOOD_ORDERED, PRICE, RESTAURANT_ORDERED_FROM, RATING) values (default, ?, ?, ?, ?)";
       PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
       preparedStmt.setString(1, foodOrdered);
       preparedStmt.setString(2, price);
       preparedStmt.setString(3, restaurantOrderedFrom);
       preparedStmt.setString(4, rating);
       preparedStmt.execute();
       connection.close();
   }
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String foodOrdered = request.getParameter("foodOrdered");
      String price = request.getParameter("price");
      String restaurantOrderedFrom = request.getParameter("restaurantOrderedFrom");
      String rating = request.getParameter("rating");

      Connection connection = null;
  
      boolean error = false;
      try {
         dbHelper(connection, foodOrdered, price, restaurantOrderedFrom, rating);
      } catch (Exception e) {
    	  error = true;
    	  e.printStackTrace();
      }

      // Set response content type
      if (!error) {
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "New Order Created With the Following Information!";
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
	      		"<section>\r\n" + 
	      		"<h2 align=\"center\">" + title + "</h2>\n" + //
	      		"<ul>\n" + //
	
	            "  <b>Food Ordered</b>: " + foodOrdered + "<br>\n" + //
	            "  <b>Price</b>: " + price + "<br>\n" + //
	            "  <b>Restaurant</b>: " + restaurantOrderedFrom + "<br>\n" + //
	            "  <b>Rating</b>: " + rating + "<br>\n" + //
	
	            "</ul>\n" +
	      		"</section>\r\n" + 
	      		"\r\n" + 
	      		"<footer>\r\n" + 
	      		"Food Tracker site created by Devin Kellogg, Logan Sortino, Conner Kennell, and Ben Jahnke\r\n" + 
	      		"</footer>\r\n" + 
	      		"</body>\r\n" + 
	      		"</html>");
	   }
      else {
    	  response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "There was an issue with the data you inputted!\nPlease try again.";
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
	      		"    width:100px;\r\n" + 
	      		"    float:left;\r\n" + 
	      		"    padding:5px;	      \r\n" + 
	      		"}\r\n" + 
	      		"section {\r\n" + 
	      		"    width:350px;\r\n" + 
	      		"    float:left;\r\n" + 
	      		"    padding:10px;\r\n" + 
	      		"    background-color:#e8a799;\r\n" + 
	      		"    color:black; \r\n" + 
	      		"    text-align:right;	 \r\n" + 
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
	      		"<section>\r\n" + 
	      		"<h2 align=\"center\">" + title + "</h2>\n" + //

	      		"</section>\r\n" + 
	      		"\r\n" + 
	      		"<footer>\r\n" + 
	      		"Food Tracker site created by Devin Kellogg, Logan Sortino, Conner Kennell, and Ben Jahnke\r\n" + 
	      		"</footer>\r\n" + 
	      		"</body>\r\n" + 
	      		"</html>");
	   }
      }
   

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
