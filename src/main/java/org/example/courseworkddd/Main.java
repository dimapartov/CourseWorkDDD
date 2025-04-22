package org.example.courseworkddd;

import org.example.courseworkddd.hexagonal.adapters.in.console.ConsoleAdapter;
import org.example.courseworkddd.layered.application.InventoryService;
import org.example.courseworkddd.layered.infrastructure.InMemoryInventoryRepository;
import org.example.courseworkddd.layered.presentation.ConsoleUI;


public class Main {

    public static void main(String[] args) {
//        Слоистая
/*        InMemoryInventoryRepository inventoryRepository = new InMemoryInventoryRepository();
        InventoryService inventoryService = new InventoryService(inventoryRepository);
        ConsoleUI consoleUI = new ConsoleUI(inventoryService);
        consoleUI.start();*/
//        Гексагональная
        ConsoleAdapter adapter = new ConsoleAdapter();
        adapter.start();
    }

}