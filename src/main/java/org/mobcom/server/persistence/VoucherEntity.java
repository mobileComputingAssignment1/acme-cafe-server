package org.mobcom.server.persistence;

import javax.persistence.*;


@Entity
@Table(name = "vouchers")
@NamedQueries({
        @NamedQuery(
                name = "VoucherEntity.findAll",
                query = "SELECT o FROM VoucherEntity o")
})
public class VoucherEntity extends BaseEntity{
    public static final String FIND_ALL = "VoucherEntity.findAll";

    @Column(name = "type")
    private byte type;

    @Column(name = "name")
    private String name;

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
