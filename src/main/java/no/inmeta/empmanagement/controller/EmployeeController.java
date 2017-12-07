package no.inmeta.empmanagement.controller;

import no.inmeta.empmanagement.model.Employee;
import no.inmeta.empmanagement.repository.EmployeeRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
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

    // Handler for  Creating new Employees
    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/employeesCreate", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Employee employee){

        try {

            employeeRepository.save( employee );
            return ResponseEntity.ok( employee.getId() );

        }catch (Exception ex){
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR).body( ex.getMessage() );
        }
    }


    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/employeesGet", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable ("emp_no") long emp_no){

        try {

             employeeRepository.findOne(emp_no);
            return ResponseEntity.ok( emp_no);

        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    // Handler for updating an existing Employees
    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/employeesUpdate/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable ("id") String emp_no, @RequestBody Employee employeeList){

        long emp_no1 = Long.valueOf( emp_no );

        try {

            Employee existEmployee = employeeRepository.findOne(emp_no1 );
            BeanUtils.copyProperties(employeeList, existEmployee );
            employeeRepository.save( existEmployee );

            return ResponseEntity.ok( emp_no1 );

        }catch (Exception ex){
           // return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR).body( ex.getMessage() );
            return ResponseEntity.notFound().build();
        }
    }

}
