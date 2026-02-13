package com.pakusoft.shop.adapter.in.rest.product;

import com.pakusoft.shop.application.port.in.product.FindProductsUseCase;
import com.pakusoft.shop.model.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.pakusoft.shop.adapter.in.rest.common.ControllerCommons.clientErrorException;

@RestController
@RequestMapping("/products")
public class FindProductsController {

    private final FindProductsUseCase findProductsUseCase;

    public FindProductsController(FindProductsUseCase findProductsUseCase) {
        this.findProductsUseCase = findProductsUseCase;
    }

    @GetMapping
    public List<ProductInListWebModel> findProducts(
            @RequestParam(value = "query", required = false) String query) {
        if (query == null) {
            throw clientErrorException(HttpStatus.BAD_REQUEST, "Missing 'query'");
        }

        List<Product> products;

        try {
            products = findProductsUseCase.findByNameOrDescription(query);
        } catch (IllegalArgumentException e) {
            throw clientErrorException(HttpStatus.BAD_REQUEST, "Invalid 'query'");
        }

        return products.stream().map(ProductInListWebModel::fromDomainModel).toList();
    }
}
