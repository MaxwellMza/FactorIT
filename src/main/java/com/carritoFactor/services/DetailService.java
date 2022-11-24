package com.carritoFactor.services;

import com.carritoFactor.entities.Detail;
import com.carritoFactor.repositories.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DetailService {

    private final DetailRepository detailRepository;

    @Autowired
    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    public void createDetail(Detail detail){
        this.detailRepository.save(detail);

    }
    //Trae detalles correspondientes a una compra
    public List<Detail> getDetailBySale(String saleId){
        return this.detailRepository.findBySale_id(saleId);

    }
}
