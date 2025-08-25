import java.sql.*;
import java.io.*;
class Oracle1
{
    public static void main(String []ss)
    {
        try 
		{
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
                                                       "system", "mca6");
            Statement s = c.createStatement();
            s.executeUpdate("CREATE TABLE image24(userid varchar(10), img BLOB)");
            s.close();
        } 
		catch (Exception e) { }
    }
}
