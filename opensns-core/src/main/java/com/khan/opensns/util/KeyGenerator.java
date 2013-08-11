package com.khan.opensns.util;

import java.util.UUID;

public class KeyGenerator {
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String createAuthKey() {
		return Base64.encode(getUUID().substring(16).getBytes()).replaceAll("=", "");
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			System.out.println(createAuthKey());
	}
}
