package com.pakusoft.shop.adapter.out.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaCartSpringDataRepository extends JpaRepository<CartJpaEntity, Integer> {}