package com.shahul.rest.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shahul.rest.models.Employee;
import com.shahul.rest.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Save a single employee
    public Employee saveEmployee(Employee employee) {
        // Add any business logic or validation here
        employee = employeeRepository.save(employee);
       return employee;
    }
 // Save a list of employees
    public List<Employee> saveAllEmployees(List<Employee> employees) {
        // Add any business logic or validation here
        employees = employeeRepository.saveAll(employees);
        return employees; 
    }
 public List<Employee> getAllEmployees() {
         return employeeRepository.findAll();
  
 
 }
 public Optional<Employee> getById(Long id) {
 return  employeeRepository.findById(id);
  
 }
 
 public Optional<Employee> getByEmail(String email) {
        return employeeRepository.findByEmail(email);
   
}
 
 public boolean deleteEmployeeById(Long id) {
   boolean exists = employeeRepository.existsById(id);
         if (exists) {
             employeeRepository.deleteById(id);
             return true;
         }
         else {
         return false;

 }


 }

 public boolean deleteEmployeeByEmail(String email) {
        boolean exists = employeeRepository.existsByEmail(email);
        if (exists) {
            employeeRepository.deleteByEmail(email);
            return true;
        }
        
        else {
         return false;

 }
   
 }
 
 
 
 
//  public void deleteAll() {
//         employeeRepository.deleteAll();
//     }
// 
 
 public List<Employee> deleteAllEmployees() {
     List<Employee>employees = employeeRepository.findAll();
      employeeRepository.deleteAll(employees);
      return employees;
 
 
 
 
}
 public Optional<Employee> updateEmployeeById(Long id,Employee newEmployee ) {
  Optional<Employee> optionalEmp=employeeRepository.findById(id);
  if(optionalEmp.isPresent())
  {
   Employee existingEmp=optionalEmp.get();
    existingEmp.setName(newEmployee.getName());
    existingEmp.setSalary(newEmployee.getSalary());
    existingEmp.setDepartment(newEmployee.getDepartment());
    existingEmp.setAdress(newEmployee.getAdress());
    existingEmp.setEmail(newEmployee.getEmail());
    Employee updateEmployee = employeeRepository.save(existingEmp);

    
    return Optional.of(updateEmployee);
  }
  else {
   return Optional.empty();
   
   
  }
 
  
 }
public Optional<Employee> updatePartialData(Long id, Map<String, Object> updates) {
	Optional<Employee> optionalEmployee =	employeeRepository.findById(id);
	if(optionalEmployee.isPresent()) {
		 Employee existingEmployee=optionalEmployee.get();
		 updates.forEach((key,value)->{
			 switch (key) {
			 case "name":
			 existingEmployee.setName((String)value);
			 break;
			 case "salary":
			 existingEmployee.setSalary((Double)value);
			 break;
			 case "department":
			 existingEmployee.setDepartment((String)value);
			 break;
			 case "adress":
			 existingEmployee.setAdress((String)value);
			 break;
			 case "adres":
				 existingEmployee.setEmail((String)value);
				 break; 
				 }
		 });
		 
		Employee updatedEmp = employeeRepository.save(existingEmployee);
		return Optional.of(updatedEmp);
	}
	else {
		return Optional.empty();
	}
}
//@Cacheable("names")
//public List<String> getnames() {
//	System.out.println("fetching the names");
//	 return List.of("shahul","hameed","mulla");
//}
 
}