package com.carritoFactor.controllers;

import com.carritoFactor.entities.Sale;
import com.carritoFactor.services.SaleService;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService){
        this.saleService = saleService;
    }

    @GetMapping("/client")
    public ResponseEntity<List<Sale>> getByClient(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userName = userDetails.getUsername();
        return new ResponseEntity<>(this.saleService.getSalesByClient(userName), HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<Message> createSale(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userName = userDetails.getUsername();
        this.saleService.createSale(userName);
        return new ResponseEntity<>(new Message("Compra generada") {
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
        }, HttpStatus.OK)
    }
}
