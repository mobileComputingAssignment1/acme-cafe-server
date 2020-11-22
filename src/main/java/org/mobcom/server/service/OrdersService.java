package org.mobcom.server.service;

import org.mobcom.server.lib.*;
import org.mobcom.server.persistence.OrderEntity;
import org.mobcom.server.persistence.OrderMenuItemEntity;
import org.mobcom.server.persistence.UserEntity;
import org.mobcom.server.persistence.UserVoucherEntity;
import org.mobcom.server.service.mappers.OrderMapper;
import org.mobcom.server.service.mappers.UserMapper;

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

            // make the voucher invalid
            TypedQuery<UserVoucherEntity> query = em.createNamedQuery(UserVoucherEntity.FIND_BY_USER_ID_VOUCHER_ID, UserVoucherEntity.class)
                    .setParameter("user", UserMapper.toUserEntity(user))
                    .setParameter("voucherId", voucher.getId());

            UserVoucherEntity userVoucherEntity =  query.getResultList().get(0);
            userVoucherEntity.setStatus("invalid");

            try {
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(userVoucherEntity);
                tx.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                em.getTransaction().rollback();
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

                // if user bought a coffee we need to save it to his enttity
                if (menuItem.getId().equals("2ccff0d5-e126-4eaf-a199-9ad96bcfe808")) {
                    try {
                        user.setActiveCoffees(user.getActiveCoffees() + item.getQuantity());
                        UserEntity userEntity = UserMapper.toUserEntity(user);
                        EntityTransaction tx = em.getTransaction();
                        tx.begin();
                        em.merge(userEntity);
                        tx.commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                        em.getTransaction().rollback();
                    }
                }

            } catch (NoResultException e){
                return null;
            }
            totalPrice += item.getQuantity() * menuItem.getPrice();
        }

        totalPrice = totalPrice - totalPrice * discount;

        // add the money to the user account
        user.setTotalMoneySpent(user.getTotalMoneySpent() + totalPrice);
        try {
            UserEntity userEntity = UserMapper.toUserEntity(user);
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(userEntity);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }


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

        UserVoucherEntity newVoucher = new UserVoucherEntity();
        // add a new coffee voucher to the client
        if (user.getActiveCoffees() > 2) {
            newVoucher.setName("free coffee");
            newVoucher.setStatus("valid");
            newVoucher.setUser(UserMapper.toUserEntity(user));
            newVoucher.setId(UUID.randomUUID().toString());
            newVoucher.setVoucherId("4ccf48df-82c2-4af0-b2c7-f99ad743c459");
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
        if (user.getTotalMoneySpent() > 100.0){
            user.setTotalMoneySpent(user.getTotalMoneySpent() - 100.0);
            newVoucher.setName("5% discount");
            newVoucher.setStatus("valid");
            newVoucher.setUser(UserMapper.toUserEntity(user));
            newVoucher.setId(UUID.randomUUID().toString());
            newVoucher.setVoucherId("d3d75981-2e2d-4e80-a616-0db48567b2b7");
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
