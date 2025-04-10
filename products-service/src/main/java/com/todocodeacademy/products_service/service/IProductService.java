package com.todocodeacademy.products_service.service;

import com.todocodeacademy.products_service.model.Product;

import java.util.List;

public interface IProductService {

    public Product findProductById(Long id);

    public List<Product> getProducts();

    public void saveProduct(Product product);

    public void deleteProductById(Long id);

    public void updateProduct(Long id, Product product);

}
