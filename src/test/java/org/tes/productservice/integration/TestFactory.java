package org.tes.productservice.integration;

import java.util.Date;
import org.tes.productservice.model.CompProduct;
import org.tes.productservice.model.DefaultProduct;
import org.tes.productservice.model.LaptopProduct;

public class TestFactory {

    public CompProduct buildCompProduct() {
        return new CompProduct(
                1L,
                "test-comp-product-title",
                "test-comp-product-description",
                "test-comp-product-condition",
                10,
                1,
                "test-comp-cpu",
                1024,
                "test-comp-ram-type",
                "test-comp-gpu-type",
                "test-comp-good-for"
        );
    }

    public LaptopProduct buildLaptopProduct() {
        return new LaptopProduct(
                1L,
                "test-laptop-product-title",
                "test-laptop-product-description",
                "test-laptop-product-condition",
                10,
                1,
                "test-laptop-cpu",
                1024,
                "test-laptop-ram-type",
                "test-laptop-gpu-type",
                "test-laptop-good-for",
                new Date(),
                true,
                "test-laptop-screen-type",
                "test-laptop-screen-resolution",
                true,
                "test-laptop-model"
        );
    }

    public DefaultProduct buildDefaultProduct() {
        return new DefaultProduct(
                1L,
                "test-default-product-title",
                "test-default-product-description",
                "test-default-product-condition",
                10,
                1
        );
    }
}
