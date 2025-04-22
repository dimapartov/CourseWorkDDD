package org.example.courseworkddd.hexagonal.domain.model;


public enum OrderStatus {
    CREATED("Создан"),
    SENT("Отправлен"),
    CONFIRMED("Подтвержден"),
    RECEIVED("Получен"),
    QUALITY_CHECKED("Качество проверено"),
    RETURNED("Возвращен");
    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}