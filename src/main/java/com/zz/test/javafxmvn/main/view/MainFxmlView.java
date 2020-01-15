package com.zz.test.javafxmvn.main.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLView;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;


/**
 * 
 * <note>
 * Desc： 
 *      下面简单的代码就可以使类与fxml关联，首先可以在view包创建一个class  extends AbstractFxmlView，加上@FXMLView注解，
 *      注解的属性有fxml文件的位置value，绑定i18n资源文件位置的bundle，和编码格式encoding，css样式文件位置的css和舞台风
 *      格stageStyle还有title
 *      from:https://www.twblogs.net/a/5c1fb9aabd9eee16b3dabc99/zh-cn
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-01-15 16:17:11
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-01-15 16:17:11    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@FXMLView(value = "/view/main/Main.fxml",title = "index",bundle = "i18n.index",encoding = "utf-8")
public class MainFxmlView extends AbstractFxmlView{
	// 注入的是一个properties对应的class
	 //@Autowired
	 //private ApplicationConfig config;

	 @PostConstruct
	 protected void initUI() throws Exception {
	   // 初始化界面，恩，主要是初始化界面的样式，因为我想让他可以换皮肤和主题
	   //UIUtils.configUI((BorderPane)this.getView(), config);
		 Parent parent = this.getView();
		 parent.setLayoutX(1080);
		 parent.setLayoutY(960);
		 
		 System.out.println("@@@@@@@@@@initUI");
	 }
}
