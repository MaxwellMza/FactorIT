package com.carritoFactor.services;

import com.carritoFactor.entities.Product;
import com.carritoFactor.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product){
        this.productRepository.save(product);

    }

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    //productos relacionados segun la categoria
    public List<Product> getRelatedProducts(String category, String productID){
        List<Product> productList = this.productRepository.findByCategoryAndIdNot(category,productID);
        List<Product> randomProducts = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < 2; i++){
            int randomIndex = random.nextInt(productList.size());
            randomProducts.add(productList.get(randomIndex));
            productList.remove(randomIndex);
        }
        return randomProducts;
    }
    public Optional<Product> getProductById(String id){
        return this.productRepository.findById(id);
    }

    public void saveProduct(Product product){
        this.productRepository.save(product);
    }
}
