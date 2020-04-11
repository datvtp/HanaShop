package datvtp.dtos;

import java.io.Serializable;

public class Tbl_InvoiceDetailDTO implements Serializable {

    int invoiceDetailId;
    int foodId;
    int quantity;
    int totalPrice;
    int invoiceId;
    String foodName;
    String email;
    int price;

    public Tbl_InvoiceDetailDTO() {
    }

    public Tbl_InvoiceDetailDTO(int invoiceDetailId, int foodId, int quantity, int totalPrice, int invoiceId) {
        this.invoiceDetailId = invoiceDetailId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.invoiceId = invoiceId;
    }

    public Tbl_InvoiceDetailDTO(int invoiceDetailId, int foodId, int quantity, int totalPrice, int invoiceId, String foodName) {
        this.invoiceDetailId = invoiceDetailId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.invoiceId = invoiceId;
        this.foodName = foodName;
    }

    public int getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setInvoiceDetailId(int invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
