/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package org.spring.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author ThinkGem
 * @version 2013-05-22
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

	private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);
	private static final char SEPARATOR = '_';
	private static final String CHARSET_NAME = "UTF-8";
	private static Pattern NUMBER_PATTERN = Pattern.compile("^[+-]?[0-9]+$");

	/**
	 * 转换为String
	 * 如果转换对象为null返回为""
	 * @param obj
	 * @return String
	 */
	public static String getString(Object obj){
		try{
			if(null == obj){
				return "";
			}
			return String.valueOf(obj);
		}catch(Exception e){
			logger.error("参数：{}转String异常", obj, e);
			return "";
		}
	}

	/**
	 * 转换为Integer
	 * 如果转换对象不是Integer类型或者为null时返回为-1
	 * @param obj
	 * @return Integer
	 */
	public static Integer getInteger(Object obj){
		try{
			String temp = getString(obj);
			if("".equals(temp)){
				return -1;
			}
			return Integer.valueOf(temp);
		}catch(Exception e){
			logger.error("参数：{}转String异常", obj, e);
			return -1;
		}
	}

	/**
	 * 转换为字节数组
	 * @param str
	 * @return
	 */
	public static byte[] getBytes(String str){
		if (str != null){
			try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		}else{
			return null;
		}
	}

	/**
	 * 转换为字节数组
	 * @param bytes
	 * @return
	 */
	public static String toString(byte[] bytes){
		try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
	}

	/**
	 * Object转换为String类型
	 */
	public static String toString(Object val){
		//防止当object为null时，返回字符串"null"
		if(val==null){
			return "";
		}
		return String.valueOf(val);
	}

	/**
	 * 是否包含字符串
	 * @param str 验证字符串
	 * @param strs 字符串组
	 * @return 包含返回true
	 */
	public static boolean inString(String str, String... strs){
		if (str != null){
			for (String s : strs){
				if (str.equals(trim(s))){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * @param html
	 * @return
	 */
	public static String replaceMobileHtml(String html){
		if (html == null){
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * Object转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		try {
			return Integer.valueOf(val.toString());
		} catch (Exception e) {
			logger.error("##Object to Integer 异常##", e);
			return 0;
		}
	}

	/**
	 * String转换为Integer类型
	 */
	public static Integer toInteger(String str){
		try {
			return Integer.valueOf(str);
		} catch (Exception e) {
			logger.error("##object to Integer 异常##", e);
			return 0;
		}
	}


	/**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld"
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld"
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld"
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toUnderScoreCase(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i > 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 如果不为空，则设置值
	 * @param target
	 * @param source
	 */
	public static void setValueIfNotBlank(String target, String source) {
		if (isNotBlank(source)){
			target = source;
		}
	}

	/**
	 * 转换为JS获取对象值，生成三目运算返回结果
	 * @param objectString 对象串
	 *   例如：row.user.id
	 *   返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
	 */
	public static String jsGetVal(String objectString){
		StringBuilder result = new StringBuilder();
		StringBuilder val = new StringBuilder();
		String[] vals = split(objectString, ".");
		for (int i=0; i<vals.length; i++){
			val.append("." + vals[i]);
			result.append("!"+(val.substring(1))+"?'':");
		}
		result.append(val.substring(1));
		return result.toString();
	}

	/**
	 * 判断对象为空，返回默认值
	 *
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static Object getDefaultValue(Object obj, String defaultValue) {
		if(obj == null) {
			return defaultValue;
		} else {
			return obj;
		}
	}

	/** 判断字符串是否全为数字 */
	public static boolean isInteger(String str){

		boolean bl = false;
		try {
			if(isNotBlank(str)){
				Matcher mer = NUMBER_PATTERN.matcher(str);
				bl =  mer.find();
			}
		} catch (Exception e) {}
		return bl;
	}
	/** 判断字符串是否全为Long */
	public static boolean isLong(String str){

		boolean bl = false;
		try {
			if(isNotBlank(str)){
				Long.parseLong(str);
				bl = true;
			}
		} catch (Exception e) {
		}
		return bl;
	}
	/** 转换字符串为数字 */
	public static int parseInteger(String str){

		int i = 0;
		try {
			if(isInteger(str)) {
				i = Integer.parseInt(str);
			}
		} catch (Exception e) {
		}
		return i;
	}

	/** 判断字符串是否全为Double  */
	public static boolean isDouble(String str){

		try{
			if(isBlank(str)){
				return false;
			}
			Double.parseDouble(str);
			return true;
		}catch(NumberFormatException ex){}
		return false;
	}

	/** 格式化银行卡号  */
	public static String formatBankCardNo(String str){

		try{
			if(isBlank(str)) {
				return "";
			}
			if(str.length() > 4){
				return "**** **** **** " + str.substring(str.length() - 4);
			}
		}catch(Exception e){
			logger.error("##FormatBankCardNo 异常##", e);
		}
		return "";
	}
}
