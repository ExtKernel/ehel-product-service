package org.tes.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tes.productservice.model.LaptopProduct;
import org.tes.productservice.service.LaptopProductService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/secured/product/laptop")
@RestController
public class LaptopProductController {
    private final LaptopProductService service;

    @Autowired
    public LaptopProductController(LaptopProductService service) {
        this.service = service;
    }

    @PostMapping()
    public LaptopProduct save(@RequestBody LaptopProduct product) {
        return service.save(Optional.ofNullable(product));
    }

    @PutMapping()
    public LaptopProduct update(@RequestBody LaptopProduct updatedProduct) {
        return service.update(Optional.ofNullable(updatedProduct));
    }

    @GetMapping()
    public List<LaptopProduct> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public LaptopProduct findById(@PathVariable long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }
}
