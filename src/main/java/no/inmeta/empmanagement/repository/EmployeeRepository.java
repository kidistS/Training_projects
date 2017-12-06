package no.inmeta.empmanagement.repository;

import no.inmeta.empmanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.sql.Date;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Long> , JpaSpecificationExecutor<Employee>, Repository <Employee, Long>{

   Employee findById(Long id);

   List<Employee> findAll();

   List<Employee> findByFirstName(String first_name);

   List<Employee> findByFirstNameLike(String first_name);

   List<Employee> findByLastName(String last_name);

   List<Employee> findByStartDateBefore(Date hire_date);
}
