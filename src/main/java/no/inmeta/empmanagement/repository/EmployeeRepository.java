package no.inmeta.empmanagement.repository;

import no.inmeta.empmanagement.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

   Employee findById(Long id);

   List<Employee> findAll();

   List<Employee> findByFirstName(String first_name);

   List<Employee> findByFirstNameLike(String first_name);

   List<Employee> findByLastName(String last_name);

   List<Employee> findByStartDateBefore(Date hire_date);

   //String create(Long emp_no, String birth_date, String first_name, String last_name, String gender, Date hire_date);

   //Employee update(Long emp_no, Employee employee);
}
