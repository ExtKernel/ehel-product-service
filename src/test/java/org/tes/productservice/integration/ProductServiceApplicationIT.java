package org.tes.productservice.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;

@Profile("test")
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@SpringBootTest
public class ProductServiceApplicationIT {

    @Test
    void contextLoads() {
    }
}
