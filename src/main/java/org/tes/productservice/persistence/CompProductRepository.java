package org.tes.productservice.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tes.productservice.model.CompProduct;

public interface CompProductRepository extends JpaRepository<CompProduct, Long> {
}
