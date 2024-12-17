package org.tes.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tes.productservice.exception.ModelIsNullException;
import org.tes.productservice.exception.ModelNotFoundException;
import org.tes.productservice.model.Cart;
import org.tes.productservice.model.User;
import org.tes.productservice.persistence.UserRepository;

import java.util.Optional;

@Service
public class UserService extends GenericCrudService<User, Long> {
    private final UserRepository repository;
    private final CartService cartService;

    @Autowired
    protected UserService(
            UserRepository repository,
            CartService cartService
    ) {
        super(repository);
        this.repository = repository;
        this.cartService = cartService;
    }

    @Override
    public User save(Optional<User> optionalUser) {
        User user = optionalUser.orElseThrow(() -> new ModelIsNullException(
                "The " + optionalUser + " model is null"
        ));

        Cart cart = user.getCart();
        if (cart != null) {
            cartService.save(Optional.of(cart));
        }

        return repository.save(user);
    }

    public User updateCart(
            long userId,
            Cart cart
    ) {
        User user = findById(userId);
        user.setCart(cart);

        return repository.save(user);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new ModelNotFoundException(
                "A model with email " + email + " was not found"
        ));
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new ModelNotFoundException(
                "A model with username " + username + " was not found"
        ));
    }
}
