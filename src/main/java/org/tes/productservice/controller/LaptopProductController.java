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
import org.tes.productservice.model.LaptopProduct;
import org.tes.productservice.service.LaptopProductService;

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
