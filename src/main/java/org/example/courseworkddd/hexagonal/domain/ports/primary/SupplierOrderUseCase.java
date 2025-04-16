package org.example.courseworkddd.hexagonal.domain.ports.primary;

import org.example.courseworkddd.hexagonal.domain.model.SupplyOrder;


public interface SupplierOrderUseCase {

    SupplyOrder createOrder(String forecastInfo);

    void sendOrder(String orderId);

    void confirmOrder(String orderId);

    SupplyOrder getOrderStatus(String orderId);

    void receiveDelivery(String orderId);

    void processReturn(String orderId);

}