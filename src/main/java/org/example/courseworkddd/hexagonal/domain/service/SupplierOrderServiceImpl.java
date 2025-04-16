package org.example.courseworkddd.hexagonal.domain.service;


import org.example.courseworkddd.hexagonal.domain.model.OrderStatus;
import org.example.courseworkddd.hexagonal.domain.model.SupplyOrder;
import org.example.courseworkddd.hexagonal.domain.ports.primary.SupplierOrderUseCase;
import org.example.courseworkddd.hexagonal.domain.ports.secondary.NotificationService;
import org.example.courseworkddd.hexagonal.domain.ports.secondary.SupplyOrderRepository;


public class SupplierOrderServiceImpl implements SupplierOrderUseCase {

    private SupplyOrderRepository repository;
    private NotificationService notificationService;

    public SupplierOrderServiceImpl(SupplyOrderRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    @Override
    public SupplyOrder createOrder(String forecastInfo) {
        String id = "ORD-" + System.currentTimeMillis();
        SupplyOrder order = new SupplyOrder(id, forecastInfo);
        repository.saveOrder(order);
        return order;
    }

    @Override
    public void sendOrder(String orderId) {
        repository.findOrderById(orderId).ifPresent(order -> {
            order.setStatus(OrderStatus.SENT);
            repository.updateOrder(order);
            notificationService.sendNotification(orderId, "Заказ отправлен поставщику");
        });
    }

    @Override
    public void confirmOrder(String orderId) {
        repository.findOrderById(orderId).ifPresent(order -> {
            order.setStatus(OrderStatus.CONFIRMED);
            repository.updateOrder(order);
            notificationService.sendNotification(orderId, "Заказ подтверждён поставщиком");
        });
    }

    @Override
    public SupplyOrder getOrderStatus(String orderId) {
        return repository.findOrderById(orderId).orElse(null);
    }

    @Override
    public void receiveDelivery(String orderId) {
        repository.findOrderById(orderId).ifPresent(order -> {
            order.setStatus(OrderStatus.RECEIVED);
            repository.updateOrder(order);
            notificationService.sendNotification(orderId, "Поставка принята и качество проверено");
        });
    }

    @Override
    public void processReturn(String orderId) {
        repository.findOrderById(orderId).ifPresent(order -> {
            order.setStatus(OrderStatus.RETURNED);
            repository.updateOrder(order);
            notificationService.sendNotification(orderId, "Возврат обработан");
        });
    }

}