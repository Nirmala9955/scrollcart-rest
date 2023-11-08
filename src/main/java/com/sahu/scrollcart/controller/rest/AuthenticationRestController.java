package com.sahu.scrollcart.controller.rest;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sahu.scrollcart.constants.PropertyKeyConstants;
import com.sahu.scrollcart.dto.request.LoginRequestDTO;
import com.sahu.scrollcart.dto.request.SignUpRequestDTO;
import com.sahu.scrollcart.dto.response.JwtAuthResponseDTO;
import com.sahu.scrollcart.model.User;
import com.sahu.scrollcart.security.JwtTokenProviderUtil;
import com.sahu.scrollcart.security.SecurityUtil;
import com.sahu.scrollcart.service.UserService;
import com.sahu.scrollcart.util.CommonsUtil;
import com.sahu.scrollcart.util.MailSenderUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {

	private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationRestController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProviderUtil jwtTokenProviderUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private Environment environment;

	@Autowired
	private MailSenderUtil mailSenderUtil;

	@Autowired
	private CommonsUtil commonsUtil;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponseDTO> login(@RequestBody LoginRequestDTO loginDto) {
		LOGGER.info("Inside login() method");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return ResponseEntity.ok(jwtTokenProviderUtil.generateJwtAuthResponse(SecurityUtil.getCurrentUser()));
	}

	@PostMapping("/registration")
	public ResponseEntity<String> registrationProcess(@RequestBody SignUpRequestDTO signUpUser,
			HttpServletRequest request) throws InterruptedException {
		LOGGER.debug("Inside registrationProcess() method");
		LOGGER.info("User data - " + signUpUser.getEmail());

		User isUserExist = userService.findByEmail(signUpUser.getEmail());

		LOGGER.info("USER Present - " + isUserExist);
		if (isUserExist != null) {
			return ResponseEntity.badRequest()
					.body(environment.getProperty(PropertyKeyConstants.DUPLICATE_USER_ERROR_MSG));
		} else {
			User registeredUser = userService.registerUser(signUpUser);

			if (registeredUser != null) {
				try {
					LOGGER.info("USER activation Code - " + registeredUser.getOtp());
					if (registeredUser.getEmail() != null) {
						mailSenderUtil.sendWelcomeAndActiveAccountMail(registeredUser, commonsUtil.getSiteURL(request),
								registeredUser.getOtp());
					}

					return ResponseEntity.ok()
							.body(environment.getProperty(PropertyKeyConstants.REGISTRATION_SUCCESS_MSG));
				} catch (MessagingException e) {
					return ResponseEntity.badRequest()
							.body(environment.getProperty(PropertyKeyConstants.EMAIL_IS_NOT_VAILD_MSG));
				}
			} else {
				return ResponseEntity.badRequest()
						.body(environment.getProperty(PropertyKeyConstants.REGISTRATION_FAILED_MSG));
			}
		}
	}

	@PostMapping("/activation")
	public ResponseEntity<String> activattionOfAcoount(@RequestParam("code") String code) {
		LOGGER.info("Inside activattionOfAcoount() method");
		LOGGER.info("Activation code - " + code);
		if (code != null && !code.isBlank()) {
			User isUserExist = userService.findByOtp(code);

			if (isUserExist != null && commonsUtil.validateOTP(isUserExist.getOtpValidedTime())) {
				Boolean userActivated = userService.activateUser(isUserExist);

				if (userActivated)
					return ResponseEntity.badRequest()
							.body(environment.getProperty(PropertyKeyConstants.ACCOUNT_ACTIVATED_SUCCESSFULLY));
			}
		}

		return ResponseEntity.badRequest()
				.body(environment.getProperty(PropertyKeyConstants.INVALID_ACTIVATION_CODE_MSG));
	}

	@PostMapping("/forget-password")
	public ResponseEntity<String> forgetPasswordProcess(@RequestParam("email") String email,
			HttpServletRequest request) {
		LOGGER.info("Inside forgetPasswordProcess() method");
		LOGGER.info("Email - " + email);
		if (email != null && !email.isBlank()) {
			User isUserExist = userService.findByEmail(email);

			if (isUserExist != null) {
				try {
					String otp = userService.getResetPasswordCode(isUserExist);
					mailSenderUtil.sendMailForResetPassword(isUserExist, commonsUtil.getSiteURL(request), otp);

					return ResponseEntity.ok().body(environment.getProperty(PropertyKeyConstants.EMAIL_IS_VAILD_MSG));
				} catch (MessagingException e) {
					return ResponseEntity.badRequest()
							.body(environment.getProperty(PropertyKeyConstants.EMAIL_IS_NOT_VAILD_MSG));
				}
			}
		}

		return ResponseEntity.badRequest().body(environment.getProperty(PropertyKeyConstants.DONT_HAVE_ACCOUNT_MSG));
	}

	@PostMapping("/validate-forget-password-code")
	public ResponseEntity<String> validateForgetPasswordCode(@RequestParam("code") String code) {
		if (code != null && !code.isBlank()) {
			User isUserExist = userService.findByOtp(code);

			if (isUserExist != null && commonsUtil.validateOTP(isUserExist.getOtpValidedTime()))
				return ResponseEntity.ok().body("Entered OTP is valid");
		}

		return ResponseEntity.badRequest()
				.body(environment.getProperty(PropertyKeyConstants.INVALID_ACTIVATION_CODE_MSG));
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPasswordProcess(@RequestParam("code") String code,
			@RequestParam("new_password") String newPassword) {
		if (newPassword != null && !newPassword.isBlank() && code != null && !code.isBlank()) {
			User isUserExist = userService.findByOtp(code);

			if (isUserExist != null) {
				Boolean passwordUpdated = userService.updatePassword(isUserExist, newPassword);
				if (passwordUpdated)
					return ResponseEntity.ok()
							.body(environment.getProperty(PropertyKeyConstants.PASSWORD_CHANGED_SUCCESSFULLY));
			}
		}

		return ResponseEntity.badRequest().body(environment.getProperty(PropertyKeyConstants.INVALID_TOKEN_MSG));
	}

}
