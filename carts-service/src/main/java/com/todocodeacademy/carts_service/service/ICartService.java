package com.todocodeacademy.carts_service.service;

import com.todocodeacademy.carts_service.dto.CartDetailsDTO;
import com.todocodeacademy.carts_service.model.Cart;

import java.util.List;

public interface ICartService {

    public Cart findCartById(Long id);

    public List<Cart> getCarts();

    public CartDetailsDTO getCartDetails(Long id);

    public void saveCart(Cart cart);

    public void deleteCartById(Long id);

    public void updateCart(long id, Cart cart);
}
