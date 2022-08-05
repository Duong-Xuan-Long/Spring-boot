package com.heaven.employee.controller;

import com.heaven.employee.model.Employee;
import com.heaven.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public String getAllEmployees(Model model){
        model.addAttribute("employees",employeeRepository.findAll());
        return "list_employees";
    }

    @GetMapping("/addEmployeeForm")
    public String addEmployee(Model model){
        model.addAttribute("employee",new Employee());
        return "employee_form";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee){
        employeeRepository.save(employee);
        return "redirect:/employees";
    }
    @GetMapping("/update")
    public String updateForm(@RequestParam Long employeeId,Model model){
        Employee employee = employeeRepository.findById(employeeId).get();
        model.addAttribute("employee",employee);
        return "employee_form";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam Long employeeId, Model model){
        employeeRepository.deleteById(employeeId);
        return "redirect:/employees";
    }
}
