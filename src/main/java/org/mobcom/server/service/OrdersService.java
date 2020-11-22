package org.mobcom.server.service;

import org.mobcom.server.lib.*;
import org.mobcom.server.persistence.OrderEntity;
import org.mobcom.server.persistence.OrderMenuItemEntity;
import org.mobcom.server.service.mappers.OrderMapper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrdersService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager em = emf.createEntityManager();

    private UsersService usersService = new UsersService();
    private VouchersService vouchersService = new VouchersService();
    private MenuService menuService = new MenuService();

    public Order verifyOrder(Order order){
        User user = null;
        Voucher voucher = null;
        UserVoucher newUserVoucher = new UserVoucher();
        MenuItem menuItem = null;
        double discount = 0.0;
        double totalPrice = 0.0;
        OrderEntity newOrderEntity = null;
        String orderId = UUID.randomUUID().toString();


        // check if user exists
        try{
            user = usersService.getUser(order.getUserId());
            order.setUserId(user.getId());
        } catch (NoResultException e){
            return null;
        }

        // create a new order and persist it
        newOrderEntity = new OrderEntity();
        newOrderEntity.setId(orderId);
        newOrderEntity.setMenuItems(new ArrayList<>());
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(newOrderEntity);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

        // check if there is a voucher and a discount
        try{
            voucher = vouchersService.getVoucher(order.getVoucherId());
            if (Byte.compare(voucher.getType(), (byte) 0) == 0) {
                if (user.getActiveCoffees() > 2) {
                    user.setActiveCoffees(user.getActiveCoffees() - 3);
                }
            } else {
                discount = 0.05;
            }
        } catch (NoResultException e){
            // user has no voucher
        }

        for (OrderMenuItem item: order.getMenuItems()) {
            try{
                // get the MenuItem and add it to OrderMenuItem and persist it
                menuItem = menuService.getMenuItem(item.getMenuItemId());
                OrderMenuItemEntity orderMenuItemEntity = new OrderMenuItemEntity();
                orderMenuItemEntity.setQuantity(item.getQuantity());
                orderMenuItemEntity.setTimestamp(item.getTimestamp());
                orderMenuItemEntity.setOrder(newOrderEntity);
                orderMenuItemEntity.setMenuItemId(item.getMenuItemId());
                orderMenuItemEntity.setId(UUID.randomUUID().toString());

                try {
                    EntityTransaction tx = em.getTransaction();
                    tx.begin();
                    em.persist(orderMenuItemEntity);
                    tx.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    em.getTransaction().rollback();
                }

            } catch (NoResultException e){
                return null;
            }
            totalPrice += item.getQuantity() * menuItem.getPrice();
        }

        totalPrice = totalPrice - totalPrice * discount;

        //
        newOrderEntity = OrderMapper.toEntity(order);
        newOrderEntity.setReceiptId("functionality not yet implemented");
        newOrderEntity.setTotalPrice(totalPrice);
        newOrderEntity.setId(orderId);

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(newOrderEntity);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

        return OrderMapper.fromEntity(newOrderEntity);
    }


    public Order getOrder(String orderId){
        OrderEntity orderEntity = em.find(OrderEntity.class, orderId);
        return OrderMapper.fromEntity(orderEntity);
    }

    public List<UserVoucher> makeOrder(String orderId) {
        return null;
    }
}
