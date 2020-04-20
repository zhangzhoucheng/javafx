package com.zz.test.javafxmvn.main.view;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


import com.zz.test.javafxmvn.commonbean.BaseObjectView;
import com.zz.test.javafxmvn.commonbean.MenuNode;
import com.zz.test.javafxmvn.commontag.TagTool;
import com.zz.test.javafxmvn.commontool.KeyValTool;
import com.zz.test.javafxmvn.commontool.redis.service.CacheService;
import com.zz.test.javafxmvn.commontool.redis.service.DictService;
import com.zz.test.javafxmvn.main.service.MainService;


import de.felixroske.jfxsupport.FXMLView;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
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
	
	/**
	 * 获取缓存的service
	 */
	@Autowired 
	CacheService cacheService;
	
	@Autowired
	private MainService ms;
	public static Parent parent = null;
	public static TreeView<String> mainTreeView = null;
	@PostConstruct
	 void initUI() throws Exception {
		List<Map<String, String>> map = cacheService.queryMainTreeView("mainTreeView");//获取缓存，从redis（new boot 2.x Lettuce redis not jdis)
		List<Map<String, String>> map1 = cacheService.queryMainTreeViewBySpring(null);
		// 初始化界面，恩，主要是初始化界面的样式，因为我想让他可以换皮肤和主题
		// UIUtils.configUI((BorderPane)this.getView(), config);
		parent = this.getView();
		SplitPane sp = (SplitPane) parent.lookup("#splitpane_main");//获取splitpane
		
		Pane paneForTree = (Pane) sp.getItems().get(0).lookup("#treeview_main");//获取存放treeview的pane(注意：此处需要要先获取SplitPane，不然直接通过#treeview_main定位不到。
		

		/*SVGGlyph svg = new SVGGlyph("M40,60 C42,48 44,30 25,32");
		TreeItem<String> rootItem = new TreeItem<String>("项目所有", svg);//构建根菜单 //new ImageView("/img/treeview/logo.jpg")
		rootItem.setExpanded(true);*/
		
		long start = System.currentTimeMillis();
		
		mainTreeView = MenuNode.getMenuTreeview(MenuNode.getMenu(this.ms.getProcessList(), new MenuNode("-1", "0", "项目所有", true, "")));
		mainTreeView.setShowRoot(false);//展示根菜单
	    //treeView.setEditable(true);//可编辑
	    //treeView.setCellFactory((TreeView<String> p)-> new TextFieldTreeCellImpl()); 界面操作新加菜单，详情看TreeViewSample
	    
		//treeView.setOnMouseEntered(this.mainTreeViewEnter(treeView));
		mainTreeView.setOnMouseClicked(this.mainTreeViewClick(mainTreeView));//点击菜单事件
	
		
		long end = System.currentTimeMillis();
		logger.warn("@@@time,initUI,getMenuTreeview:"+(end-start));
		
		paneForTree.getChildren().add(mainTreeView);//添加菜单渲染
		
		TabPane tbp = (TabPane) sp.getItems().get(1).lookup("#main_tabpane");
		
		for(Tab tab : tbp.getTabs()) {
			tab.setOnSelectionChanged(this.mainTabSelectionChanged(tab));//添加select被改变事件。
		}
		//tbp.getTabs().addEventHandler(MouseEvent.MOUSE_CLICKED, this.mainTabPaneClick(tbp));//此处给tab添加点击事件，导致点击下方区域框内容也触发。
		
		
		//treeviewProcess.setRoot(item);
		//new JMetro(JMetro.Style.LIGHT).applyTheme(parent);
		
		/*
		 * parent.setLayoutX(1080); parent.setLayoutY(960);
		 * parent.setTranslateX(500.0);
		 */
		this.logger.warn("MainFxmlView initUI");
	}
	
	/**
	 * Desc:主treeview点击事件
	 * @author jld.zhangzhou
	 * @datetime 2020-04-14 17:37:55
	 * @modify_record:
	 * @param treeView
	 * @return
	 */
	private EventHandler<MouseEvent> mainTreeViewClick(TreeView<String> treeView){
		return (new EventHandler<MouseEvent>()//定义点击事件
	    {
	        @Override
	        public void handle(MouseEvent mouseEvent)
	        {
	            if(mouseEvent.getClickCount() == 1)
	            {
	                TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
	                if(item == null) {
	                	return;
	                }
	                if(item.isLeaf()) {
	            		SplitPane sp = (SplitPane) parent.lookup("#splitpane_main");//获取splitpane
	            		TabPane tbp = (TabPane) sp.getItems().get(1).lookup("#main_tabpane");
	            		Tab choiceTab = null;
	            		for(Tab t : tbp.getTabs()) {//存在该tab则不新增，而是选中。
	            			if(t.getText().equals(item.getValue())) {
	            				TagTool.tabPaneSelectionModel(tbp, t);
	            				//mainTabPaneClickIntFlag = tbp.getTabs().indexOf(t);//标记当前最后的被点击的tab
	            				return;
	            			}
	            			
	            		}
	            		choiceTab = new Tab(item.getValue());
	            		choiceTab.setId(KeyValTool.getKeyByVal(item.getValue()));
	            		tbp.getTabs().add(choiceTab);
	            		TagTool.tabPaneSelectionModel(tbp, choiceTab);//设置选中
	            		String tabFxml = KeyValTool.mainTreeViewCode2MenuNode.get(choiceTab.getId()).getPath();
	            		if(!StringUtils.isBlank(tabFxml)) {
	            			//新建ScrollPane放入tab，之后tab渲染该菜单对应页面
		            		ScrollPane theSp = new ScrollPane();
		            		theSp.setPrefHeight(835);
		            		theSp.setPrefWidth(1395);
		            		choiceTab.setContent(theSp);
		            		try {
		            			TagTool.paneLoadFxmlSimple(theSp, tabFxml, getClass());
								//TagTool.paneLoadFxml(theSp, tabFxml);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	            		}
	            		
	            		choiceTab.setOnSelectionChanged(mainTabSelectionChanged(choiceTab));//新加的tab加上setOnSelectionChanged事件。
	          
	            		
	                }
	            }
	        }
	    });
		
	}
	
	/**
	 * 标记之前最后被选中的tab index
	 */
	private int mainTabPaneClickIntFlag = -1;
	private EventHandler<Event> mainTabSelectionChanged(Tab tab){
		
		return (new EventHandler<Event>() {
			
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				/*SingleSelectionModel<Tab> select = tab.gets();
				int i1 = select.getSelectedIndex();
				int i2 =select.getSelectedIndex();
				System.out.println("mainTabPaneClick"+select.getSelectedItem().getText());*/
			}

			@Override
			public void handle(Event event) {
				if(tab.selectedProperty().get()==true) {//当前被选中的tab
					TreeItem<String> item1 = KeyValTool.mainTreeViewCode2Item.get(tab.getId());
					mainTreeView.getSelectionModel().select(item1);
					//System.out.println(tab.getId());
				}else {
					TreeItem<String> item = KeyValTool.mainTreeViewCode2Item.get(tab.getId());
					
					//System.out.println(tab.getId());
				}
				
			}
		});
		
		
	}
	
	/**
	 * Desc:鼠标进入事件，问题是，怎样获取进入的treeview哪个item
	 * @author jld.zhangzhou
	 * @datetime 2020-04-14 18:51:41
	 * @modify_record:
	 * @param treeView
	 * @return
	 */
	private EventHandler<MouseEvent> mainTreeViewEnter(TreeView<String> treeView){
		
		return (new EventHandler<MouseEvent>()//定义点击事件
	    {
	        @Override
	        public void handle(MouseEvent mouseEvent)
	        {
	            if(mouseEvent.getEventType()==MouseEvent.MOUSE_ENTERED)
	            {	
	            	//System.out.println("t:"+ treeView.onMouseEnteredProperty().getBean());
	                TreeItem<String> item = treeView.getFocusModel().getFocusedItem();
	                if(item.getChildren().isEmpty()) {
	                	//System.out.println("Selected yezi Text : " + item.getValue());
	                }
	                //System.out.println("Selected Text : " + item.getValue());

	                if (item.getValue()=="Upload to HDFS") {
	              
	                }
	            }
	        }
	    });
	}
	
	
}
