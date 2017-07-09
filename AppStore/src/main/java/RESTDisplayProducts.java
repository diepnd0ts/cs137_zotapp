import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;

import zotapp.Product;

public class RESTDisplayProducts extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        String id = request.getParameter("id");
        if (id == null) {
            String jsonResponse =
                    target.path("v1").path("api").path("products").
                            request(). //send a request
                            accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                            get(String.class); // use the get method and return the response as a string

            System.out.println(jsonResponse);

            ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

            ArrayList<Product> productList = objectMapper.readValue(jsonResponse, new TypeReference<ArrayList<Product>>(){});

            try {
                request.setAttribute("productsList", productList);
                RequestDispatcher req = request.getRequestDispatcher("products.jsp");
                req.forward(request, response);
            } catch (ServletException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            String jsonResponse =
                    target.path("v1").path("api").path("products").path(id).
                            request(). //send a request
                            accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                            get(String.class); // use the get method and return the response as a string

            System.out.println(jsonResponse);

            ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

            Product product = objectMapper.readValue(jsonResponse, Product.class);

            try {
                request.setAttribute("product", product);
                RequestDispatcher req = request.getRequestDispatcher("viewproduct.jsp");
                req.forward(request, response);
            } catch (ServletException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://andromeda-50.ics.uci.edu:5050/jerseyrest").build();
    }


}
