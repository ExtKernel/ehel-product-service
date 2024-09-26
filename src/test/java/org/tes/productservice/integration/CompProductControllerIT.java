package org.tes.productservice.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.tes.productservice.model.CompProduct;

@Profile("test")
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CompProductControllerIT extends AbstractIntegrationTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenCorrectCompProduct_whenSave_thenReturnSavedCompProduct()
            throws Exception {
        CompProduct compProduct = buildCompProduct();

        assertEquals(compProduct, performPostRequestExpectedSuccess(
                "/secured/product/comp",
                compProduct,
                CompProduct.class
        ));
    }

    @Test
    public void givenCorrectCompProduct_whenUpdate_thenReturnUpdatedCompProduct()
            throws Exception {
        CompProduct compProduct = buildCompProduct();

        performPostRequest(
                "/secured/product/comp",
                compProduct,
                CompProduct.class,
                MockMvcResultMatchers.status().isOk()
        );

        compProduct.setCpu("new-test-comp-cpu");

        assertEquals(compProduct, performPutRequestExpectedSuccess(
                "/secured/product/comp",
                compProduct,
                CompProduct.class
        ));
    }

    @Test
    public void givenCorrectCompProducts_whenFindAll_thenReturnCompProducts()
            throws Exception {
        CompProduct compProduct1 = buildCompProduct();
        compProduct1.setId(1L);
        CompProduct compProduct2 = buildCompProduct();
        compProduct2.setId(2L);
        CompProduct compProduct3 = buildCompProduct();
        compProduct3.setId(3L);

        List<CompProduct> compProducts = new ArrayList<>();
        compProducts.add(compProduct1);
        compProducts.add(compProduct2);
        compProducts.add(compProduct3);

        compProducts.forEach(compProduct -> {
            try {
                performPostRequest(
                        "/secured/product/comp",
                        compProduct,
                        CompProduct.class,
                        MockMvcResultMatchers.status().isOk()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        List<String> expectedJsonStrings = new ArrayList<>();
        compProducts.forEach(compProduct -> {
            try {
                String jsonString = objectMapper.writeValueAsString(compProduct);
                expectedJsonStrings.add(jsonString);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        List<?> actualList = performGetRequestExpectedSuccess(
                "/secured/product/comp",
                List.class
        );

        List<String> actualJsonStrings = new ArrayList<>();
        actualList.forEach(item -> {
            try {
                String jsonString = objectMapper.writeValueAsString(item);
                actualJsonStrings.add(jsonString);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        assertEquals(expectedJsonStrings, actualJsonStrings);
    }
}
