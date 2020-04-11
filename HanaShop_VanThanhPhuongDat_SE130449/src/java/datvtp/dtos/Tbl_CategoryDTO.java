package datvtp.dtos;

import java.io.Serializable;

public class Tbl_CategoryDTO implements Serializable {

    private int categoryId;
    private String categoryName;

    public Tbl_CategoryDTO() {
    }

    public Tbl_CategoryDTO(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
