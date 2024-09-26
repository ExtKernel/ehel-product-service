package org.tes.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tes.productservice.model.DefaultProduct;
import org.tes.productservice.persistence.DefaultProductRepository;

@Service
public class DefaultProductService extends GenericCrudService<DefaultProduct, Long> {

    @Autowired
    protected DefaultProductService(DefaultProductRepository repository) {
        super(repository);
    }
}
