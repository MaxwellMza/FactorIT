package com.carritoFactor.entities;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Getter @Setter
    private String id;

    @NotNull
    @Getter @Setter
    private String name;

    @NotNull
    @Getter @Setter
    private Double price;

    @NotNull
    @Getter @Setter
    private String category;
}
