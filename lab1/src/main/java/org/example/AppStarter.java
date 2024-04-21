package org.example;

import org.example.core.CSVWorker;
import org.example.core.Serializator;
import org.example.interfaces.Subject;

import java.io.IOException;
import java.util.ArrayList;

public class AppStarter {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CSVWorker csvWorker = new CSVWorker("lab1.csv");
        Serializator.serialize(csvWorker.getExams());
        ArrayList<Subject> subjects = (ArrayList<Subject>) Serializator.deserialize();
        System.out.println("Size of new ArrayList: " + subjects.size());
        System.out.println(subjects.getFirst().toString());
    }
}
