package org.tes.productservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tes.productservice.exception.ProductNotFoundException;
import org.tes.productservice.model.ProductEntity;
import org.tes.productservice.service.ProductService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class ProductController {

    private ProductService productService;

    @PostMapping()
    public ResponseEntity<Optional<ProductEntity>> save(@RequestBody ProductEntity product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<ProductEntity>> update(@RequestParam long id, @RequestBody ProductEntity updatedProduct) {
        Optional<ProductEntity> foundProduct = productService.findById(id);

        if (foundProduct.isPresent()) {
            return ResponseEntity.ok(productService.update(updatedProduct));
        }
        else {
            throw new ProductNotFoundException("Product not found " + updatedProduct.getId());
        }
    }

    @GetMapping()
    public ResponseEntity<List<ProductEntity>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductEntity>> findById(@PathVariable long id) {
        Optional<ProductEntity> foundProduct = productService.findById(id);

        if (foundProduct.isPresent()) {
            return ResponseEntity.ok(productService.findById(id));
        }
        else {
            throw new ProductNotFoundException("Product not found " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        Optional<ProductEntity> productToDelete = productService.findById(id);

        if (productToDelete.isPresent()) {
            productService.deleteById(id);

            return ResponseEntity.ok("The product was deleted successfully");
        } else {
            throw new ProductNotFoundException("Product not found " + id);
        }
    }

    @DeleteMapping()
    public ResponseEntity<Boolean> deleteAll() {
        return ResponseEntity.ok(productService.deleteAll());
    }
}
