import java.sql.*;

class CreateUserTable {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection c = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");

            Statement s = c.createStatement();
            s.executeUpdate("CREATE TABLE userphoto (username VARCHAR2(50) PRIMARY KEY, password VARCHAR2(50), photo BLOB)");
            s.close();
            c.close();
            System.out.println("Table Created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
