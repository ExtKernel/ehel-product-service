package org.tes.productservice.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.tes.productservice.model.LaptopProduct;

@Profile("test")
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class LaptopProductServiceIT extends AbstractIntegrationTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenCorrectLaptopProduct_whenSave_thenReturnSavedLaptopProduct()
            throws Exception {
        LaptopProduct laptopProduct = buildLaptopProduct();

        assertEquals(laptopProduct, performPostRequestExpectedSuccess(
                "/secured/product/laptop",
                laptopProduct,
                LaptopProduct.class
        ));
    }

    @Test
    public void givenCorrectLaptopProduct_whenUpdate_thenReturnUpdatedLaptopProduct()
            throws Exception {
        LaptopProduct laptopProduct = buildLaptopProduct();

        performPostRequest(
                "/secured/product/laptop",
                laptopProduct,
                LaptopProduct.class,
                MockMvcResultMatchers.status().isOk()
        );

        laptopProduct.setCpu("new-test-laptop-cpu");

        assertEquals(laptopProduct, performPutRequestExpectedSuccess(
                "/secured/product/laptop",
                laptopProduct,
                LaptopProduct.class
        ));
    }

    @Test
    public void givenCorrectLaptopProducts_whenFindAll_thenReturnLaptopProducts()
            throws Exception {
        LaptopProduct laptopProduct1 = buildLaptopProduct();
        laptopProduct1.setId(1L);
        LaptopProduct laptopProduct2 = buildLaptopProduct();
        laptopProduct2.setId(2L);
        LaptopProduct laptopProduct3 = buildLaptopProduct();
        laptopProduct3.setId(3L);

        List<LaptopProduct> laptopProducts = new ArrayList<>();
        laptopProducts.add(laptopProduct1);
        laptopProducts.add(laptopProduct2);
        laptopProducts.add(laptopProduct3);

        laptopProducts.forEach(laptopProduct -> {
            try {
                performPostRequest(
                        "/secured/product/laptop",
                        laptopProduct,
                        LaptopProduct.class,
                        MockMvcResultMatchers.status().isOk()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        List<String> expectedJsonStrings = new ArrayList<>();
        laptopProducts.forEach(laptopProduct -> {
            try {
                String jsonString = objectMapper.writeValueAsString(laptopProduct);
                expectedJsonStrings.add(jsonString);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        List<?> actualList = performGetRequestExpectedSuccess(
                "/secured/product/laptop",
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
