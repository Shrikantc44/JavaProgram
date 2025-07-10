import java.sql.*;
import java.io.*;
class PostgreSql
{
	 public static void main(String[] ss) {
        try {
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres", "postgres", "manish");

            String t1 = "lalu";
            int t2 = 120;

            Statement s = con.createStatement();
            s.executeUpdate("create table emp162(name varchar(20), salary int)");

            PreparedStatement ps = con.prepareStatement("insert into emp162 values(?,?)");
            ps.setString(1, t1);
            ps.setInt(2, t2);
            ps.executeUpdate();
            ps.close();

            PreparedStatement psec = con.prepareStatement("select * from emp162 where salary=?");
            psec.setInt(1, t2);

            ResultSet result1 = psec.executeQuery();

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
