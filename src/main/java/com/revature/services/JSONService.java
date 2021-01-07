package com.revature.services;

import java.lang.reflect.Field;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONService<T> {
	public static JSONObject packageObject(Object o) throws SecurityException{
		JSONObject object = new JSONObject();
		for(Field f : o.getClass().getDeclaredFields()) {
			try {
				object.put(f.getName(),f.get(o));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return object;
	}
}
