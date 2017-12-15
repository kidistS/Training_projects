/*
package no.inmeta.empmanagement.controller;

import com.github.fge.jsonpatch.JsonPatchException;
import no.inmeta.empmanagement.controller.common.JsonPatcher;
import no.inmeta.empmanagement.controller.common.RestMediaType;
import no.inmeta.empmanagement.model.Employee;
import no.inmeta.empmanagement.model.ResourceBuilder;
import no.inmeta.empmanagement.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

public class JsonPatchController {

    private JsonPatcher jsonPatcher;

    private EmployeeRepository employeeRepository;

    @Autowired
    public JsonPatchController(JsonPatcher jsonPatcher){

        this.jsonPatcher = jsonPatcher;
    }

    //      Update firstName of specific Employee
    @Consumes(MediaType.APPLICATION_JSON)
    @PatchMapping(value = "/updateFirstName")
    public ResponseEntity<?> updatePartially(@PathVariable long id, @RequestBody String updateFirstName){

        Employee newResource = new ResourceBuilder(). build();

        try{

            Optional<Employee> patched = jsonPatcher.patch( updateFirstName, newResource );
            return new ResponseEntity<Object>( patched.get(), HttpStatus.OK );

        }catch (Exception ex){

            if(JsonPatchException.class.isAssignableFrom( ex.getCause().getClass() )){

                return new ResponseEntity<>(newResource,HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }

}

*/