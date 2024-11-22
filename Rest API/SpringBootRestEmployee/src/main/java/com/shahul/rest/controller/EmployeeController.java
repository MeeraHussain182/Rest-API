package com.shahul.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shahul.rest.exception.EmployeeNotFoundException;
import com.shahul.rest.models.Employee;
import com.shahul.rest.service.EmployeeService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;  // Autowire the service

    // Save a single employee
   
    @PostMapping("/saveemployee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid Employee employee) {
        Employee savedEmp = employeeService.saveEmployee(employee);  // Call the service method
        return ResponseEntity.status(HttpStatus.CREATED)
                             .header("employee-status", "employee saved successfully")
                             .body(savedEmp);
    }

    // Save a list of employees
    @PostMapping("/saveall")
    public ResponseEntity<List<Employee>> saveAllEmployees(@RequestBody List<Employee> employees) {
        List<Employee> savedEmps = employeeService.saveAllEmployees(employees);  // Call the service method
        return ResponseEntity.status(HttpStatus.CREATED)
                             .header("success", "all employees are saved")
                             .body(savedEmps);
    }

    // Get all employees
    @GetMapping("/getall")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> emps = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK)
                             .header("status", "data reading successfully")
                             .body(emps);
    }

    // Get employee by ID
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Employee> optionalEmp = employeeService.getById(id);
        if (optionalEmp.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(optionalEmp.get());
        } else {
          //  return ResponseEntity.status(HttpStatus.NOT_FOUND)
            //                     .body("The data is not present with ID: " + id);
        	throw new EmployeeNotFoundException("Employee is not present with this id:" +id);
        }
    }

    // Get employee by email
    @GetMapping("/getbyemail/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        Optional<Employee> optionalEmp = employeeService.getByEmail(email);
        if (optionalEmp.isPresent()) {
        	 return ResponseEntity.status(HttpStatus.OK)
                     .body(optionalEmp.get());
        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                                 .body("Employee not found with email: " + email);
        	throw new EmployeeNotFoundException("Employee is not present with this email:" +email);
        } 
    }

    // Delete employee by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id) {
        boolean status = employeeService.deleteEmployeeById(id);
        if (status) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Employee not found with ID: " + id);
        }
    }

    // Delete employee by email
    @DeleteMapping("/deletebyemail/{email}")
    public ResponseEntity<?> deleteEmployeeByEmail(@PathVariable String email) {
    	boolean status = employeeService.deleteEmployeeByEmail(email);
        if (status) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Employee not found with email: " + email);
        }
    }
    
//    @GetMapping("/getAllNames")
//   
//    public List<String> getNames(){
//    	
//    	return employeeService.getnames();
////        return List.of("shahul","hameed","mulla");
//    }

    
    
    
    @DeleteMapping("/deleteAll")
    public ResponseEntity<List<Employee>> deleteAllEmployees() {
        List<Employee> deleteEmployees = employeeService.deleteAllEmployees();
        return ResponseEntity.status(HttpStatus.OK)
                             .header("Success", "All employees have been deleted")
                             .body(deleteEmployees);
    }
//  @DeleteMapping("/deleteall")
//  public ResponseEntity<String> deleteAll() {
//      employeeService.deleteAll();
//      return ResponseEntity.ok("All employees have been deleted successfully.");
//  }
  


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployeeById(@PathVariable Long id, @RequestBody Employee newEmployee) {

        Optional<Employee> updateEmp = employeeService.updateEmployeeById(id, newEmployee);

        if (updateEmp.isPresent()) {
            // If the employee is found and updated, return the updated employee with OK status
            return ResponseEntity.status(HttpStatus.OK).body(updateEmp.get());
        } else {
            // If the employee is not found, return NOT_FOUND status
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Employee with ID " + id + " not found");
        }
    }
    @PatchMapping("/patch/{id}")
    public ResponseEntity<?> updateEmpById(@PathVariable Long id,@RequestBody Map<String , Object>updates){
    	Optional<Employee> updateEmployee = employeeService.updatePartialData(id,updates);
    	if(updateEmployee.isPresent()) {
    		return ResponseEntity.status(HttpStatus.OK)
    				             .body(updateEmployee);
    	}
    	else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
		             .body("Employee with ID"+id+"is not found");
    	}
    }

 
}

        		
        		
        		
        		
        		
        		
        		
        		
        		
        		
        		
        		
