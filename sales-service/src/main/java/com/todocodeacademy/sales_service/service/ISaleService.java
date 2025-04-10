package com.todocodeacademy.sales_service.service;

import com.todocodeacademy.sales_service.dto.CartDetailsDTO;
import com.todocodeacademy.sales_service.model.Sale;

import java.util.List;

public interface ISaleService {

    public Sale findSaleById(Long id);

    public List<Sale> getSales();

    public CartDetailsDTO getCartDetails(Long id);

    public void saveSale(Sale sale);

    public void deleteSale(Long id);

    public void updateSale(Long id, Sale sale);
}
