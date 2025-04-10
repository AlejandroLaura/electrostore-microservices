package com.todocodeacademy.sales_service.service;

import com.todocodeacademy.sales_service.dto.CartDetailsDTO;
import com.todocodeacademy.sales_service.model.Sale;
import com.todocodeacademy.sales_service.repository.ICartAPIClient;
import com.todocodeacademy.sales_service.repository.ISaleRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
@Service
public class SaleService implements ISaleService{

    @Autowired
    private ISaleRepository saleRepo;
    @Autowired
    private ICartAPIClient cartAPIClient;

    @Override
    public Sale findSaleById(Long id) {
        return saleRepo.findById(id).orElseThrow(()-> new RuntimeException("Venta no encontrada con la ID:" + id));
    }

    @Override
    public List<Sale> getSales() {
        return saleRepo.findAll();
    }

    @Override
    @CircuitBreaker(name = "carts-service", fallbackMethod = "fallbackGetCartDetails")
    public CartDetailsDTO getCartDetails(Long id) {
        //buscamos la venta
        Sale sale = this.findSaleById(id);
        //consumimos api y traemos para setea el campo date
        CartDetailsDTO cart =  cartAPIClient.getCartDetailsById(sale.getCartId());
        //setamos y retornamos
        cart.setDate(sale.getDate());
        return cart;
    }

    @Override
    @CircuitBreaker(name = "carts-service", fallbackMethod = "fallbackSaveSale")
    public void saveSale(Sale sale) {
        //consumimos la api de cart para completar el campo total
        CartDetailsDTO cart = cartAPIClient.getCartDetailsById(sale.getCartId());
        sale.setTotal(cart.getTotal());
        saleRepo.save(sale);
    }

    @Override
    public void deleteSale(Long id) {
        if(!saleRepo.existsById(id)){
            throw new RuntimeException("No se puede eliminar la venta con el ID: " + id + " no encontrado");
        }
        saleRepo.deleteById(id);
    }

    @Override
    @CircuitBreaker(name = "carts-service", fallbackMethod = "fallbackUpdateSale")
    public void updateSale(Long id, Sale sale) {
        //buscamos la venta con el id
        Sale sale2 = this.findSaleById(id);
        //consultamos a carts-service para obtener el total correcto
        CartDetailsDTO cart = cartAPIClient.getCartDetailsById(sale.getCartId());
        //seteamos los nuevos valores
        sale2.setDate(sale.getDate());
        sale2.setTotal(cart.getTotal());
        sale2.setCartId(sale.getCartId());
        //guardamos los cambios
        saleRepo.save(sale2);
    }

    public CartDetailsDTO fallbackGetCartDetails(Long id, Throwable throwable){
        System.out.println("⚠️ [Fallback] Error al obtener detalles del carrito con ID: " + id + ". Motivo: " + throwable.getMessage());
        return new CartDetailsDTO(id, 0.0, Collections.emptyList(), null);
    }

    public void fallbackSaveSale(Sale sale, Throwable throwable){
        System.out.println("⚠️ [Fallback] Error al guardar la venta. Motivo: " + throwable.getMessage());
        sale.setTotal(0.0);
        saleRepo.save(sale);
    }

    public void fallbackUpdateSale(Long id, Sale sale, Throwable throwable){
        System.out.println("⚠️ [Fallback] Error al actualizar la venta. Motivo: " + throwable.getMessage());
        Sale sale2 = this.findSaleById(id);
        sale2.setCartId(sale.getCartId());
        sale2.setDate(sale.getDate());
        saleRepo.save(sale2);
    }
}
