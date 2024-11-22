package com.shahul.rest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @NotNull(message = "ID cannot be null")
 private Long id;
 @NotBlank(message = "Name cannot be empty")
 @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
 private String name;
 @NotNull(message = "Salary cannot be null")
 @Positive(message = "Salary must be a positive value")
 private Double salary;
 @NotBlank(message = "Department cannot be empty")
 @Size(min = 1, max = 50, message = "Department must be between 1 and 50 characters")
 private String department;
 @NotBlank(message = "Address cannot be empty")
 @Size(max = 255, message = "Address must be at most 255 characters")
 private String adress;
 @Email(message = "Email should be valid")
 @Size(max = 100, message = "Email must be at most 100 characters")
 private String email;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Double getSalary() {
	return salary;
}
public void setSalary(Double salary) {
	this.salary = salary;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getAdress() {
	return adress;
}
public void setAdress(String adress) {
	this.adress = adress;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
@Override
public String toString() {
	return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", department=" + department + ", adress="
			+ adress + ", email=" + email + "]";
}
 
 

}