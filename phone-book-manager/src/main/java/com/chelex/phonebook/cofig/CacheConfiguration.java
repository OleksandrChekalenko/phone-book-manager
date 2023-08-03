package com.chelex.phonebook.cofig;

import com.chelex.phonebook.constant.CacheConstant;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Value("expireAfterWrite=60s")
    private String caffeineSpec;

    @Bean
    public CaffeineCache getAllPersonsContactsCache() {
        return new CaffeineCache(CacheConstant.ALL_PERSONS_CONTACTS,
                Caffeine.from(caffeineSpec).build());
    }
}
