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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.pakusoft.shop")
@EnableAutoConfiguration
@EnableJpaRepositories("com.pakusoft.shop.adapter.out.persistence.jpa")
@EntityScan("com.pakusoft.shop.adapter.out.persistence.jpa")

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
