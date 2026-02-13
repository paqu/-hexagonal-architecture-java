package com.pakusoft.shop.adapter.in.rest.common;

import com.pakusoft.shop.model.product.ProductId;
import org.springframework.http.HttpStatus;

import static com.pakusoft.shop.adapter.in.rest.common.ControllerCommons.clientErrorException;

public final class ProductIdParser {

    private ProductIdParser() {}

    public static ProductId parseProductId(String string) {
        if (string == null) {
            throw clientErrorException(HttpStatus.BAD_REQUEST, "Missing 'productId'");
        }

        try {
            return new ProductId(string);
        } catch (IllegalArgumentException e) {
            throw clientErrorException(HttpStatus.BAD_REQUEST, "Invalid 'productId'");
        }
    }
}