package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.junit.jupiter.api.Test;

class SimpleFormSearchSpec {

	@Test
	void testNoResultsFoundFoodAndRestaurantProvided() throws Exception {
		PrintWriter out = Mockito.mock(PrintWriter.class);
		
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		Mockito.when(response.getWriter()).thenReturn(out);
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Mockito.doReturn("test").when(request).getParameter("food");
		Mockito.doReturn("test").when(request).getParameter("restaurant");
		
		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(rs.next()).thenReturn(false);
		
		SimpleFormSearch sfs = new SimpleFormSearch();
		SimpleFormSearch sfs1 = Mockito.spy(sfs);
		Mockito.doReturn(rs).when(sfs1).dbHelper("test", "test");
		
		sfs1.doGet(request, response);
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
	      		"<section>\r\n");
		Mockito.verify(out).println("<h2 align=\"center\">There were no entries found for your search. Please try again.</h2>\n");
		Mockito.verify(out).println("</section>\r\n" + 
	      		"\r\n" + 
	      		"<footer>\r\n" + 
	      		"Food Tracker site created by Devin Kellogg, Logan Sortino, Conner Kennell, and Ben Jahnke\r\n" + 
	      		"</footer>\r\n" + 
	      		"</body>\r\n" + 
	      		"</html>");
	}
	
	@Test
	void testResultsFoundNoRestaurantYesFood() throws Exception {
		PrintWriter out = Mockito.mock(PrintWriter.class);
		
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		Mockito.when(response.getWriter()).thenReturn(out);
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Mockito.doReturn("test").when(request).getParameter("food");
		Mockito.doReturn("").when(request).getParameter("restaurant");
		
		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(rs.next()).thenAnswer(new Answer<Boolean>() {
			private int numCalled = 0;
			@Override
		    public Boolean answer(InvocationOnMock invocation) throws Throwable {
		       numCalled++;
		       return numCalled <= 2;
		    }
		});
		Mockito.doReturn(0).when(rs).getInt("id");
		Mockito.doReturn("test").when(rs).getString("food_ordered");
		Mockito.doReturn("test").when(rs).getString("price");
		Mockito.doReturn("test").when(rs).getString("restaurant_ordered_from");
		Mockito.doReturn("test").when(rs).getString("rating");
		
		SimpleFormSearch sfs = new SimpleFormSearch();
		SimpleFormSearch sfs1 = Mockito.spy(sfs);
		Mockito.doReturn(rs).when(sfs1).dbHelper("test", "");
		
		sfs1.doGet(request, response);
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
	      		"<section>\r\n");
		Mockito.verify(out).println("<h2 align=\"center\">Orders found!</h2>\n");
		Mockito.verify(out, Mockito.times(2)).println("ID: 0, ");
		Mockito.verify(out, Mockito.times(2)).println("Food: test, ");
		Mockito.verify(out, Mockito.times(2)).println("Price: test, ");
		Mockito.verify(out, Mockito.times(2)).println("Restaurant: test, ");
		Mockito.verify(out, Mockito.times(2)).println("Rating: test");
		Mockito.verify(out, Mockito.times(2)).println("<br>");

		Mockito.verify(out).println("</section>\r\n" + 
	      		"\r\n" + 
	      		"<footer>\r\n" + 
	      		"Food Tracker site created by Devin Kellogg, Logan Sortino, Conner Kennell, and Ben Jahnke\r\n" + 
	      		"</footer>\r\n" + 
	      		"</body>\r\n" + 
	      		"</html>");
	}
	
	@Test
	void testNoFoodYesRestaurant() throws Exception {
		PrintWriter out = Mockito.mock(PrintWriter.class);
		
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		Mockito.when(response.getWriter()).thenReturn(out);
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Mockito.doReturn("").when(request).getParameter("food");
		Mockito.doReturn("test").when(request).getParameter("restaurant");
		
		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(rs.next()).thenAnswer(new Answer<Boolean>() {
			private int numCalled = 0;
			@Override
		    public Boolean answer(InvocationOnMock invocation) throws Throwable {
		       numCalled++;
		       return numCalled <= 2;
		    }
		});
		Mockito.doReturn(0).when(rs).getInt("id");
		Mockito.doReturn("test").when(rs).getString("food_ordered");
		Mockito.doReturn("test").when(rs).getString("price");
		Mockito.doReturn("test").when(rs).getString("restaurant_ordered_from");
		Mockito.doReturn("test").when(rs).getString("rating");
		
		SimpleFormSearch sfs = new SimpleFormSearch();
		SimpleFormSearch sfs1 = Mockito.spy(sfs);
		Mockito.doReturn(rs).when(sfs1).dbHelper("", "test");
		
		sfs1.doGet(request, response);
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
	      		"<section>\r\n");
		Mockito.verify(out).println("<h2 align=\"center\">Orders found!</h2>\n");
		Mockito.verify(out, Mockito.times(2)).println("ID: 0, ");
		Mockito.verify(out, Mockito.times(2)).println("Food: test, ");
		Mockito.verify(out, Mockito.times(2)).println("Price: test, ");
		Mockito.verify(out, Mockito.times(2)).println("Restaurant: test, ");
		Mockito.verify(out, Mockito.times(2)).println("Rating: test");
		Mockito.verify(out, Mockito.times(2)).println("<br>");

		Mockito.verify(out).println("</section>\r\n" + 
	      		"\r\n" + 
	      		"<footer>\r\n" + 
	      		"Food Tracker site created by Devin Kellogg, Logan Sortino, Conner Kennell, and Ben Jahnke\r\n" + 
	      		"</footer>\r\n" + 
	      		"</body>\r\n" + 
	      		"</html>");
	}
	
	@Test
	void testSearchAll() throws Exception {
		PrintWriter out = Mockito.mock(PrintWriter.class);
		
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		Mockito.when(response.getWriter()).thenReturn(out);
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Mockito.doReturn("").when(request).getParameter("food");
		Mockito.doReturn("").when(request).getParameter("restaurant");
		
		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(rs.next()).thenAnswer(new Answer<Boolean>() {
			private int numCalled = 0;
			@Override
		    public Boolean answer(InvocationOnMock invocation) throws Throwable {
		       numCalled++;
		       return numCalled <= 2;
		    }
		});
		Mockito.doReturn(0).when(rs).getInt("id");
		Mockito.doReturn("test").when(rs).getString("food_ordered");
		Mockito.doReturn("test").when(rs).getString("price");
		Mockito.doReturn("test").when(rs).getString("restaurant_ordered_from");
		Mockito.doReturn("test").when(rs).getString("rating");
		
		SimpleFormSearch sfs = new SimpleFormSearch();
		SimpleFormSearch sfs1 = Mockito.spy(sfs);
		Mockito.doReturn(rs).when(sfs1).dbHelper("", "");
		
		sfs1.doGet(request, response);
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
	      		"<section>\r\n");
		Mockito.verify(out).println("<h2 align=\"center\">Orders found!</h2>\n");
		Mockito.verify(out, Mockito.times(2)).println("ID: 0, ");
		Mockito.verify(out, Mockito.times(2)).println("Food: test, ");
		Mockito.verify(out, Mockito.times(2)).println("Price: test, ");
		Mockito.verify(out, Mockito.times(2)).println("Restaurant: test, ");
		Mockito.verify(out, Mockito.times(2)).println("Rating: test");
		Mockito.verify(out, Mockito.times(2)).println("<br>");

		Mockito.verify(out).println("</section>\r\n" + 
	      		"\r\n" + 
	      		"<footer>\r\n" + 
	      		"Food Tracker site created by Devin Kellogg, Logan Sortino, Conner Kennell, and Ben Jahnke\r\n" + 
	      		"</footer>\r\n" + 
	      		"</body>\r\n" + 
	      		"</html>");
	}

}
