package org.mobcom.server.service;

import org.mobcom.server.lib.*;
import org.mobcom.server.persistence.OrderEntity;
import org.mobcom.server.persistence.OrderMenuItemEntity;
import org.mobcom.server.persistence.UserEntity;
import org.mobcom.server.persistence.UserVoucherEntity;
import org.mobcom.server.service.mappers.OrderMapper;
import org.mobcom.server.service.mappers.UserMapper;
import org.mobcom.server.service.mappers.UserVoucherMapper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrdersService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager em = emf.createEntityManager();

    private UsersService usersService = new UsersService();
    private VouchersService vouchersService = new VouchersService();
    private MenuService menuService = new MenuService();

    public Order verifyOrder(Order order){
        UserEntity userEntity = null;
        MenuItem menuItem = null;
        double discount = 0.0;
        double totalPrice = 0.0;
        OrderEntity newOrderEntity = null;
        String orderId = UUID.randomUUID().toString();

        // check if user exists
        try{
            userEntity = UserMapper.toUserEntity(usersService.getUser(order.getUserId()));
            order.setUserId(userEntity.getId());
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
        if (order.getVoucherId() != null){
            try{
                UserVoucher voucher = vouchersService.getVoucher(order.getVoucherId());
                UserVoucherEntity userVoucherEntity = UserVoucherMapper.toUserVoucherEntity(voucher);
                if (Byte.compare(userVoucherEntity.getType(), (byte) 0) == 1) {
                    discount = 0.05;
                }
                // coffee voucher
                else {
                    discount = 1;
                }

                // make the used voucher invalid
                userVoucherEntity.setStatus("invalid");
                userVoucherEntity.setUser(userEntity);

                try {
                    EntityTransaction tx = em.getTransaction();
                    tx.begin();
                    em.merge(userVoucherEntity);
                    tx.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    em.getTransaction().rollback();
                }
            } catch (NoResultException e){
                // user has no voucher
            }
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
                orderMenuItemEntity.setMenuItemName(menuItem.getName());

                try {
                    EntityTransaction tx = em.getTransaction();
                    tx.begin();
                    em.persist(orderMenuItemEntity);
                    tx.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    em.getTransaction().rollback();
                }

                // if user bought a coffee we need to save it to his entity
                if (menuItem.getId().equals("2ccff0d5-e126-4eaf-a199-9ad96bcfe808")) {
                    userEntity.setActiveCoffees(userEntity.getActiveCoffees() + item.getQuantity());
                }

            } catch (NoResultException e){
                return null;
            }
            totalPrice += item.getQuantity() * menuItem.getPrice();
        }

        // add the money to the user account
        if (discount == 1){
            totalPrice = totalPrice - 1;
        } else {
            totalPrice = totalPrice - totalPrice * discount;
        }
        userEntity.setTotalMoneySpent(userEntity.getTotalMoneySpent() + totalPrice);

        // add appropriate vouchers
        UserVoucherEntity newVoucher = new UserVoucherEntity();
        // add a new coffee voucher to the client
        if (userEntity.getActiveCoffees() > 2) {
            newVoucher.setName("free coffee");
            newVoucher.setStatus("valid");
            newVoucher.setUser(userEntity);
            newVoucher.setId(UUID.randomUUID().toString());
            newVoucher.setType((byte) 0);
            userEntity.setActiveCoffees(userEntity.getActiveCoffees() - 3);

            try {
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(newVoucher);
                tx.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                em.getTransaction().rollback();
            }
        }
        // add a new coffee voucher to the client
        if (userEntity.getTotalMoneySpent() > 100.0){
            newVoucher.setName("5% discount");
            newVoucher.setStatus("valid");
            newVoucher.setUser(userEntity);
            newVoucher.setId(UUID.randomUUID().toString());
            newVoucher.setType((byte) 1);
            userEntity.setTotalMoneySpent(userEntity.getTotalMoneySpent() - 100.0);
            try {
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(newVoucher);
                tx.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                em.getTransaction().rollback();
            }
        }
//        userEntity = UserMapper.toUserEntity(usersService.getUser(order.getUserId()));
        ArrayList<UserVoucherEntity> voucherEntities = new ArrayList<UserVoucherEntity>();
        List<UserVoucher> vouchers = vouchersService.getAllUserVouchers(order.getUserId());

        // set user's new vouchers
        if (vouchers != null){
            for (UserVoucher vouc : vouchers){
                UserVoucherEntity ent = UserVoucherMapper.toUserVoucherEntity(vouc);
                ent.setUser(userEntity);
                voucherEntities.add(ent);
            }
            userEntity.setVouchers(voucherEntities);
        }


        // persist user (because of differences with activeCoffees and moneySpent)
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(userEntity);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

        // persist the new order
        newOrderEntity = OrderMapper.toEntity(order);
        newOrderEntity.setReceiptId(UUID.randomUUID().toString());
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

    public List<Order> getOrdersFromUser (String userId){
        TypedQuery<OrderEntity> query = em.createNamedQuery(OrderEntity.FIND_ORDERS_FROM_USER, OrderEntity.class);
        query.setParameter("userId", userId);

        return query.getResultStream()
                .map(OrderMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public List<UserVoucher> makeOrder(String orderId) {
        return null;
    }
}
