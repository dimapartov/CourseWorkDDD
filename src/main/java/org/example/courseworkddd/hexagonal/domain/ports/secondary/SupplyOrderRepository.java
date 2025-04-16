package org.example.courseworkddd.hexagonal.domain.ports.secondary;

import org.example.courseworkddd.hexagonal.domain.model.SupplyOrder;

import java.util.Optional;
import java.util.List;


public interface SupplyOrderRepository {

    void saveOrder(SupplyOrder order);

    void updateOrder(SupplyOrder order);

    Optional<SupplyOrder> findOrderById(String id);

    List<SupplyOrder> getAllOrders();

}