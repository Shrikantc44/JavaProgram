import java.sql.*;
import java.io.*;

class MySql {
    public static void main(String[] ss) {
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection
            Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/codesquadz15",
                "codesquadz15",
                "codesquadz15"
            );

            // Sample data
            String t1 = "lalu";
            int t2 = 420;

            // Create table
            Statement s = c.createStatement();
            s.executeUpdate("CREATE TABLE IF NOT EXISTS emp162 (name VARCHAR(20), salary INT)");
            s.close();

            // Insert data using PreparedStatement
            PreparedStatement ps = c.prepareStatement("INSERT INTO emp162 VALUES (?, ?)");
            ps.setString(1, t1);
            ps.setInt(2, t2);
            ps.executeUpdate();
            ps.close();

            // Retrieve data with condition
            ps = c.prepareStatement("SELECT * FROM emp162 WHERE salary = ?");
            ps.setInt(1, t2);
            ResultSet result1 = ps.executeQuery();

            while (result1.next()) {
                System.out.println("name: " + result1.getString(1));
                System.out.println("salary: " + result1.getString(2));
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception i) {
            System.out.println(i);
        }
    }
}
