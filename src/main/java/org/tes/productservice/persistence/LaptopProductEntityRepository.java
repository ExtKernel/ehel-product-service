package org.tes.productservice.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tes.productservice.model.LaptopProductEntity;

public interface LaptopProductEntityRepository extends JpaRepository<LaptopProductEntity, Long> {
}
