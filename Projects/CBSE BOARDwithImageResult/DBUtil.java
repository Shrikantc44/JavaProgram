import java.sql.*;
import java.io.*;

public class DBUtil {
    private static final String URL  = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "system";
    private static final String PASS = "mca6";

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Oracle JDBC Driver not found.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // ---- Close helpers ----
    public static void closeQuietly(ResultSet rs) {
        if (rs != null) try { rs.close(); } catch (Exception ignored) {}
    }
    public static void closeQuietly(Statement st) {
        if (st != null) try { st.close(); } catch (Exception ignored) {}
    }
    public static void closeQuietly(PreparedStatement ps) {
        if (ps != null) try { ps.close(); } catch (Exception ignored) {}
    }
    public static void closeQuietly(Connection con) {
        if (con != null) try { con.close(); } catch (Exception ignored) {}
    }
    public static void closeQuietly(InputStream is) {
        if (is != null) try { is.close(); } catch (Exception ignored) {}
    }
    public static void closeQuietly(OutputStream os) {
        if (os != null) try { os.close(); } catch (Exception ignored) {}
    }
    public static void closeQuietly(Reader reader) {
        if (reader != null) try { reader.close(); } catch (Exception ignored) {}
    }
}