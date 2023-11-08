package com.sahu.scrollcart.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SignUpRequestDTO {
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("middle_name")
	private String middleName;
	@JsonProperty("last_name")
	private String lastName;
	private String email;
	private String password;
}
