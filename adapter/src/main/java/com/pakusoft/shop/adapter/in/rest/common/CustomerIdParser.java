package com.pakusoft.shop.adapter.in.rest.common;

import com.pakusoft.shop.model.customer.CustomerId;
import org.springframework.http.HttpStatus;

import static com.pakusoft.shop.adapter.in.rest.common.ControllerCommons.clientErrorException;

public final class CustomerIdParser {

    private CustomerIdParser() {}

    public static CustomerId parseCustomerId(String string) {
        try {
            return new CustomerId(Integer.parseInt(string));
        } catch (IllegalArgumentException e) {
            throw clientErrorException(HttpStatus.BAD_REQUEST, "Invalid 'customerId'");
        }
    }
}