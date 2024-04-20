package org.example.dto;

public class HDD extends DataStorage {
    public HDD(
            Integer id,
            String type,
            String model,
            Integer weight,
            String typeConnection,
            String buyerName,
            String buyerSurname,
            String buyerEmail
    ) {
        super(id, type, model,weight, typeConnection, buyerName, buyerSurname, buyerEmail);
    }
}
