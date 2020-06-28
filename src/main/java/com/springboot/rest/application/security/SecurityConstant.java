package com.springboot.rest.application.security;

import com.springboot.rest.application.SpringApplicatoContext;

public class SecurityConstant {

	public static final long EXPIRATION_TIME = 864000000;

	public static final String TOKEN_PREFIX = "Bearer ";

	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";

	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicatoContext.getBean("AppProperties");
		return appProperties.getTokenSecret();
	}
}
