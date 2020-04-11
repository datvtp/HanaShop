package datvtp.dtos;

import java.io.Serializable;

public class Tbl_InvoiceDTO implements Serializable {

    int invoiceId;
    String createTime;
    int totalPrice;
    String email;

    public Tbl_InvoiceDTO() {
    }

    public Tbl_InvoiceDTO(int invoiceId, String createTime, int totalPrice, String email) {
        this.invoiceId = invoiceId;
        this.createTime = createTime;
        this.totalPrice = totalPrice;
        this.email = email;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
