package com.greenstitch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Consumer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@NonNull
	private String name;
	
	@NonNull
	@Column(unique = true)
	@Email
	private String email;
	
	@NonNull
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//	@Pattern(regexp = "[a-zA-Z0-9]{8,}",message = "Password must contain at least 8 characters including one uppercase letter, one lowercase letter, one digit, and one special character.")
	private String password;
	
	
}
