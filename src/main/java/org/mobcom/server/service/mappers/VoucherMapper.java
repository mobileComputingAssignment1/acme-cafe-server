package org.mobcom.server.service.mappers;

import org.mobcom.server.lib.Voucher;
import org.mobcom.server.persistence.VoucherEntity;

public class VoucherMapper {

    public static Voucher fromVoucherEntity(VoucherEntity entity) {
        Voucher voucher = new Voucher();
        voucher.setId(entity.getId());
        voucher.setTimestamp(entity.getTimestamp());
        voucher.setType(entity.getType());
        voucher.setName(entity.getName());

        return voucher;
    }

    public static VoucherEntity toVoucherEntity(Voucher voucher) {
        VoucherEntity entity = new VoucherEntity();
        entity.setId(voucher.getId());
        entity.setTimestamp(voucher.getTimestamp());
        entity.setType(voucher.getType());
        entity.setName(voucher.getName());

        return entity;
    }
}
