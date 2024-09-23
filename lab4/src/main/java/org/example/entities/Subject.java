package org.example.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Subject")
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "count_of_hours")
    private int countOfHours;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Exam> exam = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject",cascade = CascadeType.ALL)
    private List<Credit> credit = new ArrayList<>();

    public Subject(String subjectName, int countOfHours) {
        this.subjectName = subjectName;
        this.countOfHours = countOfHours;
    }

    public Subject() {

     }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCountOfHours() {
        return countOfHours;
    }

    public void setCountOfHours(int countOfHours) {
        this.countOfHours = countOfHours;
    }

    @Override
    public String toString() {
        return "Subject { " +
                "id = " + id +
                ", subjectName = '" + subjectName + '\'' +
                ", countOfHours = " + countOfHours +
                ", passedTimes = " + exam.size() +
                " }";
    }
}
