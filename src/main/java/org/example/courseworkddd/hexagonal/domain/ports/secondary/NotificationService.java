package org.example.courseworkddd.hexagonal.domain.ports.secondary;

public interface NotificationService {

    void sendNotification(String orderId, String message);

}