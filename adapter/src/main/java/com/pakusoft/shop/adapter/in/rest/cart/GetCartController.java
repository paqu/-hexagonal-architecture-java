package com.pakusoft.shop.adapter.in.rest.cart;

import com.pakusoft.shop.application.port.in.cart.GetCartUseCase;
import com.pakusoft.shop.model.cart.Cart;
import com.pakusoft.shop.model.customer.CustomerId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pakusoft.shop.adapter.in.rest.common.CustomerIdParser.parseCustomerId;

/**
 * REST controller for all shopping cart use cases.
 *
 * @author Sven Woltmann
 */
@RestController
@RequestMapping("/carts")
public class GetCartController {

    private final GetCartUseCase getCartUseCase;

    public GetCartController(GetCartUseCase getCartUseCase) {
        this.getCartUseCase = getCartUseCase;
    }

    @GetMapping("/{customerId}")
    public CartWebModel getCart(@PathVariable("customerId") String customerIdString) {
        CustomerId customerId = parseCustomerId(customerIdString);
        Cart cart = getCartUseCase.getCart(customerId);
        return CartWebModel.fromDomainModel(cart);
    }
}
