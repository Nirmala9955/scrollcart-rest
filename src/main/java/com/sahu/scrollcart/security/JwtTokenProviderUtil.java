package com.sahu.scrollcart.security;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sahu.scrollcart.constants.GMartConstants;
import com.sahu.scrollcart.dto.response.JwtAuthResponseDTO;
import com.sahu.scrollcart.service.dto.CustomUserDetailsDTO;
import com.sahu.scrollcart.util.CommonsUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProviderUtil {

	private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProviderUtil.class);

	@Value("${app.jwt.secret.key}")
	private String jwtSecret;

	@Value("${app.jwt.expiration.milliseconds}")
	private Long jwtExpirationMS;

	@Autowired
	private CommonsUtil commonsUtil;

	public String generateToken(CustomUserDetailsDTO customUserDetailsDTO) {
		LOGGER.info("Name - " + customUserDetailsDTO.getUsername());

		String token = Jwts.builder().setSubject(customUserDetailsDTO.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpirationMS))
				.signWith(key(), SignatureAlgorithm.HS256).compact();

		return token;
	}

	public JwtAuthResponseDTO generateJwtAuthResponse(CustomUserDetailsDTO customUserDetailsDTO) {
		JwtAuthResponseDTO jwtAuthResponse = new JwtAuthResponseDTO();
		String accessToken = generateToken(customUserDetailsDTO);
		jwtAuthResponse.setAccessToken(accessToken);
		jwtAuthResponse.setExpirationDate(commonsUtil.convertDateToString(getExpirationDate(accessToken),
				GMartConstants.DTF_YYYY_MM_DD_HH_MM_SS));
		jwtAuthResponse.setId(customUserDetailsDTO.getUserId());
		jwtAuthResponse.setUuid(customUserDetailsDTO.getUuid());
		jwtAuthResponse.setUsername(customUserDetailsDTO.getUsername());
		jwtAuthResponse.setEmail(customUserDetailsDTO.getEmail());
		jwtAuthResponse.setFullName(customUserDetailsDTO.getFullName());

		return jwtAuthResponse;
	}

	public String getUsername(String token) {
		return getJWTClaims(token).getSubject();
	}

	public Date getExpirationDate(String token) {
		return getJWTClaims(token).getExpiration();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
			return true;
		} catch (MalformedJwtException e) {
			LOGGER.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			LOGGER.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			LOGGER.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			LOGGER.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	private Claims getJWTClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
	}

}
