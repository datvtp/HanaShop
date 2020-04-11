package datvtp.daos;

import datvtp.dtos.Tbl_InvoiceDTO;
import datvtp.utils.DBUtils;
import datvtp.utils.DateTimeDataType;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class Tbl_InvoiceDAO implements Serializable {

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

    public boolean insert(int total, String email) throws SQLException, NamingException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO tbl_Invoice (CreateTime, TotalPrice, Email) VALUES (?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, DateTimeDataType.getTimeNow());
            ps.setInt(2, total);
            ps.setString(3, email);

            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getLastInvoiceId() throws SQLException, NamingException {
        int invoiceId = 1;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT MAX(InvoiceId) as InvoiceId FROM tbl_Invoice";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                invoiceId = rs.getInt("InvoiceId");
            }
        } finally {
            closeConnection();
        }
        return invoiceId;
    }

    public Tbl_InvoiceDTO findInvoiceById(int invoiceId) throws SQLException, NamingException {
        Tbl_InvoiceDTO invoiceDTO = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT InvoiceId, CreateTime, TotalPrice, Email FROM tbl_Invoice WHERE InvoiceId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, invoiceId);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("InvoiceId");
                String createTime = rs.getString("CreateTime");
                int totalPrice = rs.getInt("TotalPrice");
                String email = rs.getString("Email");
                invoiceDTO = new Tbl_InvoiceDTO(id, createTime, totalPrice, email);
            }
        } finally {
            closeConnection();
        }
        return invoiceDTO;
    }
}
