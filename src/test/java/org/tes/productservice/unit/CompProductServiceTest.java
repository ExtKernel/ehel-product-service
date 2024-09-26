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
import org.tes.productservice.model.CompProduct;
import org.tes.productservice.persistence.CompProductRepository;
import org.tes.productservice.service.CompProductService;

@ExtendWith(MockitoExtension.class)
public class CompProductServiceTest {
    @Mock
    private CompProductRepository compProductRepository;

    @InjectMocks
    private CompProductService compProductService;

    @Test
    public void givenCorrectCompProduct_whenSave_thenReturnSavedCompProduct() {
        CompProduct compProduct = new CompProduct();
        compProduct.setId(1L);

        when(compProductRepository.save(compProduct)).thenReturn(compProduct);

        assertEquals(compProduct, compProductService.save(Optional.of(compProduct)));
    }

    @Test
    public void givenSavedCompProduct_whenFindByAll_thenReturnSavedCompProducts() {
        CompProduct compProduct = new CompProduct();
        compProduct.setId(1L);
        List<CompProduct> compProducts = new ArrayList<>();
        compProducts.add(compProduct);

        when(compProductRepository.findAll()).thenReturn(compProducts);

        assertEquals(compProducts, compProductService.findAll());
    }

    @Test
    public void givenCorrectCompProductId_whenFindById_thenReturnSavedCompProduct() {
        CompProduct compProduct = new CompProduct();
        compProduct.setId(1L);

        when(compProductRepository.findById(1L)).thenReturn(Optional.of(compProduct));

        assertEquals(compProduct, compProductService.findById(1L));
    }

    @Test
    public void givenCorrectUpdatedCompProduct_whenUpdate_thenReturnUpdatedCompProduct() {
        CompProduct compProduct = new CompProduct();
        compProduct.setId(1L);

        when(compProductRepository.save(compProduct)).thenReturn(compProduct);

        assertEquals(compProduct, compProductService.update(Optional.of(compProduct)));
    }

    @Test
    public void givenCorrectCompProductId_whenDelete_thenDoNotThrowException() {
        CompProduct compProduct = new CompProduct();
        compProduct.setId(1L);

        assertDoesNotThrow(() -> compProductService.deleteById(1L));
    }
}
