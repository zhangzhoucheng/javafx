package com.zz.test.javafxmvn.maintabview.controller;





import java.util.List;


import com.zz.test.javafxmvn.common.entity.PyProcess;
import com.zz.test.javafxmvn.commontag.TagTool;
import com.zz.test.javafxmvn.commontool.threadtool.SpringUtils;
import com.zz.test.javafxmvn.maintabview.service.LoginService;

import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

/**
 * 
 * <note>
 * Desc： 登陆进程的controller类
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-16 22:02:54
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-16 22:02:54    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@FXMLController
public class LoginController {

	/*@Autowired
	private LoginService logSer;*/
	//不能通过注解方式获取实体，因为启动类不是该LoginFxmlView，而是MainFxmlView，
	//所以无法把具有fxmlcontroller的LoginController和容器依赖起来。此时暂时通过getBean方式获取。
	private LoginService logSer = (LoginService) SpringUtils.getBean("loginService");
	

	/**
	 * 登陆进程的table容器
	 */
	@FXML
	private Pane login_table;
	/**
	 * Desc:查询登陆进程列表
	 * @author jld.zhangzhou
	 * @datetime 2020-04-16 17:04:22
	 * @modify_record:
	 * @param event
	 */
	@FXML
	public void loginSerch(ActionEvent event) {
		Button btn = (Button) event.getSource();
		List<PyProcess> list = logSer.loginSerch();
		TableView<PyProcess> table = new TableView<>();
		ObservableList<PyProcess> data = FXCollections.observableArrayList(list);
	
		
		/*TagTool.TableTool.initTable( tableView, String [] args, List<T> list);
		 table.setEditable(true);
		 
	        Callback<TableColumn<Person, String>, 
	            TableCell<Person, String>> cellFactory
	                = (TableColumn<Person, String> p) -> new EditingCell();
	 
	        TableColumn<Person, String> firstNameCol = 
	            new TableColumn<>("First Name");
	        TableColumn<Person, String> lastNameCol = 
	            new TableColumn<>("Last Name");
	        TableColumn<Person, String> emailCol = 
	            new TableColumn<>("Email");
	 
	        firstNameCol.setMinWidth(100);
	        firstNameCol.setCellValueFactory(
	            new PropertyValueFactory<>("firstName"));
	        firstNameCol.setCellFactory(cellFactory);        
	        firstNameCol.setOnEditCommit(
	            (CellEditEvent<Person, String> t) -> {
	                ((Person) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setFirstName(t.getNewValue());
	        });
	 
	 
	        lastNameCol.setMinWidth(100);
	        lastNameCol.setCellValueFactory(
	            new PropertyValueFactory<>("lastName"));
	        lastNameCol.setCellFactory(cellFactory);
	        lastNameCol.setOnEditCommit(
	            (CellEditEvent<Person, String> t) -> {
	                ((Person) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setLastName(t.getNewValue());
	        });
	 
	        emailCol.setMinWidth(200);
	        emailCol.setCellValueFactory(
	            new PropertyValueFactory<>("email"));
	        emailCol.setCellFactory(cellFactory);
	        emailCol.setOnEditCommit(
	            (CellEditEvent<Person, String> t) -> {
	                ((Person) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setEmail(t.getNewValue());
	        });
	 
	        table.setItems(data);
	        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
	 
	        final TextField addFirstName = new TextField();
	        addFirstName.setPromptText("First Name");
	        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
	        final TextField addLastName = new TextField();
	        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
	        addLastName.setPromptText("Last Name");
	        final TextField addEmail = new TextField();
	        addEmail.setMaxWidth(emailCol.getPrefWidth());
	        addEmail.setPromptText("Email");
	 
	        final Button addButton = new Button("Add");
	        addButton.setOnAction((ActionEvent e) -> {
	            data.add(new Person(
	                    addFirstName.getText(),
	                    addLastName.getText(),
	                    addEmail.getText()));
	            addFirstName.clear();
	            addLastName.clear();
	            addEmail.clear();
	        });
	 
	        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
	        hb.setSpacing(3);
	 
	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.getChildren().addAll(label, table, hb);
	 
	        ((Group) scene.getRoot()).getChildren().addAll(vbox);
	 
	        stage.setScene(scene);
	        stage.show();*/
	}

	/*@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		resourceBundle = resources;
	}*/
}
