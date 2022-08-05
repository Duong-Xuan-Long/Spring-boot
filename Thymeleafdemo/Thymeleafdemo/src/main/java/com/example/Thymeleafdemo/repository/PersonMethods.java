package com.example.Thymeleafdemo.repository;

import com.example.Thymeleafdemo.Model.Person;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class PersonMethods extends PersonAbstract<Person> {
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
    public Person createPerson(String name, String email, String department) {
        Person newp=new Person(name,email,department);
        this.add(newp);
        return newp;
    }

    @Override
    public void add(Person person) {
        int id;
        if(collections.isEmpty()){
            id=1;
        }else{
            int lastIndex=collections.get(collections.size()-1).getId();
            id=lastIndex+1;
        }
        person.setId(id);
        collections.add(person);
    }

    @Override
    public List<Person> getAll() {
        return collections;
    }

    @Override
    public Person update(List<Person> T) {
        return null;
    }

    @Override
    public Person delete(List<Person> T) {
        return null;
    }
}
