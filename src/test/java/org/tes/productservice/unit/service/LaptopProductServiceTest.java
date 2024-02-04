package org.tes.productservice.unit.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tes.productservice.model.LaptopProductEntity;
import org.tes.productservice.persistence.LaptopProductEntityRepository;
import org.tes.productservice.service.LaptopProductService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.tes.productservice.TestUtils.getMockedLaptopProductEntity;

@ExtendWith(MockitoExtension.class)
public class LaptopProductServiceTest {

    @Mock
    LaptopProductEntityRepository laptopProductRepository;

    @InjectMocks
    LaptopProductService laptopProductService;

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

    @Test
    public void save_WithLaptopProductEntity_ShouldReturnOk() throws Exception {
        when(laptopProductRepository.save(laptopProduct)).thenReturn(laptopProduct);

        assertThat(laptopProductService.save(laptopProduct).get()).isEqualTo(laptopProduct);
    }

    @Test
    public void update_WithLaptopProductEntity_ShouldReturnOk() throws Exception {
        when(laptopProductRepository.save(laptopProduct)).thenReturn(laptopProduct);

        assertThat(laptopProductService.update(laptopProduct).get()).isEqualTo(laptopProduct);
    }

    @Test
    public void findAll_WithWithLaptopProductEntityList_ShouldReturnEntityList() throws Exception {
        List<LaptopProductEntity> laptopProductList = new ArrayList<>();
        laptopProductList.add(laptopProduct);

        when(laptopProductRepository.findAll()).thenReturn(laptopProductList);

        assertThat(laptopProductService.findAll()).isEqualTo(laptopProductList);
    }

    @Test
    public void findById_WithLaptopProductEntity_ShouldReturnTheEntity() throws Exception {
        long laptopProductId = 0;

        when(laptopProductRepository.findById(laptopProductId)).thenReturn(Optional.of(laptopProduct));

        assertThat(laptopProductService.findById(laptopProductId).get()).isEqualTo(laptopProduct);
    }

    @Test
    public void deleteById_WithLaptopProductEntity_ShouldReturnTrue() throws Exception {
        long laptopProductId = 0;

        assertThat(laptopProductService.deleteById(laptopProductId)).isEqualTo(true);
    }

    @Test
    public void deleteAll_WithLaptopProductEntity_ShouldReturnTrue() throws Exception {
        assertThat(laptopProductService.deleteAll()).isEqualTo(true);
    }
}
