package com.pakusoft.shop.bootstrap.e2e;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static com.pakusoft.shop.adapter.in.rest.product.ProductsControllerAssertions.assertThatResponseIsProductList;
import static com.pakusoft.shop.adapter.out.persistence.DemoProducts.COMPUTER_MONITOR;
import static com.pakusoft.shop.adapter.out.persistence.DemoProducts.MONITOR_DESK_MOUNT;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FindProductsTest {

    @LocalServerPort
    private Integer port;

    @Test
    void givenTestProductsAndAQuery_findProducts_returnsMatchingProducts() {
        String query = "monitor";

        Response response =
                given().port(port).queryParam("query", query).get("/products").then().extract().response();


        assertThatResponseIsProductList(response, List.of(COMPUTER_MONITOR, MONITOR_DESK_MOUNT));
    }
}