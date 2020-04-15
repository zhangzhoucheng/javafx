package com.zz.test.javafxmvn.commontag;

import com.zz.test.javafxmvn.commontool.KeyValTool;
import com.zz.test.javafxmvn.main.view.MainFxmlView;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;

/**
 * 
 * <note>
 * Desc：javafx 标签相关的便捷操作封装。 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-15 16:31:03
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-15 16:31:03    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
public class TagTool {
	/**
	 * Desc:设置tabPane选中tab，其他clear
	 * @author jld.zhangzhou
	 * @datetime 2020-04-15 16:30:36
	 * @modify_record:
	 * @param tap
	 */
	public static void tabPaneSelectionModel(TabPane tbp, Tab tab) {
		SingleSelectionModel<Tab> select = tbp.getSelectionModel();

		//select.clearSelection();//该方法并不能得到想要的目的，（先清空所有被selected，之后设置select）
		select.select(tab);
	}
	
	/**
	 * Desc:通过名称获取TreeItem中的TreeItem
	 * @author jld.zhangzhou
	 * @datetime 2020-04-15 20:30:33
	 * @modify_record:
	 * @param item
	 * @param value
	 * @return
	 */
	public static TreeItem getTreeViewItem(TreeItem<String> item , String value) 
	{
	  if (item != null && item.getValue().equals(value))
	    return  item;

	  for (TreeItem<String> child : item.getChildren()){
	   TreeItem<String> s=getTreeViewItem(child, value);
	   if(s!=null)
	       return s;

	  }
	  return null;
	}
	
	/**
	 * Desc:给TabPane 的tab 添加select改变事件(关于函数value中又传参问题？？？？？）
	 * @author jld.zhangzhou
	 * @datetime 2020-04-15 21:16:13
	 * @modify_record:
	 * @param tabp
	 * @param value
	 */
	public static void  tabsSetSelectChangeEvent(TabPane tabp, EventHandler<Event> value) {
		MainFxmlView a = new MainFxmlView();
		for(Tab tab : tabp.getTabs()) {
			Tab t = tab;
			tab.setOnSelectionChanged(value);//添加select被改变事件。
		}
	}
}
