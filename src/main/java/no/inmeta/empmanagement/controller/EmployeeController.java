package no.inmeta.empmanagement.controller;

import no.inmeta.empmanagement.model.Employee;
import no.inmeta.empmanagement.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    //@Autowired
    private EmployeeRepository employeeRepository;


    // List all Employees
    @RequestMapping(value = "/all")
    public List<Employee> findAll (){

        //List <Employee> employeeList = employeeRepository.findAll();

        return employeeRepository.findAll();
    }

    // List Employee by Id
    @RequestMapping("{id}")
    public Employee findById (@RequestParam long id){

        //Employee employee = employeeRepository.findById(id);

        return employeeRepository.findById(id);
    }

    //  List Employees by their first_name
    @RequestMapping("/ByFirstName")
    public List<Employee> findByFirstName(@RequestParam String first_name){

       // List<Employee> employeeFName= employeeRepository.findByFirstName(first_name);

        return employeeRepository.findByFirstName(first_name);

    }

    //   List Employees by their first_name like %s
    @RequestMapping("/ByFirstNameLike")
    public List<Employee> findByFirstNameLike(@RequestParam String first_name){

        //List<Employee> employeeFName= employeeRepository.findByFirstNameLike(first_name);

        return employeeRepository.findByFirstNameLike(first_name);

    }


    //      List Employees by their last_name
    @RequestMapping("/ByLastName")
    public List<Employee> findByLastName(@RequestParam String last_name){

        //List<Employee> employeeLName= employeeRepository.findByLastName(last_name);

        return employeeRepository.findByLastName(last_name);

    }

    //     List employees those are hired before hire_date
    @RequestMapping("/HireDateBefore")
    public List<Employee> findByStartDateBefore(@RequestParam Date hire_date){

       // List<Employee> employeeHDate= employeeRepository.findByStartDateBefore(hire_date);

        return employeeRepository.findByStartDateBefore(hire_date);

    }

    //   Creating new Employees
    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/Create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Employee employee){

        try {

            employeeRepository.save( employee );
            return ResponseEntity.ok( employee.getId() );

        }catch (Exception ex){
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR).body( ex.getMessage() );
        }
    }

    //      Updating the existing Employees
    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/Update", method = RequestMethod.PUT)
    public ResponseEntity<?> update( @RequestParam(value = "emp_no") long emp_no, @RequestBody Employee employeeList){

     //   long longId = Long.valueOf( emp_no );

        try {

            Employee existEmployee = employeeRepository.findOne(emp_no );

            existEmployee.setId( employeeList.getId() );
            existEmployee.setBirthDate( employeeList.getBirth_date() );
            existEmployee.setFirstName( employeeList.getFirstName() );
            existEmployee.setLastName(  employeeList.getLastName() );
            existEmployee.setGender( employeeList.getGender() );
            existEmployee.setHireDate( employeeList.getHire_date() );

            employeeRepository.save( existEmployee );

            return new ResponseEntity<>(existEmployee, HttpStatus.OK);

        }catch (Exception ex){

            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }

    }

}
