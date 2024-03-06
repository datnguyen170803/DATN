package com.example.helper;

import java.util.concurrent.ThreadLocalRandom;

public class IntegerHelper {

	public static String randomString(int length) {
	    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=";
	    StringBuilder sb = new StringBuilder(length);

	    for (int i = 0; i < length; i++) {
	        int index = ThreadLocalRandom.current().nextInt(characters.length());
	        char randomChar = characters.charAt(index);
	        sb.append(randomChar);
	    }

	    return sb.toString();
	}
	public static String random5Number() {
	    return randomString(10);
	}
}
