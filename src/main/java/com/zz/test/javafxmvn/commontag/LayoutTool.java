package com.zz.test.javafxmvn.commontag;

import java.util.List;

/**
 * 
 * <note> Desc： 程序布局的类
 * 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-05-05 09:05:53
 * @location mobile base 3th,BeiJing version 1.0
 * 
 * @REVISIONS: Version Date Author Location Description
 *             ------------------------------------------------------------------------------------------------------
 *             1.0 2020-05-05 09:05:53 jld.zhangzhou mobile base 3th,BeiJing
 *             1.create the class </note>
 */
public class LayoutTool {
	/**
	 * 元素名
	 */
	private String elementId;
	/**
	 * 对应javafx对象
	 */
	private String elementObject;
	private String prefWidth;
	private String preHeight;
	private String minWidth;
	private String minHeight;
	private String maxWidth;
	private String maxHeight;
	private List<LayoutTool> childrens;
	
	public LayoutTool() {
		
	}
	
	public LayoutTool(String layoutNum) {
		
	}
	
	
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public String getElementObject() {
		return elementObject;
	}
	public void setElementObject(String elementObject) {
		this.elementObject = elementObject;
	}
	public String getPrefWidth() {
		return prefWidth;
	}
	public void setPrefWidth(String prefWidth) {
		this.prefWidth = prefWidth;
	}
	public String getPreHeight() {
		return preHeight;
	}
	public void setPreHeight(String preHeight) {
		this.preHeight = preHeight;
	}
	public String getMinWidth() {
		return minWidth;
	}
	public void setMinWidth(String minWidth) {
		this.minWidth = minWidth;
	}
	public String getMinHeight() {
		return minHeight;
	}
	public void setMinHeight(String minHeight) {
		this.minHeight = minHeight;
	}
	public String getMaxWidth() {
		return maxWidth;
	}
	public void setMaxWidth(String maxWidth) {
		this.maxWidth = maxWidth;
	}
	public String getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(String maxHeight) {
		this.maxHeight = maxHeight;
	}
	public List<LayoutTool> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<LayoutTool> childrens) {
		this.childrens = childrens;
	}
	
	
}
