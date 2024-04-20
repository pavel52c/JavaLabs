package org.example.dto;

public class SSD extends DataStorage {
    public SSD(
            Integer id,
            String type,
            String model,
            Integer weight,
            String typeConnection,
            String buyerName,
            String buyerSurname,
            String buyerEmail
    ) {
        super(id, type,model,weight, typeConnection, buyerName, buyerSurname, buyerEmail);
    }
}
