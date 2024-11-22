package com.shahul.rest.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shahul.rest.model.Hospitall;
import com.shahul.rest.repository.HospitallRepository;

@Service
public class HospitallService {
	@Autowired
	HospitallRepository hospitallRepository;

	public Hospitall saveHospital(Hospitall hospital) {
	 hospital=hospitallRepository.save(hospital);
		return hospital;
	}

	public List<Hospitall> saveAllHospitals(List<Hospitall>hospitall) {
	hospitall = hospitallRepository.saveAll(hospitall);
		return hospitall;
	}

	public Optional<Hospitall> getById(Long id) {
		Optional<Hospitall> hsp = hospitallRepository.findById(id);
		return hsp;
	}

	public Optional<Hospitall> getBySpecialization(String specialization) {
		Optional<Hospitall> optionalHsp = hospitallRepository.findBySpecialization(specialization);
		return optionalHsp;
	}
	public List<Hospitall> deleteAllHospitals() {
	    List<Hospitall>hospitall = hospitallRepository.findAll();
	    hospitallRepository.deleteAll(hospitall);
	     return hospitall;
	}
	public boolean deleteHospitalByEmail(String email) {
	    boolean exists = hospitallRepository.existsByEmail(email);
	    if (exists) {
	        hospitallRepository.deleteByEmail(email);
	        return true;
	    }
	    
	    else {
	     return false;

	}

	}

	public Optional<Hospitall> updatePartialData(Long id, Map<String, Object> updates) {
		Optional<Hospitall> optionalHospitall =	hospitallRepository.findById(id);
		if(optionalHospitall.isPresent()) {
			Hospitall existingHospitall=optionalHospitall.get();
			 updates.forEach((key,value)->{
				 switch (key) {
				 case "name":
					 existingHospitall.setName((String)value);
				 break;
				 case "location":
					 existingHospitall.setLocation((String)value);
				 break;
				 case "rating":
					 existingHospitall.setRating((Double)value);
				 break;
				 case "specialization":
					 existingHospitall.setSpecialization((String)value);
				 break;
				 case "email":
					 existingHospitall.setEmail((String)value);
					 break; 
					 }
			 });
			 
			Hospitall updatedHsp = hospitallRepository.save(existingHospitall);
			return Optional.of(updatedHsp);
		}
		else {
			return Optional.empty();
		}
	}

	public Optional<Hospitall> updateHospitalById(Long id, Hospitall newHospitall) {
		 Optional<Hospitall> optionalEmp=hospitallRepository.findById(id);
		  if(optionalEmp.isPresent())
		  {
		   Hospitall existingHsp=optionalEmp.get();
		    existingHsp.setName(newHospitall.getName());
		    existingHsp.setLocation(newHospitall.getLocation());
		    existingHsp.setRating(newHospitall.getRating());
		    existingHsp.setSpecialization(newHospitall.getSpecialization());
		    existingHsp.setEmail(newHospitall.getEmail());
		    Hospitall updateHospital = hospitallRepository.save(existingHsp);

		    
		    return Optional.of(updateHospital);
		  }
		  else {
		   return Optional.empty();
		   

	}
	}
}

