package org.tes.productservice.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tes.productservice.model.LaptopProductEntity;
import org.tes.productservice.persistence.LaptopProductEntityRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LaptopService {

    private LaptopProductEntityRepository laptopRepository;

    @Transactional
    public Optional<LaptopProductEntity> save(LaptopProductEntity laptopProductEntity) {
        return Optional.of(laptopRepository.save(laptopProductEntity));
    }

    @Transactional
    public Optional<LaptopProductEntity> update(LaptopProductEntity updatedLaptopProductEntity) {
        return Optional.of(laptopRepository.save(updatedLaptopProductEntity));
    }

    @Cacheable("LaptopProductCache")
    public List<LaptopProductEntity> findAll() {
        return laptopRepository.findAll();
    }

    @Cacheable("LaptopProductCache")
    public Optional<LaptopProductEntity> findById(long id) {
        return laptopRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(long id) {
        laptopRepository.deleteById(id);

        return true;
    }

    @Transactional
    public boolean deleteAll() {
        laptopRepository.deleteAll();

        return true;
    }
}
