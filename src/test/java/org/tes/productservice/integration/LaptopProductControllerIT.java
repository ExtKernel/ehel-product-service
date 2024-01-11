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
import org.tes.productservice.model.LaptopProductEntity;
import org.tes.productservice.service.LaptopProductService;

import java.util.Date;

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
public class LaptopProductControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private LaptopProductService laptopProductService;

    private static LaptopProductEntity laptopProduct;

    @BeforeAll
    public static void setUpEntity() {
        String title = "A laptop";
        String condition = "Used";
        int price = 0;
        int quantity = 0;
        String cpu = "Intel Core i5";
        int ramCapacity = 0;
        String ramType = "DDR4";
        String gpuType = "Integrated";
        String goodFor = "Browsing internet";
        Date releaseDate = new Date(1153785600000L);
        boolean comesWithCharger = true;
        String screenType = "IPS";
        String screenResolution = "1920x1080";
        boolean hasBacklit = false;
        String model = "ThinkPad X1";
        laptopProduct = getMockedLaptopProductEntity(title, condition, price, quantity, cpu, ramCapacity, ramType, gpuType, goodFor, releaseDate,
                comesWithCharger, screenType, screenResolution, hasBacklit, model);
    }

    @Test
    public void saveTest() throws Exception {
        mvc.perform(post("/laptop").contentType(MediaType.APPLICATION_JSON).content(asJsonString(laptopProduct)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(generateJsonPathExpressions(null, laptopProduct));
    }

// To be implemented
//    @Test
//    public void updateTest() throws Exception {
//
//    }

    @Test
    public void findAllTest() throws Exception {
        laptopProductService.save(laptopProduct);

        mvc.perform(get("/laptop").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(generateJsonPathExpressions(0, laptopProduct));
    }

    @Test
    public void findByIdTest() throws Exception {
        laptopProductService.save(laptopProduct);

        mvc.perform(get("/laptop/{id}", laptopProduct.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(generateJsonPathExpressions(null, laptopProduct));
    }

    @Test
    public void deleteByIdTest() throws Exception {
        laptopProductService.save(laptopProduct);

        mvc.perform(delete("/laptop/{id}", laptopProduct.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllTest() throws Exception {
        laptopProductService.save(laptopProduct);

        mvc.perform(delete("/laptop"))
                .andExpect(status().isOk());
    }
}
