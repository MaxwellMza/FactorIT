package com.carritoFactor.services;

import com.carritoFactor.entities.Detail;
import com.carritoFactor.entities.Sale;
import com.carritoFactor.entities.ShoppingCart;
import com.carritoFactor.entities.User;
import com.carritoFactor.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class SaleService {

    private final SaleRepository saleRepository;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final DetailService detailService;

    @Autowired
    public SaleService(SaleRepository saleRepository, UserService userService, ShoppingCartService shoppingCartService, DetailService detailService ) {
        this.saleRepository = saleRepository;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.detailService = detailService;
    }

    public List<Sale> getSalesByClient(String client_dni) {return this.saleRepository.FindByClient_dni(client_dni);}
    //Genera ventas de acuerdo a los productos que tenga cada cliente en su carrito
    public void createSale(int client_dni){
        User client = this.userService.existByUserDni(int dni).get();
        List<ShoppingCart> shoppingCartList = this.shoppingCartService.getListByClient(client.getDni());
        DecimalFormat decimalFormat = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        double total = shoppingCartList.stream().mapToDouble(shoppingCartItem -> shoppingCartItem.getProduct().getPrice()
        *shoppingCartItem.getAmount()).sum();
        Sale sale = new Sale(Double.parseDouble(decimalFormat.format(total)), new Date(), client);
        Sale saveSale = this.saleRepository.save(sale); //guarda el resultado cuando ya se guardo en la base de datos
          //creamos los detalles
        for (int i=0; i< shoppingCartList.size();i++){
             Detail detail = new Detail();
             detail.setProduct(shoppingCartList.get(i).getProduct());
             detail.setAmount(shoppingCartList.get(i).getAmount());
             detail.setSale(saveSale);
             this.detailService.createDetail(detail);
        }
        this.shoppingCartService.cleanShoppingCart(client.getDni());
    }
}
