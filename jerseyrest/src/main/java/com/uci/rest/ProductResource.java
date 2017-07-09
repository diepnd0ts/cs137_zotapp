package com.uci.rest;

import com.uci.rest.model.Product;
import com.uci.rest.service.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * This class contains the methods that will respond to the various endpoints that you define for your RESTful API Service.
 *
 */
//products will be the pathsegment that precedes any path segment specified by @Path on a method.
@Path("/products")
public class ProductResource {


    //This method represents an endpoint with the URL /products/{id} and a GET request ( Note that {id} is a placeholder for a path parameter)
    @Path("{id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getProductById(@PathParam("id") int id/* The {id} placeholder parameter is resolved */) {
        //invokes the DB method which will fetch a product_list item object by id
        Product product = ProductService.getProductById(id);

        //Respond with a 404 if there is no such product_list item for the id provided
        if(product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a product_list_item object to return as response
        return Response.ok(product).build();
    }

    //This method represents an endpoint with the URL /products and a GET request.
    // Since there is no @PathParam mentioned, the /products as a relative path and a GET request will invoke this method.
    @GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getAllProducts() {
        List<Product> productList = ProductService.getAllProducts();

        if(productList == null || productList.isEmpty()) {

        }

        return Response.ok(productList).build();
    }

    //This method represents an endpoint with the URL /products and a POST request.
    // Since there is no @PathParam mentioned, the /products as a relative path and a POST request will invoke this method.
    @POST
    @Consumes({MediaType.APPLICATION_JSON}) //This method accepts a request of the JSON type
    public Response addProduct(Product product) {

        //The product object here is automatically constructed from the json request. Jersey is so cool!
        if(ProductService.AddProduct(product)) {
            return Response.ok().entity("PRODUCT Added Successfully").build();
        }

        // Return an Internal Server error because something wrong happened. This should never be executed
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    //Similar to the method above.
    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED}) //This method accepts form parameters.
    //If you were to send a POST through a form submit, this method would be called.
    public Response addProduct(@FormParam("title") String title,
                            @FormParam("developer") String developer,
                            @FormParam("price") double price, 
                            @FormParam("genre") String genre,
                            @FormParam("downloads") int downloads,
                            @FormParam("downloadSize") String downloadSize,
                            @FormParam("contentRating") String contentRating,
                            @FormParam("appRating") double appRating,
                            @FormParam("icon") String icon,
                            @FormParam("description") String description) {
        Product product = new Product();
        product.setTitle(title);
        product.setDeveloper(developer);
        product.setPrice(price);
        product.setGenre(genre);
        product.setDownloads(downloads);
        product.setDownloadSize(downloadSize);
        product.setContentRating(contentRating);
        product.setAppRating(appRating);
        product.setIcon(icon);
        product.setDescription(description);

        System.out.println(product);

        if(ProductService.AddProduct(product)) {
            return Response.ok().entity("PRODUCT Added Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    //This method represents a PUT request where the id is provided as a path parameter and the request body is provided in JSON
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateProduct(@PathParam("id") int id, Product product) {

        // Retrieve the product that you will need to change
        Product retrievedProduct = ProductService.getProductById(id);

        if(retrievedProduct == null) {
            //If not found then respond with a 404 response.
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested resource").build();
        }

        // This is the product_object retrieved from the json request sent.
        product.setId(id);

        // if the user has provided null, then we set the retrieved values.
        // This is done so that a null value is not passed to the product object when updating it.
        if(product.getTitle() == null) {
            product.setTitle(retrievedProduct.getTitle());
        }

        //Same as above. We only change fields in the product_resource when the user has entered something in a request.
        if (product.getDeveloper() == null) {
            product.setDeveloper(retrievedProduct.getDeveloper());
        }

        //This calls the JDBC method which in turn calls the the UPDATE SQL command
        if(ProductService.updateProduct(product)) {

            return Response.ok().entity(product).build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    //This method represents a DELETE request where the id is provided as a path parameter and the request body is provided in JSON
    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public Response deleteProduct(@PathParam("id") int id) {

        //Retrieve the product_object that you want to delete.
        Product retrievedProduct = ProductService.getProductById(id);

        if(retrievedProduct == null) {
            //If not found throw a 404
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested resource").build();
        }

        //This calls the JDBC method which in turn calls the DELETE SQL command.
        if(ProductService.deleteProduct(retrievedProduct)) {
            return Response.ok().entity("PRODUCT Deleted Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
