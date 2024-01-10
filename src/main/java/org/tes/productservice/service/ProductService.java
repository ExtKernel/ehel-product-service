package org.tes.productservice.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tes.productservice.model.ProductEntity;
import org.tes.productservice.persistence.ProductEntityRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private ProductEntityRepository productRepository;

    @Transactional
    public Optional<ProductEntity> save(ProductEntity productEntity) {
        return Optional.of(productRepository.save(productEntity));
    }

    @Transactional
    public Optional<ProductEntity> update(ProductEntity updatedProductEntity) {
        return Optional.of(productRepository.save(updatedProductEntity));
    }

    @Cacheable("ProductCache")
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Cacheable("ProductCache")
    public Optional<ProductEntity> findById(long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(long id) {
        productRepository.deleteById(id);

        return true;
    }

    @Transactional
    public boolean deleteAll() {
        productRepository.deleteAll();

        return true;
    }
}
