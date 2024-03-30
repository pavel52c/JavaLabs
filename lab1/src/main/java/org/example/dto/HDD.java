package org.example.dto;

public class HDD extends DataStorage {
    private final Integer cost;
    public HDD(String type, String model, Integer weight, Integer cost) {
        super(type, weight, model);
        this.cost = cost;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Cost: " + cost);
        System.out.println();
    }
}
