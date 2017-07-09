package com.uci.rest.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by tariqibrahim on 5/28/17.
 */
public class DatabaseConfig {
    static Properties properties;

    static {

        properties = new Properties();

        String resourceName = "db.config.properties";

        InputStream inputStream = DatabaseConfig.class.getResourceAsStream("/" + resourceName);

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    public static String getDatabaseName() {
        return properties.getProperty("jdbc.db.databasename");
    }

    public static String getUser() {
        return properties.getProperty("jdbc.db.user");
    }

    public static String getPassword() {
        return properties.getProperty("jdbc.db.password");
    }

    public static String getHost() {
        return properties.getProperty("jdbc.db.host");
    }

}
