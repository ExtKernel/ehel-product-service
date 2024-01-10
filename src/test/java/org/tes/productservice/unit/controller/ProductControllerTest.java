package org.tes.productservice.unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tes.productservice.controller.ProductController;
import org.tes.productservice.model.ProductEntity;
import org.tes.productservice.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    public void setUpMockHttp() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void saveTest() throws Exception {
        String title = "product";
        String condition = "used";
        int price = 100;
        int quantity = 0;

        assertThat(productController.save(title, condition, price, quantity).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

// To be implemented
//    @Test
//    public void updateTest() throws Exception {
//
//    }

    @Test
    public void findAllTest() throws Exception {
        ProductEntity product1 = new ProductEntity();
        ProductEntity product2 = new ProductEntity();
        List<ProductEntity> productList = new ArrayList<>();

        productList.add(product1);
        productList.add(product2);

        when(productService.findAll()).thenReturn(productList);

        assertThat(productController.findAll().getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(productController.findAll().getBody()).size().isEqualTo(2);
        assertThat(productController.findAll().getBody().get(0).getTitle()).isEqualTo(product1.getTitle());
        assertThat(productController.findAll().getBody().get(1).getTitle()).isEqualTo(product2.getTitle());
    }

    @Test
    public void findByIdTest() throws Exception {
        ProductEntity product = new ProductEntity();
        long productId = 0;
        product.setId(productId);

        when(productService.findById(0)).thenReturn(Optional.of(product));

        assertThat(productController.findById(0).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(productController.findById(0).getBody().get().getId()).isEqualTo(product.getId());
    }

    @Test
    public void deleteByIdTest() {
        ProductEntity product = new ProductEntity();
        long productId = 0;
        product.setId(productId);

        when(productService.findById(productId)).thenReturn(Optional.of(product));
        when(productService.deleteById(productId)).thenReturn(true);

        assertThat(productController.deleteById(productId).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deleteAllTest() {
        assertThat(productController.deleteAll().getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
