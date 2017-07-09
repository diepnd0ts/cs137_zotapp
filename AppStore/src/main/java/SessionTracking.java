import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;


public class SessionTracking extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(true);
        
        /*
            EDITING THE RECENTLY VISITED GAMES ARRAYLIST AFTER USER CLICKS ON A GAME
        */
        String id = request.getParameter("id");
        String game = request.getParameter("title");
        if (game != null) {
            ArrayList<String> recentlyVisited = (ArrayList<String>)session.getAttribute("recentVisits");
            if (recentlyVisited == null) {
                recentlyVisited = new ArrayList<String>();
            }
            if (recentlyVisited.contains(game)) {
                recentlyVisited.remove(recentlyVisited.indexOf(game));
            }
            else if (recentlyVisited.size() == 5) {
                recentlyVisited.remove(0);
            }
            recentlyVisited.add(game);
            
            session.setAttribute("recentVisits", recentlyVisited);
            RequestDispatcher req = request.getRequestDispatcher("RESTDisplayProducts?id=" + id);
            req.forward(request, response);
        }
        
    }
}
