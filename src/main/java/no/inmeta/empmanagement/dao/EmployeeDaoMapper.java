package no.inmeta.empmanagement.dao;

import no.inmeta.empmanagement.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee(rs.getLong("emp_no"), rs.getDate("birth_date"),rs.getString("first_name"),
                rs.getString("last_name"), rs.getString("gender"), rs.getDate("hire_date"));

    }
}
