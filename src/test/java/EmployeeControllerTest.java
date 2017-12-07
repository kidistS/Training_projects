import no.inmeta.empmanagement.controller.EmployeeController;
import no.inmeta.empmanagement.model.Employee;
import no.inmeta.empmanagement.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class EmployeeControllerTest {

    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeRepository employeeRepository;


    List<Employee> employeeList = new ArrayList<Employee>();
    Employee employee1 = new Employee();
    Employee employee2 = new Employee();
    Employee employee3 = new Employee();
    Employee employee4 = new Employee();

    @Before
    public void init(){

        employee1.setId(10001);
        employee1.setBirthDate( new Date( 1990-10-01) );
        employee1.setFirstName( "Johanna" );
        employee1.setLastName( "Jon" );
        employee1.setGender( "F" );
        employee1.setHireDate(new Date( 2000-12-01 ));

        employee2.setId(10002);
        employee2.setBirthDate(new Date(  1987-01-21) );
        employee2.setFirstName( "Kim" );
        employee2.setLastName( "Jakob" );
        employee2.setGender( "M" );
        employee2.setHireDate(new Date(1967-01-18));

        employee3.setId(10003);
        employee3.setBirthDate(new Date(1970-12-21) );
        employee3.setFirstName( "Tam" );
        employee3.setLastName( "Tommi" );
        employee3.setGender( "F" );
        employee3.setHireDate(new Date( 1996-01-01 ) );

        employee4.setId(10004);
        employee4.setBirthDate(new Date(1977-10-01) );
        employee4.setFirstName( "Tam-like" );
        employee4.setLastName( "Tommi" );
        employee4.setGender( "F" );
        employee4.setHireDate(new Date( 2001-11-21 ) );

        employeeList.add( employee1 );
        employeeList.add( employee2 );
        employeeList.add( employee3 );
        employeeList.add( employee4 );

        MockitoAnnotations.initMocks( this );
    }


    @Test
    public void testFindByFistName(){

        when(employeeRepository.findByFirstName("Johanna")).thenReturn( employeeList );

        List<Employee> employeeFound = employeeController.findByFirstName("Johanna");
        verify( employeeRepository).findByFirstName( "Johanna" );
        assertEquals("Johanna is found", "Johanna", employeeFound.get(0).getFirstName());
    }


    @Test
    public void testFindByFistNameLike(){

        when(employeeRepository.findByFirstNameLike("Tam")).thenReturn( employeeList );

        List<Employee> employeeFound = employeeController.findByFirstNameLike("Tam");
        verify( employeeRepository).findByFirstNameLike( "Tam" );
        assertEquals("Tam-like is found", "Tam-like", employeeFound.get(3).getFirstName());
    }


    @Test
    public void testFindByLastName(){

        when(employeeRepository.findByLastName("Jon")).thenReturn( employeeList );

        List<Employee> employeeFound = employeeController.findByLastName("Jon");
        verify( employeeRepository).findByLastName( "Jon" );
        assertEquals("Jon is found", "Jon", employeeFound.get(0).getLastName());
    }


    @Test
    public void testFindAll(){

        when(employeeRepository.findAll()).thenReturn( employeeList );

        List<Employee> employeeFound = employeeController.findAll();
        verify( employeeRepository).findAll( );
        assertEquals("All Employees are found", employeeFound.size()==3, employeeFound.size()==3);
    }

    @Test
    public void testFindById(){

        when(employeeRepository.findById((long) 10002)).thenReturn(employeeList.get( 1 ));

        Employee employeeFound = employeeController.findById(10002);
        verify( employeeRepository).findById((long) 10002);
        assertEquals("Id No. 10002 is found", (long)10002, employeeFound.getId());

    }

 /*   @Test
    public void testStartDateBefore( ){

        when(employeeRepository.findByStartDateBefore(new Date(2017-01-18))).thenReturn(employeeList);

        List<Employee> employeeFound = employeeController.findByStartDateBefore(new Date( 2017-01-18 ));
        verify( employeeRepository).findByStartDateBefore(new Date( 2017-01-18 ));
        assertEquals("Hire Date after 2017-01-03 is found", employeeFound.size()==1, employeeFound.size()==1);

    }*/


}

