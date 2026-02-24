package com.pakusoft.shop.adapter.out.persistence.inmemory;


import com.pakusoft.shop.adapter.out.persistence.AbstractProductRepositoryTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(classes = com.pakusoft.shop.adapter.SpringAppConfig.class)
@ActiveProfiles("InMem")
class InMemoryProductRepositoryTest extends AbstractProductRepositoryTest {}