package org.example.dto;

import java.io.Serializable;

public abstract class DataStorage implements Serializable {
    protected String type;
    protected String model;
    protected Integer weight;

    public DataStorage(String type, Integer weight, String model) {
        this.type = type;
        this.weight = weight;
        this.model = model;
    }

    public void displayInfo() {
        System.out.println("DataStorage type: " + this.type);
        System.out.println("Model: " + model);
        System.out.println("Weight: " + weight);
    }
}
