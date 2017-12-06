package no.inmeta.empmanagement.dao;

import no.inmeta.empmanagement.model.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(long emp_no);

    List<Employee> findByFirstName(String first_name);

    List<Employee> findByLastName(String last_name);

}
