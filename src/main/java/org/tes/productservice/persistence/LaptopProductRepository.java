package org.tes.productservice.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tes.productservice.model.LaptopProduct;

public interface LaptopProductRepository extends JpaRepository<LaptopProduct, Long> {
}
