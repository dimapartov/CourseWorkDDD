package org.example.courseworkddd.hexagonal.domain.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class SupplyOrder {

    private String id;
    private String forecastInfo;
    private OrderStatus status;
    private LocalDate createdDate;

    public SupplyOrder(String id, String forecastInfo) {
        this.id = id;
        this.forecastInfo = forecastInfo;
        this.status = OrderStatus.CREATED;
        this.createdDate = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public String getForecastInfo() {
        return forecastInfo;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "Заказ поставщику {идентификатор='" + id + "', информация о прогнозе='" + forecastInfo + "', статус=" + status + ", дата создания=" + createdDate.format(formatter) + "}";
    }

}