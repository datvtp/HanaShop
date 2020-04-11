package datvtp.daos;

import datvtp.dtos.Tbl_FoodDTO;
import datvtp.utils.DBUtils;
import datvtp.utils.DateTimeDataType;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Tbl_FoodDAO implements Serializable {

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

    public int getNumberOfFood(String foodName, int categoryId, int minPrice, int maxPrice) throws SQLException, NamingException {
        int number = 0;
        try {
            conn = DBUtils.getConnection();
            if (categoryId == 0) {
                String sql = "SELECT Count(*) as Total FROM tbl_Food "
                        + "WHERE Name LIKE ? AND Price >= ? AND Price <= ? AND Quantity > 0 AND StatusId = 1";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + foodName + "%");
                ps.setInt(2, minPrice);
                ps.setInt(3, maxPrice);
            } else {
                String sql = "SELECT Count(*) as Total FROM tbl_Food "
                        + "WHERE Name LIKE ? AND Price >= ? AND Price <= ? AND CategoryId = ? AND Quantity > 0 AND StatusId = 1";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + foodName + "%");
                ps.setInt(2, minPrice);
                ps.setInt(3, maxPrice);
                ps.setInt(4, categoryId);
            }
            rs = ps.executeQuery();

            if (rs.next()) {
                number = rs.getInt("Total");
            }

        } finally {
            closeConnection();
        }
        return number;
    }

    public int getNumberOfFoodForAdmin(String foodName, int categoryId, int minPrice, int maxPrice) throws SQLException, NamingException {
        int number = 0;
        try {
            conn = DBUtils.getConnection();
            if (categoryId == 0) {
                String sql = "SELECT Count(*) as Total FROM tbl_Food "
                        + "WHERE Name LIKE ? AND Price >= ? AND Price <= ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + foodName + "%");
                ps.setInt(2, minPrice);
                ps.setInt(3, maxPrice);
            } else {
                String sql = "SELECT Count(*) as Total FROM tbl_Food "
                        + "WHERE Name LIKE ? AND Price >= ? AND Price <= ? AND CategoryId = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + foodName + "%");
                ps.setInt(2, minPrice);
                ps.setInt(3, maxPrice);
                ps.setInt(4, categoryId);
            }
            rs = ps.executeQuery();

            if (rs.next()) {
                number = rs.getInt("Total");
            }

        } finally {
            closeConnection();
        }
        return number;
    }

    public List<Tbl_FoodDTO> getListFoodSearch(String foodName, int categoryId, int minPrice, int maxPrice, int offsetRecord, int next) throws SQLException, NamingException {
        List<Tbl_FoodDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (categoryId == 0) {
                String sql = "SELECT FoodId, Name, Image, Description, Price, Quantity, CreateTime, CategoryId, StatusId FROM tbl_Food\n"
                        + "	Where Name LIKE ? AND Price >= ? AND Price <= ? AND Quantity > 0 AND StatusId = 1\n"
                        + "	ORDER BY CreateTime DESC\n"
                        + "	OFFSET ? ROWS\n"
                        + "	FETCH NEXT ? ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + foodName + "%");
                ps.setInt(2, minPrice);
                ps.setInt(3, maxPrice);
                ps.setInt(4, offsetRecord);
                ps.setInt(5, next);
            } else {
                String sql = "SELECT FoodId, Name, Image, Description, Price, Quantity, CreateTime, CategoryId, StatusId FROM tbl_Food\n"
                        + "	Where Name LIKE ? AND Price >= ? AND Price <= ? AND CategoryId = ? AND Quantity > 0 AND StatusId = 1\n"
                        + "	ORDER BY CreateTime DESC\n"
                        + "	OFFSET ? ROWS\n"
                        + "	FETCH NEXT ? ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + foodName + "%");
                ps.setInt(2, minPrice);
                ps.setInt(3, maxPrice);
                ps.setInt(4, categoryId);
                ps.setInt(5, offsetRecord);
                ps.setInt(6, next);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                int foodId = rs.getInt("FoodId");
                String name = rs.getString("Name");
                String image = rs.getString("Image");
                String description = rs.getString("Description");
                int price = rs.getInt("Price");
                int quantity = rs.getInt("Quantity");
                String createTime = rs.getString("CreateTime");
                int category = rs.getInt("CategoryId");
                int statusId = rs.getInt("StatusId");

                list.add(new Tbl_FoodDTO(foodId, name, image, description, price, quantity, createTime, category, statusId));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<Tbl_FoodDTO> getListFoodSearchForAdmin(String foodName, int categoryId, int minPrice, int maxPrice, int offsetRecord, int next) throws SQLException, NamingException {
        List<Tbl_FoodDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (categoryId == 0) {
                String sql = "SELECT FoodId, Name, Image, Description, Price, Quantity, CreateTime, CategoryId, StatusId FROM tbl_Food\n"
                        + "	Where Name LIKE ? AND Price >= ? AND Price <= ?\n"
                        + "	ORDER BY CreateTime DESC\n"
                        + "	OFFSET ? ROWS\n"
                        + "	FETCH NEXT ? ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + foodName + "%");
                ps.setInt(2, minPrice);
                ps.setInt(3, maxPrice);
                ps.setInt(4, offsetRecord);
                ps.setInt(5, next);
            } else {
                String sql = "SELECT FoodId, Name, Image, Description, Price, Quantity, CreateTime, CategoryId, StatusId FROM tbl_Food\n"
                        + "	Where Name LIKE ? AND Price >= ? AND Price <= ? AND CategoryId = ?\n"
                        + "	ORDER BY CreateTime DESC\n"
                        + "	OFFSET ? ROWS\n"
                        + "	FETCH NEXT ? ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + foodName + "%");
                ps.setInt(2, minPrice);
                ps.setInt(3, maxPrice);
                ps.setInt(4, categoryId);
                ps.setInt(5, offsetRecord);
                ps.setInt(6, next);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                int foodId = rs.getInt("FoodId");
                String name = rs.getString("Name");
                String image = rs.getString("Image");
                String description = rs.getString("Description");
                int price = rs.getInt("Price");
                int quantity = rs.getInt("Quantity");
                String createTime = rs.getString("CreateTime");
                int category = rs.getInt("CategoryId");
                int statusId = rs.getInt("StatusId");

                list.add(new Tbl_FoodDTO(foodId, name, image, description, price, quantity, createTime, category, statusId));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean insertFood(String foodName, String image, String description, int price, int quantity, String createTime, int categoryId) throws SQLException, NamingException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO tbl_Food (Name, Image, Description, Price, Quantity, CreateTime, CategoryId, StatusId) VALUES (?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, foodName);
            ps.setString(2, image);
            ps.setString(3, description);
            ps.setInt(4, price);
            ps.setInt(5, quantity);
            ps.setString(6, createTime);
            ps.setInt(7, categoryId);
            ps.setInt(8, 1);

            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean deleteFood(int foodId) throws SQLException, NamingException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tbl_Food SET StatusId = 2 WHERE FoodId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, foodId);

            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean record(String email, int foodId) throws SQLException, NamingException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tbl_Food SET UpdateUser = ?, UpdateTime = ? WHERE FoodId = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, DateTimeDataType.getTimeNow());
            ps.setInt(3, foodId);

            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public Tbl_FoodDTO findFoodById(int foodId) throws SQLException, NamingException {
        Tbl_FoodDTO foodDTO = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT FoodId, Name, Image, Description, Price, Quantity, CreateTime, CategoryId, StatusId FROM tbl_Food WHERE FoodId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, foodId);
            rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("FoodId");
                String name = rs.getString("Name");
                String image = rs.getString("Image");
                String description = rs.getString("Description");
                int price = rs.getInt("Price");
                int quantity = rs.getInt("Quantity");
                String createTime = rs.getString("CreateTime");
                int categoryId = rs.getInt("CategoryId");
                int statusId = rs.getInt("StatusId");

                foodDTO = new Tbl_FoodDTO(id, name, image, description, price, quantity, createTime, categoryId, statusId);
            }
        } finally {
            closeConnection();
        }
        return foodDTO;
    }

    public boolean updateFood(int foodId, String name, String image, String description, int price, int quantity, int categoryId, int statusId) throws SQLException, NamingException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tbl_Food SET Name = ?, Image = ?, Description = ?, Price = ?, Quantity = ?, CategoryId = ?, StatusId = ? WHERE FoodId = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, description);
            ps.setInt(4, price);
            ps.setInt(5, quantity);
            ps.setInt(6, categoryId);
            ps.setInt(7, statusId);
            ps.setInt(8, foodId);

            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateFoodNoImage(int foodId, String name, String description, int price, int quantity, int categoryId, int statusId) throws SQLException, NamingException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tbl_Food SET Name = ?, Description = ?, Price = ?, Quantity = ?, CategoryId = ?, StatusId = ? WHERE FoodId = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, price);
            ps.setInt(4, quantity);
            ps.setInt(5, categoryId);
            ps.setInt(6, statusId);
            ps.setInt(7, foodId);

            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateFoodQuantity(int foodId, int quantity) throws SQLException, NamingException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tbl_Food SET Quantity = ? WHERE FoodId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, foodId);

            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<Tbl_FoodDTO> getFoodsBuyTogether(int foodId) throws SQLException, NamingException {
        List<Tbl_FoodDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT FoodId, Name, Image, Description, Price, Quantity, CreateTime, CategoryId, StatusId FROM tbl_Food\n"
                    + "	WHERE FoodId IN (SELECT TOP 2 FoodId FROM tbl_InvoiceDetail WHERE InvoiceId IN (SELECT InvoiceId FROM tbl_InvoiceDetail WHERE FoodId = ?)\n"
                    + "	AND FoodId != ?\n"
                    + "	GROUP BY FoodId\n"
                    + "	ORDER BY COUNT(FoodId) DESC) AND Quantity > 0 AND StatusId = 1";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, foodId);
            ps.setInt(2, foodId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("FoodId");
                String name = rs.getString("Name");
                String image = rs.getString("Image");
                String description = rs.getString("Description");
                int price = rs.getInt("Price");
                int quantity = rs.getInt("Quantity");
                String createTime = rs.getString("CreateTime");
                int categoryId = rs.getInt("CategoryId");
                int statusId = rs.getInt("StatusId");

                list.add(new Tbl_FoodDTO(id, name, image, description, price, quantity, createTime, categoryId, statusId));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<Tbl_FoodDTO> getFoodsUserLike(String email) throws SQLException, NamingException {
        List<Tbl_FoodDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT FoodId, Name, Image, Description, Price, Quantity, CreateTime, CategoryId, StatusId FROM tbl_Food\n"
                    + "	WHERE FoodId IN (SELECT TOP 5 FoodId FROM tbl_InvoiceDetail WHERE InvoiceId IN (SELECT InvoiceId FROM tbl_Invoice WHERE Email = ?)\n"
                    + "	GROUP BY FoodId\n"
                    + "	ORDER BY COUNT(FoodId) DESC) AND Quantity > 0 AND StatusId = 1";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("FoodId");
                String name = rs.getString("Name");
                String image = rs.getString("Image");
                String description = rs.getString("Description");
                int price = rs.getInt("Price");
                int quantity = rs.getInt("Quantity");
                String createTime = rs.getString("CreateTime");
                int categoryId = rs.getInt("CategoryId");
                int statusId = rs.getInt("StatusId");

                list.add(new Tbl_FoodDTO(id, name, image, description, price, quantity, createTime, categoryId, statusId));
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
