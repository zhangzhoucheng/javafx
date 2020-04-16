package com.zz.test.javafxmvn.maintabview.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.zz.test.javafxmvn.maintabview.service.LoginService;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@FXMLController
public class LoginController {
	@Autowired
	private LoginService logSer;
	public void tet() {
		
		
	}
	
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
		this.logSer.loginSerch();

	}
}
