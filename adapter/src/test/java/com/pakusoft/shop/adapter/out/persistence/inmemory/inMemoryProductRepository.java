package com.pakusoft.shop.adapter.out.persistence.inmemory;


import com.pakusoft.shop.adapter.out.persistance.inmemory.InMemoryProductRepository;
import com.pakusoft.shop.adapter.out.persistence.AbstractProductRepositoryTest;

class InMemoryProductRepositoryTest
        extends AbstractProductRepositoryTest<InMemoryProductRepository> {

    @Override
    protected InMemoryProductRepository createProductRepository() {
        return new InMemoryProductRepository();
    }
}

