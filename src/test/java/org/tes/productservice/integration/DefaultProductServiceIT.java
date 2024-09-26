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
import org.tes.productservice.model.DefaultProduct;

@Profile("test")
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class DefaultProductServiceIT extends AbstractIntegrationTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenCorrectDefaultProduct_whenSave_thenReturnSavedDefaultProduct()
            throws Exception {
        DefaultProduct defaultProduct = buildDefaultProduct();

        assertEquals(defaultProduct, performPostRequestExpectedSuccess(
                "/secured/product/default",
                defaultProduct,
                DefaultProduct.class
        ));
    }

    @Test
    public void givenCorrectDefaultProduct_whenUpdate_thenReturnUpdatedDefaultProduct()
            throws Exception {
        DefaultProduct defaultProduct = buildDefaultProduct();

        performPostRequest(
                "/secured/product/default",
                defaultProduct,
                DefaultProduct.class,
                MockMvcResultMatchers.status().isOk()
        );

        defaultProduct.setCondition("new-test-default-title");

        assertEquals(defaultProduct, performPutRequestExpectedSuccess(
                "/secured/product/default",
                defaultProduct,
                DefaultProduct.class
        ));
    }

    @Test
    public void givenCorrectDefaultProducts_whenFindAll_thenReturnDefaultProducts()
            throws Exception {
        DefaultProduct defaultProduct1 = buildDefaultProduct();
        defaultProduct1.setId(1L);
        DefaultProduct defaultProduct2 = buildDefaultProduct();
        defaultProduct2.setId(2L);
        DefaultProduct defaultProduct3 = buildDefaultProduct();
        defaultProduct3.setId(3L);

        List<DefaultProduct> defaultProducts = new ArrayList<>();
        defaultProducts.add(defaultProduct1);
        defaultProducts.add(defaultProduct2);
        defaultProducts.add(defaultProduct3);

        defaultProducts.forEach(defaultProduct -> {
            try {
                performPostRequest(
                        "/secured/product/default",
                        defaultProduct,
                        DefaultProduct.class,
                        MockMvcResultMatchers.status().isOk()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        List<String> expectedJsonStrings = new ArrayList<>();
        defaultProducts.forEach(defaultProduct -> {
            try {
                String jsonString = objectMapper.writeValueAsString(defaultProduct);
                expectedJsonStrings.add(jsonString);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        List<?> actualList = performGetRequestExpectedSuccess(
                "/secured/product/default",
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
