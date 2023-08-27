package com.adfer.security.utils;

import java.lang.reflect.Field;

public class RequestBodyUtils {

	public static boolean isBodyEmpty(Object body) throws IllegalArgumentException, IllegalAccessException {
		
		for(Field field : body.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if(field.get(body) != null) return false;
		}
		
		return true;
	}
}
