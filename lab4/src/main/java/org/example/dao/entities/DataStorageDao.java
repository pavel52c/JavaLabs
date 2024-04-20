package org.example.dao.entities;

import org.example.dao.Dao;
import org.example.entities.DataStorage;
import org.example.utils.HibernateConnector;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import java.util.List;


public class DataStorageDao implements Dao<DataStorage> {

    private final EntityManager entityManager = HibernateConnector.entityManager;

    @Override
    public List<DataStorage> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM dataStorage", DataStorage.class).getResultList();
    }

    @Override
    public DataStorage findById(long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(DataStorage.class, id);
    }

    @Override
    public void save(DataStorage obj) {
        Session session = entityManager.unwrap(Session.class);
        session.beginTransaction();
        Transaction transaction = session.getTransaction();
        session.saveOrUpdate(obj);
        transaction.commit();
    }

    @Override
    public void deleteById(long id) {
        Session session = entityManager.unwrap(Session.class);
        DataStorage obj = findById(id);
        session.delete(obj);
    }
}
