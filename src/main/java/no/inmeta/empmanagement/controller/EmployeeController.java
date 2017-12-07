package no.inmeta.empmanagement.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import no.inmeta.empmanagement.model.Employee;
import no.inmeta.empmanagement.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.Consumes;
import java.sql.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;


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
/*

    // Handler which Create Employees
    @RequestMapping(value = "/employeesSaved", method = RequestMethod.POST)
    public Employee create(@RequestBody Employee json){

        Employee employee  = employeeRepository.save(json);

        return employee;

    }
*/


    // Handler which Create Employees
    @RequestMapping(value = "/employeesCreate", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {

        if (employeeRepository.exists( employee.getId() )) {
            System.out.println("An Employee with name " + employee.getFirstName() + " already exist");
            return new ResponseEntity<Void>( HttpStatus.CONFLICT);
        }

        employeeRepository.save(employee);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/employeesCreate/{id}").buildAndExpand(employee.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


}
