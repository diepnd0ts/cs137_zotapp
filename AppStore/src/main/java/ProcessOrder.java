import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import zotapp.*;

public class ProcessOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
        
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipcode = request.getParameter("zipcode");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String creditcard = request.getParameter("creditcard");
        String expiration = request.getParameter("expiration");
        String securitycode = request.getParameter("securitycode");

        Connection jdbcConnection = DatabaseConnect.getInstance();
        
        //PreparedStatement update = null;
        Statement statement = null;
        try {
                
            for (int i = 0; i < cart.getCartSize(); i++) {
                String query = "INSERT INTO orderform (game, firstname, lastname, address, city, "
                            + "state, zipcode, phone, email, creditcard, expiration, securitycode) "
                            + "VALUES ('" + cart.getItemName(i) + "', '" + firstname + "', '" + lastname + "', '" + address + "', "
                                    + "'" + city + "', '" + state + "','" + zipcode + "', '" + phone + "','" + email + "',"
                                            + "'" + creditcard + "','" + expiration + "','" + securitycode + "')";
                for (int j = 0; j < cart.getItemQuantity(i); j++) {    
                    statement = jdbcConnection.createStatement();
                    statement.executeUpdate(query);
                }
            }
                //Statement statement = jdbcConnection.createStatement();
                
                //update = jdbcConnection.prepareStatement(query);
                //update.executeUpdate();
                //statement.executeUpdate(query);
                
                //Clear all cart items after the order has been processed
                session.setAttribute("cart", new ShoppingCart());
                RequestDispatcher req = request.getRequestDispatcher("orderconfirmation.jsp");
                req.forward(request, response);
        } catch (SQLException sqlException) {
            out.println("<p>SQL Exception Error: " + sqlException.getMessage() + "</p>");
        }
    }

}
