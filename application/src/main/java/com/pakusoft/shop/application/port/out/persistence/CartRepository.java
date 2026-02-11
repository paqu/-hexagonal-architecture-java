package com.pakusoft.shop.application.port.out.persistence;

import com.pakusoft.shop.model.cart.Cart;
import com.pakusoft.shop.model.customer.CustomerId;

import java.util.Optional;

public interface CartRepository {

    void save(Cart cart);

    Optional<Cart> findByCustomerId(CustomerId customerId);

    void deleteByCustomerId(CustomerId customerId);
}