package org.example.courseworkddd.hexagonal.domain.ports.secondary;

import org.example.courseworkddd.hexagonal.domain.model.SupplyOrder;

import java.util.List;
import java.util.Optional;


public interface SupplyOrderRepository {

    void saveOrder(SupplyOrder order);

    void updateOrder(SupplyOrder order);

    Optional<SupplyOrder> findOrderById(String id);

    List<SupplyOrder> getAllOrders();

}