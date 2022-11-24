package com.carritoFactor.repositories;

import com.carritoFactor.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    List<ShoppingCart> findByClient_dni(int dni);
    void deleteById(String id);
    void deleteByClient_dni(int dni); //borra items de un carrito de un cliente
    Long countByClient_dni(int dni); //ara saber cuantos items tiene en un carrito un cliente

}
