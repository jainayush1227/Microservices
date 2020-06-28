package com.springboot.rest.application.helper;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	private final Random RANDOM = new SecureRandom();
	private final String Alphabets = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public String generateUserId(int length) {
		return generateRandomString(length);
	}
	public String generateAddressId(int length) {
		return generateRandomString(length);
	}


	public String generateRandomString(int length) {

		StringBuilder returnValue = new StringBuilder();
		for (int i = 0; i < length; i++) {
			returnValue.append(Alphabets.charAt(RANDOM.nextInt(Alphabets.length())));
		}
		return new String(returnValue);
	}

}
