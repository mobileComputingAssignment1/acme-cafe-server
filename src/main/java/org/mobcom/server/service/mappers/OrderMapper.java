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
        order.setVoucherId(entity.getVoucherId());
        order.setTotalPrice(entity.getTotalPrice());
        order.setReceiptId(entity.getReceiptId());


        if (entity.getMenuItems() != null) {
            order.setMenuItems(entity.getMenuItems()
                    .stream()
                    .map(OrderMapper::menuItemFromEntity)
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
        entity.setReceiptId(order.getReceiptId());
        entity.setVoucherId(order.getVoucherId());

        if (order.getMenuItems() != null) {
            entity.setMenuItems(order.getMenuItems()
                    .stream()
                    .map(OrderMapper::menuItemToEntity)
                    .collect(Collectors.toList()));
        } else {
            entity.setMenuItems(new ArrayList<>());
        }

        for (OrderMenuItemEntity orderMenuItemEntity : entity.getMenuItems()){
            orderMenuItemEntity.setOrder(entity);
        }

        return entity;
    }

    public static OrderMenuItem menuItemFromEntity(OrderMenuItemEntity entity) {
        OrderMenuItem product = new OrderMenuItem();
        product.setId(entity.getId());
        product.setTimestamp(entity.getTimestamp());
        product.setMenuItemId(entity.getMenuItemId());
        product.setQuantity(entity.getQuantity());
        product.setOrderId(entity.getOrder().getId());
        return product;
    }

    public static OrderMenuItemEntity menuItemToEntity(OrderMenuItem orderMenuItem) {
        OrderMenuItemEntity entity = new OrderMenuItemEntity();
        entity.setId(orderMenuItem.getId());
        entity.setTimestamp(orderMenuItem.getTimestamp());
        entity.setMenuItemId(orderMenuItem.getMenuItemId());
        entity.setQuantity(orderMenuItem.getQuantity());
//        entity.setOrder(orderMenuItem.getOrderId());
        return entity;
    }
}
