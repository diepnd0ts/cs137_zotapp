package com.uci.rest.service;

import com.uci.rest.db.DatabaseConnector;
import com.uci.rest.db.DatabaseUtils;
import com.uci.rest.model.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class OrderService {


    private final static String ALL_ORDERS_QUERY = "SELECT * FROM orderform";

    public static Order getOrderById(int id) {
        //Get a new connection object before going forward with the JDBC invocation.
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_ORDERS_QUERY + " WHERE id = " + id);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Order order = new Order();

                    order.setId(resultSet.getInt("id"));
                    order.setGame(resultSet.getString("game"));
                    order.setFirstName(resultSet.getString("firstname"));
                    order.setLastName(resultSet.getString("lastname"));
                    order.setAddress(resultSet.getString("address"));
                    order.setCity(resultSet.getString("city"));
                    order.setState(resultSet.getString("state"));
                    order.setZipCode(resultSet.getInt("zipcode"));
                    order.setPhone(resultSet.getString("phone"));
                    order.setEmail(resultSet.getString("email"));
                    order.setCreditCard(resultSet.getString("creditcard"));
                    order.setExpiration(resultSet.getString("expiration"));
                    order.setSecurityCode(resultSet.getInt("securitycode"));

                    return order;

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {

                    // We will always close the connection once we are done interacting with the Database.
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<Order>();

        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_ORDERS_QUERY);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Order order = new Order();

                    order.setId(resultSet.getInt("id"));
                    order.setGame(resultSet.getString("game"));
                    order.setFirstName(resultSet.getString("firstname"));
                    order.setLastName(resultSet.getString("lastname"));
                    order.setAddress(resultSet.getString("address"));
                    order.setCity(resultSet.getString("city"));
                    order.setState(resultSet.getString("state"));
                    order.setZipCode(resultSet.getInt("zipcode"));
                    order.setPhone(resultSet.getString("phone"));
                    order.setEmail(resultSet.getString("email"));
                    order.setCreditCard(resultSet.getString("creditcard"));
                    order.setExpiration(resultSet.getString("expiration"));
                    order.setSecurityCode(resultSet.getInt("securitycode"));

                    orders.add(order);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return orders;
    }

    public static boolean AddOrder(Order order) {
        String sql = "INSERT INTO orderform (game, firstname, lastname, address, city, "
                + "state, zipcode, phone, email, creditcard, expiration, securitycode) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DatabaseConnector.getConnection();
        return DatabaseUtils.performDBUpdate(connection, sql, order.getGame(), order.getFirstName(), order.getLastName(), 
            order.getAddress(), order.getCity(), order.getState(), String.valueOf(order.getZipCode()), order.getPhone(),
            order.getEmail(), order.getCreditCard(), order.getExpiration(), String.valueOf(order.getSecurityCode()));
    }

    public static boolean updateOrder(Order order) {

        String sql = "UPDATE orderform SET game=?, firstname=?, lastname=?, address=?, "
                + "city=?, state=?, zipcode=?, phone=?, email=?, creditcard=? expiration=? securitycode=? WHERE id=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, order.getGame(), order.getFirstName(), order.getLastName(), 
                order.getAddress(), order.getCity(), order.getState(), String.valueOf(order.getZipCode()), order.getPhone(),
                order.getEmail(), order.getCreditCard(), order.getExpiration(), String.valueOf(order.getSecurityCode()),
                String.valueOf(order.getId()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;

    }

    public static boolean deleteOrder(Order retrievedOrder) {

        String sql = "DELETE FROM orderform WHERE id=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(retrievedOrder.getId()));
        
        try {
            connection.close();         
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;
    }
}
