package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mockito.*;
import org.junit.jupiter.api.Test;

class SimpleFormInsertSpec {

	@Test
	void testInvalidInput() throws IOException, SQLException, ServletException {
		PrintWriter out = Mockito.mock(PrintWriter.class);
	
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		Mockito.when(response.getWriter()).thenReturn(out);
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Mockito.when(request.getParameter("foodOrdered")).thenReturn("test");
		Mockito.when(request.getParameter("price")).thenReturn("test");
		Mockito.when(request.getParameter("restaurantOrderedFrom")).thenReturn("test");
		Mockito.when(request.getParameter("rating")).thenReturn("test");
		
		SimpleFormInsert sfi = new SimpleFormInsert();
		SimpleFormInsert sfi1 = Mockito.spy(sfi);
		Mockito.doThrow(new SQLException()).when(sfi1).dbHelper(null, "test", "test", "test", "test");
		
		sfi1.doGet(request, response);
		Mockito.verify(out).println("<html>\r\n" + 
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
	      		"<h2 align=\"center\">There was an issue with the data you inputted!\nPlease try again.</h2>\n" + //

	      		"</section>\r\n" + 
	      		"\r\n" + 
	      		"<footer>\r\n" + 
	      		"Food Tracker site created by Devin Kellogg, Logan Sortino, Conner Kennell, and Ben Jahnke\r\n" + 
	      		"</footer>\r\n" + 
	      		"</body>\r\n" + 
	      		"</html>");	
	}
	
	@Test
	void testValidInput() throws IOException, SQLException, ServletException {
		PrintWriter out = Mockito.mock(PrintWriter.class);
		
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		Mockito.when(response.getWriter()).thenReturn(out);
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Mockito.when(request.getParameter("foodOrdered")).thenReturn("test");
		Mockito.when(request.getParameter("price")).thenReturn("test");
		Mockito.when(request.getParameter("restaurantOrderedFrom")).thenReturn("test");
		Mockito.when(request.getParameter("rating")).thenReturn("test");
		
		SimpleFormInsert sfi = new SimpleFormInsert();
		SimpleFormInsert sfi1 = Mockito.spy(sfi);
		Mockito.doNothing().when(sfi1).dbHelper(null, "test", "test", "test", "test");
		
		sfi1.doGet(request, response);
		Mockito.verify(out).println("<html>\r\n" + 
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
	      		"<h2 align=\"center\">New Order Created With the Following Information!</h2>\n" + //
	      		"<ul>\n" + //
	
	            "  <b>Food Ordered</b>: test<br>\n" + //
	            "  <b>Price</b>: test<br>\n" + //
	            "  <b>Restaurant</b>: test<br>\n" + //
	            "  <b>Rating</b>: test<br>\n" + //
	
	            "</ul>\n" +
	      		"</section>\r\n" + 
	      		"\r\n" + 
	      		"<footer>\r\n" + 
	      		"Food Tracker site created by Devin Kellogg, Logan Sortino, Conner Kennell, and Ben Jahnke\r\n" + 
	      		"</footer>\r\n" + 
	      		"</body>\r\n" + 
	      		"</html>");
		
	}

}
