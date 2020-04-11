package datvtp.daos;

import datvtp.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class Tbl_UserDAO implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    
    public int checkLogin(String email, String password) throws NamingException, SQLException {
        int role = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT RoleId, Name FROM tbl_User WHERE Email = ? AND Password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                this.name = rs.getString("Name");
                role = rs.getInt("RoleId");
            }
        } finally {
            closeConnection();
        }
        return role;
    }
    
    public String checkEmailExist(String emailCheck) throws SQLException, NamingException {
        String email = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT Email FROM tbl_User WHERE Email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, emailCheck);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                email = rs.getString("Email");
            }
        } finally {
            closeConnection();
        }
        return email;
    }
    
    public boolean insert(String email, String name, String password) throws SQLException, NamingException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO tbl_User(Email, Name, Password, RoleId) VALUES (?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, name);
            ps.setString(3, password);
            ps.setInt(4, 2);

            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}
