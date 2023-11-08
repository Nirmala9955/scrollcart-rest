package com.sahu.scrollcart.constants;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public interface GMartConstants {

	public static final String SUPPORT_MAIL = "digitalmarketinghub.info@gmail.com";

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String RESET_PWD_TOKEN_ATR = "/reset-password?token=";

	public static final String ACTIVATION_CODE_ATR = "/activation?code=";

	public static final String PHONE_REGEXP = "^(\\d{10})$";

	public static final String EMAIL_REGEXP = "^[A-Za-z0-9+_.-]+@(.+)$";

	public static final String EMAIL = "EMAIL";

	public static final String PHONE_NO = "PHONE_NO";

	public static final String DEFAULT_TIMEZONE = "Asia/Kolkata";

	public static final ZonedDateTime DEFAULT_ZONE_ID = ZonedDateTime.now(ZoneId.of(DEFAULT_TIMEZONE));
	
	public static final String DTF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

}