package org.example.dao.entities;

import org.example.dao.Dao;
import org.example.entities.SSD;
import org.example.utils.HibernateConnector;

import java.util.List;

public class SSDDAo implements Dao<SSD> {
    private final EntityManager entityManager = HibernateConnector.entityManager;

    @Override
    public List<SSD> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM SSD", SSD.class).getResultList();
    }

    @Override
    public SSD findById(long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(SSD.class, id);
    }

    @Override
    public void save(SSD obj) {
        Session session = entityManager.unwrap(Session.class);
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.saveOrUpdate(obj);
        transaction.commit();
    }

    @Override
    public void deleteById(long id) {
        Session session = entityManager.unwrap(Session.class);
        SSD obj = findById(id);
        session.delete(obj);
    }
}
