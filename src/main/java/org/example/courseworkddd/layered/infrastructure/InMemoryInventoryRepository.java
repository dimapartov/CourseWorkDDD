package org.example.courseworkddd.layered.infrastructure;

import org.example.courseworkddd.layered.domain.InventoryRepository;
import org.example.courseworkddd.layered.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InMemoryInventoryRepository implements InventoryRepository {

    private List<Product> products = new ArrayList<>();

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public void update(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(product.getId())) {
                products.set(i, product);
                return;
            }
        }
    }

    @Override
    public void delete(Product product) {
        products.removeIf(p -> p.getId().equals(product.getId()));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public Optional<Product> findById(String id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

}