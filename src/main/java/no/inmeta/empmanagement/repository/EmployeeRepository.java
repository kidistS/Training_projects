package no.inmeta.empmanagement.repository;

import no.inmeta.empmanagement.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

   Employee findById(Long id);

   List<Employee> findAll();

   List<Employee> findByFirstName(String first_name);

   List<Employee> findByFirstNameLike(String first_name);

   List<Employee> findByLastName(String last_name);

   List<Employee> findByStartDateBefore(Date hire_date);

    //EmployeeFirstNameOnly partialUpdateName(EmployeeFirstNameOnly firstNameOnly, Long id);

  // ResponseEntity<?> updatePartially(Long id, String newResource);

}
