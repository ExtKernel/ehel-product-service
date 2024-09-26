package org.tes.productservice.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tes.productservice.exception.ModelIsNullException;
import org.tes.productservice.exception.ModelNotFoundException;

/**
 * A generic class that implements the generic behaviour
 * of services that perform CRUD operations.
 *
 * @param <T> the type of objects on which CRUD operations will be performed.
 * @param <ID> the type of the id of the {@link T} objects.
 */
@Transactional(
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRES_NEW
)
public abstract class GenericCrudService<T, ID> implements CrudService<T, ID> {
    private final JpaRepository<T, ID> repository;

    protected GenericCrudService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T save(Optional<T> optionalT) {
        return optionalT.map(repository::save).orElseThrow(() -> new ModelIsNullException(
                "The " + optionalT + " model is null"
        ));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id).orElseThrow(() -> new ModelNotFoundException(
                "A model with id " + id + " was not found"
        ));
    }

    @Override
    public T update(Optional<T> optionalT) {
        return optionalT.map(repository::save).orElseThrow(() -> new ModelIsNullException(
                "The " + optionalT + " model is null"
        ));
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
