package com.todocodeacademy.sales_service.repository;

import com.todocodeacademy.sales_service.dto.CartDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "carts-service")
public interface ICartAPIClient {
    @GetMapping("/carts/details/{cartId}")
    public CartDetailsDTO getCartDetailsById(@PathVariable("cartId") Long cartId);
}
