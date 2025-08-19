import javax.naming.*;     // JNDI (Java Naming and Directory Interface) classes import
import java.util.*;        // For Properties class
import java.sql.*;         // For JDBC classes (Connection, Statement, ResultSet)
import javax.sql.*;        // For DataSource interface

class MyPool {
    public static void main(String s1[]) {
        try {
            // Properties object banaya for JNDI configuration
            Properties parm = new Properties();

            // Initial context factory set kar rahe hain (WebLogic specific)
            parm.setProperty("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");

            // WebLogic server ka URL set kar rahe hain
            parm.setProperty("java.naming.provider.url", "t3://localhost:7002");

            // Username set kar rahe hain for WebLogic authentication
            parm.setProperty("java.naming.security.principal", "weblogic");

            // Password set kar rahe hain for WebLogic authentication
            parm.setProperty("java.naming.security.credentials", "weblogic10");

            // InitialContext create kar rahe hain (yeh naming service ka entry point hai)
            InitialContext ctx = new InitialContext(parm);

            // JNDI lookup se DataSource object fetch kar rahe hain (tindi is pool ka JNDI name hai)
            DataSource ds = (DataSource) ctx.lookup("tindi");

            // Connection pool se ek connection le rahe hain
            Connection con = ds.getConnection();

            // Statement object create kar rahe hain for SQL execution
            Statement s = con.createStatement();

            // Query execute karke ResultSet me data store kar rahe hain
            ResultSet result = s.executeQuery("select * from emp101");

            // ResultSet ke through data iterate kar rahe hain
            while (result.next()) {
                // First column (id) ka data print kar rahe hain
                System.out.println("id: " + result.getString(1));

                // Second column (name) ka data print kar rahe hain
                System.out.println("name: " + result.getString(2));

                // Agar third column ka data chahiye to yeh uncomment kar sakte ho
                // System.out.println("name" + result.getString(3));
            }

        } catch (Exception e) {
            // Agar koi exception aata hai to print karenge
            System.out.println(e);
        }
    }
}
