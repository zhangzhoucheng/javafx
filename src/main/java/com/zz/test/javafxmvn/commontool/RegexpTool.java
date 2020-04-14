package com.zz.test.javafxmvn.commontool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * <note>
 * Desc：正则相关的工具类 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-03-31 15:01:07
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-03-31 15:01:07    jld.zhangzhou     通瑞嘉苑,BeiJing      1.create the class            
 * </note>
 */
public class RegexpTool {
	public static String getOneByReg(String str, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		if(m.find()) {
			return m.group();
		}
		return null;
	}
	
	public static boolean isMatch(String str,  String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
}
