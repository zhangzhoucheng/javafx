package com.zz.test.javafxmvn.commontag;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zz.test.javafxmvn.commontool.RegexpTool;

/**
 * 
 * <note>
 * Desc：用于渲染表格头部内容的工具类。 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-05-04 18:51:19
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-05-04 18:51:19    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
public class TableHeadFields {

	/**
	 * 菜单渲染行头 首字符：菜单名，_$c_ :菜单id(code),_$w_:菜单宽度，_$node_cb:启用CheckBoxTableCell。_$noadd_:'新增'按钮，新增内容是否有它，_$noadd_空则有，_$noadd_1存在值则没有
	 */
	/*final String[] fieldsHead = { "选择_$c_choice_$w_100_$node_cb_$noadd_1", "进程编码_$c_processCode_$w_100", "进程名_$c_processName", "进程类型_$c_typeCode", "进程描述_$c_processRemark_$w_200",
			"进程开关_$c_processStatus", "进程可用_$c_disable", "定时任务_$c_processCron", "定时任务校准_$c_cronCalibration_$w_200",
			"by主任务启动标记_$c_everyDayStartFlag", "执行最大次数_$c_executeMax", "任务限制执行秒数_$c_processLimittime", };*/
	
	/**
	 * 菜单id
	 */
	private String field;
	
	/**
	 * 菜单名
	 */
	private String fieldName;
	
	/**
	 *列宽度
	 */
	private String width = "100";
	
	/**
	 * 启用CheckBoxTableCell等的标记,val=CheckBoxTableCell，表示启用CheckBoxTableCell，空则不启用
	 */
	private String startCheckBox = null;
	
	/**
	 * 不新增标记，noAddFalg=ture,则该字段不用于‘新增’按钮的获取值对象。
	 */
	private boolean noAddFalg = false;
	
	
	private	List<TableHeadFields>  tableHeadFields = new ArrayList<TableHeadFields>();

	public TableHeadFields() {
		
	}
	
	//根据fieldsHead构建TableHeadFields
	public TableHeadFields(String [] fieldsHead) {
		for (String head : fieldsHead) {
			TableHeadFields t = new TableHeadFields();
			String w_ = (String) RegexpTool.getContent4LR(head, "_\\$w_", "_");
			String c_ = (String) RegexpTool.getContent4LR(head, "_\\$c_", "_");
			String node_ = (String) RegexpTool.getContent4LR(head, "_\\$node_", "_");
			String noadd_ = (String) RegexpTool.getContent4LR(head, "_\\$noadd_", "_");
			String cname_ = (String) RegexpTool.getContent4LR(head, "^", "_");
			
			if(StringUtils.isNotBlank(noadd_)) {
				t.setNoAddFalg(true);
			}
			
			if(StringUtils.isNotBlank(node_)) {
				if("cb".equals(node_)) {
					t.setStartCheckBox("CheckBoxTableCell");
				}
			}
			
			t.setField(c_);
			t.setFieldName(cname_);
			t.setWidth(w_);
			
			this.tableHeadFields.add(t);
		}
		
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getStartCheckBox() {
		return startCheckBox;
	}

	public void setStartCheckBox(String startCheckBox) {
		this.startCheckBox = startCheckBox;
	}

	public boolean isNoAddFalg() {
		return noAddFalg;
	}

	public void setNoAddFalg(boolean noAddFalg) {
		this.noAddFalg = noAddFalg;
	}

	public List<TableHeadFields> getTableHeadFields() {
		return tableHeadFields;
	}

	public void setTableHeadFields(List<TableHeadFields> tableHeadFields) {
		this.tableHeadFields = tableHeadFields;
	}
	
	
	
}
