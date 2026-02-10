package com.pakusoft.shop.application.port.in.cart;

import com.pakusoft.shop.model.cart.Cart;
import com.pakusoft.shop.model.customer.CustomerId;



public interface GetCartUseCase {

    Cart getCart(CustomerId customerId);
}