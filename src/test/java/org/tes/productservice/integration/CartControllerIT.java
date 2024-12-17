package org.tes.productservice.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.tes.productservice.model.Cart;
import org.tes.productservice.model.CompProduct;
import org.tes.productservice.model.DefaultProduct;
import org.tes.productservice.model.LaptopProduct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@Profile("test")
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CartControllerIT extends AbstractIntegrationTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenCorrectCart_whenSave_thenReturnSavedCart()
            throws Exception {
        Cart cart = buildCart();
        cart.setId(1L);

        assertEquals(cart, performPostRequestExpectedSuccess(
                "/secured/cart",
                cart,
                Cart.class
        ));
    }

    @Test
    public void givenCorrectCart_whenUpdate_thenReturnUpdatedCart()
            throws Exception {
        Cart cart = buildCart();
        cart.setId(1L);

        performPostRequest(
                "/secured/cart",
                cart,
                Cart.class,
                MockMvcResultMatchers.status().isOk()
        );

        // set new, update
        DefaultProduct defaultProduct = buildDefaultProduct();
        defaultProduct.setId(1L);
        List<DefaultProduct> defaultProducts = Collections.singletonList(defaultProduct);
        cart.setDefaultProducts(defaultProducts);

        assertEquals(cart.toString(), performPutRequestExpectedSuccess(
                "/secured/cart",
                cart,
                Cart.class
        ).toString());
    }

    @Test
    public void givenCorrectCarts_whenFindAll_thenReturnCarts()
            throws Exception {
        DefaultProduct defaultProduct1 = buildDefaultProduct();
        defaultProduct1.setId(1L);
        DefaultProduct defaultProduct2 = buildDefaultProduct();
        defaultProduct2.setId(2L);
        DefaultProduct defaultProduct3 = buildDefaultProduct();
        defaultProduct3.setId(3L);
        // save
        for (DefaultProduct product : Arrays.asList(defaultProduct1, defaultProduct2, defaultProduct3)) {
            performPostRequest(
                    "/secured/product/default",
                    product,
                    DefaultProduct.class,
                    MockMvcResultMatchers.status().isOk()
            );
        }

        LaptopProduct laptopProduct1 = buildLaptopProduct();
        laptopProduct1.setId(1L);
        LaptopProduct laptopProduct2 = buildLaptopProduct();
        laptopProduct2.setId(2L);
        LaptopProduct laptopProduct3 = buildLaptopProduct();
        laptopProduct3.setId(3L);
        // save
        for (LaptopProduct product : Arrays.asList(laptopProduct1, laptopProduct2, laptopProduct3)) {
            performPostRequest(
                    "/secured/product/laptop",
                    product,
                    LaptopProduct.class,
                    MockMvcResultMatchers.status().isOk()
            );
        }

        CompProduct compProduct1 = buildCompProduct();
        compProduct1.setId(4L);
        CompProduct compProduct2 = buildCompProduct();
        compProduct2.setId(5L);
        CompProduct compProduct3 = buildCompProduct();
        compProduct3.setId(6L);
        // save
        for (CompProduct product : Arrays.asList(compProduct1, compProduct2, compProduct3)) {
            performPostRequest(
                    "/secured/product/comp",
                    product,
                    CompProduct.class,
                    MockMvcResultMatchers.status().isOk()
            );
        }

        Cart cart1 = buildCart();
        cart1.setId(1L);
        cart1.setDefaultProducts(new ArrayList<>(Arrays.asList(defaultProduct1)));
        cart1.setCompProducts(new ArrayList<>(Arrays.asList(compProduct1)));
        cart1.setLaptopProducts(new ArrayList<>(Arrays.asList(laptopProduct1)));
        Cart cart2 = buildCart();
        cart2.setId(2L);
        cart2.setDefaultProducts(new ArrayList<>(Arrays.asList(defaultProduct2)));
        cart2.setCompProducts(new ArrayList<>(Arrays.asList(compProduct2)));
        cart2.setLaptopProducts(new ArrayList<>(Arrays.asList(laptopProduct2)));
        Cart cart3 = buildCart();
        cart3.setId(3L);
        cart3.setDefaultProducts(new ArrayList<>(Arrays.asList(defaultProduct3)));
        cart3.setCompProducts(new ArrayList<>(Arrays.asList(compProduct3)));
        cart3.setLaptopProducts(new ArrayList<>(Arrays.asList(laptopProduct3)));

        List<Cart> carts = Arrays.asList(cart1, cart2, cart3);
        // save
        carts.forEach(cart -> {
            try {
                performPostRequest(
                        "/secured/cart",
                        cart,
                        Cart.class,
                        MockMvcResultMatchers.status().isOk()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // JPA Repository will retrieve LaptopProduct object along with CompProduct objects due to inheritance
        cart1.getCompProducts().add(laptopProduct1);
        cart2.getCompProducts().add(laptopProduct2);
        cart3.getCompProducts().add(laptopProduct3);

        List<String> expectedJsonStrings = new ArrayList<>();
        carts.forEach(cart -> {
            try {
                objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
                String jsonString = objectMapper.writeValueAsString(cart);
                expectedJsonStrings.add(jsonString);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        List<?> actualList = performGetRequestExpectedSuccess(
                "/secured/cart",
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

    @Test
    public void givenCorrectCart_whenFindById_thenReturnCart()
            throws Exception {
        DefaultProduct defaultProduct1 = buildDefaultProduct();
        defaultProduct1.setId(1L);
        performPostRequest(
                "/secured/product/default",
                defaultProduct1,
                DefaultProduct.class,
                MockMvcResultMatchers.status().isOk()
        );

        LaptopProduct laptopProduct1 = buildLaptopProduct();
        laptopProduct1.setId(2L);
        performPostRequest(
                "/secured/product/laptop",
                laptopProduct1,
                LaptopProduct.class,
                MockMvcResultMatchers.status().isOk()
        );

        CompProduct compProduct1 = buildCompProduct();
        compProduct1.setId(3L);
        performPostRequest(
                "/secured/product/comp",
                compProduct1,
                CompProduct.class,
                MockMvcResultMatchers.status().isOk()
        );

        Cart cart1 = buildCart();
        cart1.setId(1L);
        cart1.setDefaultProducts(Collections.singletonList(defaultProduct1));
        cart1.setCompProducts(Collections.singletonList(compProduct1));
        cart1.setLaptopProducts(Collections.singletonList(laptopProduct1));

        assertEquals(cart1.toString(), performPostRequest(
                "/secured/cart",
                cart1,
                Cart.class,
                MockMvcResultMatchers.status().isOk()
        ).toString());
    }
}
