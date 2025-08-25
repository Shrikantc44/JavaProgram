import java.sql.*;
import java.io.*;
import java.util.*;

class TestRetrieve2 {
    public static void main(String[] ss) {
        try {
            // Load database properties from file
            FileInputStream fn = new FileInputStream("db.properties");
            Properties pr = new Properties();
            pr.load(fn);

            // Read properties
            String driver = pr.getProperty("driver");
            String url = pr.getProperty("url");
            String user = pr.getProperty("user");
            String pass = pr.getProperty("pass");

            // Load the JDBC driver
            Class.forName(driver);

            // Create database connection
            Connection c = DriverManager.getConnection(url, user, pass);
            Statement s = c.createStatement();

            // Execute query
            ResultSet result1 = s.executeQuery("SELECT * FROM emp101");

            // Print results
            while (result1.next()) {
                System.out.println(result1.getString("name") + " = " + result1.getString(2));
            }

            // Close resources
            result1.close();
            s.close();
            c.close();

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e);
        } catch (Exception i) {
            System.out.println("Error: " + i);
        }
    }
}
