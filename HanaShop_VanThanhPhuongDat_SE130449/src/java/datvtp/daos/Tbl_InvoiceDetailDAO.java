package datvtp.daos;

import datvtp.dtos.Tbl_InvoiceDetailDTO;
import datvtp.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Tbl_InvoiceDetailDAO implements Serializable {

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

    public boolean insert(int foodId, int quantity, int total, int invoiceId) throws SQLException, NamingException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO tbl_InvoiceDetail (FoodId, Quantity, TotalPrice, InvoiceId) VALUES (?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, foodId);
            ps.setInt(2, quantity);
            ps.setInt(3, total);
            ps.setInt(4, invoiceId);

            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<Tbl_InvoiceDetailDTO> getListInvoiceDetailForUser(String foodName, String dateFrom, String dateTo, String email) throws SQLException, NamingException {
        List<Tbl_InvoiceDetailDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT InvoiceDetailId, FoodId, Quantity, TotalPrice, InvoiceId FROM tbl_InvoiceDetail\n"
                    + "	WHERE FoodId IN (SELECT FoodId FROM tbl_Food WHERE Name LIKE ?)\n"
                    + "	AND InvoiceId IN (SELECT InvoiceId FROM tbl_Invoice WHERE CreateTime >= ? AND CreateTime <= ? AND Email = ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%" + foodName + "%");
            ps.setString(2, dateFrom);
            ps.setString(3, dateTo);
            ps.setString(4, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                int invoiceDetailId = rs.getInt("InvoiceDetailId");
                int foodId = rs.getInt("FoodId");
                int quantity = rs.getInt("Quantity");
                int totalPrice = rs.getInt("TotalPrice");
                int invoiceId = rs.getInt("InvoiceId");
                list.add(new Tbl_InvoiceDetailDTO(invoiceDetailId, foodId, quantity, totalPrice, invoiceId));
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    
    public List<Tbl_InvoiceDetailDTO> getListInvoiceDetailForAdmin(String foodName, String dateFrom, String dateTo) throws SQLException, NamingException {
        List<Tbl_InvoiceDetailDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT InvoiceDetailId, FoodId, Quantity, TotalPrice, InvoiceId FROM tbl_InvoiceDetail\n"
                    + "	WHERE FoodId IN (SELECT FoodId FROM tbl_Food WHERE Name LIKE ?)\n"
                    + "	AND InvoiceId IN (SELECT InvoiceId FROM tbl_Invoice WHERE CreateTime >= ? AND CreateTime <= ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%" + foodName + "%");
            ps.setString(2, dateFrom);
            ps.setString(3, dateTo);
            rs = ps.executeQuery();

            while (rs.next()) {
                int invoiceDetailId = rs.getInt("InvoiceDetailId");
                int foodId = rs.getInt("FoodId");
                int quantity = rs.getInt("Quantity");
                int totalPrice = rs.getInt("TotalPrice");
                int invoiceId = rs.getInt("InvoiceId");
                list.add(new Tbl_InvoiceDetailDTO(invoiceDetailId, foodId, quantity, totalPrice, invoiceId));
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
