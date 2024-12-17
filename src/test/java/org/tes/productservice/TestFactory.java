package org.tes.productservice;

import org.tes.productservice.model.*;

import java.util.Date;

public class TestFactory {

    public CompProduct buildCompProduct() {
        return new CompProduct(
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
                "test-default-product-title",
                "test-default-product-description",
                "test-default-product-condition",
                10,
                1
        );
    }

    public User buildUser() {
        return new User(
                "test-username",
                "test-password",
                "test-firstname",
                "test-lastname",
                "test-email"
        );
    }

    public Cart buildCart() {
//        List<DefaultProduct> defaultProducts = Collections.singletonList(buildDefaultProduct());
//        List<CompProduct> compProducts = Collections.singletonList(buildCompProduct());
//        List<LaptopProduct> laptopProducts = Collections.singletonList(buildLaptopProduct());

        return new Cart(
//                defaultProducts,
//                compProducts,
//                laptopProducts
        );
    }
}
