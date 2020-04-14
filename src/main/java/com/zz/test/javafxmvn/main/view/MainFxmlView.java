package com.zz.test.javafxmvn.main.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.jfoenix.svg.SVGGlyph;
import com.zz.test.javafxmvn.commonbean.BaseObjectView;
import com.zz.test.javafxmvn.commonbean.MenuNode;
import com.zz.test.javafxmvn.main.service.MainService;

import de.felixroske.jfxsupport.FXMLView;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;


/**
 * 
 * <note> Desc： 下面简单的代码就可以使类与fxml关联，首先可以在view包创建一个class extends
 * AbstractFxmlView，加上@FXMLView注解，
 * 注解的属性有fxml文件的位置value，绑定i18n资源文件位置的bundle，和编码格式encoding，css样式文件位置的css和舞台风
 * 格stageStyle还有title
 * from:https://www.twblogs.net/a/5c1fb9aabd9eee16b3dabc99/zh-cn
 * 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-01-15 16:17:11
 * @location mobile base 3th,BeiJing version 1.0
 * 
 * @REVISIONS: Version Date Author Location Description
 *             ------------------------------------------------------------------------------------------------------
 *             1.0 2020-01-15 16:17:11 jld.zhangzhou mobile base 3th,BeiJing
 *             1.create the class </note>
 */
@FXMLView(value = "/view/main/Main.fxml", title = "jld zz world!", bundle = "i18n.Main", encoding = "utf-8",css = {"/view/main/main.css"})
public class MainFxmlView extends BaseObjectView {
	// 注入的是一个properties对应的class
	// @Autowired
	// private ApplicationConfig config;
	
	//初始化时候，不是通过界面点击等操作来赋值，所以用下列parent.lookup 方法；
	//@FXML
	//private TreeView<String> treeviewProcess;
	
	@Autowired
	private MainService ms;


	@PostConstruct
	 void initUI() throws Exception {
		// 初始化界面，恩，主要是初始化界面的样式，因为我想让他可以换皮肤和主题
		// UIUtils.configUI((BorderPane)this.getView(), config);

		Parent parent = this.getView();//获取当前parent
		
		SplitPane sp = (SplitPane) parent.lookup("#splitpane_main");//获取splitpane
		
		Pane paneForTree = (Pane) sp.getItems().get(0).lookup("#treeview_main");//获取存放treeview的pane(注意：此处需要要先获取SplitPane，不然直接通过#treeview_main定位不到。
		

		/*SVGGlyph svg = new SVGGlyph("M40,60 C42,48 44,30 25,32");
		TreeItem<String> rootItem = new TreeItem<String>("项目所有", svg);//构建根菜单 //new ImageView("/img/treeview/logo.jpg")
		rootItem.setExpanded(true);*/
		
		long start = System.currentTimeMillis();
		
		TreeView<String> treeView = MenuNode.getMenuTreeview(MenuNode.getMenu(this.ms.getProcessList(), new MenuNode("-1", "0", "项目所有", true, "")));
		treeView.setShowRoot(false);//展示根菜单
	    //treeView.setEditable(true);//可编辑
	    //treeView.setCellFactory((TreeView<String> p)-> new TextFieldTreeCellImpl()); 界面操作新加菜单，详情看TreeViewSample
	        
		long end = System.currentTimeMillis();
		logger.warn("@@@time,initUI,getMenuTreeview:"+(end-start));
		
		paneForTree.getChildren().add(treeView);

		
		//treeviewProcess.setRoot(item);
		//new JMetro(JMetro.Style.LIGHT).applyTheme(parent);
		
		/*
		 * parent.setLayoutX(1080); parent.setLayoutY(960);
		 * parent.setTranslateX(500.0);
		 */
		this.logger.warn("MainFxmlView initUI");
	}
}
