package com.pakusoft.shop.adapter.out.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaCartSpringDataRepository extends JpaRepository<CartJpaEntity, Integer> {}