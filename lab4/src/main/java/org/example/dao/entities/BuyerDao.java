package org.example.dao.entities;

import org.example.dao.Dao;
import org.example.entities.Buyer;
import org.example.utils.HibernateConnector;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import java.util.List;

public class BuyerDao implements Dao<Buyer> {
    private final EntityManager entityManager = HibernateConnector.entityManager;

    @Override
    public List<Buyer> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM buyer", Buyer.class).getResultList();
    }

    @Override
    public Buyer findById(long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Buyer.class, id);
    }

    @Override
    public void save(Buyer obj) {
        Session session = entityManager.unwrap(Session.class);
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.saveOrUpdate(obj);
        transaction.commit();
    }

    @Override
    public void deleteById(long id) {
        Session session = entityManager.unwrap(Session.class);
        Buyer obj = findById(id);
        session.delete(obj);
    }
}
