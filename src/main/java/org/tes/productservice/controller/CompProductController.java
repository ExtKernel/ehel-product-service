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
import org.tes.productservice.model.CompProduct;
import org.tes.productservice.service.CompProductService;

@RequestMapping("/secured/product/comp")
@RestController
public class CompProductController {
    private final CompProductService service;

    @Autowired
    public CompProductController(CompProductService service) {
        this.service = service;
    }

    @PostMapping()
    public CompProduct save(@RequestBody CompProduct product) {
        return service.save(Optional.ofNullable(product));
    }

    @PutMapping()
    public CompProduct update(@RequestBody CompProduct updatedProduct) {
        return service.update(Optional.ofNullable(updatedProduct));
    }

    @GetMapping()
    public List<CompProduct> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CompProduct findById(@PathVariable long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }
}
