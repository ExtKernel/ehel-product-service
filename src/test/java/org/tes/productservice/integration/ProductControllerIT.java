package org.tes.productservice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    public void saveTest() throws Exception {
        ProductEntity product1 = new ProductEntity();
        String title = "the title";
        String condition = "used";
        int price = 100;
        int quantity = 0;

        product1.setTitle(title);
        product1.setCondition(condition);
        product1.setPrice(price);
        product1.setQuantity(quantity);

        mvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(product1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(title)))
                .andExpect(jsonPath("$.condition", is(condition)))
                .andExpect(jsonPath("$.price", is(price)))
                .andExpect(jsonPath("$.quantity", is(quantity)));
    }

// To be implemented
//    @Test
//    public void updateTest() throws Exception {
//
//    }

    @Test
    public void findAllTest() throws Exception {
        ProductEntity product1 = new ProductEntity();
        String title = "first product";
        String condition = "used";
        int price = 0;
        int quantity = 0;

        product1.setTitle(title);
        product1.setCondition(condition);
        product1.setPrice(price);
        product1.setQuantity(quantity);
        productService.save(product1);

        ProductEntity product2 = new ProductEntity();
        title = "second product";

        product2.setTitle(title);
        product2.setCondition(condition);
        product2.setPrice(price);
        product2.setQuantity(quantity);
        productService.save(product2);

        mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title", is(product1.getTitle())))
                .andExpect(jsonPath("$[1].title", is(product2.getTitle())));
    }

    @Test
    public void findByIdTest() throws Exception {
        ProductEntity product1 = new ProductEntity();
        String title = "first product";
        String condition = "used";
        int price = 0;
        int quantity = 0;

        product1.setTitle(title);
        product1.setCondition(condition);
        product1.setPrice(price);
        product1.setQuantity(quantity);
        productService.save(product1);

        ProductEntity product2 = new ProductEntity();
        title = "second product";

        product2.setTitle(title);
        product2.setCondition(condition);
        product2.setPrice(price);
        product2.setQuantity(quantity);
        productService.save(product2);

        mvc.perform(get("/{id}", product1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(product1.getTitle())));
        mvc.perform(get("/{id}", product2.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(product2.getTitle())));
    }

    @Test
    public void deleteByIdTest() throws Exception {
        ProductEntity product1 = new ProductEntity();
        String title = "first product";
        String condition = "used";
        int price = 0;
        int quantity = 0;

        product1.setTitle(title);
        product1.setCondition(condition);
        product1.setPrice(price);
        product1.setQuantity(quantity);
        productService.save(product1);

        mvc.perform(delete("/{id}", product1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllTest() throws Exception {
        ProductEntity product1 = new ProductEntity();
        String title = "first product";
        String condition = "used";
        int price = 0;
        int quantity = 0;

        product1.setTitle(title);
        product1.setCondition(condition);
        product1.setPrice(price);
        product1.setQuantity(quantity);
        productService.save(product1);

        ProductEntity product2 = new ProductEntity();
        title = "second product";

        product2.setTitle(title);
        product2.setCondition(condition);
        product2.setPrice(price);
        product2.setQuantity(quantity);
        productService.save(product2);

        mvc.perform(delete("/"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
