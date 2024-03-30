package org.example.dto;

public class SSD extends DataStorage {
    private final String typeConnection;
    public SSD(String type, String model, Integer weight, String typeConnection) {
        super(type, weight, model);
        this.typeConnection = typeConnection;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("TypeConnection: " + typeConnection);
        System.out.println();
    }
}
