/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.wefive.goverment.common.utils;





import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("code", 200);
		put("msg", "success");
	}
	
	public static R error() {
		return error( 400, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(400, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	//返回R列表
	public static R ok(List<Map<String, Object>> l) {
		R r=new R();
		for(Map<String, Object> entry : l) {
			r.putAll(entry);
		}
		return r;
	}
	public static R ok() {
		return new R();
	}
	/*public R ok(String key, JSONArray value) {
		R r=new R();
		for(bject jsonObject : value) {
			r.p
		}
		return r;
	}*/
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	public  Integer getCode() {

		return (Integer) this.get("code");
	}

}
