package org.example.core;

import java.io.*;
import java.time.LocalDateTime;

public class Serializator implements ISerializator{
    public void serialize(Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.dat"));
        oos.writeObject(obj);
        System.out.println("Serialize is complete");
    }

    public Object deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.dat"));
        Object obj = ois.readObject();
        System.out.println("Deserialize is complete");
        return obj;
    }
}