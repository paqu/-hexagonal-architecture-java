package com.pakusoft.shop.adapter.out.persistence.jpa;

import com.pakusoft.shop.adapter.out.persistence.DemoProducts;
import com.pakusoft.shop.application.port.out.persistence.ProductRepository;
import com.pakusoft.shop.model.product.Product;
import com.pakusoft.shop.model.product.ProductId;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("!InMem")
public class JpaProductRepository implements ProductRepository {

    private final JpaProductSpringDataRepository springDataRepository;

    public JpaProductRepository(JpaProductSpringDataRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @PostConstruct
    void createDemoProducts() {
        DemoProducts.DEMO_PRODUCTS.forEach(this::save);
    }

    @Override
    @Transactional
    public void save(Product product) {
        springDataRepository.save(ProductMapper.toJpaEntity(product));
    }

    @Override
    @Transactional
    public Optional<Product> findById(ProductId productId) {
        Optional<ProductJpaEntity> jpaEntity = springDataRepository.findById(productId.value());
        return jpaEntity.map(ProductMapper::toModelEntity);
    }

    @Override
    @Transactional
    public List<Product> findByNameOrDescription(String queryString) {
        List<ProductJpaEntity> entities =
                springDataRepository.findByNameOrDescriptionLike("%" + queryString + "%");

        return ProductMapper.toModelEntities(entities);
    }
}
