package org.mobcom.server.service.mappers;

import org.mobcom.server.lib.UserVoucher;
import org.mobcom.server.persistence.UserVoucherEntity;

public class UserVoucherMapper {

    public static UserVoucher fromUserVoucherEntity(UserVoucherEntity entity) {
        UserVoucher voucher = new UserVoucher();
        voucher.setId(entity.getId());
        voucher.setTimestamp(entity.getTimestamp());
        voucher.setType(entity.getType());
        voucher.setName(entity.getName());
        voucher.setStatus(entity.getStatus());
//            if(entity.getUser() != null){
//                voucher.setUser(UserMapper.fromUserEntity(entity.getUser()));
//            }


        return voucher;
    }

    public static UserVoucherEntity toUserVoucherEntity(UserVoucher voucher) {
        UserVoucherEntity entity = new UserVoucherEntity();

        entity.setId(voucher.getId());
        entity.setTimestamp(voucher.getTimestamp());
        entity.setType(voucher.getType());
        entity.setName(voucher.getName());
        entity.setStatus(voucher.getStatus());
//            if(voucher.getUser() != null){
//                entity.setUser(UserMapper.toUserEntity(voucher.getUser()));
//            }


        return entity;
    }
}
