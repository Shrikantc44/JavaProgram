import java.sql.*;

public class MyBatchCallableStatement {
    public static void main(String a[]) {
        CallableStatement callSt = null;

        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to Oracle DB
            Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", 
                "system", 
                "mca6"
            );

            // Create Statement
            Statement s = conn.createStatement();

            // Prepare CallableStatement to call stored procedure
            callSt = conn.prepareCall("{call recins88(?,?)}");

            // First Batch
            callSt.setInt(1, 372);
            callSt.setString(2, "cheeta372");
            callSt.addBatch();

            // Second Batch
            callSt.setInt(1, 373);
            callSt.setString(2, "cheeta373");
            callSt.addBatch();

            // Execute batch of stored procedure calls
            int[] updateCounts = callSt.executeBatch();

            // Retrieve and print data from emp65 table
            ResultSet result1 = s.executeQuery("select * from emp65");
            while (result1.next()) {
                System.out.println("ID   : " + result1.getString(1));
                System.out.println("Name : " + result1.getString(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
