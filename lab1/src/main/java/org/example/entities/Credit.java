package org.example.entities;


import org.example.interfaces.SubjectAbstract;

public class Credit extends SubjectAbstract {

    public Credit(int id, String studentName, String studentSurname, String subjectName, int hours, String mark) {
        super(id, studentName, studentSurname, subjectName, hours, mark);
    }
}
