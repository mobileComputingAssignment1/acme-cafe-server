package org.mobcom.server.persistence;

import javax.persistence.*;

@Entity
@Table(name = "menu")
@NamedQueries({
        @NamedQuery(
                name = "MenuItemEntity.findAll",
                query = "SELECT o FROM MenuItemEntity o")
})
public class MenuItemEntity extends BaseEntity {
    public static final String FIND_ALL = "MenuItemEntity.findAll";

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
