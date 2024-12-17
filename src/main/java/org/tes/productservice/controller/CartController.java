package org.tes.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tes.productservice.model.Cart;
import org.tes.productservice.service.CartService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/secured/cart")
@RestController
public class CartController {
    private final CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping()
    public Cart save(@RequestBody Cart cart) {
        return service.save(Optional.ofNullable(cart));
    }

    @PutMapping()
    public Cart update(@RequestBody Cart updatedCart) {
        return service.update(Optional.ofNullable(updatedCart));
    }

    @GetMapping()
    public List<Cart> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Cart findById(@PathVariable long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }
}
