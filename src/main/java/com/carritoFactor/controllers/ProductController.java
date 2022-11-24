package com.carritoFactor.controllers;


import com.carritoFactor.entities.Product;
import com.carritoFactor.services.ProductService;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<Object> getProductById(@PathVariable("product_id") String productId) {
        Optional<Product> productOptional = this.productService.getProductById(productId);
        if (productOptional.isEmpty()) {
            return new ResponseEntity<>(new Message("No encntrado") {
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
            }, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/related/{category}/{product_id}")
    public ResponseEntity<Object> getRelatedProduct(@PathVariable("category") String category, @PathVariable("product_id") String productId) {
        return new ResponseEntity<>(this.productService.getRelatedProducts(category, productId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Message> createProduct(@Validated @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Message("Revise los campos") {
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
            }, HttpStatus.BAD_REQUEST);
        }
        this.productService.saveProduct(product);
        return new ResponseEntity<>(new Message() {
            @Override
            public String getFormattedMessage("Creado correctamente") {
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
        }, HttpStatus.OK);
    }

}
