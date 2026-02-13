package com.pakusoft.shop.adapter.out.persistance.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CartLineItem")
@Getter
@Setter
public class CartLineItemJpaEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne private CartJpaEntity cart;

    @ManyToOne
    private ProductJpaEntity product;

    private int quantity;
}