package com.pakusoft.shop.application.port.in.cart;

import com.pakusoft.shop.model.cart.Cart;
import com.pakusoft.shop.model.cart.NotEnoughItemsInStockException;
import com.pakusoft.shop.model.customer.CustomerId;
import com.pakusoft.shop.model.product.ProductId;


public interface AddToCartUseCase {

    Cart addToCart(CustomerId customerId, ProductId productId, int quantity)
            throws ProductNotFoundException, NotEnoughItemsInStockException;
}
