package org.example.dao;

import java.util.List;

public interface Dao<T> {
    public List<T> findAll();

    public T findById(int id);

    public void save(T obj);

    public void deleteById(int id);

    public List<Object[]> union();
}
