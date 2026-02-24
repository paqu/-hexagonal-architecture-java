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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.pakusoft.shop")
@EnableAutoConfiguration
public class SpringAppConfig {

    @Bean
    GetCartUseCase getCartUseCase(CartRepository cartRepository) {
        return new GetCartService(cartRepository);
    }

    @Bean
    EmptyCartUseCase emptyCartUseCase(CartRepository cartRepository) {
        return new EmptyCartService(cartRepository);
    }

    @Bean
    FindProductsUseCase findProductsUseCase(ProductRepository productRepository) {
        return new FindProductsService(productRepository);
    }

    @Bean
    AddToCartUseCase addToCartUseCase(CartRepository cartRepository, ProductRepository productRepository) {
        return new AddToCartService(cartRepository, productRepository);
    }
}
