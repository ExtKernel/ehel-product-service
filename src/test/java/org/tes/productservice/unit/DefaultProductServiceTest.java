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
import org.tes.productservice.model.DefaultProduct;
import org.tes.productservice.persistence.DefaultProductRepository;
import org.tes.productservice.service.DefaultProductService;

@ExtendWith(MockitoExtension.class)
public class DefaultProductServiceTest {
    @Mock
    private DefaultProductRepository defaultProductRepository;

    @InjectMocks
    private DefaultProductService defaultProductService;

    @Test
    public void givenCorrectDefaultProduct_whenSave_thenReturnSavedDefaultProduct() {
        DefaultProduct defaultProduct = new DefaultProduct();
        defaultProduct.setId(1L);

        when(defaultProductRepository.save(defaultProduct)).thenReturn(defaultProduct);

        assertEquals(defaultProduct, defaultProductService.save(Optional.of(defaultProduct)));
    }

    @Test
    public void givenSavedDefaultProduct_whenFindByAll_thenReturnSavedDefaultProducts() {
        DefaultProduct defaultProduct = new DefaultProduct();
        defaultProduct.setId(1L);
        List<DefaultProduct> defaultProducts = new ArrayList<>();
        defaultProducts.add(defaultProduct);

        when(defaultProductRepository.findAll()).thenReturn(defaultProducts);

        assertEquals(defaultProducts, defaultProductService.findAll());
    }

    @Test
    public void givenCorrectDefaultProductId_whenFindById_thenReturnSavedDefaultProduct() {
        DefaultProduct defaultProduct = new DefaultProduct();
        defaultProduct.setId(1L);

        when(defaultProductRepository.findById(1L)).thenReturn(Optional.of(defaultProduct));

        assertEquals(defaultProduct, defaultProductService.findById(1L));
    }

    @Test
    public void givenCorrectUpdatedDefaultProduct_whenUpdate_thenReturnUpdatedDefaultProduct() {
        DefaultProduct defaultProduct = new DefaultProduct();
        defaultProduct.setId(1L);

        when(defaultProductRepository.save(defaultProduct)).thenReturn(defaultProduct);

        assertEquals(defaultProduct, defaultProductService.update(Optional.of(defaultProduct)));
    }

    @Test
    public void givenCorrectDefaultProductId_whenDelete_thenDoNotThrowException() {
        DefaultProduct defaultProduct = new DefaultProduct();
        defaultProduct.setId(1L);

        assertDoesNotThrow(() -> defaultProductService.deleteById(1L));
    }
}
