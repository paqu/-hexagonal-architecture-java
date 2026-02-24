package com.pakusoft.shop.adapter.out.persistence.jpa;

import com.pakusoft.shop.application.port.out.persistence.CartRepository;
import com.pakusoft.shop.model.cart.Cart;
import com.pakusoft.shop.model.customer.CustomerId;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public class JpaCartRepository implements CartRepository {

    private final JpaCartSpringDataRepository springDataRepository;

    public JpaCartRepository(JpaCartSpringDataRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    @Transactional
    public void save(Cart cart) {
        springDataRepository.save(CartMapper.toJpaEntity(cart));
    }

    @Override
    @Transactional
    public Optional<Cart> findByCustomerId(CustomerId customerId) {
        Optional<CartJpaEntity> cartJpaEntity = springDataRepository.findById(customerId.value());
        return cartJpaEntity.map(CartMapper::toModelEntity);
    }

    @Override
    @Transactional
    public void deleteByCustomerId(CustomerId customerId) {
        springDataRepository.deleteById(customerId.value());
    }
}
