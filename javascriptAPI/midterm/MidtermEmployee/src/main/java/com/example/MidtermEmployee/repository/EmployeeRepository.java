package com.example.MidtermEmployee.repository;

import com.example.MidtermEmployee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    List<Employee> findByEmailAddressAndLastName(String emailAddress, String lastName);

    List<Employee> findDistinctByFirstNameOrLastName(String firstName, String lastName);

    List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);

    List<Employee> findByFirstNameAllIgnoreCase(String firstName);



}
