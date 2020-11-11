package org.mobcom.server.service;

import org.mobcom.server.lib.Voucher;
import org.mobcom.server.persistence.VoucherEntity;
import org.mobcom.server.service.mappers.VoucherMapper;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

public class VouchersService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager em = emf.createEntityManager();

    public Voucher getVoucher(String voucherId){
        VoucherEntity voucherEntity = em.find(VoucherEntity.class, voucherId);
        return VoucherMapper.fromVoucherEntity(voucherEntity);
    }

    public List<Voucher> getAllVouchers() {
        TypedQuery<VoucherEntity> query = em.createNamedQuery(VoucherEntity.FIND_ALL, VoucherEntity.class);

        return query.getResultStream()
                .map(VoucherMapper::fromVoucherEntity)
                .collect(Collectors.toList());
    }


}
