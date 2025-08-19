import java.sql.*;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
        // Change credentials as per your DB
        return DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:XE", "system", "mca6");
    }
}
