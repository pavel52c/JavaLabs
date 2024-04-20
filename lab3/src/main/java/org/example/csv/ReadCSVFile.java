package org.example.csv;

import org.example.dto.DataStorage;
import org.example.dto.HDD;
import org.example.dto.SSD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadCSVFile implements IReadSCVFile {
    public ArrayList<DataStorage> readDataStorages(String filename) {
        ArrayList<DataStorage> result = new ArrayList<>();
        String del = ";";
        try {
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] curObject = line.replaceAll(String.valueOf((char) 65279), "").split(del);
                if (curObject.length != 8 || curObject[4] == null) {
                    System.out.println("Невалидная структура объекта: " + Arrays.toString(curObject));
                    continue;
                } else {
                    if (curObject[5] == null && curObject[6] == null && curObject[7] == null){
                        System.out.println("Нет данных о покупателе");
                    }
                    else {
                        // HDD
                        if (curObject[4].equals("3.5-inch")) {
                            HDD cur = new HDD(Integer.parseInt(curObject[0]), "HDD", curObject[1], Integer.parseInt(curObject[2]), curObject[3], curObject[5], curObject[6], curObject[7]);
                            result.add(cur);
                        } else if (curObject[4].equals("2.5-inch") || curObject[4].equals("NVMe")) {
                            SSD cur = new SSD(Integer.parseInt(curObject[0]), "SSD", curObject[1], Integer.parseInt(curObject[2]), curObject[3], curObject[5], curObject[6], curObject[7]);
                            result.add(cur);
                        } else {
                            System.out.println("Невалидная тип объекта: " + curObject[4]);
                        }
                    }
                }
                line = reader.readLine();

            }
            reader.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
