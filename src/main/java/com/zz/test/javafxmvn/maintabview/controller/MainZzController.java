package com.zz.test.javafxmvn.maintabview.controller;




import java.util.Iterator;
import java.util.List;
import java.util.Map;



import com.zz.test.javafxmvn.common.entity.PyProcess;
import com.zz.test.javafxmvn.commonbean.BaseObjectViewOth;
import com.zz.test.javafxmvn.commontag.TableHeadFields;
import com.zz.test.javafxmvn.commontag.TagTool;
import com.zz.test.javafxmvn.commontool.RegexpTool;
import com.zz.test.javafxmvn.commontool.threadtool.ButiToolClassZz;
import com.zz.test.javafxmvn.commontool.threadtool.SpringUtils;
import com.zz.test.javafxmvn.maintabview.service.LoginService;
import com.zz.test.javafxmvn.maintabview.service.MainZzService;

import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * 
 * <note>
 * Desc： 主进程
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-29 13:12:22
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-29 13:12:22    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@FXMLController
public class MainZzController extends BaseObjectViewOth{

	/*
	 * @Autowired private LoginService logSer;
	 */
	// 不能通过注解方式获取实体，因为启动类不是该LoginFxmlView，而是MainFxmlView，
	// 所以无法把具有fxmlcontroller的LoginController和容器依赖起来。此时暂时通过getBean方式获取。
	private MainZzService logSer = (MainZzService) SpringUtils.getBean("mainZzService");

	/**
	 * 登陆进程的table容器
	 */
	@FXML
	private Pane login_table;

	@FXML
	private FlowPane login_table_addrow;
	
	@FXML
	private Label type_code;
	
	@FXML
	private Label process_code;
	
	private Button editButton = new Button("编辑");//编辑按钮
	
	
	/**
	 * 菜单渲染行头 首字符：菜单名，_$c_ :菜单id(code),_$w_:菜单宽度，_$node_cb:启用CheckBoxTableCell。_$noadd_:'新增'按钮，新增内容是否有它，_$noadd_空则有，_$noadd_1存在值则没有
	 */
	final String[] fieldsHeadNew = { "选择_$c_choice_$w_100_$node_cb_$noadd_1", "进程编码_$c_processCode_$w_100", "进程名_$c_processName", "进程类型_$c_typeCode", "进程描述_$c_processRemark_$w_200",
			"进程开关_$c_processStatus", "进程可用_$c_disable", "定时任务_$c_processCron", "定时任务校准_$c_cronCalibration_$w_200",
			"by主任务启动标记_$c_everyDayStartFlag", "执行最大次数_$c_executeMax", "任务限制执行秒数_$c_processLimittime", };
	
	private final TableHeadFields fieldsHead = new TableHeadFields(fieldsHeadNew);
	/**
	 * Desc:查询登陆进程列表
	 * 
	 * @author jld.zhangzhou
	 * @datetime 2020-04-16 17:04:22
	 * @modify_record:
	 * @param event
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings("rawtypes")
	@FXML
	public void loginSerch(ActionEvent event) throws InstantiationException, IllegalAccessException {
		//列宽
		final int windth = 100;
		//Button btn = (Button) event.getSource();
		List<PyProcess> list = logSer.loginSerch(type_code.getText(),process_code.getText());
		TableView<PyProcess> table = new TableView<>();
		
		table = TagTool.TableTool.initTableOld(fieldsHead.getTableHeadFields(), list, windth, false);
		login_table.getChildren().clear();
		login_table.getChildren().add(table);
		
		if(login_table_addrow.getChildren().size() > 0)
			return;
		
		
		
		//新增行：用于添加新的PyProcess
		for(TableHeadFields f : fieldsHead.getTableHeadFields()) {
			final TextField textField = new TextField();
			Object w_ = f.getWidth();

			if(f.isNoAddFalg()) {
				continue;
			}
			textField.setPromptText(f.getFieldName());
			textField.setId(f.getField());
			textField.setMaxWidth(w_ == null ? windth : Integer.parseInt((String)w_));
			login_table_addrow.getChildren().add(textField);

		}
		
		final Button addButton = new Button("新增");
		editButton.setText("编辑");//查询后置为 ’编辑‘
		final Button saveButton = new Button("保存");
		final Button deleteButton = new Button("删除");
		
		login_table_addrow.getChildren().addAll(addButton, editButton, saveButton, deleteButton);
		
		/**
		 * 新增按钮点击事件
		 */
		addButton.setOnAction(this.addButtonClick());
		
		/**
		 * 编辑按钮点击事件
		 */
		editButton.setOnAction(this.editButtonClick());
		
		/**
		 * 保存按钮点击事件
		 */
		saveButton.setOnAction(this.saveButtonClick());
		
		
		/**
		 * 删除按钮点击事件
		 */
		deleteButton.setOnAction(this.deleteButton());
	}
	
	/**
	 * Desc:点击编辑 或取消编辑，让表格变的可以编辑，或者不能编辑
	 * @author jld.zhangzhou
	 * @datetime 2020-05-04 16:37:42
	 * @modify_record:
	 * @return
	 */
	private EventHandler<ActionEvent> editButtonClick() { 
		// TODO Auto-generated method stub
		return  (ActionEvent e) -> {
			TableView<PyProcess> tab = (TableView<PyProcess>) login_table.getChildren().get(0);
			
			if("编辑".equals(editButton.getText())) {
				tab.setEditable(true);
				editButton.setText("取消编辑");
				return;
			}
			if("取消编辑".equals(editButton.getText())) {
				tab.setEditable(false);
				editButton.setText("编辑");
				return;
			}
			
		};
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
			int insertRet = -1;
			try {
				insertRet = logSer.insertPyProcess(addP);
			} catch(Exception e1){
				logger.error("@@addButtonClick,insertPyProcess error!",e1);
				TagTool.AlertPrompt.alertMsg_warn("进程编码已经存在等！");
				return;
			}
			if(1 == insertRet) {
				TableView<PyProcess> table = (TableView<PyProcess>) (login_table.getChildren().get(0));
				table.getItems().add(addP);
				//成功设置值则清除文本值
				TagTool.TableTool.delTextOfTextFieldOrT(login_table_addrow.getChildren());
			}
			
			

		};
	}
	
	public EventHandler<ActionEvent> saveButtonClick() {

		return (ActionEvent e) -> {

			TableView<PyProcess> tab = (TableView<PyProcess>) login_table.getChildren().get(0);
		
			
			ObservableList<PyProcess> list =tab.getItems();
			Iterator<PyProcess> itpy = list.iterator();
			int i = 0;
			while(itpy.hasNext()){
				PyProcess p = itpy.next();
				if(p.getChoice().getValue() == true && "取消编辑".equals(editButton.getText())) {
					int ret = logSer.updatePyProcessList(p);
					p.getChoice().set(false);
					i ++ ;
				}
				
			}
			editButton.setText("编辑");
			tab.setEditable(false);
			try {
				this.loginSerch(null);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 

		};
	}
	
	public EventHandler<ActionEvent> deleteButton() {

		return (ActionEvent e) -> {

			TableView<PyProcess> tab = (TableView<PyProcess>) login_table.getChildren().get(0);
		
			
			ObservableList<PyProcess> list =tab.getItems();
			Iterator<PyProcess> itpy = list.iterator();
			int i = 0;
			while(itpy.hasNext()){
				PyProcess p = itpy.next();
				if(p.getChoice().getValue() == true && "取消编辑".equals(editButton.getText())) {
					int ret = logSer.deleteByPrimaryKeySet2(p);
					p.getChoice().set(false);
					itpy.remove();
				}
				i ++ ;
			}
			editButton.setText("编辑");
			tab.setEditable(false);

		};
	}
	/*
	 * @Override public void initialize(URL location, ResourceBundle resources) { //
	 * TODO Auto-generated method stub resourceBundle = resources; }
	 */
}
