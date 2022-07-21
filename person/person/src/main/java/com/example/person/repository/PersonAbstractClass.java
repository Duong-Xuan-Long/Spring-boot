package com.example.person.repository;

import com.example.person.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PersonAbstractClass<T> {
    protected List<T> collections=new ArrayList<>();

    public abstract void readCSV(String csvFile);

    public abstract void add(T t);
    public abstract List<T> getAll();

    public abstract List<T> sortPeopleByFullName(List<T> list);

    public abstract List<T> getSortedJobs(List<T> list);

    public abstract List<T> getSortedCities(List<T> list);

    public abstract HashMap<String,List<T>> groupPeopleByCity(List<T> list);

    public abstract HashMap<String,Integer> groupJobByCount(List<T> list);

    public abstract Map<String,Integer> findTop5Jobs(List<T> list);

    public abstract Map<String,Integer> findTop5Cities(List<T> list);

    public abstract HashMap<String,String> findTopJobInCity(List<T> list,String city);

    public abstract List find5CitiesHaveMostSpecificJob(String job,List<T> list);
}
