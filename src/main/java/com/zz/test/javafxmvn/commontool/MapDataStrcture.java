package com.zz.test.javafxmvn.commontool;

import java.util.Map;

/**
 * 
 * <note>
 * Desc： map ,list,String operation etc
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-05-20 19:44:48
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-05-20 19:44:48    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
public class MapDataStrcture {

	/**
	 * Desc:getDiffOneFromMap2Map
	 * @author jld.zhangzhou
	 * @datetime 2020-05-20 19:45:32
	 * @modify_record:
	 * @param fromMap
	 * @param toMap
	 * @return
	 */
	public static String  getDiffOneFromMap2Map(Map<String, Boolean> fromMap, Map<String, Boolean> toMap) {
		for(String key : fromMap.keySet()) {
			if(toMap.get(key) == null) {
				return key;
			}
		}
		return "";
	}
}
