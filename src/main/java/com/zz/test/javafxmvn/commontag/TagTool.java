package com.zz.test.javafxmvn.commontag;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import com.zz.test.javafxmvn.main.Main;
import com.zz.test.javafxmvn.main.view.MainFxmlView;
import com.zz.test.javafxmvn.maintabview.view.LoginFxmlView;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


/**
 * 
 * <note> Desc：javafx 标签相关的便捷操作封装。
 * 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-15 16:31:03
 * @location mobile base 3th,BeiJing version 1.0
 * 
 * @REVISIONS: Version Date Author Location Description
 *             ------------------------------------------------------------------------------------------------------
 *             1.0 2020-04-15 16:31:03 jld.zhangzhou mobile base 3th,BeiJing
 *             1.create the class </note>
 */
public class TagTool {
	/**
	 * Desc:设置tabPane选中tab，其他clear
	 * 
	 * @author jld.zhangzhou
	 * @datetime 2020-04-15 16:30:36
	 * @modify_record:
	 * @param tap
	 */
	public static void tabPaneSelectionModel(TabPane tbp, Tab tab) {
		SingleSelectionModel<Tab> select = tbp.getSelectionModel();

		// select.clearSelection();//该方法并不能得到想要的目的，（先清空所有被selected，之后设置select）
		select.select(tab);
	}

	/**
	 * Desc:通过名称获取TreeItem中的TreeItem
	 * 
	 * @author jld.zhangzhou
	 * @datetime 2020-04-15 20:30:33
	 * @modify_record:
	 * @param item
	 * @param value
	 * @return
	 */
	public static TreeItem getTreeViewItem(TreeItem<String> item, String value) {
		if (item != null && item.getValue().equals(value))
			return item;

		for (TreeItem<String> child : item.getChildren()) {
			TreeItem<String> s = getTreeViewItem(child, value);
			if (s != null)
				return s;

		}
		return null;
	}

	/**
	 * Desc:把fxml对应内容装入ScrollPane
	 * @author jld.zhangzhou
	 * @datetime 2020-04-16 13:09:58
	 * @modify_record:
	 * @param pane
	 * @param fxml
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("finally")
	public static Initializable paneLoadFxml(Object pane, String fxml) throws IOException {
		FXMLLoader loader = new FXMLLoader(); // 创建对象

		loader.setBuilderFactory(new JavaFXBuilderFactory()); // BuilderFactory设置
		URL a=LoginFxmlView.class.getResource(fxml);
		loader.setLocation(LoginFxmlView.class.getResource(fxml)); // 设置路径基础
		InputStream in = null;
		try {
			in = Main.class.getResourceAsStream(fxml);
			AnchorPane page = (AnchorPane) loader.load(in); // 
			
			//Scene scene = new Scene(page, 800, 600);
			//Stage.setScene(scene);
			//stage.sizeToScene();//可以参照https://blog.csdn.net/baidu_30325009/article/details/93321504，完成场景切换。
			if(pane.getClass() == Pane.class) {
				((Pane)pane).getChildren().clear();
				((Pane)pane).getChildren().add(page);
			}
			if(pane.getClass() == ScrollPane.class) {
				((ScrollPane)pane).getChildrenUnmodifiable().clear();
				((ScrollPane)pane).setContent(page);
			}
			//sp.getChildrenUnmodifiable().add(page);
			return (Initializable) loader.getController(); // 这样得到Controller

		} finally {
			in.close();
			return null;
		}

	}
	
	/**
	 * Desc:在pane中加载资源fxml，和paneLoadFxml方法功能一样。
	 * @author jld.zhangzhou
	 * @datetime 2020-04-16 22:56:39
	 * @modify_record:
	 * @param pane
	 * @param fxml
	 * @param obj of ScrollPane or Pane
	 * @throws IOException
	 */
	public static void paneLoadFxmlSimple(Object pane, String fxml, Object obj) throws IOException {
		if(pane.getClass() == Pane.class) {
			((Pane)pane).getChildren().clear();
			((Pane)pane).getChildren().add(FXMLLoader.load(((Class<?>) obj).getResource(fxml)));
		}
		if(pane.getClass() == ScrollPane.class) {
			((ScrollPane)pane).getChildrenUnmodifiable().clear();
			((ScrollPane)pane).setContent(FXMLLoader.load(((Class<?>) obj).getResource(fxml)));
		}
	}

	/**
	 * 
	 * <note>
	 * Desc：表格操作的工具类 
	 * @author jld.zhangzhou
	 * @email idiot_jillidan@163.com;
	 * @re be willing to communicate
	 * @refactor for jld
	 * @datetime 2020-04-17 08:52:18
	 * @location mobile base 3th,BeiJing 
	 * version  1.0
	 *  
	 * @REVISIONS: 
	 * Version 	        Date 		         Author             Location                   Description          
	 * ------------------------------------------------------------------------------------------------------  
	 * 1.0 		  2020-04-17 08:52:18    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
	 * </note>
	 */
	static class TableTool<T> {
		public  TableView<T> initTable(TableView<T> tableView, String [] cols, List<T> list) {
			
			
			return tableView;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Desc:给TabPane 的tab 添加select改变事件(关于函数value中又传参问题？？？？？）
	 * 
	 * @author jld.zhangzhou
	 * @datetime 2020-04-15 21:16:13
	 * @modify_record:
	 * @param tabp
	 * @param value
	 */
	public static void tabsSetSelectChangeEvent(TabPane tabp, EventHandler<Event> value) {
		MainFxmlView a = new MainFxmlView();
		for (Tab tab : tabp.getTabs()) {
			Tab t = tab;
			tab.setOnSelectionChanged(value);// 添加select被改变事件。
		}
	}
}
