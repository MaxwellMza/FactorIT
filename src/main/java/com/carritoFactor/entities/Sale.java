package com.carritoFactor.entities;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Getter @Setter
    private String id;

    @NotNull
    @Getter @Setter
    private Double total;

    @NonNull
    @Getter @Setter
    @Column(columnDefinition = "Date")
    private Date date;
    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @Getter @Setter
    private User client;

    public Sale(double total, Date date, User client) {
        this.total = total;
        this.date = date;
        this.client = client;
    }

}
