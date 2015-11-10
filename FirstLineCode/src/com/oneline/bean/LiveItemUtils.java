package com.oneline.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LiveItemUtils {

	
	public void setValue(){
		LiveItem item = new LiveItem();
		item.setItem1("");
	}
	public static LiveItem setValue2(){
		LiveItem item = new LiveItem();
		Method[]  method = item.getClass().getMethods();
		
		for(Method m : method){
			String name = m.getName();
			
			if(name.charAt(0) == 's' && name.charAt(1) == 'e' && name.charAt(2) == 't') {
				try {
					m.invoke(item, "你好");
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return item;
	}
	
	
}
