package org.mobcom.server.lib;

import org.mobcom.server.persistence.BaseEntity;
import org.mobcom.server.persistence.UserVoucherEntity;
import java.util.List;

public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private int NIF;
    private String creditCard;
    private String userName;
    private String password;
    private String RSAKey;
    private int activeCoffees;
    private List<UserVoucher> vouchers;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public List<UserVoucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<UserVoucher> vouchers) {
        this.vouchers = vouchers;
    }
}
