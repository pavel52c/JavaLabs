package org.example.utils;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import org.example.dao.Dao;

import java.util.ArrayList;
import java.util.List;

public class HibernateUtil<T> {
    private final Dao<T> dao;

    public HibernateUtil(Class<?> clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String className = getDaoName(initEntity(clazz));
        Class<?> daoClass = Class.forName(className);
        this.dao = (Dao<T>) daoClass.newInstance();
    }

    private String initEntity(Class<?> theClass) throws IllegalAccessException, InstantiationException {
        return theClass.newInstance().getClass().getName();
    }

    private String getDaoName(String entityName){
        String entitiesDaoPackage = "org.example.dao.entitiesDao";
        ArrayList<String> classesArray = new ArrayList<>();
        try (ScanResult scanResult = new ClassGraph()
                .whitelistPackages(entitiesDaoPackage)
                .scan()) {
            for (ClassInfo classInfo : scanResult.getAllClasses()) {
                String[] classesTmp = classInfo.getName().split("\\.");
                int index = classesTmp.length-1;
                classesArray.add(classesTmp[index]);
            }
        }
        for (String s: classesArray) {
            String[] tmp = entityName.split("\\.");
            int index = tmp.length-1;
            if(s.matches(tmp[index]+".*")){
                return entitiesDaoPackage+"."+s;
            }
        }
        return null;
    }

    public void displayList(){
        List<T> list = dao.findAll();
        for (T item: list) {
            System.out.println(item);
        }
    }

    public void displayItem(int id){
        T item = dao.findById(id);
        System.out.println(item);
    }

    public T getItem(int id){
        return dao.findById(id);
    }

    public void updateOrSaveItem(T obj){
        dao.save(obj);
    }

    public List<Object[]> union() {
        return dao.union();
    }
}
