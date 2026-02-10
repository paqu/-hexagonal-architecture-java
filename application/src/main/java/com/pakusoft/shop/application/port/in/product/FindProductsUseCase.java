package com.pakusoft.shop.application.port.in.product;

import com.pakusoft.shop.model.product.Product;
import java.util.List;


public interface FindProductsUseCase {

    List<Product> findByNameOrDescription(String query);
}