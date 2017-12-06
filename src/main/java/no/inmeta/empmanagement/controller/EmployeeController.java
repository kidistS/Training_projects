package no.inmeta.empmanagement.controller;

import no.inmeta.empmanagement.model.Employee;
import no.inmeta.empmanagement.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;


@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;


    // Handler for all Employees
    @RequestMapping(value = "/employees/all")
    public List<Employee> findAll (){

        List <Employee> employeeList = employeeRepository.findAll();

        return employeeList;
    }

    // Handler for particular Employee by Id
    @RequestMapping("/employees")
    public Employee findById (@RequestParam long id){

        Employee employee = employeeRepository.findById(id);

        return employee;
    }

    // Handler for particular Employee by first_name
    @RequestMapping("/employeesByFirstName")
    public List<Employee> findByFirstName(@RequestParam String first_name){

        List<Employee> employeeFName= employeeRepository.findByFirstName(first_name);

        return employeeFName;

    }

    // Handler for particular Employee by first_name like %s
    @RequestMapping("/employeesByFirstNameLike")
    public List<Employee> findByFirstNameLike(@RequestParam String first_name){

        List<Employee> employeeFName= employeeRepository.findByFirstNameLike(first_name);

        return employeeFName;

    }


    // Handler for particular Employee by last_name
    @RequestMapping("/employeesByLastName")
    public List<Employee> findByLastName(@RequestParam String last_name){

        List<Employee> employeeLName= employeeRepository.findByLastName(last_name);

        return employeeLName;

    }

    // Handler for an employees who are hired (start)before hire_date
    @RequestMapping("/employeesHireDateBefore")
    public List<Employee> findByStartDateBefore(@RequestParam Date hire_date){

        List<Employee> employeeHDate= employeeRepository.findByStartDateBefore(hire_date);

        return employeeHDate;

    }

}
