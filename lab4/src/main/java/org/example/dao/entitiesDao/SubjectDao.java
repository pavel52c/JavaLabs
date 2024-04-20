package org.example.dao.entitiesDao;

import org.example.dao.Dao;
import org.example.entities.Subject;
import org.example.utils.HibernateConnector;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import java.util.List;

public class SubjectDao implements Dao<Subject> {

    private final EntityManager entityManager = HibernateConnector.entityManager;

    @Override
    public List<Subject> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM Subject", Subject.class).getResultList();
    }

    @Override
    public Subject findById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Subject.class, id);
    }

    @Override
    public void save(Subject obj) {
        Session session = entityManager.unwrap(Session.class);
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.saveOrUpdate(obj);
        transaction.commit();
    }

    @Override
    public void deleteById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Subject obj = findById(id);
        session.delete(obj);
    }
}
