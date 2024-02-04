package org.tes.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.tes.productservice.exception.IsNotInstanceOfKnownEntitiesException;
import org.tes.productservice.exception.NullTokenException;
import org.tes.productservice.model.LaptopProductEntity;
import org.tes.productservice.model.ProductEntity;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Helper methods for unit and integration testing.
 */
public class TestUtils {

    /**
     * Checks given entity for type and calls an appropriate method if found.
     *
     * @param index An index of the entity in json body.
     * @param entity An persistent entity.
     * @return A return of a method, which was called after the checks.
     */
    public static ResultMatcher generateJsonPathExpressions(Integer index, Object entity) {
        if (entity instanceof ProductEntity) {
            return generateJsonPathExpressionsForProductEntity(index, (ProductEntity) entity);
        } else if (entity instanceof LaptopProductEntity) {
            return generateJsonPathExpressionsForLaptopEntity(index, (LaptopProductEntity) entity);
        } else {
            throw new IsNotInstanceOfKnownEntitiesException(
                    entity + " is not an instance of known entities"
            );
        }
    }

    private static ResultMatcher generateJsonPathExpressionsForProductEntity(
            Integer index,
            ProductEntity entity
    ) {
        Objects.requireNonNull(entity, "Template object must not be null");

        String jsonPathPrefix = (index != null) ? "$[" + index + "]" : "$";

        return ResultMatcher.matchAll(
                jsonPath(jsonPathPrefix + ".title").value(entity.getTitle()),
                jsonPath(jsonPathPrefix + ".condition").value(entity.getCondition()),
                jsonPath(jsonPathPrefix + ".price").value(entity.getPrice()),
                jsonPath(jsonPathPrefix + ".quantity").value(entity.getQuantity())
        );
    }

    /**
     * Creates mocked entity of class ProductEntity.
     *
     * @param title A title of the product, which the entity represents.
     * @param condition A description of condition of the product, which the entity represents.
     * @param price A price of the product, which the entity represents.
     * @param quantity A quantity of the product, which the entity represents.
     * @return An object of class ProductEntity.
     */
    public static ProductEntity getMockedProductEntity(
            String title,
            String condition,
            int price,
            int quantity
    ) {
        ProductEntity product = new ProductEntity();
        // Set values for NotNull fields
        product.setTitle(title);
        product.setCondition(condition);
        product.setPrice(price);
        product.setQuantity(quantity);

        return product;
    }

    private static ResultMatcher generateJsonPathExpressionsForLaptopEntity(
            Integer index,
            LaptopProductEntity entity
    ) {
        Objects.requireNonNull(entity, "Template object must not be null");

        String jsonPathPrefix = (index != null) ? "$[" + index + "]" : "$";

        return ResultMatcher.matchAll(
                jsonPath(jsonPathPrefix + ".title").value(entity.getTitle()),
                jsonPath(jsonPathPrefix + ".condition").value(entity.getCondition()),
                jsonPath(jsonPathPrefix + ".price").value(entity.getPrice()),
                jsonPath(jsonPathPrefix + ".quantity").value(entity.getQuantity()),
                jsonPath(jsonPathPrefix + ".cpu").value(entity.getCpu()),
                jsonPath(jsonPathPrefix + ".ramCapacity").value(entity.getRamCapacity()),
                jsonPath(jsonPathPrefix + ".ramType").value(entity.getRamType()),
                jsonPath(jsonPathPrefix + ".gpuType").value(entity.getGpuType()),
                jsonPath(jsonPathPrefix + ".goodFor").value(entity.getGoodFor()),
                jsonPath(jsonPathPrefix + ".releaseDate").exists(),  // Assuming a date format check
                jsonPath(jsonPathPrefix + ".comesWithCharger").value(entity.isComesWithCharger()),
                jsonPath(jsonPathPrefix + ".screenType").value(entity.getScreenType()),
                jsonPath(jsonPathPrefix + ".screenResolution").value(entity.getScreenResolution()),
                jsonPath(jsonPathPrefix + ".hasBacklit").value(entity.isHasBacklit()),
                jsonPath(jsonPathPrefix + ".model").value(entity.getModel())
        );
    }

    /**
     * Creates mocked entity of class LaptopProductEntity.
     *
     * @param title A title of the product, which the entity represents.
     * @param condition A description of condition of the product, which the entity represents.
     * @param price A price of the product, which the entity represents.
     * @param quantity A quantity of the product, which the entity represents.
     * @param cpu CPU model of the product, which the entity represents.
     * @param ramCapacity RAM capacity of the product, which the entity represents.
     * @param ramType RAM type of the product, which the entity represents.
     * @param gpuType GPU type of the product, which the entity represents,
     *               e.g. integrated, discrete.
     * @param goodFor A description for what the product, which the entity represents is good for,
     *               e.g. internet browsing, gaming.
     * @param releaseDate The release date of the product, which the entity represents.
     * @param comesWithCharger Does the product, which the entity represents,
     *                        come with a charger or not.
     * @param screenType Screen panel type of the product, which the entity represents.
     * @param screenResolution Screen resolution of the product, which the entity represents.
     * @param hasBacklit Has the product, which the entity represents backlit keyboard or not.
     * @param model The model of the product, which the entity represents.
     * @return An object of class LaptopProductEntity
     */
    public static LaptopProductEntity getMockedLaptopProductEntity(
            String title,
            String condition,
            int price,
            int quantity,
            String cpu,
            int ramCapacity,
            String ramType,
            String gpuType,
            String goodFor,
            Date releaseDate,
            boolean comesWithCharger,
            String screenType,
            String screenResolution,
            boolean hasBacklit,
            String model
    ) {
        LaptopProductEntity laptopProduct = new LaptopProductEntity();
        // Set values for NotNull fields
        laptopProduct.setTitle(title);
        laptopProduct.setCondition(condition);
        laptopProduct.setPrice(price);
        laptopProduct.setQuantity(quantity);
        laptopProduct.setCpu(cpu);
        laptopProduct.setRamCapacity(ramCapacity);
        laptopProduct.setRamType(ramType);
        laptopProduct.setGpuType(gpuType);
        laptopProduct.setGoodFor(goodFor);
        laptopProduct.setReleaseDate(releaseDate);
        laptopProduct.setComesWithCharger(comesWithCharger);
        laptopProduct.setScreenType(screenType);
        laptopProduct.setScreenResolution(screenResolution);
        laptopProduct.setHasBacklit(hasBacklit);
        laptopProduct.setModel(model);

        return laptopProduct;
    }

    /**
     * This method is designed to get a JWT access token from a Keycloak realm.
     *
     * @param username A username of the user registered in the Keycloak realm or client.
     * @param password A password of the user registered in the Keycloak realm or client.
     * @param tokenEndpointUrl An endpoint for obtaining the token.
     * @return A JWT access token obtained from the Keycloak realm.
     */
    public static String obtainKeycloakAccessToken(
            String username,
            String password,
            String tokenEndpointUrl
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("client_id", "product-service");
        map.add("username", username);
        map.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(
                tokenEndpointUrl,
                request,
                Map.class
        );

        if (response.getBody() != null) {
            return response.getBody().get("access_token").toString();
        } else {
            throw new NullTokenException("Token is null");
        }
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
