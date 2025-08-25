import java.sql.*;

public class UserDAO {
    private static final String CREATE_TABLE =
        "CREATE TABLE image14 (username VARCHAR2(50) PRIMARY KEY, password VARCHAR2(50) NOT NULL, photo BLOB)";
    private static final String INSERT_USER =
        "INSERT INTO image14 (username, password, photo) VALUES (?, ?, ?)";
    private static final String GET_USER =
        "SELECT * FROM image14 WHERE username = ? AND password = ?";

    static {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            stmt.execute(CREATE_TABLE);
        } catch (SQLException e) {
            // Ignore if exists
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception ex) {}
            try { if (conn != null) conn.close(); } catch (Exception ex) {}
        }
    }

    public boolean registerUser(String username, String password, byte[] photo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(INSERT_USER);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setBytes(3, photo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception ex) {}
            try { if (conn != null) conn.close(); } catch (Exception ex) {}
        }
    }

    public User getUser(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(GET_USER);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getBytes("photo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ex) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ex) {}
            try { if (conn != null) conn.close(); } catch (Exception ex) {}
        }
        return null;
    }
}
