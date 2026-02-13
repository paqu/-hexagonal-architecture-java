package com.pakusoft.shop.adapter.in.rest.cart;

import com.pakusoft.shop.application.port.in.cart.AddToCartUseCase;
import com.pakusoft.shop.application.port.in.cart.ProductNotFoundException;
import com.pakusoft.shop.model.cart.Cart;
import com.pakusoft.shop.model.cart.NotEnoughItemsInStockException;
import com.pakusoft.shop.model.customer.CustomerId;
import com.pakusoft.shop.model.product.ProductId;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.pakusoft.shop.adapter.in.rest.common.ControllerCommons.clientErrorException;
import static com.pakusoft.shop.adapter.in.rest.common.CustomerIdParser.parseCustomerId;
import static com.pakusoft.shop.adapter.in.rest.common.ProductIdParser.parseProductId;

@RestController
@RequestMapping("/carts")
public class AddToCartController {

    private final AddToCartUseCase addToCartUseCase;

    public AddToCartController(AddToCartUseCase addToCartUseCase) {
        this.addToCartUseCase = addToCartUseCase;
    }

    @PostMapping("/{customerId}/line-items")
    public CartWebModel addLineItem(
            @PathVariable("customerId") String customerIdString,
            @RequestParam("productId") String productIdString,
            @RequestParam("quantity") int quantity) {
        CustomerId customerId = parseCustomerId(customerIdString);
        ProductId productId = parseProductId(productIdString);

        try {
            Cart cart = addToCartUseCase.addToCart(customerId, productId, quantity);
            return CartWebModel.fromDomainModel(cart);
        } catch (ProductNotFoundException e) {
            throw clientErrorException(HttpStatus.BAD_REQUEST, "The requested product does not exist");
        } catch (NotEnoughItemsInStockException e) {
            throw clientErrorException(
                    HttpStatus.BAD_REQUEST, "Only %d items in stock".formatted(e.itemsInStock()));
        }
    }
}