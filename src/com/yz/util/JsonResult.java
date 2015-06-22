package com.yz.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JsonResult implements Serializable {

	private static final long serialVersionUID = 1L;

	// 成员变量
	private boolean success;
	private Map<String, Object> result;

	// 构造方法
	public JsonResult(boolean success) {
		this.success = success;
	}

	public JsonResult(boolean success, String key, Object value) {
		this(success);
		this.put(key, value);
	}

	// 成员方法
	public JsonResult put(String key, Object value) {
		if (this.result == null) {
			this.result = new HashMap<String, Object>();
		}
		this.result.put(key, value);
		return this;
	}

	public JsonResult msg(String msg) {
		return this.put("msg", msg);
	}

	public JsonResult fail(String msg) {
		return this.put("fail", msg);
	}

	public JsonResult id(Integer id) {
		return this.put("id", id);
	}

	// get/set

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

}
