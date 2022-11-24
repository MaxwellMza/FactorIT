package com.carritoFactor.repositories;

import com.carritoFactor.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product,String>{

    List<Product> findByCategoryAndIdNot(String category, String ProductID);

}
