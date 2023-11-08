package com.sahu.scrollcart.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sahu.scrollcart.model.User;

@Component
public class CommonsUtil {

	@Value("${otp.validity.period}")
	private Long otpValidityPeriod;

	public String getSiteURL(HttpServletRequest request) {
		return ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build().toString();
	}

	public String generateOTP() {
		return String.valueOf(new Random().nextInt(900000) + 100000);
	}

	public String getFullName(User user) {
		return user.getFirstName() + " " + user.getLastName();
	}

	public Boolean validateOTP(Date generateDateTime) {
		ZonedDateTime dateTime = convertDateToZonedDateTime(generateDateTime);
		Long minutesDifference = ChronoUnit.MINUTES.between(dateTime, ZonedDateTime.now());
		return (minutesDifference >= 0 && minutesDifference <= otpValidityPeriod) ? true : false;
	}

	public Date getCurrentDateTime() {
		return Date.from(ZonedDateTime.now().toInstant());
	}

	public ZonedDateTime convertDateToZonedDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault());
	}

	public Date convertZonedDateTimeToDate(ZonedDateTime dateTime) {
		return Date.from(dateTime.toInstant());
	}

	public String convertDateToString(Date date, String dateTimeFormat) {
		return convertDateToZonedDateTime(date).format(DateTimeFormatter.ofPattern(dateTimeFormat));
	}

	public String convertZoneDateTimeToString(ZonedDateTime dateTime, String dateTimeFormat) {
		return dateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat));
	}

}
