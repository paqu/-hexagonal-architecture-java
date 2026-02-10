package com.pakusoft.shop.application.service.cart;

import com.pakusoft.shop.application.port.in.cart.GetCartUseCase;
import com.pakusoft.shop.application.port.out.persistance.CartRepository;
import com.pakusoft.shop.model.cart.Cart;
import com.pakusoft.shop.model.customer.CustomerId;

import java.util.Objects;



public class GetCartService implements GetCartUseCase {

    private final CartRepository cartRepository;

    public GetCartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart getCart(CustomerId customerIdVeryLong) {
        Objects.requireNonNull(customerIdVeryLong, "'customerId' must not be null");

        return cartRepository
                .findByCustomerId(customerIdVeryLong)
                .orElseGet(() -> new Cart(customerIdVeryLong));
    }
}