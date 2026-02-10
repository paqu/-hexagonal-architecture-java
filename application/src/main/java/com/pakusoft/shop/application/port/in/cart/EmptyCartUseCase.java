package com.pakusoft.shop.application.port.in.cart;

import com.pakusoft.shop.model.customer.CustomerId;

public interface EmptyCartUseCase {

    void emptyCart(CustomerId customerId);
}