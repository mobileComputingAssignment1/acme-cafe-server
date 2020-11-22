package org.mobcom.server.persistence;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(
                name = "UserEntity.findAll",
                query = "SELECT o FROM UserEntity o"),
        @NamedQuery(
                name = "UserEntity.findByUserName",
                query = "SELECT o FROM UserEntity o WHERE o.userName = :userName")
})
public class UserEntity extends BaseEntity {
    public static final String FIND_ALL = "UserEntity.findAll";
    public static final String FIND_BY_USER_NAME = "UserEntity.findByUserName";

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "nif")
    private int NIF;

    @Column(name = "credit_card")
    private String creditCard;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "rsa_key", length=1024)
    private String RSAKey;

    @Column(name = "active_coffees")
    private int activeCoffees;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserVoucherEntity> vouchers;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getNIF() {
        return NIF;
    }

    public void setNIF(int NIF) {
        this.NIF = NIF;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRSAKey() {
        return RSAKey;
    }

    public void setRSAKey(String RSAKey) {
        this.RSAKey = RSAKey;
    }

    public int getActiveCoffees() {
        return activeCoffees;
    }

    public void setActiveCoffees(int activeCoffees) {
        this.activeCoffees = activeCoffees;
    }

    public List<UserVoucherEntity> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<UserVoucherEntity> vouchers) {
        this.vouchers = vouchers;
    }
}
