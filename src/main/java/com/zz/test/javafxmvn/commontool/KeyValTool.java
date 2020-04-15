package com.zz.test.javafxmvn.commontool;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import javafx.scene.control.TreeItem;

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
}
