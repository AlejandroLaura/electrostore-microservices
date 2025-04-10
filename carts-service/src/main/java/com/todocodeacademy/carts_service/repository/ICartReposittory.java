package com.todocodeacademy.carts_service.repository;

import com.todocodeacademy.carts_service.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartReposittory extends JpaRepository<Cart, Long> {
}
