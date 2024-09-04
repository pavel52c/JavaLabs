package org.example;


import org.example.dao.Dao;
import org.example.dao.entitiesDao.ExamDao;
import org.example.dao.entitiesDao.StudentDao;
import org.example.dao.entitiesDao.SubjectDao;
import org.example.entities.Exam;
import org.example.entities.Student;
import org.example.entities.Subject;
import org.example.utils.HibernateUtil;

public class AppStarter {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        HibernateUtil<Exam> examHibernateUtil = new HibernateUtil<Exam>(Exam.class);
        HibernateUtil<Student> studentHibernateUtil = new HibernateUtil<Student>(Student.class);

        System.out.println("Отобразим список экзаменов");
        examHibernateUtil.displayList();
        System.out.println("==================================================================");

        Dao<Student> studentDao = new StudentDao();
        Dao<Subject> subjectDao = new SubjectDao();
        Dao<Exam> examDao = new ExamDao();

        System.out.println("Проверка на findById в subject");
        Subject subject = subjectDao.findById(2);
        System.out.println(subject);
        System.out.println("==================================================================");

        System.out.println("Проверка на findById в student");
        Student student = studentDao.findById(3);
        System.out.println(student);
        System.out.println("==================================================================");

        System.out.println("Создадим экзамен у студента, выведенного выше, и отобразим их");
        Exam exam = new Exam(subject,"отл", student);
        examDao.save(exam);
        examHibernateUtil.displayItem(1);
        int studentId = 3;
        Student studentExample = studentHibernateUtil.getItem(studentId);
        System.out.println(studentExample);
        System.out.println("==================================================================");

        System.out.println("Изменим имя и фамилию студента и отобразим его");
        studentExample.setStudentName("QQQQQQQQQ");
        studentExample.setStudentSurname("LLLLLLLLLL");
        studentHibernateUtil.updateOrSaveItem(studentExample);
        studentHibernateUtil.displayItem(studentId);
        System.out.println("==================================================================");

        System.out.println("Создадим студента и выведем всех, в конце должен появится наш новенький");
        Student newStudent = new Student("GGGGGGGGGG","JJJJJJJJJJ","555555555");
        studentHibernateUtil.updateOrSaveItem(newStudent);
        studentHibernateUtil.displayList();
    }
}
