package com.kotiki.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@Configuration
@EnableJpaRepositories(basePackages={"entitiesDAO"})
@EntityScan(basePackages = {"Entities"})
public class ConfigCats {
}
