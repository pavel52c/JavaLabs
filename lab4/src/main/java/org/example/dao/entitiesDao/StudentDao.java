package org.example.dao.entitiesDao;

import org.example.dao.Dao;
import org.example.entities.Student;
import org.example.utils.HibernateConnector;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements Dao<Student> {


    private final EntityManager entityManager = HibernateConnector.entityManager;

    @Override
    public List<Student> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM Student", Student.class).getResultList();
    }

    @Override
    public Student findById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Student.class, id);
    }

    @Override
    public void save(Student obj) {
        Session session = entityManager.unwrap(Session.class);
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.saveOrUpdate(obj);
        transaction.commit();
    }

    @Override
    public void deleteById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Student obj = findById(id);
        session.delete(obj);
    }

    @Override
    public List<Object[]> union() {
        List<Object[]> resultList = new ArrayList<>();
        Transaction tx = null;
        Session session = entityManager.unwrap(Session.class);

        try {
            tx = session.beginTransaction();
            String sql = "SELECT " +
                    "    s.student_name, " +
                    "    s.number_of_record_book, " +
                    "    subject.subject_name, " +
                    "    c.mark AS credit_mark, " +
                    "    e.mark AS exam_mark " +
                    "FROM " +
                    "    student AS s " +
                    "LEFT JOIN " +
                    "    credit AS c ON c.student_id = s.id " +
                    "LEFT JOIN " +
                    "    subject ON c.subject_id = subject.id " +
                    "LEFT JOIN " +
                    "    exam AS e ON e.subject_id = subject.id;";
            NativeQuery<Object[]> query = session.createNativeQuery(sql);
            resultList = query.getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        for (Object[] items: resultList) {
            System.out.println("==============");
            for (Object insideItem: items) {
                System.out.println(insideItem);
            }
            System.out.println("==============");
        }
        return resultList;
    }
}
