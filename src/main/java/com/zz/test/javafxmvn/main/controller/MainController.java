package com.zz.test.javafxmvn.main.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.zz.test.javafxmvn.main.service.MainService;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * 
 * <note>
 * Desc：主控制类 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-01-13 14:32:04
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-01-13 14:32:04    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
public class MainController {
	
	@Autowired
	private MainService ms;

	@FXMLController
    public void handlerBtnClick(ActionEvent event) {
        Button btnSource = (Button) event.getSource();
        ms.handlerBtnClick();
        btnSource.setText("I am clicked!");
        
    }
	
	/**
	 * Desc:获取进程列表
	 * @author jld.zhangzhou
	 * @datetime 2020-01-14 17:51:29
	 * @modify_record:
	 * @param event
	 */
	@FXMLController
    public void getProcessList(ActionEvent event) {
        Button btnSource = (Button) event.getSource();
        btnSource.setText("I am clicked!");
        
        
    }

}
