package org.tes.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tes.productservice.model.DefaultProduct;
import org.tes.productservice.service.DefaultProductService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/secured/product/default")
@RestController
public class DefaultProductController {
    private final DefaultProductService service;

    @Autowired
    public DefaultProductController(DefaultProductService service) {
        this.service = service;
    }

    @PostMapping()
    public DefaultProduct save(@RequestBody DefaultProduct product) {
        return service.save(Optional.ofNullable(product));
    }

    @PutMapping()
    public DefaultProduct update(@RequestBody DefaultProduct updatedProduct) {
        return service.update(Optional.ofNullable(updatedProduct));
    }

    @GetMapping()
    public List<DefaultProduct> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DefaultProduct findById(@PathVariable long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }
}
