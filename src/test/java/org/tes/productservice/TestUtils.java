package org.tes.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultMatcher;
import org.tes.productservice.exception.IsNotInstanceOfKnownEntitiesException;
import org.tes.productservice.model.LaptopProductEntity;
import org.tes.productservice.model.ProductEntity;

import java.util.Date;
import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class TestUtils {

    public static ResultMatcher generateJsonPathExpressions(Integer index, Object entity) {
        if (entity instanceof ProductEntity) {
            return generateJsonPathExpressionsForProductEntity(index, (ProductEntity) entity);
        }
        else if (entity instanceof LaptopProductEntity) {
            return generateJsonPathExpressionsForLaptopEntity(index, (LaptopProductEntity) entity);
        }
        else {
            throw new IsNotInstanceOfKnownEntitiesException(entity + " is not an instance of known entities");
        }
    }

    private static ResultMatcher generateJsonPathExpressionsForProductEntity(Integer index, ProductEntity entity) {
        Objects.requireNonNull(entity, "Template object must not be null");

        String jsonPathPrefix = (index != null) ? "$[" + index + "]" : "$";

        return ResultMatcher.matchAll(
                jsonPath(jsonPathPrefix + ".title").value(entity.getTitle()),
                jsonPath(jsonPathPrefix + ".condition").value(entity.getCondition()),
                jsonPath(jsonPathPrefix + ".price").value(entity.getPrice()),
                jsonPath(jsonPathPrefix + ".quantity").value(entity.getQuantity())
        );
    }

    public static ProductEntity getMockedProductEntity(String title, String condition, int price, int quantity) {
        ProductEntity product = new ProductEntity();
        // Set values for NotNull fields
        product.setTitle(title);
        product.setCondition(condition);
        product.setPrice(price);
        product.setQuantity(quantity);

        return product;
    }

    private static ResultMatcher generateJsonPathExpressionsForLaptopEntity(Integer index, LaptopProductEntity entity) {
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

    public static LaptopProductEntity getMockedLaptopProductEntity(String title, String condition, int price, int quantity, String cpu, int ramCapacity, String ramType,
                                                                      String gpuType, String goodFor, Date releaseDate, boolean comesWithCharger, String screenType, String screenResolution,
                                                                      boolean hasBacklit, String model) {
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

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
