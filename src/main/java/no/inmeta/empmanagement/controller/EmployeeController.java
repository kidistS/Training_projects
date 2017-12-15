package no.inmeta.empmanagement.controller;

import com.github.fge.jsonpatch.JsonPatchException;
import no.inmeta.empmanagement.controller.common.JsonPatcher;
import no.inmeta.empmanagement.controller.common.RestMediaType;
import no.inmeta.empmanagement.model.Employee;
import no.inmeta.empmanagement.model.NameResource;
import no.inmeta.empmanagement.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;


    //      List all Employees
    @RequestMapping(value = "/employees/all")
    public List<Employee> findAll (){

        List <Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }

    //      List Employee by Id
    @RequestMapping("/employees")
    public Employee findById (@RequestParam long id){

        Employee employeeId = employeeRepository.findById(id);
        return employeeId;
    }

    //      List Employees by their first_name
    @RequestMapping("/employeesByFirstName")
    public List<Employee> findByFirstName(@RequestParam String first_name){

        List <Employee> employeeFN = employeeRepository.findByFirstName(first_name);
        return employeeFN;

    }

    //      List Employees by their first_name like %s
    @RequestMapping("/employeesByFirstNameLike")
    public List<Employee> findByFirstNameLike(@RequestParam String first_name){

        List<Employee> employeesFNL = employeeRepository.findByFirstNameLike(first_name);
        return employeesFNL;

    }


    //      List Employees by their last_name
    @RequestMapping("/employeesByLastName")
    public List<Employee> findByLastName(@RequestParam String last_name){

        List<Employee> employeesLN = employeeRepository.findByLastName(last_name);
        return employeesLN;

    }

    //      List employees those are hired before hire_date
    @RequestMapping("/employeesHireDateBefore")
    public List<Employee> findByStartDateBefore(@RequestParam (value = "startDate") Date hire_date ){

        List<Employee> employeesSDB = employeeRepository.findByStartDateBefore(hire_date);
        return employeesSDB;
    }

    //      Create new Employees
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

    //      Update the existing Employees
    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/employeesUpdate", method = RequestMethod.PUT)
    public ResponseEntity<?> update( @RequestParam(value = "id") long emp_no, @RequestBody Employee employeeList){

        try {

            Employee existEmployee = employeeRepository.findOne(emp_no );

            existEmployee.setId( employeeList.getId() );
            existEmployee.setBirthDate( employeeList.getBirthDate());
            existEmployee.setFirstName( employeeList.getFirstName() );
            existEmployee.setLastName(  employeeList.getLastName() );
            existEmployee.setGender( employeeList.getGender() );
            existEmployee.setHireDate( employeeList.getStartDate());

            employeeRepository.save( existEmployee );

            return new ResponseEntity<>(existEmployee, HttpStatus.OK);

        }catch (Exception ex){

            return new ResponseEntity<Employee>( HttpStatus.NOT_FOUND);
        }
    }

    private JsonPatcher jsonPatcher;

    public JsonPatcher getJsonPatcher() {
        return jsonPatcher;
    }

    public void setJsonPatcher(JsonPatcher jsonPatcher) {
        this.jsonPatcher = jsonPatcher;
    }

    //      Update specific entities of an Employee
    @RequestMapping(value = "/updatePartially", method = RequestMethod.PATCH,
            consumes = RestMediaType.APPLICATION_MERGE_PATCH_JSON_VALUE,
            produces = RestMediaType.APPLICATION_PATCH_JSON_VALUE)
    public ResponseEntity<?> updatePartial(@RequestParam(value = "id") long emp_no, @RequestBody String updateResource) {

        Employee existEmployee = employeeRepository.findOne(emp_no );

        try {

            NameResource nameResource = new NameResource();

            nameResource.setId( emp_no );
            nameResource.setFirstName(updateResource );

            existEmployee.setId( nameResource.getId() );
            existEmployee.setFirstName(nameResource.getFirstName());


            Optional<Employee> patched = jsonPatcher.patch( updateResource, existEmployee );
            return new ResponseEntity<>( patched.get() , HttpStatus.OK);

        }catch (RuntimeException ex){
            if(JsonPatchException.class.isAssignableFrom( ex.getCause() .getClass())){
                return  new ResponseEntity<>( existEmployee, HttpStatus.NOT_FOUND );
            }

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
