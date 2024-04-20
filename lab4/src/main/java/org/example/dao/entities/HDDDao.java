package org.example.dao.entities;

import org.example.dao.Dao;
import org.example.entities.HDD;
import org.example.utils.HibernateConnector;

import java.util.List;

public class HDDDao implements Dao<HDD> {
    private final EntityManager entityManager = HibernateConnector.entityManager;

    @Override
    public List<HDD> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM HDD", HDD.class).getResultList();
    }

    @Override
    public HDD findById(long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(HDD.class, id);
    }

    @Override
    public void save(HDD obj) {
        Session session = entityManager.unwrap(Session.class);
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.saveOrUpdate(obj);
        transaction.commit();
    }

    @Override
    public void deleteById(long id) {
        Session session = entityManager.unwrap(Session.class);
        HDD obj = findById(id);
        session.delete(obj);
    }
}
