package org.mobcom.server.service.mappers;

import org.mobcom.server.lib.Order;
import org.mobcom.server.lib.OrderMenuItem;
import org.mobcom.server.persistence.OrderEntity;
import org.mobcom.server.persistence.OrderMenuItemEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class OrderMapper {
    public static Order fromEntity(OrderEntity entity) {
        Order order = new Order();
        order.setId(entity.getId());
        order.setTimestamp(entity.getTimestamp());

        order.setUserId(entity.getUserId());
        order.setOrderId(entity.getOrderId());
        order.setVoucherId(entity.getVoucherId());
        order.setTotalPrice(entity.getTotalPrice());
        order.setReceiptId(entity.getReceiptId());


        if (entity.getMenuItems() != null) {
            order.setMenuItems(entity.getMenuItems()
                    .stream()
                    .map(OrderMapper::fromEntity)
                    .collect(Collectors.toList()));
        } else {
            order.setMenuItems(new ArrayList<>());
        }

        return order;
    }

    public static OrderEntity toEntity(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setTimestamp(order.getTimestamp());
        entity.setUserId(order.getUserId());
        entity.setTotalPrice(order.getTotalPrice());
        entity.setOrderId(order.getOrderId());

        return entity;
    }

    public static OrderMenuItem fromEntity(OrderMenuItemEntity entity) {
        OrderMenuItem product = new OrderMenuItem();
        product.setId(entity.getId());
        product.setTimestamp(entity.getTimestamp());
        product.setMenuItemId(entity.getMenuItemId());
        product.setQuantity(entity.getQuantity());
        return product;
    }
}
