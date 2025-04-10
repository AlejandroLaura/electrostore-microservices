package com.todocodeacademy.carts_service.controller;

import com.todocodeacademy.carts_service.dto.CartDetailsDTO;
import com.todocodeacademy.carts_service.model.Cart;
import com.todocodeacademy.carts_service.repository.IProductAPIClient;
import com.todocodeacademy.carts_service.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Cart findCartById(@PathVariable Long id){
        return cartService.findCartById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Cart> getCarts(){
        return cartService.getCarts();
    }

    @GetMapping("/details/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CartDetailsDTO getCartDetailsById(@PathVariable Long id){
        return cartService.getCartDetails(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String saveCart(@RequestBody Cart cart){

        cartService.saveCart(cart);
        return "El carrito ha sido guardado Exitosamente";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCartById(@PathVariable Long id){
        cartService.deleteCartById(id);
        return "El carrito Se ha eliminado correctamente";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateCart(@PathVariable Long id, @RequestBody Cart cart){
        cartService.updateCart(id, cart);
        return "El carrrito se ha actualizado correctamente";
    }
}
