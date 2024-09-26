package org.tes.productservice.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tes.productservice.model.DefaultProduct;

public interface DefaultProductRepository extends JpaRepository<DefaultProduct, Long> {
}
