package com.alkemy.aceleracion.disney.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {
	
	@Email(message = "Username must be an email")
	private String username;
	@Size(min = 8)
	private String password;

}
