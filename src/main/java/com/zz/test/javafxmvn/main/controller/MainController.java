package com.zz.test.javafxmvn.main.controller;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import com.zz.test.javafxmvn.main.service.MainService;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;

/**
 * 
 * <note> Desc：主控制类
 * 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-01-13 14:32:04
 * @location mobile base 3th,BeiJing version 1.0
 * 
 * @REVISIONS: Version Date Author Location Description
 *             ------------------------------------------------------------------------------------------------------
 *             1.0 2020-01-13 14:32:04 jld.zhangzhou mobile base 3th,BeiJing
 *             1.create the class </note>
 */
@FXMLController
public class MainController implements Initializable {

	@Autowired
	private MainService ms;

	private ResourceBundle resourceBundle;

	@FXML // 很关键，不然定义了fx:id 会报错的
	private Parent pane_root;

	@FXML
	private MenuItem menu1_1task12;
	
	@FXML
	private TabPane main_tabpane;

	@FXML
	public void handlerBtnClick(ActionEvent event) {
		Button btnSource = (Button) event.getSource();
		ms.handlerBtnClick();
		btnSource.setText("I am clicked!");
	}

	/**
	 * Desc:获取进程列表
	 * 
	 * @author jld.zhangzhou
	 * @datetime 2020-01-14 17:51:29
	 * @modify_record:
	 * @param event
	 */
	@FXML
	public void getProcessList(ActionEvent event) {
		Button btnSource = (Button) event.getSource();
		btnSource.setText("I am clicked!");

	}

	@FXML
	public void getMenuItem(ActionEvent event) {
		List<Map<String, Object>> list = ms.getProcessList();
		
		System.out.println("geti:");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		resourceBundle = resources;
	}

}
