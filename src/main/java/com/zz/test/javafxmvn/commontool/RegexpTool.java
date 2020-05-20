package com.zz.test.javafxmvn.commontool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

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
	/**
	 * Desc:get on by pattern
	 * @author jld.zhangzhou
	 * @datetime 2020-05-20 17:50:34
	 * @modify_record:
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static String getOneByReg(String str, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		if(m.find()) {
			return m.group();
		}
		return null;
	}
	
	/**
	 * Desc:is match
	 * @author jld.zhangzhou
	 * @datetime 2020-05-20 17:50:23
	 * @modify_record:
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static boolean isMatch(String str,  String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	/**
	 * Desc:get the content from between lfet and right in str
	 * eg:RegexpTool.getContent4LR("进程编码_$c_processCode_$w_100_$w_200_", "_\\$c_", "_") return "processCode";
	 * @author jld.zhangzhou
	 * @datetime 2020-04-22 18:41:16
	 * @modify_record:
	 * @param str
	 * @param left
	 * @param right
	 * @return
	 */
	public static Object getContent4LR(String str, String left, String right) {
		Pattern p = Pattern.compile(String.format("(?<=%s)[^%s]+", left, right));
		Matcher m = p.matcher(str);
		if(m.find()) {
			return m.group();
		}
		return null;
	}
	
	/**
	 * Desc:replace By Pattern
	 * @author jld.zhangzhou
	 * @datetime 2020-05-20 17:53:14
	 * @modify_record:
	 * @param replacedStr
	 * @param replaceStr
	 * @param pattern
	 * @return
	 */
	public static String replaceByPattern(String replacedStr, String replaceStr,  String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(replacedStr);
		return m.replaceAll(replaceStr);
	}
	
	@Test
	public void test () {
		System.out.println(RegexpTool.getContent4LR("进程编码_$c_processCode_$w_100_$w_200_", "_\\$c_", "_"));
		System.out.println(RegexpTool.replaceByPattern("hh    xixexe 456789    8   j   k", ",", "[ ]+"));
		
	}
	
	
}
