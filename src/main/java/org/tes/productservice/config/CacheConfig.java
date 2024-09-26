package org.tes.productservice.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {
    public Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder();
    }

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(
                "defaultProduct",
                "compProduct",
                "laptopProduct"
        );
        cacheManager.setCaffeine(caffeineCacheBuilder());

        return cacheManager;
    }
}
