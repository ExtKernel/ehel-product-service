package org.tes.productservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tes.productservice.exception.ProductNotFoundException;
import org.tes.productservice.model.LaptopProductEntity;
import org.tes.productservice.service.LaptopProductService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RequestMapping("/laptop")
@RestController
public class LaptopProductController {

    private LaptopProductService laptopProductService;

    @PostMapping()
    public ResponseEntity<Optional<LaptopProductEntity>> saveLaptop(@RequestBody LaptopProductEntity laptopProduct) {
        return ResponseEntity.ok(laptopProductService.save(laptopProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<LaptopProductEntity>> updateLaptop(@RequestParam long id, @RequestBody LaptopProductEntity updatedLaptopProduct) {
        Optional<LaptopProductEntity> foundLaptopProduct = laptopProductService.findById(id);

        if (foundLaptopProduct.isPresent()) {
            return ResponseEntity.ok(laptopProductService.update(updatedLaptopProduct));
        } else {
            throw new ProductNotFoundException("Laptop product not found with ID: " + updatedLaptopProduct.getId());
        }
    }

    @GetMapping()
    public ResponseEntity<List<LaptopProductEntity>> findAllLaptops() {
        return ResponseEntity.ok(laptopProductService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LaptopProductEntity>> findLaptopById(@PathVariable long id) {
        Optional<LaptopProductEntity> foundLaptopProduct = laptopProductService.findById(id);

        if (foundLaptopProduct.isPresent()) {
            return ResponseEntity.ok(laptopProductService.findById(id));
        } else {
            throw new ProductNotFoundException("Laptop product not found with ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLaptopById(@PathVariable long id) {
        Optional<LaptopProductEntity> laptopProductToDelete = laptopProductService.findById(id);

        if (laptopProductToDelete.isPresent()) {
            laptopProductService.deleteById(id);
            return ResponseEntity.ok("The laptop product was deleted successfully");
        } else {
            throw new ProductNotFoundException("Laptop product not found with ID: " + id);
        }
    }

    @DeleteMapping()
    public ResponseEntity<Boolean> deleteAllLaptops() {
        return ResponseEntity.ok(laptopProductService.deleteAll());
    }
}
