package org.mobcom.server.persistence;

import javax.persistence.*;

@Entity
@Table(name = "user_vouchers")
@NamedQueries({
        @NamedQuery(
                name = "UserVoucherEntity.findAll",
                query = "SELECT o FROM UserVoucherEntity o"),
        @NamedQuery(
                name = "UserVoucherEntity.findByUserId",
                query = "SELECT o FROM UserVoucherEntity o WHERE o.user = :user AND o.status = 'valid'")
})
public class UserVoucherEntity extends BaseEntity{
    public static final String FIND_ALL = "UserVoucherEntity.findAll";
    public static final String FIND_BY_USER_ID = "UserVoucherEntity.findByUserId";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "status")
    private String status;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private byte type;

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

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
}
