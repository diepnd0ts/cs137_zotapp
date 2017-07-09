package com.uci.rest.service;

import com.uci.rest.db.DatabaseConnector;
import com.uci.rest.db.DatabaseUtils;
import com.uci.rest.model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ProductService {


    private final static String ALL_PRODUCTS_QUERY = "SELECT * FROM products";

    public static Product getProductById(int id) {
        //Get a new connection object before going forward with the JDBC invocation.
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_PRODUCTS_QUERY + " WHERE id = " + id);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Product product = new Product();

                    product.setId(resultSet.getInt("id"));
                    product.setTitle(resultSet.getString("title"));
                    product.setDeveloper(resultSet.getString("developer"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setGenre(resultSet.getString("genre"));
                    product.setDownloads(resultSet.getInt("downloads"));
                    product.setDownloadSize(resultSet.getString("download_size"));
                    product.setContentRating(resultSet.getString("content_rating"));
                    product.setAppRating(resultSet.getDouble("app_rating"));
                    product.setIcon(resultSet.getString("icon"));
                    product.setDescription(resultSet.getString("description"));

                    return product;

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

    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();

        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_PRODUCTS_QUERY);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Product product = new Product();

                    product.setId(resultSet.getInt("id"));
                    product.setTitle(resultSet.getString("title"));
                    product.setDeveloper(resultSet.getString("developer"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setGenre(resultSet.getString("genre"));
                    product.setDownloads(resultSet.getInt("downloads"));
                    product.setDownloadSize(resultSet.getString("download_size"));
                    product.setContentRating(resultSet.getString("content_rating"));
                    product.setAppRating(resultSet.getDouble("app_rating"));
                    product.setIcon(resultSet.getString("icon"));
                    product.setDescription(resultSet.getString("description"));

                    products.add(product);

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

        return products;
    }

    public static boolean AddProduct(Product product) {
        String sql = "INSERT INTO products (title, developer, price, genre, downloads, "
                + "download_size, content_rating, app_rating, icon, description) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DatabaseConnector.getConnection();
        return DatabaseUtils.performDBUpdate(connection, sql, product.getTitle(), product.getDeveloper(), 
                String.valueOf(product.getPrice()), product.getGenre(), String.valueOf(product.getDownloads()), product.getDownloadSize(), 
                product.getContentRating(), String.valueOf(product.getAppRating()), product.getIcon(), product.getDescription());
    }

    public static boolean updateProduct(Product product) {

        String sql = "UPDATE product SET title=?, developer=?, price=?, genre=?, downloads=?, download_size=?, content_rating=?, app_rating=?, icon=?, description=? WHERE id=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, product.getTitle(), product.getDeveloper(), 
                String.valueOf(product.getPrice()), product.getGenre(), String.valueOf(product.getDownloads()), product.getDownloadSize(), 
                product.getContentRating(), String.valueOf(product.getAppRating()), product.getIcon(), product.getDescription(),
                String.valueOf(product.getId()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;

    }

    public static boolean deleteProduct(Product retrievedProduct) {

        String sql = "DELETE FROM products WHERE id=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(retrievedProduct.getId()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;
    }
}
