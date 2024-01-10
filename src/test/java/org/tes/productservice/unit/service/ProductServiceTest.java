package org.tes.productservice.unit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tes.productservice.model.ProductEntity;
import org.tes.productservice.persistence.ProductEntityRepository;
import org.tes.productservice.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductEntityRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    public void saveTest() throws Exception {
        ProductEntity product = new ProductEntity();

        when(productRepository.save(product)).thenReturn(product);

        assertThat(productService.save(product).get()).isEqualTo(product);
    }

    @Test
    public void updateTest() throws Exception {
        ProductEntity product = new ProductEntity();

        when(productRepository.save(product)).thenReturn(product);

        assertThat(productService.update(product).get()).isEqualTo(product);
    }

    @Test
    public void findAllTest() throws Exception {
        ProductEntity product1 = new ProductEntity();
        ProductEntity product2 = new ProductEntity();
        List<ProductEntity> productList = new ArrayList<>();

        productList.add(product1);
        productList.add(product2);

        when(productRepository.findAll()).thenReturn(productList);

        assertThat(productService.findAll()).isEqualTo(productList);
    }

    @Test
    public void findByIdTest() throws Exception {
        ProductEntity product = new ProductEntity();
        long productId = 0;

        product.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        assertThat(productService.findById(productId).get()).isEqualTo(product);
    }

    @Test
    public void deleteByIdTest() throws Exception {
        long productId = 0;

        assertThat(productService.deleteById(productId)).isEqualTo(true);
    }

    @Test
    public void deleteAllTest() throws Exception {
        assertThat(productService.deleteAll()).isEqualTo(true);
    }
}
