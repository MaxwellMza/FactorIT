package com.carritoFactor.controllers;


import com.carritoFactor.entities.ShoppingCart;
import com.carritoFactor.services.ShoppingCartService;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingList")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping() // obtenemos directamente el user detal del usuario autenticado
    public ResponseEntity<List<ShoppingCart>> getListByClient(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userName = userDetails.getUsername();
        return new ResponseEntity<>(this.shoppingCartService.getListByClient(userName), HttpStatus.OK);
    }

    @GetMapping("/count/{client_dni}")
    public ResponseEntity<Long> countByClient(@PathVariable("client_dni")int client_dni){
        return new ResponseEntity<>(this.shoppingCartService.getCountByClient(client_dni),HttpStatus.OK);

    }

    //Añade productos al carrito
    @PostMapping()
    public ResponseEntity<Message> addProduct(@Validated @RequestBody ShoppingCart shoppingCart,
                                              BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("revise los campos") {
                @Override
                public String getFormattedMessage() {
                    return null;
                }

                @Override
                public String getFormat() {
                    return null;
                }

                @Override
                public Object[] getParameters() {
                    return new Object[0];
                }

                @Override
                public Throwable getThrowable() {
                    return null;
                }
            },HttpStatus.BAD_REQUEST);
        this.shoppingCartService.addProduct(shoppingCart);
        return new ResponseEntity<>(new Message("producto agregado") {
            @Override
            public String getFormattedMessage() {
                return null;
            }

            @Override
            public String getFormat() {
                return null;
            }

            @Override
            public Object[] getParameters() {
                return new Object[0];
            }

            @Override
            public Throwable getThrowable() {
                return null;
            }
        },HttpStatus.OK);
    }

    //vacia el carrito
    @DeleteMapping("/clean/{item_id}")
    public ResponseEntity<Message> removeProduct(@PathVariable("item_id")String product_id){
        this.shoppingCartService.removeProduct(product_id);
        return new ResponseEntity<>(new Message("eliminado") {
            @Override
            public String getFormattedMessage() {
                return null;
            }

            @Override
            public String getFormat() {
                return null;
            }

            @Override
            public Object[] getParameters() {
                return new Object[0];
            }

            @Override
            public Throwable getThrowable() {
                return null;
            }
        },HttpStatus.OK)
    }
}
