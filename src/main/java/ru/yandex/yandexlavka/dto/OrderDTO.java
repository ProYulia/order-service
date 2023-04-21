package ru.yandex.yandexlavka.dto;

import jakarta.persistence.OneToOne;
import ru.yandex.yandexlavka.model.Region;
import ru.yandex.yandexlavka.model.Shift;

import java.util.List;

public class OrderDTO {
    private int orderId;
    private float weight;
    private int region;
    private List<Shift> deliveryHours;
    private int cost;
    private String completeTime;

    public OrderDTO(int orderId, float weight, int region, List<Shift> deliveryHours, int cost, String completeTime) {
        this.orderId = orderId;
        this.weight = weight;
        this.region = region;
        this.deliveryHours = deliveryHours;
        this.cost = cost;
        this.completeTime = completeTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public List<Shift> getDeliveryHours() {
        return deliveryHours;
    }

    public void setDeliveryHours(List<Shift> deliveryHours) {
        this.deliveryHours = deliveryHours;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }
}
