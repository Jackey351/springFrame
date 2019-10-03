package com.yanfei.springFrame.Utils;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {
	
	// 响应业务状态
	private Integer status;
	
	// 响应消息
	private String message;
	
	// 响应中的数据
	private Object data;
	
	public static ResultUtil ok(Object data) {
		return new ResultUtil(data);
	}
	
	public static ResultUtil ok() {
		return new ResultUtil(200, "成功", null);
	}
	
	public static Map<String, Object> toMap(String key, Object data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, data);
		return map;
	}
	
	public ResultUtil() {
		
	}
	
	public ResultUtil(Integer status, String message, Object data) {
		this.status = status;
		this.setMessage(message);
		this.data = data;
	}
	
	public static ResultUtil customize(Integer code, String message, Object data) {
		return new ResultUtil(code, message, data);
	}
	
	public static ResultUtil fail(String message) {
		return new ResultUtil(100, message, null);
	}
	
	public ResultUtil(Object data) {
		this.status = 200;
		this.setMessage("OK");
		this.data = data;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
