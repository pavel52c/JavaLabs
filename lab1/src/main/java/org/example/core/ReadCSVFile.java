package org.example.core;

import com.opencsv.CSVReader;
import org.example.dto.DataStorage;
import org.example.dto.HDD;
import org.example.dto.SSD;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSVFile implements IReadSCVFile {
    public List<DataStorage> readDataStorages(String filename) {
        List<DataStorage> result = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(filename));
            String[] line;

            while ((line = reader.readNext()) != null) {
                String[] curObject = line[0].split(";");
                if (curObject.length != 5 || curObject[3] == null) {
                    System.out.println("Невалидная структура объекта: " + Arrays.toString(curObject));
                    continue;
                } else {
                    // HDD
                    if (curObject[3].equals("3.5-inch")) {
                        HDD cur = new HDD("HDD", curObject[0], Integer.parseInt(curObject[1]), Integer.parseInt(curObject[4]));
                        result.add(cur);
                    } else if (curObject[3].equals("2.5-inch") || curObject[3].equals("NVMe")) {
                        SSD cur = new SSD("SSD", curObject[0], Integer.parseInt(curObject[1]), curObject[2]);
                        result.add(cur);
                    } else {
                        System.out.println("Невалидная тип объекта: " + curObject[3]);
                    }
                }
            }
            reader.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
