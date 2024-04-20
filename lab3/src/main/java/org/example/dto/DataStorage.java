package org.example.dto;

import java.io.Serializable;

public abstract class DataStorage implements Serializable {

    private Integer id;
    protected String type;
    protected String model;
    protected Integer weight;
    protected String typeConnection;

    // Buyer params

    protected String buyerName;
    protected String buyerSurname;
    protected String buyerEmail;

    public DataStorage(
            Integer id,
            String type,
            String model,
            Integer weight,
            String typeConnection,
            String buyerName,
            String buyerSurname,
            String buyerEmail
    ) {
        this.id = id;
        this.type = type;
        this.weight = weight;
        this.model = model;
        this.typeConnection = typeConnection;
        this.buyerName = buyerName;
        this.buyerSurname = buyerSurname;
        this.buyerEmail = buyerEmail;
    }

    public void displayInfo() {
        System.out.println("DataStorage type: " + this.type);
        System.out.println("Model: " + model);
        System.out.println("Weight: " + weight);
        System.out.println("TypeConnection: " + typeConnection);
        System.out.println();
    }

    public String getModel() {
        return model;
    }
    public Integer getWeight() {
        return weight;
    }

    public String getTypeConnection() {
        return typeConnection;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerSurname() {
        return buyerSurname;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }
}
