package com.shahul.rest.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shahul.rest.model.Hospitall;
import com.shahul.rest.service.HospitallService;



@RestController
public class HospitallController {
	@Autowired
	HospitallService hospitallService;
	@PostMapping("/savehospital")
	public ResponseEntity<Hospitall> saveHospital(@RequestBody Hospitall hospitall) {
	   Hospitall saveHsp = hospitallService.saveHospital(hospitall);
	    
	    return ResponseEntity.status(HttpStatus.CREATED)
	    		             .header("sucess","all data is saved")
	    		             .body(saveHsp);
	}

		

	@PostMapping("/saveAll")
	public ResponseEntity<List<Hospitall>> saveAllHospitals(@RequestBody List<Hospitall> hospitall){
		List<Hospitall>hsp=hospitallService.saveAllHospitals(hospitall);
		return  ResponseEntity.status(HttpStatus.CREATED)
	            .header("sucess","all data is saved")
	            .body(hsp);
	}
	@GetMapping("/getById/{id}")
	public ResponseEntity<?>getById(@PathVariable Long id){ 
	Optional<Hospitall>optionalHsp = hospitallService.getById(id);
	if(optionalHsp.isPresent()) {
		return ResponseEntity.status(HttpStatus.OK)
				             .body(optionalHsp);
	}
	else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body("the hsp is not found by this id"+id);
	}
	}
	@GetMapping("/getBySpecialization/{specialization}")
	public ResponseEntity<?> getBySpecialization(@PathVariable String specialization) { 
	    Optional<Hospitall> optionalSpecialization = hospitallService.getBySpecialization(specialization);
	    if (optionalSpecialization.isPresent()) {
	        return ResponseEntity.status(HttpStatus.OK)
	                             .body(optionalSpecialization.get());
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body("The hospital is not found by this specialization: " +specialization);
	    }
	}
	    
	    @DeleteMapping("/deletebyemail/{email}")
	    public ResponseEntity<?> deleteHospitalByEmail(@PathVariable String email) {
	    	boolean status = hospitallService.deleteHospitalByEmail(email);
	        if (status) {
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body("Employee not found with email: " + email);
	        }
	    }
	  
	    @DeleteMapping("/deleteAll")
	    public ResponseEntity<List<Hospitall>> deleteAllEmployees() {
	        List<Hospitall> deleteEmployees = hospitallService.deleteAllHospitals();
	        return ResponseEntity.status(HttpStatus.OK)
	                             .header("Success", "All employees have been deleted")
	                             .body(deleteEmployees);
	    }
	    @PutMapping("/update/{id}")
	    public ResponseEntity<?> updateEmployeeById(@PathVariable Long id, @RequestBody Hospitall newHospital) {

	        Optional<Hospitall> updateHsp = hospitallService.updateHospitalById(id, newHospital);

	        if (updateHsp.isPresent()) {
	            // If the employee is found and updated, return the updated employee with OK status
	            return ResponseEntity.status(HttpStatus.OK).body(updateHsp.get());
	        } else {
	            // If the employee is not found, return NOT_FOUND status
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body("Employee with ID " + id + " not found");
	        }
	    }
	    @PatchMapping("/patch/{id}")
	    public ResponseEntity<?> updateHspById(@PathVariable Long id,@RequestBody Map<String , Object>updates){
	    	Optional<Hospitall> updateHospital = hospitallService.updatePartialData(id,updates);
	    	if(updateHospital.isPresent()) {
	    		return ResponseEntity.status(HttpStatus.OK)
	    				             .body(updateHospital);
	    	}
	    	else {
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			             .body("Hospital with ID"+id+"is not found");
	    	}
	    }
	   
	}


