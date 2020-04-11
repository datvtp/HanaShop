package datvtp.daos;

import datvtp.dtos.Tbl_CategoryDTO;
import datvtp.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Tbl_CategoryDAO implements Serializable {

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

    public List<Tbl_CategoryDTO> getListCategory() throws SQLException, NamingException {
        List<Tbl_CategoryDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT CategoryId, CategoryName FROM tbl_Category";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int categoryId = rs.getInt("CategoryId");
                String categoryName = rs.getString("CategoryName");

                list.add(new Tbl_CategoryDTO(categoryId, categoryName));
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
