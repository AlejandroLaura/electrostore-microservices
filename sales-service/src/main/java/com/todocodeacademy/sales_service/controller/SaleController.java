package com.todocodeacademy.sales_service.controller;

import com.todocodeacademy.sales_service.dto.CartDetailsDTO;
import com.todocodeacademy.sales_service.model.Sale;
import com.todocodeacademy.sales_service.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Sale findSaleById(@PathVariable Long id){
        return saleService.findSaleById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Sale> getSales(){
        return saleService.getSales();
    }

    @GetMapping("/details/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CartDetailsDTO getCartDetails(@PathVariable Long id){
        return saleService.getCartDetails(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String saveSale(@RequestBody Sale sale){
        saleService.saveSale(sale);
        return "La venta ha sido guadada correctamente";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteSaleById(@PathVariable Long id){
        saleService.deleteSale(id);
        return "La venta se ha eliminado Exitosamente";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateSale(@PathVariable  Long id, @RequestBody Sale sale){
        saleService.updateSale(id, sale);
        return "La veta se ha actualizado exitosamente";
    }




}
