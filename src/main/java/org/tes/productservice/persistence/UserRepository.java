package org.tes.productservice.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tes.productservice.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
