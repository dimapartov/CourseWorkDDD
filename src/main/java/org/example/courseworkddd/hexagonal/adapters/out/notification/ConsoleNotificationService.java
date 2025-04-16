package org.example.courseworkddd.hexagonal.adapters.out.notification;

import org.example.courseworkddd.hexagonal.domain.ports.secondary.NotificationService;


public class ConsoleNotificationService implements NotificationService {

    @Override
    public void sendNotification(String orderId, String message) {
        System.out.println("Уведомление для заказа " + orderId + ": " + message);
    }

}