package org.mobcom.server.lib;

import java.util.List;

public class Order extends BaseType{
    private String userId;
    private List<OrderMenuItem> menuItems;
    private String voucherId;
    private String orderId;
    private double totalPrice;
    private String receiptId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrderMenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<OrderMenuItem> menuItems) {
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
