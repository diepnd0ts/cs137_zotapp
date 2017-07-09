import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;

public class DisplayProducts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Connection jdbcConnection = DatabaseConnect.getInstance();
        
        /* 
        String newItem = request.getParameter("newItem");
        if (newItem != null) {
            out.println("<h1>Justed added to cart: " + newItem + "!</h1>");
        }
        */
        //RequestDispatcher req = request.getRequestDispatcher("SessionTracking?getVisited=true");
        //req.include(request,response);
        
        try {
            Statement statement = jdbcConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            ArrayList<ArrayList<String>> productsList = new ArrayList<ArrayList<String>>();
            while (resultSet.next()) {
                ArrayList<String> productInfo = new ArrayList<String>();
                productInfo.add(resultSet.getString(2)); //title 0
                productInfo.add(resultSet.getString(4)); //price 1
                productInfo.add(resultSet.getString(5)); //genre 2
                productInfo.add(resultSet.getString(6)); //downloads 3
                productInfo.add(resultSet.getString(7)); //download_size 4
                productInfo.add(resultSet.getString(8)); //content_rating 5
                productInfo.add(resultSet.getString(9)); //app_rating 6
                productInfo.add(resultSet.getString(10)); //icon 7
                
                productsList.add(productInfo);
            }
            
            request.setAttribute("productsList", productsList);
            RequestDispatcher req = request.getRequestDispatcher("products.jsp");
            req.forward(request, response);
            

        } catch (SQLException sqlException) {
            System.out.println("SQL Exception Error: " + sqlException.getMessage());
        }
    }

}
