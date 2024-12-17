package org.tes.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tes.productservice.model.Cart;
import org.tes.productservice.model.User;
import org.tes.productservice.service.UserService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/secured/user")
@RestController
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping()
    public User save(@RequestBody User user) {
        return service.save(Optional.ofNullable(user));
    }

    @PutMapping()
    public User update(@RequestBody User updatedUser) {
        return service.update(Optional.ofNullable(updatedUser));
    }

    @PutMapping("/{id}/cart")
    public User updateCart(
            @PathVariable Long id,
            @RequestBody Cart cart
    ) {
        return service.updateCart(id, cart);
    }

    @GetMapping()
    public List<User> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }
}
