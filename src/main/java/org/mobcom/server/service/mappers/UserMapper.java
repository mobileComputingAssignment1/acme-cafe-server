package org.mobcom.server.service.mappers;

import org.mobcom.server.lib.User;
import org.mobcom.server.persistence.UserEntity;

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
}
