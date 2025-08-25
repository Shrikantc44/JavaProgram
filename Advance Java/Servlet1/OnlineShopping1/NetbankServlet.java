import java.sql.*;
import java.util.Scanner;

public class BankSearch {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter card number: ");
            String cardNo = sc.nextLine();

            Class.forName("oracle.jdbc.driver.OracleDriver");

            try (Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "system",
                    "mca6");
                 PreparedStatement ps = con.prepareStatement(
                    "SELECT CUSTNAME FROM bank WHERE CARDNO = ?"
                 )) {

                ps.setString(1, cardNo);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String custName = rs.getString("CUSTNAME");
                        System.out.println("Customer Name: " + custName);
                    } else {
                        System.out.println("No record found for Card No: " + cardNo);
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Oracle JDBC Driver not found. Add ojdbc.jar to classpath.");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
