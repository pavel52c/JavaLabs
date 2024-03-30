package org.example;

import org.example.core.ISerializator;
import org.example.core.ReadCSVFile;
import org.example.core.Serializator;
import org.example.dto.DataStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ReadCSVFile reader = new ReadCSVFile();
        List<DataStorage> data = reader.readDataStorages("data.csv");
        for (DataStorage el : data) {
            el.displayInfo();
        }
        System.out.println("------------------------");
        ISerializator serializator = new Serializator();
        serializator.serialize(data);
        ArrayList<DataStorage> subjects = (ArrayList<DataStorage>) serializator.deserialize();
        System.out.println("Size of new ArrayList: "+subjects.size());
        System.out.println("------------------------");
        for (DataStorage el : subjects) {
            el.displayInfo();
        }
    }
}