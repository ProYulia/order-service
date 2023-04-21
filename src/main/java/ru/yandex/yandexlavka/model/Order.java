package ru.yandex.yandexlavka.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_weight")
    private float weight;
    @OneToOne
    private Region region;

    @OneToMany
    private List<Shift> deliveryHours;

    @Column(name = "order_cost")
    private int cost;

    @Column(name = "complete_time")
    private String completeTime;

    public Order() {
    }

    public Order(float weight, Region region, List<Shift> deliveryHours, int cost) {
        this.weight = weight;
        this.region = region;
        this.deliveryHours = deliveryHours;
        this.cost = cost;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
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
