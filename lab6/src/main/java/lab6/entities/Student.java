package lab6.entities;

public class Student {
    private String name;
    private String surname;
    private String middleName;
    private String subject;
    private String mark;

    public Student(String name, String surname, String middleName, String subject, String mark) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.subject = subject;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", subject='" + subject + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}
