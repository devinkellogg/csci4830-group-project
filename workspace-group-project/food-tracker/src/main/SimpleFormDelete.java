package main;

/**
 * @file SimpleFormDelete.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpleFormDelete")
public class SimpleFormDelete extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SimpleFormDelete() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String deleteId = request.getParameter("deleteID");

      Connection connection = null;
      String deleteSql = " DELETE FROM FoodTrackerTable WHERE id = ?";
      int numOrders = 0;
      int numOrdersAfter = 0;

      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         String selectSQL = "SELECT * FROM FoodTrackerTable";
         PreparedStatement statementForNumOrders = connection.prepareStatement(selectSQL);
         ResultSet rs = statementForNumOrders.executeQuery();
         while (rs.next()) {
        	 numOrders++;
         }
         PreparedStatement preparedStmt = connection.prepareStatement(deleteSql);
         preparedStmt.setString(1, deleteId);
         preparedStmt.execute();
         rs = statementForNumOrders.executeQuery();
         while (rs.next()) {
        	 numOrdersAfter++;
         }
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
      String title;
      if (numOrders > numOrdersAfter) {
    	  title = "Deletion was successful";
      }
      else {
    	  title = "Nothing with the given ID was found!";
      }
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
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

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
