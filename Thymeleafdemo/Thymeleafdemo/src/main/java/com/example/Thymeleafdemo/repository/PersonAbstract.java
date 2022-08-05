package com.example.Thymeleafdemo.repository;

import java.util.ArrayList;
import java.util.List;

public abstract class PersonAbstract<T> {
    protected List<T> collections=new ArrayList<>();

    public abstract void readCSV(String csvFile);

    public abstract T createPerson(String name,String email,String department);

    public abstract void add(T t);

    public abstract List<T> getAll();

    public abstract T update(List<T> T);

    public abstract T delete(List<T> T);
}
