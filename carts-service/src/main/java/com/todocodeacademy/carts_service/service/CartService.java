package com.todocodeacademy.carts_service.service;

import com.todocodeacademy.carts_service.dto.CartDetailsDTO;
import com.todocodeacademy.carts_service.dto.ProductDTO;
import com.todocodeacademy.carts_service.model.Cart;
import com.todocodeacademy.carts_service.model.CartItem;
import com.todocodeacademy.carts_service.repository.ICartReposittory;
import com.todocodeacademy.carts_service.repository.IProductAPIClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService{

    @Autowired
    private ICartReposittory cartRepo;

    @Autowired
    private IProductAPIClient productAPIClient;

    @Override
    public Cart findCartById(Long id) {
        return cartRepo.findById(id).orElseThrow(()-> new RuntimeException("Carrito no encontrado con la ID:" + id));
    }

    @Override
    public List<Cart> getCarts() {
        return cartRepo.findAll();
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallbackGetCartDetails")
    public CartDetailsDTO getCartDetails(Long id) {
        //buscamos el cart con estrutura clasica
        Cart cart = this.findCartById(id);
        CartDetailsDTO cartDetails = new CartDetailsDTO();
        List<ProductDTO> productList = new ArrayList<>();
        for(CartItem item: cart.getItems()){
            ProductDTO pro = productAPIClient.getProductById(item.getProductId());
            pro.setQuantity(item.getQuantity());
            productList.add(pro);

        }
        //usando lambdas y streams
        /*
        List<ProductDTO> producList = cart.getItems().stream()
                        .map(item-> productAPIClient.getProductById(item.getProductId()))
                        .collect(Collectors.toList());
        */
        //seteamos y retornamos
        cartDetails.setId(cart.getId());
        cartDetails.setTotal(cart.getTotal());
        cartDetails.setProductList(productList);

        return cartDetails;
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallbackSaveCart")
    public void saveCart(Cart cart) {
        //el total viene vacio asi que procedemos a calcular el total iterando la lista de items
        Double total = 0.0;

        for(CartItem item: cart.getItems()){
            //buscamos el producto consumiendo la api cient para tener a mano el precio unitario
            ProductDTO product = productAPIClient.getProductById(item.getProductId());
            total = total + (item.getQuantity() * product.getPrice());
            item.setCart(cart);
        }
        cart.setTotal(total);
        cartRepo.save(cart);
        //USANDO STREAM Y LAMBDAS
        /*
        Double monto_total = cart.getItems().stream()
                .mapToDouble(item -> {
                    ProductDTO produc = productAPIClient.getProductById(item.getProductId());
                    return item.getQuantity()*produc.getPrice();
                })
                .sum();
        cart.setTotal(monto_total);
        cartRepo.save(cart);
        */
    }

    @Override
    public void deleteCartById(Long id) {

        if(!cartRepo.existsById(id)){
            throw new RuntimeException("No se puede eliminar el producto con el ID: " + id + " no encontrado");
        }
        cartRepo.deleteById(id);
    }

    @Override
    public void updateCart(long id, Cart cart) {
        //buscamos el carrito con l id
        Cart car = this.findCartById(id);
        //setamos los nuevos valores
        car.setTotal(cart.getTotal());
        car.setItems(cart.getItems());
        //guardamos
        cartRepo.save(car);

    }

    public CartDetailsDTO fallbackGetCartDetails(Long id, Throwable throwable){
        System.out.println("⚠️ [Fallback] Error al obtener detalles del carrito con ID: " + id +  ". Motivo: " + throwable.getMessage());
        return new CartDetailsDTO(id, 0.0, Collections.emptyList());
    }

    public void fallbackSaveCart(Cart cart, Throwable throwable){
        System.out.println("⚠️ [Fallback] Error al guardar el carrito. Motivo: " + throwable.getMessage());
        cart.setTotal(0.0);
        cartRepo.save(cart);
    }
}
