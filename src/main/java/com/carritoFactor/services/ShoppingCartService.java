package com.carritoFactor.services;

import com.carritoFactor.entities.ShoppingCart;
import com.carritoFactor.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public List<ShoppingCart> getListByClient(int client_dni) {
       return this.shoppingCartRepository.findByClient_dni(client_dni);
    }

    //Vacia el carrito cuando se complete la compra
    public void cleanShoppingCart(int client_dni){
        this.shoppingCartRepository.deleteByClient_dni(client_dni);
    }
    //Añade cada item
    public void addProduct(ShoppingCart shoppingCart){
        this.shoppingCartRepository.save(shoppingCart);
    }
    public Long getCountByClient(int client_dni){
        return this.shoppingCartRepository.countByClient_dni(client_dni);
    }
    public void removeProduct(String id){
        this.shoppingCartRepository.deleteById(id);
    }
}
