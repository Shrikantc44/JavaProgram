import java.sql.*;

public class TestJDBC {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC", 
            "root", "shrik");
        System.out.println("Connected successfully!");
        conn.close();
    }
}
