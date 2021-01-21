package com.revature.services;

import java.lang.reflect.Field;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.revature.models.Ticket;

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
	
	public static JSONObject ticketListToObject(List<Ticket> list) {
		JSONObject object = new JSONObject();
		for(int i = 0; i < list.size();i++) {
			object.putOpt(String.valueOf(list.get(i).getId()), list.get(i));
		}
		return object;
	}
}
