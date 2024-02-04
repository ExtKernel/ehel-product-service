package org.tes.productservice.integration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${oauthTestAdminUsername}")
    private String oauthTestAdminUsername;

    @Value("${oauthTestAdminPassword}")
    private String oauthTestAdminPassword;

    @Value("${tokenEndpointUrl}")
    private String tokenEndpointUrl;;

    private static ProductEntity product;
    private static String accessToken;

    @BeforeEach
    public void setUpOauth() throws Exception {
        accessToken = obtainKeycloakAccessToken(oauthTestAdminUsername, oauthTestAdminPassword, tokenEndpointUrl);
    }

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
        mvc.perform(post("/generic/secured")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(product))
                        .header("Authorization", "Bearer " + accessToken))
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

        mvc.perform(get("/generic")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(generateJsonPathExpressions(0, product));
    }

    @Test
    public void findByIdTest() throws Exception {
        productService.save(product);

        mvc.perform(get("/generic/{id}", product.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(generateJsonPathExpressions(null, product));
    }

    @Test
    public void deleteByIdTest() throws Exception {
        productService.save(product);

        mvc.perform(delete("/generic/secured/{id}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllTest() throws Exception {
        productService.save(product);

        mvc.perform(delete("/generic/secured")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk());
    }
}
