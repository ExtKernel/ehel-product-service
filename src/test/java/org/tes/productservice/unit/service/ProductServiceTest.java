package org.tes.productservice.unit.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tes.productservice.model.ProductEntity;
import org.tes.productservice.persistence.ProductEntityRepository;
import org.tes.productservice.service.ProductService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.tes.productservice.TestUtils.getMockedProductEntity;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductEntityRepository productRepository;

    @InjectMocks
    ProductService productService;

    private static ProductEntity product;

    @BeforeAll
    public static void setUpEntity() {
        String title = "A product";
        String condition = "Used";
        int price = 0;
        int quantity = 0;
        product = getMockedProductEntity(title, condition, price, quantity);
    }

    @Test
    public void save_WithProductEntity_ShouldReturnOk() throws Exception {
        when(productRepository.save(product)).thenReturn(product);

        assertThat(productService.save(product).get()).isEqualTo(product);
    }

    @Test
    public void update_WithProductEntity_ShouldReturnOk() throws Exception {
        when(productRepository.save(product)).thenReturn(product);

        assertThat(productService.update(product).get()).isEqualTo(product);
    }

    @Test
    public void findAll_WithWithProductEntityList_ShouldReturnEntityList() throws Exception {
        List<ProductEntity> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList);

        assertThat(productService.findAll()).isEqualTo(productList);
    }

    @Test
    public void findById_WithProductEntity_ShouldReturnTheEntity() throws Exception {
        long productId = 0;

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        assertThat(productService.findById(productId).get()).isEqualTo(product);
    }

    @Test
    public void deleteById_WithProductEntity_ShouldReturnTrue() throws Exception {
        long productId = 0;

        assertThat(productService.deleteById(productId)).isEqualTo(true);
    }

    @Test
    public void deleteAll_WithProductEntity_ShouldReturnTrue() throws Exception {
        assertThat(productService.deleteAll()).isEqualTo(true);
    }
}
