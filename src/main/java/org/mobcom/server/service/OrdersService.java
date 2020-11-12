package org.mobcom.server.service;

import org.mobcom.server.lib.Order;
import org.mobcom.server.lib.OrderMenuItem;
import org.mobcom.server.lib.User;
import org.mobcom.server.lib.Voucher;
import org.mobcom.server.persistence.OrderEntity;
import org.mobcom.server.persistence.UserEntity;
import org.mobcom.server.service.mappers.OrderMapper;
import org.mobcom.server.service.mappers.UserMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OrdersService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager em = emf.createEntityManager();

    private UsersService usersService = new UsersService();
    private VouchersService vouchersService = new VouchersService();

    public Order verifyOrder(Order order){
        User user = usersService.getUser(order.getUserId());
        Voucher voucher = vouchersService.getVoucher(order.getVoucherId());
        if (user == null){
            return null;
        }

        return null;
    }


    public Order getOrder(String orderId){
        OrderEntity orderEntity = em.find(OrderEntity.class, orderId);
        return OrderMapper.fromEntity(orderEntity);
    }
}
