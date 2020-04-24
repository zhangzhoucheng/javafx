package com.zz.test.javafxmvn.maintabview.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.jms.artemis.ArtemisNoOpBindingRegistry;

import com.zz.test.javafxmvn.common.entity.PyProcess;
import com.zz.test.javafxmvn.commontag.TagBase;
import com.zz.test.javafxmvn.commontag.TagTool;
import com.zz.test.javafxmvn.commontag.TagTool.TableTool;
import com.zz.test.javafxmvn.commontool.RegexpTool;
import com.zz.test.javafxmvn.commontool.threadtool.ButiToolClassZz;
import com.zz.test.javafxmvn.commontool.threadtool.SpringUtils;
import com.zz.test.javafxmvn.maintabview.service.LoginService;

import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * 
 * <note> Desc： 登陆进程的controller类
 * 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-16 22:02:54
 * @location mobile base 3th,BeiJing version 1.0
 * 
 * @REVISIONS: Version Date Author Location Description
 *             ------------------------------------------------------------------------------------------------------
 *             1.0 2020-04-16 22:02:54 jld.zhangzhou mobile base 3th,BeiJing
 *             1.create the class </note>
 */
@FXMLController
public class LoginController {

	/*
	 * @Autowired private LoginService logSer;
	 */
	// 不能通过注解方式获取实体，因为启动类不是该LoginFxmlView，而是MainFxmlView，
	// 所以无法把具有fxmlcontroller的LoginController和容器依赖起来。此时暂时通过getBean方式获取。
	private LoginService logSer = (LoginService) SpringUtils.getBean("loginService");

	private static boolean loginSerchGoFlag = false;
	/**
	 * 登陆进程的table容器
	 */
	@FXML
	private Pane login_table;

	@FXML
	private FlowPane login_table_addrow;
	
	/**
	 * 菜单渲染行头
	 */
	final String[] fieldsHead = { "进程编码_$c_processCode_$w_100", "进程名_$c_processName", "进程类型_$c_typeCode", "进程描述_$c_processRemark_$w_200",
			"进程开关_$c_processStatus", "进程可用_$c_disable", "定时任务_$c_processCron", "定时任务校准_$c_cronCalibration_$w_200",
			"by主任务启动标记_$c_everyDayStartFlag", "执行最大次数_$c_executeMax", "任务限制执行秒数_$c_processLimittime", };
	/**
	 * Desc:查询登陆进程列表
	 * 
	 * @author jld.zhangzhou
	 * @datetime 2020-04-16 17:04:22
	 * @modify_record:
	 * @param event
	 */
	@FXML
	public void loginSerch(ActionEvent event) {
		//列宽
		final int windth = 100;
		//Button btn = (Button) event.getSource();
		List<PyProcess> list = logSer.loginSerch();
		TableView<PyProcess> table = new TableView<>();
		table = TagTool.TableTool.initTableOld(fieldsHead, list, windth);
		login_table.getChildren().add(table);
		
		if(login_table_addrow.getChildren().size() > 0)
			return;
		
		//新增行：用于添加新的PyProcess
		for(String f : fieldsHead) {
			final TextField textField = new TextField();
			Object w_ = RegexpTool.getContent4LR(f, "_\\$w_", "_");
			textField.setPromptText((String) RegexpTool.getContent4LR(f, "^", "_"));
			textField.setId((String) RegexpTool.getContent4LR(f, "_\\$c_", "_"));
			textField.setMaxWidth(w_ == null ? windth : Integer.parseInt((String)w_));
			login_table_addrow.getChildren().add(textField);

		}
		
		final Button addButton = new Button("新增");
		final Button saveButton = new Button("保存");
		login_table_addrow.getChildren().addAll(addButton,saveButton);
		
		/**
		 * 新增按钮点击事件
		 */
		addButton.setOnAction(this.addButtonClick());
		
		/**
		 * 保存按钮点击事件
		 */
		saveButton.setOnAction(this.saveButtonClick());
		
	}
	
	public EventHandler<ActionEvent>  addButtonClick() {
		
		return  (ActionEvent e) -> {

			Map<String, Object> fieldsHeadMaps = TagTool.TableTool.getFieldsByfieldsHeadAndVal(fieldsHead, login_table_addrow.getChildren());
			PyProcess addP = new PyProcess();
			try {
				String ret = ButiToolClassZz.ReflexRel.reflexObjectSetValByField2fieldsHead(addP, fieldsHeadMaps, fieldsHead);
				if(!"1".equals(ret)) {
					TagTool.AlertPrompt.alertMsg("填写错误", ret);
					return;
				}else {//成功设置值则清除文本值
					TagTool.TableTool.delTextOfTextFieldOrT(login_table_addrow.getChildren());
				}
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			TableView<PyProcess> table = (TableView<PyProcess>) (login_table.getChildren().get(0));
			table.getItems().add(addP);
			logSer.insertPyProcess(addP);

		};
	}
	
	public EventHandler<ActionEvent> saveButtonClick() {

		return (ActionEvent e) -> {

			TableView<PyProcess> tab = (TableView<PyProcess>) login_table.getChildren().get(0);
			
			ObservableList<PyProcess> list =tab.getItems();
			Iterator<PyProcess> itpy = list.iterator();
			while(itpy.hasNext()){
				logSer.updatePyProcessList(itpy.next());
			}

		};
	}

	/*
	 * @Override public void initialize(URL location, ResourceBundle resources) { //
	 * TODO Auto-generated method stub resourceBundle = resources; }
	 */
}
