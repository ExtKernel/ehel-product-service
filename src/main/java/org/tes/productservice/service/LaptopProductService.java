package org.tes.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tes.productservice.model.LaptopProduct;
import org.tes.productservice.persistence.LaptopProductRepository;

@Service
public class LaptopProductService extends GenericCrudService<LaptopProduct, Long> {

    @Autowired
    protected LaptopProductService(LaptopProductRepository repository) {
        super(repository);
    }
}
