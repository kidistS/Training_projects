package no.inmeta.empmanagement.model;


public class ResourceBuilder {

    public Employee build(){

        NameResource firstEmployee = new NameResource();
        firstEmployee.setFirstName( "Hanna Tsegaye" );

        Employee employee = new Employee(  );

        employee.setId( 500002 );
        employee.setFirstName(firstEmployee.getFirstName());

        return employee;

    }
}
