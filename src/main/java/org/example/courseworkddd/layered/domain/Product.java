package org.example.courseworkddd.layered.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Product {

    private String id;
    private String name;
    private int quantity;
    private LocalDate expiryDate;
    private int minimumStock;
    private int optimalStock;
    private int criticalLevel;

    public Product(String id, String name, int quantity, LocalDate expiryDate, int minimumStock, int optimalStock, int criticalLevel) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.minimumStock = minimumStock;
        this.optimalStock = optimalStock;
        this.criticalLevel = criticalLevel;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public int getMinimumStock() {
        return minimumStock;
    }

    public int getOptimalStock() {
        return optimalStock;
    }

    public int getCriticalLevel() {
        return criticalLevel;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMinimumStock(int minimumStock) {
        this.minimumStock = minimumStock;
    }

    public void setOptimalStock(int optimalStock) {
        this.optimalStock = optimalStock;
    }

    public void setCriticalLevel(int criticalLevel) {
        this.criticalLevel = criticalLevel;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "Продукт{id='" + id + "', название='" + name + "', количество=" + quantity +
                ", срок годности=" + expiryDate.format(formatter) +
                ", мин. запас=" + minimumStock + ", оптимальный запас=" + optimalStock +
                ", критический уровень=" + criticalLevel + "}";
    }

}