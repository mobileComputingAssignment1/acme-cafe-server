package org.mobcom.server.persistence;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(
                name = "org.mobcom.server.persistence.UserEntity.findAll",
                query = "SELECT o FROM UserEntity o")
})
public class UserEntity extends BaseEntity {
    public static final String findAll = "org.mobcom.server.persistence.UserEntity.findAll";

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "nif")
    int NIF;

    @Column(name = "credit_card")
    String creditCard;

    @Column(name = "user_name")
    String userName;

    @Column(name = "password")
    String password;

    @Column(name = "rsa_key")
    String RSAKey;

    public static String getFindAll() {
        return findAll;
    }

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
}