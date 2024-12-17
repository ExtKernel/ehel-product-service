package org.tes.productservice.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tes.productservice.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
