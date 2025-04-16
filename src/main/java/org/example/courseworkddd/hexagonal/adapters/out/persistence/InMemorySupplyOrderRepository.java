package org.example.courseworkddd.hexagonal.adapters.out.persistence;

import org.example.courseworkddd.hexagonal.domain.model.SupplyOrder;
import org.example.courseworkddd.hexagonal.domain.ports.secondary.SupplyOrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InMemorySupplyOrderRepository implements SupplyOrderRepository {

    private List<SupplyOrder> orders = new ArrayList<>();

    @Override
    public void saveOrder(SupplyOrder order) {
        orders.add(order);
    }

    @Override
    public void updateOrder(SupplyOrder order) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(order.getId())) {
                orders.set(i, order);
                break;
            }
        }
    }

    @Override
    public Optional<SupplyOrder> findOrderById(String id) {
        return orders.stream().filter(o -> o.getId().equals(id)).findFirst();
    }

    @Override
    public List<SupplyOrder> getAllOrders() {
        return new ArrayList<>(orders);
    }

}