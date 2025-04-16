package org.example.courseworkddd.hexagonal.domain.ports.secondary;



import org.example.courseworkddd.hexagonal.domain.model.Product;

import java.util.List;
import java.util.Optional;


public interface InventoryRepository {

    void save(Product product);

    void update(Product product);

    void delete(Product product);

    List<Product> findAll();

    Optional<Product> findById(String id);

}