package com.uci.rest;

import com.uci.rest.model.Order;
import com.uci.rest.service.OrderService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.ArrayList;

/**
 * This class contains the methods that will respond to the various endpoints that you define for your RESTful API Service.
 *
 */
//orders will be the pathsegment that precedes any path segment specified by @Path on a method.
@Path("/orders")
public class OrderResource {


    //This method represents an endpoint with the URL /orders/{id} and a GET request ( Note that {id} is a placeholder for a path parameter)
    @Path("{id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getOrderById(@PathParam("id") int id/* The {id} placeholder parameter is resolved */) {
        //invokes the DB method which will fetch a order_list item object by id
        Order order = OrderService.getOrderById(id);

        //Respond with a 404 if there is no such order_list item for the id provided
        if(order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a order_list_item object to return as response
        return Response.ok(order).build();
    }

    //This method represents an endpoint with the URL /orders and a GET request.
    // Since there is no @PathParam mentioned, the /orders as a relative path and a GET request will invoke this method.
    @GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getAllOrders() {
        List<Order> orderList = OrderService.getAllOrders();

        if(orderList == null || orderList.isEmpty()) {

        }

        return Response.ok(orderList).build();
    }

    //This method represents an endpoint with the URL /orders and a POST request.
    // Since there is no @PathParam mentioned, the /orders as a relative path and a POST request will invoke this method.
    @POST
    @Consumes({MediaType.APPLICATION_JSON}) //This method accepts a request of the JSON type
    public Response addOrder(Order order) {

        //The order object here is automatically constructed from the json request. Jersey is so cool!
        if(OrderService.AddOrder(order)) {
            return Response.ok().entity("ORDER Added Successfully").build();
        }

        // Return an Internal Server error because something wrong happened. This should never be executed
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    //Similar to the method above.
    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED}) //This method accepts form parameters.
    //If you were to send a POST through a form submit, this method would be called.
    public Response addOrder(@FormParam("game") String game,
                            @FormParam("firstName") String firstName,
                            @FormParam("lastName") String lastName, 
                            @FormParam("address") String address,
                            @FormParam("city") String city,
                            @FormParam("state") String state,
                            @FormParam("zipCode") int zipCode,
                            @FormParam("phone") String phone,
                            @FormParam("email") String email,
                            @FormParam("creditCard") String creditCard,
                            @FormParam("expiration") String expiration,
                            @FormParam("securityCode") int securityCode) { 
        Order order = new Order();
        order.setGame(game);
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setAddress(address);
        order.setCity(city);
        order.setState(state);
        order.setZipCode(zipCode);
        order.setPhone(phone);
        order.setEmail(email);
        order.setCreditCard(creditCard);
        order.setExpiration(expiration);
        order.setSecurityCode(securityCode);

        System.out.println(order);

        if(OrderService.AddOrder(order)) {
            return Response.ok().entity("ORDER Added Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    //This method represents a PUT request where the id is provided as a path parameter and the request body is provided in JSON
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateOrder(@PathParam("id") int id, Order order) {

        // Retrieve the order that you will need to change
        Order retrievedOrder = OrderService.getOrderById(id);

        if(retrievedOrder == null) {
            //If not found then respond with a 404 response.
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested resource").build();
        }

        // This is the order_object retrieved from the json request sent.
        order.setId(id);

        // if the user has provided null, then we set the retrieved values.
        // This is done so that a null value is not passed to the order object when updating it.
        if(order.getGame() == null) {
            order.setGame(retrievedOrder.getGame());
        }

        //Same as above. We only change fields in the order_resource when the user has entered something in a request.
        if (order.getFirstName() == null) {
            order.setFirstName(retrievedOrder.getFirstName());
        }

        //This calls the JDBC method which in turn calls the the UPDATE SQL command
        if(OrderService.updateOrder(order)) {

            return Response.ok().entity(order).build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    //This method represents a DELETE request where the id is provided as a path parameter and the request body is provided in JSON
    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public Response deleteOrder(@PathParam("id") int id) {

        //Retrieve the order_object that you want to delete.
        Order retrievedOrder = OrderService.getOrderById(id);

        if(retrievedOrder == null) {
            //If not found throw a 404
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested resource").build();
        }

        //This calls the JDBC method which in turn calls the DELETE SQL command.
        if(OrderService.deleteOrder(retrievedOrder)) {
            return Response.ok().entity("ORDER Deleted Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

}
