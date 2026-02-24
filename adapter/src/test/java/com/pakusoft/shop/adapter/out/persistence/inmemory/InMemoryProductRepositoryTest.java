package com.pakusoft.shop.adapter.out.persistence.inmemory;


import com.pakusoft.shop.adapter.out.persistence.AbstractProductRepositoryTest;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = com.pakusoft.shop.adapter.SpringAppConfig.class)
class InMemoryProductRepositoryTest extends AbstractProductRepositoryTest {}