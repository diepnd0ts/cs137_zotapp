import java.sql.*;

public class DatabaseConnect {
    
    private DatabaseConnect() {
        
    }
    
    public static Connection getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection("jdbc:mysql://sylvester-mccoy-v3.ics.uci.edu/inf124-db-020","inf124-db-020","M{TNepH<WF!}");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
