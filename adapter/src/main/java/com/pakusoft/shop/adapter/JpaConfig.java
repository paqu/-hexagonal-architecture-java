package com.pakusoft.shop.adapter;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Profile("!InMem")
@EnableJpaRepositories("com.pakusoft.shop.adapter.out.persistence.jpa")
@EntityScan("com.pakusoft.shop.adapter.out.persistence.jpa")
public class JpaConfig {
}
