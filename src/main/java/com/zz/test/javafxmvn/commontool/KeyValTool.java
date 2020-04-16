package com.zz.test.javafxmvn.commontool;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.zz.test.javafxmvn.commonbean.MenuNode;

import javafx.scene.control.TreeItem;

/**
 * 
 * <note>
 * Desc：缓存的map 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-16 10:17:18
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-16 10:17:18    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
public class KeyValTool {
	/**
	 * 主菜单渲染的时候，把菜单的code,和name存入map
	 */
	public static Map<String, Object> itemKeyVal = new HashMap<String, Object>();
	
	public static String getKeyByVal(String val) {
		if(StringUtils.isBlank(val)) {
			return null;
		}
		for(Entry<String, Object> en : itemKeyVal.entrySet()) {
			if(val.equals(en.getValue())) {
				return en.getKey();
			}
		}
		return null;
		
	}
	
	/**
	 * 主菜单渲染的时候，把菜单的code,TreeItem存入map
	 */
	public static Map<String, TreeItem<String>> mainTreeViewCode2Item = new HashMap<String, TreeItem<String>>();
	
	/**
	 * 主菜单渲染的时候，把菜单的code,MenuNode存入map
	 */
	public static Map<String, MenuNode> mainTreeViewCode2MenuNode = new HashMap<>();
}
