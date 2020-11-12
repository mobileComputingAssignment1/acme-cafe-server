package org.mobcom.server.service.mappers;

import org.mobcom.server.lib.User;
import org.mobcom.server.lib.UserVoucher;
import org.mobcom.server.persistence.UserEntity;
import org.mobcom.server.persistence.UserVoucherEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserMapper {

    public static User fromUserEntity(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setTimestamp(entity.getTimestamp());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setNIF(entity.getNIF());
        user.setCreditCard(entity.getCreditCard());
        user.setUserName(entity.getUserName());
        user.setPassword(entity.getPassword());
        user.setRSAKey(entity.getRSAKey());
        user.setActiveCoffees(entity.getActiveCoffees());

        if (entity.getVouchers() != null) {
            user.setVouchers(entity.getVouchers()
                    .stream()
                    .map(UserMapper::fromEntity)
                    .collect(Collectors.toList()));
        } else {
            user.setVouchers(new ArrayList<>());
        }

        return user;
    }

    public static UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setTimestamp(user.getTimestamp());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setNIF(user.getNIF());
        entity.setCreditCard(user.getCreditCard());
        entity.setUserName(user.getUserName());
        entity.setPassword(user.getPassword());
        entity.setRSAKey(user.getRSAKey());

        return entity;
    }

    public static UserVoucher fromEntity(UserVoucherEntity entity) {
        UserVoucher userVoucher = new UserVoucher();
        userVoucher.setId(entity.getId());
        userVoucher.setTimestamp(entity.getTimestamp());
        userVoucher.setVoucherId(entity.getVoucherId());

        return userVoucher;
    }
}
