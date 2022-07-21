package com.example.person.repository;

import com.example.person.model.Person;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public  class PersonMethods extends PersonAbstractClass<Person> {
    public PersonMethods(String csvFile){
        this.readCSV(csvFile);
    }
    @Override
    public void readCSV(String csvFile) {
        try {
            File file = ResourceUtils.getFile("classpath:static/" + csvFile);
            CsvMapper mapper = new CsvMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
            CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(','); // Dòng đầu tiên sử dụng làm Header
            ObjectReader oReader = mapper.readerFor(Person.class).with(schema); // Cấu hình bộ đọc CSV phù hợp với kiểu
            Reader reader = new FileReader(file);
            MappingIterator<Person> mi = oReader.readValues(reader); // Iterator đọc từng dòng trong file
            while (mi.hasNext()) {
                Person person = mi.next();
                this.add(person);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void add(Person person) {
        int id;
        if (collections.isEmpty()) {
            id = 1;
        } else {
            Person lastPerson = collections.get(collections.size() - 1);
            id =lastPerson.getId() + 1;
        }
        person.setId(id);
        collections.add(person);
    }

    @Override
    public List<Person> getAll() {
        return collections;
    }

    @Override
    public List<Person> sortPeopleByFullName(List<Person> list) {
        list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getFullName().compareTo(o2.getFullName());
            }
        });
        return list;
    }

    @Override
    public List<Person> getSortedJobs(List<Person> list) {
        list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getJob().compareTo(o2.getJob());
            }
        });
        return list;
    }

    @Override
    public List<Person> getSortedCities(List<Person> list) {
        list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getCity().compareTo(o2.getCity());
            }
        });
        return list;
    }

    @Override
    public HashMap<String, List<Person>> groupPeopleByCity(List<Person> list) {
        HashMap<String, List<Person>> map=new HashMap<>();
        for(Person p:list){
            ArrayList<Person> list1= (ArrayList<Person>) map.get(p.getJob());
            if(list1==null) list1=new ArrayList<>();
            list1.add(p);
            map.put(p.getJob(),list1);
        }
        return map;
    }
    @Override
    public HashMap<String, Integer> groupJobByCount(List<Person> list) {
        HashMap<String,Integer> map=new HashMap<>();
        for(Person p:list){
            map.put(p.getJob(),map.getOrDefault(p.getJob(),0)+1);
        }
        return map;
    }

    @Override
    public Map<String, Integer> findTop5Jobs(List<Person> list) {
        HashMap<String,Integer> map=new HashMap<>();
        for(Person p:list){
            map.put(p.getJob(),map.getOrDefault(p.getJob(),0)+1);
        }
        List<Map.Entry<String,Integer>> entry=new ArrayList<>(map.entrySet());
        entry.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });
        HashMap<String,Integer> result_map=new HashMap<>();
        for(int i=0;i<=4;i++){
            result_map.put(entry.get(i).getKey(),entry.get(i).getValue());
        }
        return result_map;
    }

    @Override
    public Map findTop5Cities(List<Person> list) {
        HashMap<String,Integer> map=new HashMap<>();
        for(Person p:list){
            map.put(p.getCity(),map.getOrDefault(p.getCity(),0)+1);
        }
        List<Map.Entry<String,Integer>>entry=new ArrayList<>(map.entrySet());
        entry.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });
        int count=0;
        HashMap<String,Integer> result=new HashMap<>();
        for(Map.Entry<String, Integer> i:entry){
            if(count==5) break;
            result.put(i.getKey(),i.getValue());
            count++;
        }
        return result;
    }

    @Override
    public HashMap<String, String> findTopJobInCity(List<Person> list,String city) {
        List<Person> list1=new ArrayList<>();
        for(Person p:list){
            if(p.getCity().equals(city)){
                list1.add(p);
            }
        }
        String[] strArr=new String[list1.size()];
        int i=0;
        for(Person p:list1){
            strArr[i++]=p.getJob();
        }
        Arrays.sort(strArr);
        int max=Integer.MIN_VALUE;
        int max_count=1;
        String job_max=strArr[0];
        int k=0;
        for(int j=0;j<strArr.length;j++){
            while(k<strArr.length&&j<strArr.length&&strArr[k]==strArr[j]){
                k++;
                max_count++;
            }
            if(max<max_count){
                max=max_count;
                job_max=strArr[k-1];
                j=k;
                max_count=1;
            }
        }
        HashMap<String,String> result=new HashMap<>();
        result.put(city,job_max);
        return result;
    }

    @Override
    public List find5CitiesHaveMostSpecificJob(String job,List<Person> list) {
        List<String> result=new ArrayList<>();
        List<Person> list1=new ArrayList<>();
        for(Person p:list){
            if(p.getCity().equals(job)){
                list1.add(p);
            }
        }
        String[] strArr=new String[list1.size()];
        int i=0;
        for(Person p:list1){
            strArr[i++]=p.getCity();
        }
        HashMap<String,Integer> map=new HashMap<>();
        for(int k=0;k<strArr.length;k++){
            map.put(strArr[k],map.getOrDefault(map.get(strArr[k]),0)+1);
        }

       List< Map.Entry<String,Integer>> entry=new ArrayList<>(map.entrySet());
        entry.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });
        if (entry.size()<5){
            for(int m=0;m<entry.size();m++){
                result.add(entry.get(m).getKey());
            }
        }else{
            int count=0;
            for(int m=0;m<entry.size();m++){
                if(count==5) break;
                result.add(entry.get(m).getKey());
                count++;
            }
        }
        return result;
    }
}
