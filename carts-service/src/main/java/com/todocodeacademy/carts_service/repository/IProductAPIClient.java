package com.todocodeacademy.carts_service.repository;

import com.todocodeacademy.carts_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products-service")
public interface IProductAPIClient {
    @GetMapping("/products/{productId}")
    public ProductDTO getProductById(@PathVariable("productId") Long productId);
}
