import zotapp.ShoppingCart;
import zotapp.Item;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

/*
   * Gets cart list from session
   * Adds new game FROM viewproduct.jsp into cart
   * SQL Queries (title, developer, price, icon) from cart list
   * Stores query info in cartInfoList
   * Forwards to mycart.jsp
*/
public class CartInfo extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(true);
        //Create a ShoppingCart object if it does not exist in the session
        ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
        }
        
        String game = request.getParameter("game");
        String developer = request.getParameter("developer");
        double price = Double.parseDouble(request.getParameter("price"));
        String icon = request.getParameter("icon");
        
        //Add the new game into the ShoppingCart and save it in session
        if (game != null) {
            cart.addToCart(new Item(game, developer, price, icon));
            session.setAttribute("cart", cart);
        }

        RequestDispatcher req = request.getRequestDispatcher("mycart.jsp");
        req.forward(request, response);
    }

}
