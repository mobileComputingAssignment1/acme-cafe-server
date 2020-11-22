package org.mobcom.server.persistence;

import javax.persistence.*;

@Entity
@Table(name = "user_vouchers")
@NamedQueries({
        @NamedQuery(
                name = "UserVoucherEntity.findAll",
                query = "SELECT o FROM UserVoucherEntity o"),
        @NamedQuery(
                name = "UserVoucherEntity.findByUserIdVoucherId",
                query = "SELECT o FROM UserVoucherEntity o WHERE o.user = :user AND o.voucherId = :voucherId AND o.status = 'valid'")
})
public class UserVoucherEntity extends BaseEntity{
    public static final String FIND_ALL = "UserVoucherEntity.findAll";
    public static final String FIND_BY_USER_ID_VOUCHER_ID = "UserVoucherEntity.findByUserIdVoucherId";

    @Column(name = "voucher_id")
    private String voucherId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "status")
    private String status;

    @Column(name = "name")
    private String name;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
