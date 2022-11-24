package com.carritoFactor.entities;


import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.JOINED) //relaciona las tablas a partir de algo igual
public class ShoppingCart {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Getter @Setter
    private String id;

    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @Getter @Setter
    private Product product;

    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @Getter @Setter
    private User client;

    @NotNull
    @Getter @Setter
    private int amount;

}
