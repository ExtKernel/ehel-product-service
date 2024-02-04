package org.tes.productservice.unit.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
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
import static org.tes.productservice.TestUtils.getMockedProductEntity;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    private static ProductEntity product;

    @BeforeAll
    public static void setUpEntity() {
        String title = "A product";
        String condition = "Used";
        int price = 0;
        int quantity = 0;
        product = getMockedProductEntity(
                title,
                condition,
                price,
                quantity
        );
    }

    @BeforeEach
    public void setUpMockHttp() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void save_WithProductEntity_ShouldReturnOk() throws Exception {
        assertThat(productController
                .save(product)
                .getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

// To be implemented
//    @Test
//    public void updateTest() throws Exception {
//
//    }

    @Test
    public void findAll_WithEntityList_ShouldReturnOk() throws Exception {
        List<ProductEntity> productList = new ArrayList<>();
        productList.add(product);

        when(productService.findAll()).thenReturn(productList);

        assertThat(productController
                .findAll()
                .getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void findAll_WithProductEntityList_ShouldReturnListSize1() throws Exception {
        List<ProductEntity> productList = new ArrayList<>();
        productList.add(product);

        when(productService.findAll()).thenReturn(productList);

        assertThat(productController
                .findAll()
                .getBody())
                .size()
                .isEqualTo(1);
    }

    @Test
    public void findAll_WithWithProductEntityList_ShouldReturnListContainingTheEntity()
            throws Exception {
        List<ProductEntity> productList = new ArrayList<>();
        productList.add(product);

        when(productService.findAll()).thenReturn(productList);

        assertThat(productController
                .findAll()
                .getBody()
                .get(0)
                .getTitle())
                .isEqualTo(product.getTitle());
    }

    @Test
    public void findById_WithProductEntity_ShouldReturnOk() throws Exception {
        when(productService.findById(0)).thenReturn(Optional.of(product));

        assertThat(productController
                .findById(0)
                .getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void findById_WithProductEntity_ShouldReturnTheEntity() throws Exception {
        when(productService.findById(0)).thenReturn(Optional.of(product));

        assertThat(productController
                .findById(0)
                .getBody()
                .get()
                .getId())
                .isEqualTo(product.getId());
    }

    @Test
    public void deleteById_WithProductEntity_ShouldReturnOk() {
        long productId = 0;

        when(productService.findById(productId)).thenReturn(Optional.of(product));
        when(productService.deleteById(productId)).thenReturn(true);

        assertThat(productController
                .deleteById(productId)
                .getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deleteAll_ShouldReturnOk() {
        assertThat(productController.deleteAll().getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
