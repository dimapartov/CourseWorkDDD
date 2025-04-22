package org.example.courseworkddd.hexagonal.domain.service;


import org.example.courseworkddd.hexagonal.domain.model.OrderStatus;
import org.example.courseworkddd.hexagonal.domain.model.SupplyOrder;
import org.example.courseworkddd.hexagonal.domain.ports.primary.SupplierOrderUseCase;
import org.example.courseworkddd.hexagonal.domain.ports.secondary.NotificationService;
import org.example.courseworkddd.hexagonal.domain.ports.secondary.SupplyOrderRepository;


public class SupplierOrderUseCaseImpl implements SupplierOrderUseCase {

    private SupplyOrderRepository supplyOrderRepository;
    private NotificationService notificationService;

    public SupplierOrderUseCaseImpl(SupplyOrderRepository supplyOrderRepository, NotificationService notificationService) {
        this.supplyOrderRepository = supplyOrderRepository;
        this.notificationService = notificationService;
    }

    @Override
    public SupplyOrder createOrder(String forecastInfo) {
        String id = String.valueOf(System.currentTimeMillis());
        SupplyOrder order = new SupplyOrder(id, forecastInfo);
        supplyOrderRepository.saveOrder(order);
        return order;
    }

    @Override
    public void sendOrder(String orderId) {
        supplyOrderRepository.findOrderById(orderId).ifPresent(order -> {
            order.setStatus(OrderStatus.SENT);
            supplyOrderRepository.updateOrder(order);
            notificationService.sendNotification(orderId, "Заказ отправлен поставщику");
        });
    }

    @Override
    public void confirmOrder(String orderId) {
        supplyOrderRepository.findOrderById(orderId).ifPresent(order -> {
            order.setStatus(OrderStatus.CONFIRMED);
            supplyOrderRepository.updateOrder(order);
            notificationService.sendNotification(orderId, "Заказ подтверждён поставщиком");
        });
    }

    @Override
    public SupplyOrder getOrderStatus(String orderId) {
        return supplyOrderRepository.findOrderById(orderId).orElse(null);
    }

    @Override
    public void receiveDelivery(String orderId) {
        supplyOrderRepository.findOrderById(orderId).ifPresent(order -> {
            order.setStatus(OrderStatus.RECEIVED);
            supplyOrderRepository.updateOrder(order);
            notificationService.sendNotification(orderId, "Заказ принят");
        });
    }

    @Override
    public void processReturn(String orderId) {
        supplyOrderRepository.findOrderById(orderId).ifPresent(order -> {
            order.setStatus(OrderStatus.RETURNED);
            supplyOrderRepository.updateOrder(order);
            notificationService.sendNotification(orderId, "Возврат выполнен");
        });
    }

}