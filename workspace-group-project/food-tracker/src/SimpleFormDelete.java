
/**
 * @file SimpleFormDelete.java
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

      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(deleteSql);
         preparedStmt.setString(1, deleteId);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Delete Data from DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Record Deleted</b>\n" + //

            "</ul>\n");

      out.println("<a href=/webproject/simpleFormInsert.html>Insert Data</a> <br>");
      out.println("<a href=/webproject/simpleFormSearch.html>Search Data</a> <br>");
      out.println("<a href=/webproject/simpleFormDelete.html>Delete Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
