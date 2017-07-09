import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 *
 * @author brydi
 */
public class AutoComplete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Connection jdbcConnection = DatabaseConnect.getInstance();
        
        try {
            String search = request.getParameter("sql");
            Statement statement = jdbcConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products WHERE title LIKE '%" + search + "%'");
            int i = 0;
            while (resultSet.next()) {
                out.println("<tr>");
                    out.println("<td><a href='SessionTracking?game=" + resultSet.getString(2) + "'><img src='" + resultSet.getString(10) + "' height='75' width='75' class='product'/></a></td>");
                    out.println("<td><a href='SessionTracking?game=" + resultSet.getString(2) + "'>" + resultSet.getString(2) + "</a></td>");
                    out.println("<td>$" + resultSet.getString(4) + "</td>");
                    out.println("<td>" + resultSet.getString(5) + "</td>");
                    out.println("<td>" + String.format("%,d", resultSet.getInt(6)) + "</td>");
                    out.println("<td>" + resultSet.getString(7) + " MB</td>"); 
                    out.println("<td>" + resultSet.getString(8) + "</td> ");
                    out.println("<td>" + resultSet.getString(9) + "</td> ");
                out.println("</tr>");
            } 
        } catch (SQLException sqlException) {
            System.out.println("SQL Exception Error: " + sqlException.getMessage());
        }
    }
}