package org.example.courseworkddd.hexagonal.adapters.out.persistence;

import org.example.courseworkddd.hexagonal.domain.model.Product;
import org.example.courseworkddd.hexagonal.domain.ports.secondary.InventoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InMemoryInventoryRepository implements InventoryRepository {

    private List<Product> products = new ArrayList<>();

    public void save(Product product) {
        products.add(product);
    }

    public void update(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(product.getId())) {
                products.set(i, product);
                return;
            }
        }
    }

    public void delete(Product product) {
        products.removeIf(p -> p.getId().equals(product.getId()));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Optional<Product> findById(String id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

}