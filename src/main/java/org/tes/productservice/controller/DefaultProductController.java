package org.tes.productservice.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tes.productservice.model.DefaultProduct;
import org.tes.productservice.service.DefaultProductService;

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
