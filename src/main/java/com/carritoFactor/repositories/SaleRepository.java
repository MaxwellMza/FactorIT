package com.carritoFactor.repositories;

import com.carritoFactor.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale,String> {

    List<Sale> FindByClient_dni(String clientDni);
}
