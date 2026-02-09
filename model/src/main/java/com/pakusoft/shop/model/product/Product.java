package com.pakusoft.shop.model.product;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import com.pakusoft.shop.model.money.Money;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class Product {

    private final ProductId id;
    private String name;
    private String description;
    private Money price;
    private int itemsInStock;
}