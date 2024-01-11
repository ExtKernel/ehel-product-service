package org.tes.productservice.integration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.tes.productservice.ProductServiceApplication;
import org.tes.productservice.model.ProductEntity;
import org.tes.productservice.service.ProductService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.tes.productservice.TestUtils.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProductServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@Transactional
public class ProductControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductService productService;

    private static ProductEntity product;

    @BeforeAll
    public static void setUpEntity() {
        String title = "A laptop";
        String condition = "Used";
        int price = 0;
        int quantity = 0;
        product = getMockedProductEntity(title, condition, price, quantity);
    }

    @Test
    public void saveTest() throws Exception {
        mvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(generateJsonPathExpressions(null, product));
    }

// To be implemented
//    @Test
//    public void updateTest() throws Exception {
//
//    }

    @Test
    public void findAllTest() throws Exception {
        productService.save(product);

        mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(generateJsonPathExpressions(0, product));
    }

    @Test
    public void findByIdTest() throws Exception {
        productService.save(product);

        mvc.perform(get("/{id}", product.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(generateJsonPathExpressions(null, product));
    }

    @Test
    public void deleteByIdTest() throws Exception {
        productService.save(product);

        mvc.perform(delete("/{id}", product.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllTest() throws Exception {
        productService.save(product);

        mvc.perform(delete("/"))
                .andExpect(status().isOk());
    }
}
