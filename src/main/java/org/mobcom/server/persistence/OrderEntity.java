package org.mobcom.server.persistence;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Column(name = "user_id")
    private String userId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderMenuItemEntity> menuItems;

    @Column(name = "voucher_id")
    private String voucherId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "receipt_id")
    private String receiptId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrderMenuItemEntity> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<OrderMenuItemEntity> menuItems) {
        this.menuItems = menuItems;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }
}