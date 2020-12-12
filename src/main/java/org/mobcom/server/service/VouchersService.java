package org.mobcom.server.service;

import org.mobcom.server.lib.UserVoucher;
import org.mobcom.server.persistence.UserEntity;
import org.mobcom.server.persistence.UserVoucherEntity;
import org.mobcom.server.service.mappers.UserVoucherMapper;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

public class VouchersService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager em = emf.createEntityManager();

    public UserVoucher getVoucher(String userVoucherId){
        UserVoucherEntity voucherEntity = em.find(UserVoucherEntity.class, userVoucherId);
        return UserVoucherMapper.fromUserVoucherEntity(voucherEntity);
    }

    public List<UserVoucher> getAllVouchers() {
        TypedQuery<UserVoucherEntity> query = em.createNamedQuery(UserVoucherEntity.FIND_ALL, UserVoucherEntity.class);

        return query.getResultStream()
                .map(UserVoucherMapper::fromUserVoucherEntity)
                .collect(Collectors.toList());
    }

    public List<UserVoucher> getAllUserVouchers(String userId) {
        TypedQuery<UserVoucherEntity> query = em.createNamedQuery(UserVoucherEntity.FIND_BY_USER_ID, UserVoucherEntity.class);
        UserEntity userEntity = em.find(UserEntity.class, userId);
        query.setParameter("user", userEntity);

        return query.getResultStream()
                .map(UserVoucherMapper::fromUserVoucherEntity)
                .collect(Collectors.toList());
    }



}
