package org.mobcom.server.persistence;

import javax.persistence.*;


@Entity
@Table(name = "order_menu_items")
@NamedQueries(value = {
        @NamedQuery(
                name = "OrderMenuItemEntity.findByOrderId",
                query = "SELECT o FROM OrderMenuItemEntity o WHERE o.order.id = :orderId")
})
public class OrderMenuItemEntity extends BaseEntity {
    public static final String FIND_BY_ORDER_ID = "OrderMenuEntity.findByOrderId";

    @Column(name = "menu_item_id")
    private String menuItemId;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
