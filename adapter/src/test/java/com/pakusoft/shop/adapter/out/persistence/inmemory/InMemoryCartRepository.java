package com.pakusoft.shop.adapter.out.persistence.inmemory;

import com.pakusoft.shop.adapter.out.persistance.inmemory.InMemoryCartRepository;
import com.pakusoft.shop.adapter.out.persistance.inmemory.InMemoryProductRepository;
import com.pakusoft.shop.adapter.out.persistence.AbstractCartRepositoryTest;


class InMemoryCartRepositoryTest
        extends AbstractCartRepositoryTest<InMemoryCartRepository, InMemoryProductRepository> {

    @Override
    protected InMemoryCartRepository createCartRepository() {
        return new InMemoryCartRepository();
    }

    @Override
    protected InMemoryProductRepository createProductRepository() {
        return new InMemoryProductRepository();
    }
}