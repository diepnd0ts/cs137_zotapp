import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ProductDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection jdbcConnection = DatabaseConnect.getInstance();
        try {
            String game = request.getParameter("game");
            Statement statement = jdbcConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products WHERE title='" + game + "'");
            resultSet.next();
            
            ArrayList<String> productInfo = new ArrayList<String>();
            productInfo.add(resultSet.getString(2));
            productInfo.add(resultSet.getString(3));
            productInfo.add(resultSet.getString(4));
            productInfo.add(resultSet.getString(5));
            productInfo.add(resultSet.getString(6));
            productInfo.add(resultSet.getString(7));
            productInfo.add(resultSet.getString(8));
            productInfo.add(resultSet.getString(9));
            productInfo.add(resultSet.getString(10));
            productInfo.add(resultSet.getString(11));

            request.setAttribute("productInfo", productInfo);
            RequestDispatcher req = request.getRequestDispatcher("viewproduct.jsp");
            req.forward(request,response);
           
        } catch (SQLException sqlException) {
            System.out.println("SQL Exception Error: " + sqlException.getMessage());
        }
    }
}
    