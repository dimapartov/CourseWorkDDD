package org.example.courseworkddd.layered.application;

import org.example.courseworkddd.layered.domain.InventoryRepository;
import org.example.courseworkddd.layered.domain.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class InventoryService {

    private InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public void addProduct(Product product) {
        repository.save(product);
    }

    public boolean useProduct(String id, int quantity) {
        return repository.findById(id).map(product -> {
            if (product.getQuantity() >= quantity) {
                product.setQuantity(product.getQuantity() - quantity);
                repository.update(product);
                return true;
            }
            return false;
        }).orElse(false);
    }

    public void writeOffExpiredProducts() {
        LocalDate today = LocalDate.now();
        for (Product product : repository.findAll()) {
            if (product.getExpiryDate().isBefore(today)) {
                repository.delete(product);
            }
        }
    }

    public boolean adjustInventory(String id, int newQuantity) {
        return repository.findById(id).map(product -> {
            product.setQuantity(newQuantity);
            repository.update(product);
            return true;
        }).orElse(false);
    }

    public List<Product> generateReport() {
        return repository.findAll();
    }

    public List<Product> getCriticalProducts() {
        return repository.findAll().stream()
                .filter(product -> product.getQuantity() <= product.getCriticalLevel())
                .collect(Collectors.toList());
    }

}