package org.tes.productservice.unit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tes.productservice.model.LaptopProduct;
import org.tes.productservice.persistence.LaptopProductRepository;
import org.tes.productservice.service.LaptopProductService;

@ExtendWith(MockitoExtension.class)
public class LaptopProductServiceTest {
    @Mock
    private LaptopProductRepository laptopProductRepository;

    @InjectMocks
    private LaptopProductService laptopProductService;

    @Test
    public void givenCorrectLaptopProduct_whenSave_thenReturnSavedLaptopProduct() {
        LaptopProduct laptopProduct = new LaptopProduct();
        laptopProduct.setId(1L);

        when(laptopProductRepository.save(laptopProduct)).thenReturn(laptopProduct);

        assertEquals(laptopProduct, laptopProductService.save(Optional.of(laptopProduct)));
    }

    @Test
    public void givenSavedLaptopProduct_whenFindByAll_thenReturnSavedLaptopProducts() {
        LaptopProduct laptopProduct = new LaptopProduct();
        laptopProduct.setId(1L);
        List<LaptopProduct> laptopProducts = new ArrayList<>();
        laptopProducts.add(laptopProduct);

        when(laptopProductRepository.findAll()).thenReturn(laptopProducts);

        assertEquals(laptopProducts, laptopProductService.findAll());
    }

    @Test
    public void givenCorrectLaptopProductId_whenFindById_thenReturnSavedLaptopProduct() {
        LaptopProduct laptopProduct = new LaptopProduct();
        laptopProduct.setId(1L);

        when(laptopProductRepository.findById(1L)).thenReturn(Optional.of(laptopProduct));

        assertEquals(laptopProduct, laptopProductService.findById(1L));
    }

    @Test
    public void givenCorrectUpdatedLaptopProduct_whenUpdate_thenReturnUpdatedLaptopProduct() {
        LaptopProduct laptopProduct = new LaptopProduct();
        laptopProduct.setId(1L);

        when(laptopProductRepository.save(laptopProduct)).thenReturn(laptopProduct);

        assertEquals(laptopProduct, laptopProductService.update(Optional.of(laptopProduct)));
    }

    @Test
    public void givenCorrectLaptopProductId_whenDelete_thenDoNotThrowException() {
        LaptopProduct laptopProduct = new LaptopProduct();
        laptopProduct.setId(1L);

        assertDoesNotThrow(() -> laptopProductService.deleteById(1L));
    }
}
