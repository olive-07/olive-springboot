package org.spring.springboot.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * 获取map
 * @author gaoyuhai
 * 2016-11-18 下午06:02:52
 */
public class MapUtils {
	/**
	 * 获取map
	 * @param code 0:成功  其他不成功
	 * @param message
	 * @return
	 */
	public static HashMap<String,Object> getResultMap(String code ,String message){
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}
	/**
	 * 获取map-error
	 * @param code 0:成功  其他不成功
	 * @param message
	 * @return
	 */
	public static HashMap<String,Object> getResultMapByError(String code ,String message){
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		map.put("data", "{}");
		return map;
	}
	/**
	 * 设置map
	 * @param map
	 * @param code
	 * @param message
	 */
	public static void setResultMap(Map<String,Object> map,String code ,String message){
		map.put("code", code);
		map.put("message", message);
	}
	/**
	 * 设置map msg
	 * @param map
	 * @param message
	 */
	public static void setResultMapMsg(Map<String,Object> map,String message){
		map.put("message", message);
	}
	

}
