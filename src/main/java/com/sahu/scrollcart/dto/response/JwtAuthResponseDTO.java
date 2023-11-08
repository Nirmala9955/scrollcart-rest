package com.sahu.scrollcart.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class JwtAuthResponseDTO {
	private Long id;
	private String uuid;
	private String email;
	private String username;
	@JsonProperty("full_name")
	private String fullName;
	@JsonProperty("token_type")
	private String tokenType = "Bearer";
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("expiration_date")
	private String expirationDate;
}
