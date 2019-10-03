package com.yanfei.springFrame.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassUtil {
	public void fieldCopy(Object source, Object target) throws Exception {
		Method[] methods = source.getClass().getDeclaredMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (methodName.startsWith("get")) {
				Object value = method.invoke(source, new Object[0]);
				String setMethodName = methodName.replaceFirst("(get)", "set");
				Method setMethod = target.getClass().getMethod(setMethodName, method.getReturnType());
				setMethod.invoke(target, value);
			}
		}
	}
	
	/** 属性字段名、值、数据类型 */
	public static List<String> getFields(Object object) throws Exception {
		Field[] fields = object.getClass().getDeclaredFields();
		List<String> fieldsNameList = new ArrayList<String>();
		for (Field field : fields) {
			field.setAccessible(true);
			fieldsNameList.add(field.getName());
		}
		return fieldsNameList;
	}
	
}
