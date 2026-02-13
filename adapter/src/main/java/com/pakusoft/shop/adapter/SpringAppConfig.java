package com.pakusoft.shop.adapter;

import com.pakusoft.shop.application.port.in.cart.AddToCartUseCase;
import com.pakusoft.shop.application.port.in.cart.EmptyCartUseCase;
import com.pakusoft.shop.application.port.in.cart.GetCartUseCase;
import com.pakusoft.shop.application.port.in.product.FindProductsUseCase;
import com.pakusoft.shop.application.port.out.persistence.CartRepository;
import com.pakusoft.shop.application.port.out.persistence.ProductRepository;
import com.pakusoft.shop.application.service.cart.AddToCartService;
import com.pakusoft.shop.application.service.cart.EmptyCartService;
import com.pakusoft.shop.application.service.cart.GetCartService;
import com.pakusoft.shop.application.service.product.FindProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAppConfig {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Bean
    GetCartUseCase getCartUseCase() {
        return new GetCartService(cartRepository);
    }

    @Bean
    EmptyCartUseCase emptyCartUseCase() {
        return new EmptyCartService(cartRepository);
    }

    @Bean
    FindProductsUseCase findProductsUseCase() {
        return new FindProductsService(productRepository);
    }

    @Bean
    AddToCartUseCase addToCartUseCase() {
        return new AddToCartService(cartRepository, productRepository);
    }
}
