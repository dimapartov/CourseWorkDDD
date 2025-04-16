package org.example.courseworkddd.layered.presentation;

import org.example.courseworkddd.layered.application.InventoryService;
import org.example.courseworkddd.layered.domain.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class ConsoleUI {

    private InventoryService service;
    private Scanner scanner;

    public ConsoleUI(InventoryService service) {
        this.service = service;
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Выберите операцию:");
            System.out.println("1. Добавить новый продукт");
            System.out.println("2. Списать продукт (использование для приготовления)");
            System.out.println("3. Списать просроченные продукты");
            System.out.println("4. Провести инвентаризацию и скорректировать запасы");
            System.out.println("5. Сгенерировать отчёт о текущих запасах");
            System.out.println("6. Показать продукты с критическим уровнем запасов");
            System.out.println("0. Выход");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    useProduct();
                    break;
                case "3":
                    writeOffExpired();
                    break;
                case "4":
                    adjustInventory();
                    break;
                case "5":
                    generateReport();
                    break;
                case "6":
                    showCriticalProducts();
                    break;
                case "0":
                    System.out.println("Завершение работы");
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    private void addProduct() {
        System.out.println("Введите идентификатор продукта:");
        String id = scanner.nextLine();
        System.out.println("Введите название продукта:");
        String name = scanner.nextLine();
        System.out.println("Введите количество:");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите срок годности (дд-мм-гггг):");
        String dateStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate expiryDate = LocalDate.parse(dateStr, formatter);
        System.out.println("Введите минимальный запас:");
        int minStock = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите оптимальный запас:");
        int optimalStock = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите критический уровень:");
        int criticalLevel = Integer.parseInt(scanner.nextLine());
        Product product = new Product(id, name, quantity, expiryDate, minStock, optimalStock, criticalLevel);
        service.addProduct(product);
        System.out.println("Продукт добавлен");
    }

    private void useProduct() {
        System.out.println("Введите идентификатор продукта:");
        String id = scanner.nextLine();
        System.out.println("Введите количество для списания:");
        int quantity = Integer.parseInt(scanner.nextLine());
        boolean result = service.useProduct(id, quantity);
        if (result) {
            System.out.println("Продукт списан");
        } else {
            System.out.println("Ошибка: недостаточно продукта или продукт не найден");
        }
    }

    private void writeOffExpired() {
        service.writeOffExpiredProducts();
        System.out.println("Просроченные продукты списаны");
    }

    private void adjustInventory() {
        System.out.println("Введите идентификатор продукта:");
        String id = scanner.nextLine();
        System.out.println("Введите новое количество:");
        int newQuantity = Integer.parseInt(scanner.nextLine());
        boolean result = service.adjustInventory(id, newQuantity);
        if (result) {
            System.out.println("Запасы скорректированы");
        } else {
            System.out.println("Ошибка: продукт не найден");
        }
    }

    private void generateReport() {
        List<Product> products = service.generateReport();
        if (products.isEmpty()) {
            System.out.println("Инвентаризация пуста");
        } else {
            System.out.println("Отчёт о текущих запасах:");
            products.forEach(System.out::println);
        }
    }

    private void showCriticalProducts() {
        List<Product> products = service.getCriticalProducts();
        if (products.isEmpty()) {
            System.out.println("Нет продуктов с критическим уровнем запасов");
        } else {
            System.out.println("Продукты с критическим уровнем запасов:");
            products.forEach(System.out::println);
        }
    }

}