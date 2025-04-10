package com.todocodeacademy.products_service.controller;

import com.todocodeacademy.products_service.model.Product;
import com.todocodeacademy.products_service.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Product findProductById(@PathVariable Long id){

        return productService.findProductById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Product> getProducts(){

        return productService.getProducts();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String saveProduct(@RequestBody Product produc){
        productService.saveProduct(produc);
        return "El producto ha sido Guardado exitosamente";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
        return "El producto fue eliminado Correctamente";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateProduct(@PathVariable Long id, @RequestBody Product produc){
        productService.updateProduct(id, produc);
        return "El producto ha sido actualizado correctamente";
    }




}
