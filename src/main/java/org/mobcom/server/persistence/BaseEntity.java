package org.mobcom.server.persistence;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type="pg-uuid")
    @Id
    protected String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    protected Date timestamp;

    @PrePersist
    private void onCreate() {
        this.timestamp = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}