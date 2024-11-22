package com.shahul.rest.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeErrorResponse {
private LocalDateTime localDateTime;
private String message;
private int status;
public LocalDateTime getLocalDateTime() {
	return localDateTime;
}
public void setLocalDateTime(LocalDateTime localDateTime) {
	this.localDateTime = localDateTime;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}


}
