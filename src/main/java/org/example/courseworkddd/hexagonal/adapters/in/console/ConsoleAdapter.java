package org.example.courseworkddd.hexagonal.adapters.in.console;

import org.example.courseworkddd.hexagonal.adapters.out.notification.ConsoleNotificationService;
import org.example.courseworkddd.hexagonal.adapters.out.persistence.InMemorySupplyOrderRepository;
import org.example.courseworkddd.hexagonal.domain.model.SupplyOrder;
import org.example.courseworkddd.hexagonal.domain.ports.primary.SupplierOrderUseCase;
import org.example.courseworkddd.hexagonal.domain.service.SupplierOrderServiceImpl;

import java.util.Scanner;


public class ConsoleAdapter {

    private SupplierOrderUseCase service;
    private Scanner scanner;

    public ConsoleAdapter() {
        service = new SupplierOrderServiceImpl(new InMemorySupplyOrderRepository(), new ConsoleNotificationService());
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Выберите операцию:");
            System.out.println("1. Создать заказ поставщику");
            System.out.println("2. Отправить заказ поставщику");
            System.out.println("3. Подтвердить заказ поставщику");
            System.out.println("4. Получить статус заказа");
            System.out.println("5. Принять поставку и проверить качество");
            System.out.println("6. Обработать возврат");
            System.out.println("0. Завершить работу");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    createOrder();
                    break;
                case "2":
                    sendOrder();
                    break;
                case "3":
                    confirmOrder();
                    break;
                case "4":
                    getOrderStatus();
                    break;
                case "5":
                    receiveDelivery();
                    break;
                case "6":
                    processReturn();
                    break;
                case "0":
                    System.out.println("Завершение работы");
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    private void createOrder() {
        System.out.println("Введите информацию о прогнозе потребности:");
        String info = scanner.nextLine();
        SupplyOrder order = service.createOrder(info);
        System.out.println("Создан новый заказ:");
        System.out.println(order);
    }

    private void sendOrder() {
        System.out.println("Введите идентификатор заказа:");
        String id = scanner.nextLine();
        service.sendOrder(id);
        System.out.println("Заказ отправлен");
    }

    private void confirmOrder() {
        System.out.println("Введите идентификатор заказа:");
        String id = scanner.nextLine();
        service.confirmOrder(id);
        System.out.println("Заказ подтверждён");
    }

    private void getOrderStatus() {
        System.out.println("Введите идентификатор заказа:");
        String id = scanner.nextLine();
        SupplyOrder order = service.getOrderStatus(id);
        if (order != null) {
            System.out.println("Статус заказа:");
            System.out.println(order);
        } else {
            System.out.println("Заказ не найден");
        }
    }

    private void receiveDelivery() {
        System.out.println("Введите идентификатор заказа:");
        String id = scanner.nextLine();
        service.receiveDelivery(id);
        System.out.println("Поставка принята и качество проверено");
    }

    private void processReturn() {
        System.out.println("Введите идентификатор заказа:");
        String id = scanner.nextLine();
        service.processReturn(id);
        System.out.println("Возврат обработан");
    }

    public static void main(String[] args) {
        new ConsoleAdapter().start();
    }

}