package org.tes.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tes.productservice.exception.ModelIsNullException;
import org.tes.productservice.model.Cart;
import org.tes.productservice.model.CompProduct;
import org.tes.productservice.model.DefaultProduct;
import org.tes.productservice.model.LaptopProduct;
import org.tes.productservice.persistence.CartRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService extends GenericCrudService<Cart, Long> {
    private final CartRepository repository;

    @Autowired
    protected CartService(CartRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Cart save(Optional<Cart> optionalCart) {
        Cart cart = optionalCart.orElseThrow(() -> new ModelIsNullException(
                "The " + optionalCart + " model is null"
        ));

        List<DefaultProduct> defaultProducts = cart.getDefaultProducts();
        if (defaultProducts == null) {
            defaultProducts = new ArrayList<>();
        }

        List<CompProduct> compProducts = cart.getCompProducts();
        if (compProducts == null) {
            compProducts = new ArrayList<>();
        }

        List<LaptopProduct> laptopProducts = cart.getLaptopProducts();
        if (laptopProducts == null) {
            laptopProducts = new ArrayList<>();
        }

        return repository.save(cart);
    }
}
