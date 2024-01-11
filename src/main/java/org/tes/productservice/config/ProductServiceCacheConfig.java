package org.tes.productservice.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@EnableCaching
@Configuration
public class ProductServiceCacheConfig {
    public Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(200)
                .maximumSize(500)
                .expireAfterWrite(Duration.ofMinutes(10));
    }

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("ProductCache", "LaptopProductCache");
        cacheManager.setCaffeine(caffeineCacheBuilder());

        return cacheManager;
    }
}
