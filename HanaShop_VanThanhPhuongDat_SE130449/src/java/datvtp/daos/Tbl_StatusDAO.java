package datvtp.daos;

import datvtp.dtos.Tbl_StatusDTO;
import datvtp.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Tbl_StatusDAO implements Serializable {

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

    public List<Tbl_StatusDTO> getListStatus() throws SQLException, NamingException {
        List<Tbl_StatusDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT StatusId, StatusName FROM tbl_Status";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int statusId = rs.getInt("StatusId");
                String statusName = rs.getString("StatusName");

                list.add(new Tbl_StatusDTO(statusId, statusName));
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
