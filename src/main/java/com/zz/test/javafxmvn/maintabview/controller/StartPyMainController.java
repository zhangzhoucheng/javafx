package com.zz.test.javafxmvn.maintabview.controller;




import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.Iterator;
import java.util.Map;

import com.zz.test.javafxmvn.common.aop.PaginationAopAno;
import com.zz.test.javafxmvn.common.entity.PyProcess;
import com.zz.test.javafxmvn.commonbean.BaseObjectViewOth;
import com.zz.test.javafxmvn.commonbean.CommonRequest;
import com.zz.test.javafxmvn.commonbean.PageCommonRequest;
import com.zz.test.javafxmvn.commontag.TableHeadFields;
import com.zz.test.javafxmvn.commontag.TagTool;
import com.zz.test.javafxmvn.commontool.threadtool.ButiToolClassZz;
import com.zz.test.javafxmvn.commontool.threadtool.SpringUtils;
import com.zz.test.javafxmvn.commontool.threadtool.ThreadPollTool;
import com.zz.test.javafxmvn.maintabview.service.StartPyMainService;

import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

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
public class StartPyMainController extends BaseObjectViewOth{

	/*
	 * @Autowired private LoginService logSer;
	 */
	// 不能通过注解方式获取实体，因为启动类不是该LoginFxmlView，而是MainFxmlView，
	// 所以无法把具有fxmlcontroller的LoginController和容器依赖起来。此时暂时通过getBean方式获取。
	private StartPyMainService startPyMainService = (StartPyMainService) SpringUtils.getBean("startPyMainService");

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
	
	private  Button startButton = new Button("启动");
	
	private Pagination thePagination;
	

	/**
	 * 菜单渲染行头 首字符：菜单名，_$c_ :菜单id(code),_$w_:菜单宽度，_$node_cb:启用CheckBoxTableCell。_$noadd_:'新增'按钮，新增内容是否有它，_$noadd_空则有，_$noadd_1存在值则没有
	 */
	final String[] fieldsHeadNew = { "选择_$c_choice_$w_100_$node_cb_$noadd_1", "进程编码_$c_processCode_$w_100", "进程名_$c_processName_$w_150", "进程类型_$c_typeCode", "进程描述_$c_processRemark_$w_300",
			"进程开关_$c_processStatus", "进程可用_$c_disable", "定时任务_$c_processCron_$w_200", "定时任务校准_$c_cronCalibration_$w_120",
			"by主任务启动标记_$c_everyDayStartFlag_$w_150", "执行最大次数_$c_executeMax_$w_120", "任务限制执行秒数_$c_processLimittime_$w_120", };
	
	
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
		final int width = 100;
		// Button btn = (Button) event.getSource();
		// List<PyProcess> list =
		// logSer.loginSerch(type_code.getText(),process_code.getText());
		
		CommonRequest request = new PageCommonRequest(10, 1);
		request.addParameter("type_code", type_code.getText());
		request.addParameter("process_code", process_code.getText());
		//logSer.loginSerchnew((PageCommonRequest) request);该方法上注解生效的。
		//pagination的装载table内容，会在loginserch完成后，由@FXML所触发，才会将tableview赋值。所以如下@1无法得到loginTable；
		thePagination = TagTool.TableTool.tablePagePagination(10,"startPyMainService.loginSerchnew", request, fieldsHead.getTableHeadFields(), width, false);
		//@1：loginTable = (TableView<PyProcess>) ((StackPane) pagination.getChildrenUnmodifiable().get(0)).getChildren().get(0);
		
	/*	Pagination pagination = new Pagination();
		pagination.setStyle("-fx-font-size:16");
		pagination.setMaxPageIndicatorCount(12);
		
		pagination.setPageFactory((Integer pageIndex) -> {
			loginTable =  TagTool.TableTool.tablePage(pageIndex, "loginService.loginSerchnew",
					request, fieldsHead.getTableHeadFields(), width, false);
			return loginTable;

		});*/
		
		
	
		
		login_table.getChildren().clear();
		login_table.getChildren().add(thePagination);
		this.testAop("hhh");
		if(login_table_addrow.getChildren().size() > 0) {
			editButton.setText("编辑");//查询后置为 ’编辑‘
			ThreadPollTool.executorThread(ThreadPollTool.getRunnableExecutorBatchMethod(new String [] {"startPyMainService.startPyMainTableClickRow"}, thePagination));
			return;
		}
			
		
		
		
		//新增行：用于添加新的PyProcess
		for(TableHeadFields f : fieldsHead.getTableHeadFields()) {
			final TextField textField = new TextField();
			Object w_ = f.getWidth();

			if(f.isNoAddFalg()) {
				continue;
			}
			textField.setPromptText(f.getFieldName());
			textField.setId(f.getField());
			textField.setMaxWidth(w_ == null ? width : Integer.parseInt((String)w_));
			login_table_addrow.getChildren().add(textField);

		}
		
		final Button addButton = new Button("新增");
		editButton.setText("编辑");//查询后置为 ’编辑‘
		editButton.setId("btn_edit");
		final Button saveButton = new Button("保存");
		
		final Button deleteButton = new Button("删除");
		deleteButton.setVisible(false);
		deleteButton.setManaged(false);
		
		//启动按钮
		startButton.setId("btn_start");
		
		login_table_addrow.getChildren().addAll(addButton, editButton, saveButton, deleteButton, startButton);
		
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
		//Event.fireEvent(editButton, event);//触发事件
		
		startButton.setOnAction(this.startButtonClick());
		
		/**
		 * 点击行事件
		 */
	/*	table1.getSelectionModel().selectedItemProperty().addListener(// 选中某一行
                new ChangeListener<ILayer>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends ILayer> observableValue,
                            ILayer oldItem, ILayer newItem) {
                        System.out.println(newItem.getLayerName());
        }*/
                    
        ThreadPollTool.executorThread(ThreadPollTool.getRunnableExecutorBatchMethod(new String [] {"startPyMainService.startPyMainTableClickRow"}, thePagination));
		
	}
	
	@PaginationAopAno
	private void testAop(String a) {
		System.out.println("aop:"+a);
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
			Node n = login_table;
			Pagination p = (Pagination) login_table.getChildren().get(0);
		
			
			
			TableView<PyProcess> tab =  TagTool.TableTool.tablePagePaginationGetTable(thePagination);
			
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
				insertRet = startPyMainService.insertPyProcess(addP);
			} catch(Exception e1){
				logger.error("@@addButtonClick,insertPyProcess error!",e1);
				TagTool.AlertPrompt.alertMsg_warn("进程编码已经存在等！");
				return;
			}
			if(1 == insertRet) {
				
				TagTool.TableTool.tablePagePaginationGetTable(thePagination).getItems().add(addP);
				//成功设置值则清除文本值
				TagTool.TableTool.delTextOfTextFieldOrT(login_table_addrow.getChildren());
			}
			
			

		};
	}
	
	public EventHandler<ActionEvent> saveButtonClick() {

		return (ActionEvent e) -> {
			TableView<PyProcess> tab =  TagTool.TableTool.tablePagePaginationGetTable(thePagination);
			ObservableList<PyProcess> list =tab.getItems();
			Iterator<PyProcess> itpy = list.iterator();
			int i = 0;
			while(itpy.hasNext()){
				PyProcess p = itpy.next();
				if(p.getChoice().getValue() == true && "取消编辑".equals(editButton.getText())) {
					int ret = startPyMainService.updatePyProcessList(p);
					p.getChoice().set(false);
					i ++ ;
				}
				
			}
			editButton.setText("编辑");
			tab.setEditable(false);
			try {
				//this.loginSerch(null);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 

		};
	}
	
	public EventHandler<ActionEvent> deleteButton() {

		return (ActionEvent e) -> {
			TableView<PyProcess> tab =  TagTool.TableTool.tablePagePaginationGetTable(thePagination);
			ObservableList<PyProcess> list =tab.getItems();
			Iterator<PyProcess> itpy = list.iterator();
			int i = 0;
			while(itpy.hasNext()){
				PyProcess p = itpy.next();
				if(p.getChoice().getValue() == true && "取消编辑".equals(editButton.getText())) {
					int ret = startPyMainService.deleteByPrimaryKeySet2(p);
					p.getChoice().set(false);
					itpy.remove();
				}
				i ++ ;
			}
			editButton.setText("编辑");
			tab.setEditable(false);

		};
	}
	
	public EventHandler<ActionEvent> startButtonClick() {

		return (ActionEvent e) -> {
			startButton.setText("启动中........");
			TableView<PyProcess> tab =  TagTool.TableTool.tablePagePaginationGetTable(thePagination);
			ObservableList<PyProcess> list =tab.getItems();
			Iterator<PyProcess> itpy = list.iterator();
			int i = 0;
			while(itpy.hasNext()){
				PyProcess p = itpy.next();
				if(p.getChoice().getValue() == true && "取消编辑".equals(editButton.getText())) {
					startPyMainService.startPyFile(p.getProcessOthmsg());
					p.getChoice().set(false);
				}
				i ++ ;
			}
			startButton.setText("启动");
			editButton.setText("编辑");
			tab.setEditable(false);

		};
	}
	/*
	 * @Override public void initialize(URL location, ResourceBundle resources) { //
	 * TODO Auto-generated method stub resourceBundle = resources; }
	 */
}
