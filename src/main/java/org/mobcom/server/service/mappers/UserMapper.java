package org.mobcom.server.service.mappers;

import org.mobcom.server.lib.User;
import org.mobcom.server.lib.UserVoucher;
import org.mobcom.server.persistence.OrderMenuItemEntity;
import org.mobcom.server.persistence.UserEntity;
import org.mobcom.server.persistence.UserVoucherEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserMapper {

    public static User fromUserEntity(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setTimestamp(entity.getTimestamp());
        user.setFullName(entity.getFullName());
        user.setNIF(entity.getNIF());
        user.setCreditCard(entity.getCreditCard());
        user.setUserName(entity.getUserName());
        user.setPassword(entity.getPassword());
        user.setRSAKey(entity.getRSAKey());
        user.setActiveCoffees(entity.getActiveCoffees());

        if (entity.getVouchers() != null) {
            user.setVouchers(entity.getVouchers()
                    .stream()
                    .map(UserMapper::fromUserVoucherEntity)
                    .collect(Collectors.toList()));
        } else {
            user.setVouchers(new ArrayList<>());
        }

//        for (UserVoucher userVoucher : user.getVouchers()){
//            userVoucher.setUser(user);
//        }

        return user;
    }

    public static UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setTimestamp(user.getTimestamp());
        entity.setFullName(user.getFullName());
        entity.setNIF(user.getNIF());
        entity.setCreditCard(user.getCreditCard());
        entity.setUserName(user.getUserName());
        entity.setPassword(user.getPassword());
        entity.setRSAKey(user.getRSAKey());
        entity.setActiveCoffees(user.getActiveCoffees());

        if (user.getVouchers() != null) {
            entity.setVouchers(user.getVouchers()
                    .stream()
                    .map(UserMapper::toUserVoucherEntity)
                    .collect(Collectors.toList()));
        } else {
            user.setVouchers(new ArrayList<>());
        }

        for (UserVoucherEntity userVoucherEntity : entity.getVouchers()){
            userVoucherEntity.setUser(entity);
        }

        return entity;
    }

    public static UserVoucher fromUserVoucherEntity(UserVoucherEntity entity) {
        UserVoucher userVoucher = new UserVoucher();
        userVoucher.setId(entity.getId());
        userVoucher.setTimestamp(entity.getTimestamp());
        userVoucher.setVoucherId(entity.getVoucherId());
        userVoucher.setStatus(entity.getStatus());

        return userVoucher;
    }

    public static UserVoucherEntity toUserVoucherEntity(UserVoucher userVoucher){
        UserVoucherEntity entity = new UserVoucherEntity();
        entity.setId(userVoucher.getId());
        entity.setStatus(userVoucher.getStatus());
        entity.setVoucherId(entity.getVoucherId());
        entity.setTimestamp(entity.getTimestamp());

        return entity;
    }
}
