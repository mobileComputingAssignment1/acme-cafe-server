package org.mobcom.server.persistence;

import javax.persistence.*;

@Entity
@Table(name = "user_vouchers")
@NamedQueries({
        @NamedQuery(
                name = "UserVoucherEntity.findAll",
                query = "SELECT o FROM UserVoucherEntity o")
})
public class UserVoucherEntity extends BaseEntity{
    public static final String FIND_ALL = "UserVoucherEntity.findAll";

    @Column(name = "voucher_id")
    private String voucherId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
