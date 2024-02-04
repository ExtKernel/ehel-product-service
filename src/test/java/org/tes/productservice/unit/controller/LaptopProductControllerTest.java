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
import org.tes.productservice.controller.LaptopProductController;
import org.tes.productservice.model.LaptopProductEntity;
import org.tes.productservice.service.LaptopProductService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.tes.productservice.TestUtils.getMockedLaptopProductEntity;

@ExtendWith(MockitoExtension.class)
public class LaptopProductControllerTest {

    @Mock
    LaptopProductService laptopProductService;

    @InjectMocks
    LaptopProductController laptopProductController;

    private static LaptopProductEntity laptopProduct;

    @BeforeAll
    public static void setUpEntity() {
        String title = "A laptop";
        String condition = "Used";
        int price = 0;
        int quantity = 0;
        String cpu = "Intel Core i5";
        int ramCapacity = 0;
        String ramType = "DDR4";
        String gpuType = "Integrated";
        String goodFor = "Browsing internet";
        Date releaseDate = new Date(1153785600000L);
        boolean comesWithCharger = true;
        String screenType = "IPS";
        String screenResolution = "1920x1080";
        boolean hasBacklit = false;
        String model = "ThinkPad X1";
        laptopProduct = getMockedLaptopProductEntity(
                title,
                condition,
                price,
                quantity,
                cpu,
                ramCapacity,
                ramType,
                gpuType,
                goodFor,
                releaseDate,
                comesWithCharger,
                screenType,
                screenResolution,
                hasBacklit,
                model
        );
    }

    @BeforeEach
    public void setUpMockHttp() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void save_WithLaptopProductEntity_ShouldReturnOk() throws Exception {
        assertThat(laptopProductController
                .saveLaptop(laptopProduct)
                .getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

// To be implemented
//    @Test
//    public void updateTest() throws Exception {
//
//    }

    @Test
    public void findAll_WithLaptopProductEntityList_ShouldReturnOk() throws Exception {
        List<LaptopProductEntity> laptopProductList = new ArrayList<>();
        laptopProductList.add(laptopProduct);

        when(laptopProductService.findAll()).thenReturn(laptopProductList);

        assertThat(laptopProductController
                .findAllLaptops()
                .getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void findAll_WithLaptopProductEntityList_ShouldReturnListSize1() throws Exception {
        List<LaptopProductEntity> laptopProductList = new ArrayList<>();
        laptopProductList.add(laptopProduct);

        when(laptopProductService.findAll()).thenReturn(laptopProductList);

        assertThat(laptopProductController
                .findAllLaptops()
                .getBody())
                .size()
                .isEqualTo(1);
    }

    @Test
    public void findAll_WithWithLaptopProductEntityList_ShouldReturnListContainingTheEntity()
            throws Exception {
        List<LaptopProductEntity> laptopProductList = new ArrayList<>();
        laptopProductList.add(laptopProduct);

        when(laptopProductService.findAll()).thenReturn(laptopProductList);

        assertThat(laptopProductController
                .findAllLaptops()
                .getBody()
                .get(0)
                .getTitle())
                .isEqualTo(laptopProduct.getTitle());
    }

    @Test
    public void findById_WithLaptopProductEntity_ShouldReturnOk() throws Exception {
        when(laptopProductService.findById(0)).thenReturn(Optional.of(laptopProduct));

        assertThat(laptopProductController
                .findLaptopById(0)
                .getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void findById_WithLaptopProductEntity_ShouldReturnTheEntity()
            throws Exception {
        when(laptopProductService.findById(0)).thenReturn(Optional.of(laptopProduct));

        assertThat(laptopProductController
                .findLaptopById(0)
                .getBody()
                .get()
                .getId())
                .isEqualTo(laptopProduct.getId());
    }

    @Test
    public void deleteById_WithLaptopProductEntity_ShouldReturnOk() {
        long laptopProductId = 0;

        when(laptopProductService.findById(laptopProductId)).thenReturn(Optional.of(laptopProduct));
        when(laptopProductService.deleteById(laptopProductId)).thenReturn(true);

        assertThat(laptopProductController
                .deleteLaptopById(laptopProductId)
                .getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deleteAll_ShouldReturnOk() {
        assertThat(laptopProductController
                .deleteAllLaptops()
                .getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }
}
