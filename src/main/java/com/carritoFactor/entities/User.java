package com.carritoFactor.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Getter
    @Setter
    private String id;

    @NotNull
    @Getter @Setter
    private String name;

    @NotNull
    @Getter @Setter
    private int dni;
}
