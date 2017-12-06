package no.inmeta.empmanagement.dao;

import no.inmeta.empmanagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImp implements EmployeeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Getting particular Employee

    @Override
    public Employee findById (long emp_no){

        String  sql = "SELECT * FROM employees WHERE emp_no = ?";
        Object[] param = new Object[]{emp_no};

        Employee employee= (Employee) jdbcTemplate.queryForObject(sql,param,new EmployeeDaoMapper());

        return employee;
    }

    @Override
    public List<Employee> findByFirstName(String first_name) {

        String sql = "SELECT * FROM employees WHERE first_name =?";
        Object [] param = new Object[]{first_name};

        List<Employee> employees = jdbcTemplate.query(sql,param, new EmployeeDaoMapper());

        return employees;

    }


    @Override
    public List<Employee> findByLastName(String last_name) {

        String sql = "SELECT * FROM employees WHERE last_name =?";
        Object [] param = new Object[]{last_name};

        List<Employee> employees = jdbcTemplate.query(sql,param, new EmployeeDaoMapper());

        return employees;

    }

    // Getting all Employees

    @Override
    public List<Employee> findAll() {

        String sql = "SELECT * FROM employees";

        List<Employee> employees = jdbcTemplate.query(sql, new EmployeeDaoMapper());

       return employees;
    }


}
