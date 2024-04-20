package org.example.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "DataStorage")
@Table(name = "dataStorage")
public class DataStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "model")
    private String model;

    @Column(name = "weight")
    private int weight;

    @Column(name = "typeConnection")
    private String typeConnection;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dataStorage", cascade = CascadeType.ALL)
    private List<Exam> hdd = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dataStorage",cascade = CascadeType.ALL)
    private List<Credit> ssd = new ArrayList<>();

    public DataStorage(String model, int weight, String typeConnection) {
        this.model = model;
        this.weight = weight;
        this.typeConnection = typeConnection;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "DataStorage {" +
                "id=" + id +
                ", subjectName='" + model + '\'' +
                ", countOfHours=" + weight +
                ", typeConnection" + typeConnection +
                '}';
    }
}
}
