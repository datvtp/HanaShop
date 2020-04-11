package datvtp.dtos;

import java.io.Serializable;

public class Tbl_FoodDTO implements Serializable {

    private int foodId;
    private String name;
    private String image;
    private String description;
    private int price;
    private int quantity;
    private String createTime;
    private int categoryId;
    private int statusId;

    public Tbl_FoodDTO() {
    }

    public Tbl_FoodDTO(int foodId, String name, String image, String description, int price, int quantity, String createTime, int categoryId, int statusId) {
        this.foodId = foodId;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.createTime = createTime;
        this.categoryId = categoryId;
        this.statusId = statusId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

}
