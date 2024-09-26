package org.tes.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tes.productservice.model.CompProduct;
import org.tes.productservice.persistence.CompProductRepository;

@Service
public class CompProductService extends GenericCrudService<CompProduct, Long> {

    @Autowired
    protected CompProductService(CompProductRepository repository) {
        super(repository);
    }
}
