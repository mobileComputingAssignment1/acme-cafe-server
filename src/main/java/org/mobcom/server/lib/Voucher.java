package org.mobcom.server.lib;

import org.mobcom.server.persistence.BaseEntity;

public class Voucher extends BaseEntity {
    private byte type;
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
