package com.todocodeacademy.products_service.service;

import com.todocodeacademy.products_service.model.Product;
import com.todocodeacademy.products_service.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepo;

    @Override
    public Product findProductById(Long id) {
        return productRepo.findById(id).orElseThrow(()-> new RuntimeException("Producto no encontrado con ID:" + id));
    }

    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public void deleteProductById(Long id) {

        if(!productRepo.existsById(id)){
            throw new RuntimeException("No se puede eliminar el producto con el ID: " + id + " no encontrado");
        }
        productRepo.deleteById(id);
    }

    @Override
    public void updateProduct(Long id, Product product) {
        Product pro = this.findProductById(id);
        pro.setCode(product.getCode());
        pro.setName(product.getName());
        pro.setBrand(product.getBrand());
        pro.setPrice(product.getPrice());
        productRepo.save(pro);
    }
}
