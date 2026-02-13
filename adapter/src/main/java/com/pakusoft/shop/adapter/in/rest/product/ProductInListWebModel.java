package com.pakusoft.shop.adapter.in.rest.product;

import com.pakusoft.shop.model.money.Money;
import com.pakusoft.shop.model.product.Product;

public record ProductInListWebModel(String id, String name, Money price, int itemsInStock) {

    public static ProductInListWebModel fromDomainModel(Product product) {
        return new ProductInListWebModel(
                product.id().value(), product.name(), product.price(), product.itemsInStock());
    }
}