import java.sql.*;

class Oracle {
    public static void main(String[] args) {
        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Correct Oracle DB connection URL
            Connection c = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",  // Corrected colon after 'thin:'
                "system",                                // Username
                "mca6"                                   // Password
            );

            int t1 = 123;
            String t2 = "lalu";

            // Create statement for table operations
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            // Drop and create table
            try {
                s.executeUpdate("drop table emp66");
            } catch (SQLException e) {
                System.out.println("Table doesn't exist or already dropped.");
            }

            s.executeUpdate("create table emp66(id number, name varchar2(20))");
            s.close();

            // Insert data using PreparedStatement
            PreparedStatement ps = c.prepareStatement(
                "insert into emp66 values(?, ?)",
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
            );

            ps.setInt(1, t1);
            ps.setString(2, t2);
            ps.executeUpdate();
            ps.close();

            // Retrieve data using PreparedStatement
            ps = c.prepareStatement("select * from emp66 where id = ?");
            ps.setInt(1, t1);
            ResultSet result1 = ps.executeQuery();

            while (result1.next()) {
                System.out.println("ID: " + result1.getInt("id"));
                System.out.println("Name: " + result1.getString("name"));
            }

            // Cleanup
            result1.close();
            ps.close();
            c.close();

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e);
        } catch (Exception i) {
            System.out.println("Error: " + i);
        }
    }
}
