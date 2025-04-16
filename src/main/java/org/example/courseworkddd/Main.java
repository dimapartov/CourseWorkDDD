package org.example.courseworkddd;

import org.example.courseworkddd.layered.application.InventoryService;
import org.example.courseworkddd.layered.domain.Product;
import org.example.courseworkddd.layered.infrastructure.InMemoryInventoryRepository;
import org.example.courseworkddd.layered.presentation.ConsoleUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Main {

    public static void main(String[] args) {
        InMemoryInventoryRepository repository = new InMemoryInventoryRepository();
        InventoryService inventoryService = new InventoryService(repository);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        inventoryService.addProduct(new Product("P001", "Мясо", 100, LocalDate.parse("15-10-2025", formatter), 10, 50, 5));
        inventoryService.addProduct(new Product("P002", "Овощи", 200, LocalDate.parse("20-11-2025", formatter), 20, 100, 15));
        inventoryService.addProduct(new Product("P003", "Соус", 50, LocalDate.parse("01-09-2025", formatter), 5, 30, 3));
        ConsoleUI ui = new ConsoleUI(inventoryService);
        ui.start();
    }

}