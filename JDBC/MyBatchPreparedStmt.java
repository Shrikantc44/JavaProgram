import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MyBatchPreparedStmt 
{
    public static void main(String a[])
	{
        Connection con = null;
        PreparedStatement pst = null;

        try
		{
            // Oracle JDBC Driver Load
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Database Connection
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", 
                "system", 
                "mca6"
            );

            // Statement creation
            Statement s = con.createStatement();

            // PreparedStatement for batch update
            pst = con.prepareStatement("update emp101 set name=? where pass=?");

            // First batch
            pst.setString(1, "Gadar2");
            pst.setString(2, "Gadar2");
            pst.addBatch();

            // Second batch
            pst.setString(1, "jaddu4545");
            pst.setString(2, "jaddu");
            pst.addBatch();

            // Execute batch
            int count[] = pst.executeBatch();

            // Fetch and print updated data
            ResultSet result1 = s.executeQuery("select * from emp101");
            while (result1.next()) {
                System.out.println("Name: " + result1.getString(1));
                System.out.println("Pass: " + result1.getString(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

