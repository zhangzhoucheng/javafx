package com.zz.test.javafxmvn.commontag;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.event.TableColumnModelEvent;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.zz.test.javafxmvn.common.aop.PaginationAopAno;
import com.zz.test.javafxmvn.common.entity.PyProcess;
import com.zz.test.javafxmvn.commonbean.CommonRequest;
import com.zz.test.javafxmvn.commonbean.CommonResult;
import com.zz.test.javafxmvn.commonbean.Constants;
import com.zz.test.javafxmvn.commonbean.PageCommonRequest;
import com.zz.test.javafxmvn.commonbean.PageCommonResult;
import com.zz.test.javafxmvn.commoninterface.Function;
import com.zz.test.javafxmvn.commontag.TagBase.PaginationButi;
import com.zz.test.javafxmvn.commontool.RegexpTool;
import com.zz.test.javafxmvn.commontool.threadtool.ButiToolClassZz;
import com.zz.test.javafxmvn.commontool.threadtool.ThreadPollTool;
import com.zz.test.javafxmvn.main.Main;
import com.zz.test.javafxmvn.main.view.MainFxmlView;
import com.zz.test.javafxmvn.maintabview.view.LoginFxmlView;

import ch.qos.logback.core.joran.action.Action;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * 
 * <note> Desc：javafx 标签相关的便捷操作封装。
 * 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-15 16:31:03
 * @location mobile base 3th,BeiJing version 1.0
 * 
 * @REVISIONS: Version Date Author Location Description
 *             ------------------------------------------------------------------------------------------------------
 *             1.0 2020-04-15 16:31:03 jld.zhangzhou mobile base 3th,BeiJing
 *             1.create the class </note>
 */

public class TagTool {
	/**
	 * Desc:设置tabPane选中tab，其他clear
	 * 
	 * @author jld.zhangzhou
	 * @datetime 2020-04-15 16:30:36
	 * @modify_record:
	 * @param tap
	 */
	public static void tabPaneSelectionModel(TabPane tbp, Tab tab) {
		SingleSelectionModel<Tab> select = tbp.getSelectionModel();

		// select.clearSelection();//该方法并不能得到想要的目的，（先清空所有被selected，之后设置select）
		select.select(tab);
	}

	/**
	 * Desc:通过名称获取TreeItem中的TreeItem
	 * 
	 * @author jld.zhangzhou
	 * @datetime 2020-04-15 20:30:33
	 * @modify_record:
	 * @param item
	 * @param value
	 * @return
	 */
	public static TreeItem getTreeViewItem(TreeItem<String> item, String value) {
		if (item != null && item.getValue().equals(value))
			return item;

		for (TreeItem<String> child : item.getChildren()) {
			TreeItem<String> s = getTreeViewItem(child, value);
			if (s != null)
				return s;

		}
		return null;
	}

	/**
	 * Desc:把fxml对应内容装入ScrollPane
	 * 
	 * @author jld.zhangzhou
	 * @datetime 2020-04-16 13:09:58
	 * @modify_record:
	 * @param pane
	 * @param fxml
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("finally")
	public static Initializable paneLoadFxml(Object pane, String fxml) throws IOException {
		FXMLLoader loader = new FXMLLoader(); // 创建对象

		loader.setBuilderFactory(new JavaFXBuilderFactory()); // BuilderFactory设置
		URL a = LoginFxmlView.class.getResource(fxml);
		loader.setLocation(LoginFxmlView.class.getResource(fxml)); // 设置路径基础
		InputStream in = null;
		try {
			in = Main.class.getResourceAsStream(fxml);
			AnchorPane page = (AnchorPane) loader.load(in); //

			// Scene scene = new Scene(page, 800, 600);
			// Stage.setScene(scene);
			// stage.sizeToScene();//可以参照https://blog.csdn.net/baidu_30325009/article/details/93321504，完成场景切换。
			if (pane.getClass() == Pane.class) {
				((Pane) pane).getChildren().clear();
				((Pane) pane).getChildren().add(page);
			}
			if (pane.getClass() == ScrollPane.class) {
				((ScrollPane) pane).getChildrenUnmodifiable().clear();
				((ScrollPane) pane).setContent(page);
			}
			// sp.getChildrenUnmodifiable().add(page);
			return (Initializable) loader.getController(); // 这样得到Controller

		} finally {
			in.close();
			return null;
		}

	}

	/**
	 * Desc:在pane中加载资源fxml，和paneLoadFxml方法功能一样。
	 * 
	 * @author jld.zhangzhou
	 * @datetime 2020-04-16 22:56:39
	 * @modify_record:
	 * @param pane
	 * @param fxml
	 * @param obj
	 *            of ScrollPane or Pane
	 * @throws IOException
	 */
	public static void paneLoadFxmlSimple(Object pane, String fxml, Object obj) throws IOException {
		if (pane.getClass() == Pane.class) {
			((Pane) pane).getChildren().clear();
			((Pane) pane).getChildren().add(FXMLLoader.load(((Class<?>) obj).getResource(fxml)));
		}
		if (pane.getClass() == ScrollPane.class) {
			((ScrollPane) pane).getChildrenUnmodifiable().clear();
			((ScrollPane) pane).setContent(FXMLLoader.load(((Class<?>) obj).getResource(fxml)));
		}
		
	}

	/**
	 * 
	 * <note> Desc：表格操作的工具类 (静态类，泛型化，泛型静态方法）
	 * 
	 * @author jld.zhangzhou
	 * @email idiot_jillidan@163.com;
	 * @re be willing to communicate
	 * @refactor for jld
	 * @datetime 2020-04-17 08:52:18
	 * @location mobile base 3th,BeiJing version 1.0
	 * 
	 * @REVISIONS: Version Date Author Location Description
	 *             ------------------------------------------------------------------------------------------------------
	 *             1.0 2020-04-17 08:52:18 jld.zhangzhou mobile base 3th,BeiJing
	 *             1.create the class </note>
	 */
	public static class TableTool<T> {
		/**
		 * 表格默认宽度
		 */
		static final int defaultWidth = 100;
		
		static final Double defaultTableWidth = 1720.0;

		/**
		 * Desc:通过传入的cols，和list<T>渲染T列表
		 * 
		 * @author jld.zhangzhou
		 * @datetime 2020-04-23 12:43:08
		 * @modify_record:
		 * @param cols
		 * @param list
		 * @return
		 */
		public static<T> TableView initTable(String[] cols, List<T> list, int defaultWidth) {
			if (defaultWidth <= 0) {
				defaultWidth = TableTool.defaultWidth;
			}
			TableView<T> table = new TableView<>();
			String[] fieldsHead = cols; /*
										 * { "进程编码_$c_processCode_$w_100", "进程名_$processName", "进程类型_$typeCode",
										 * "进程描述_$processRemark", "进程开关_$processStatus", "进程可用_$disable",
										 * "定时任务_$processCron", "定时任务校准_$cronCalibration",
										 * "by主任务启动标记_$everyDayStartFlag", "执行最大次数_$executeMax",
										 * "任务限制执行秒数_$processLimittime", };
										 */
			table.setPrefWidth(1325);

			ObservableList<T> data = FXCollections.observableArrayList(list);

			// TagTool.TableTool.initTable( tableView, String [] args, List<T> list);
			table.setEditable(true);

			Callback<TableColumn<T, Object>, TableCell<T, Object>> cellFactory = (
					TableColumn<T, Object> p) -> new TagBase().new EditingCell();
			List<TableColumn<T, Object>> listTabCol = new ArrayList();

			int i = 0;
			for (String head : fieldsHead) {
				TableColumn<T, Object> col = new TableColumn<>(fieldsHead[i].substring(0, fieldsHead[i].indexOf("_$")));
				Object w_ = RegexpTool.getContent4LR(fieldsHead[i], "_\\$w_", "_");
				Object c_ = RegexpTool.getContent4LR(fieldsHead[i], "_\\$c_", "_");
				col.setPrefWidth(w_ == null ? defaultWidth : Integer.parseInt((String) w_));

				col.setCellValueFactory(new PropertyValueFactory<>((String) c_));
				
				col.setCellFactory(cellFactory);
				col.setOnEditCommit((CellEditEvent<T, Object> t) -> {
					Object o = ((T) t.getTableView().getItems().get(t.getTablePosition().getRow()));
					try {
						ButiToolClassZz.ReflexRel.reflexObjectSetFieldVal(o, (String) c_, t.getNewValue());
					} catch (NoSuchFieldException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				});
				listTabCol.add(col);
				i++;
			}

			table.setItems(data);
			table.getColumns().addAll(listTabCol);

			return table;
		}
		
		/**
		 * Desc:Desc:通过传入的cols，和list<T>渲染T列表
		 * @author jld.zhangzhou
		 * @datetime 2020-05-05 21:52:27
		 * @modify_record:
		 * @param cols
		 * @param list
		 * @param defaultWidth
		 * @param editAble
		 * @return
		 * @throws InstantiationException
		 * @throws IllegalAccessException
		 */
		public  static<T> TableView initTableOld(List<TableHeadFields> cols, List<T> list, int defaultWidth, Boolean editAble){
			try {
				return initTableOldWidth(cols, list, defaultWidth, editAble, -1.0);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
			 
		
		/**
		 * Desc:通过传入的cols，和list<T>渲染T列表,包括表宽度
		 * 
		 * @author jld.zhangzhou
		 * @datetime 2020-04-23 12:43:08
		 * @modify_record:
		 * @param cols
		 * @param list
		 * @param editAble
		 * @return
		 * @throws IllegalAccessException 
		 * @throws InstantiationException 
		 */
		public  static<T> TableView initTableOldWidth(List<TableHeadFields> cols, List<T> list, int defaultWidth, Boolean editAble, Double width) throws InstantiationException, IllegalAccessException {
			if (defaultWidth <= 0) {
				defaultWidth = TableTool.defaultWidth;
			}
			TableView<T> table = new TableView<>();
			List<TableHeadFields> fieldsHead = cols; /*
										 * { "进程编码_$c_processCode_$w_100", "进程名_$processName", "进程类型_$typeCode",
										 * "进程描述_$processRemark", "进程开关_$processStatus", "进程可用_$disable",
										 * "定时任务_$processCron", "定时任务校准_$cronCalibration",
										 * "by主任务启动标记_$everyDayStartFlag", "执行最大次数_$executeMax",
										 * "任务限制执行秒数_$processLimittime", };
										 */
			if(width > 0) {
				table.setPrefWidth(width);
			}
			
			ObservableList<T> data = FXCollections.observableArrayList(list);

			// TagTool.TableTool.initTable( tableView, String [] args, List<T> list);
			table.setEditable(editAble);
			table.setItems(data);
			/*Callback<TableColumn<T, R>, TableCell<T, R>> cellFactory = (
					TableColumn<T, R> p) -> new TextFieldTableCell<T,R>( ( new StringConverter<R>() {

						@Override
						public String toString(R object) {
							
							return ""+object;
						}

						@Override
						public R fromString(String string) {
							System.out.println(1);
							return  (R) ButiToolClassZz.ReflexRel.reflexTypeParseFromValToObject(string,(Object)R);
						}
					})
							);*/
			List<TableColumn<T, Object>> listTabCol = new ArrayList();
			
			 

			int i = 0;
			boolean checkboxEtcFlag = false;//对于存在多选的表格，会进行标记，用于触发事件
			for (TableHeadFields head : fieldsHead) {
				
				Object w_ = head.getWidth();
				Object c_ = head.getField();
				String node_ = head.getStartCheckBox();
				

				if(StringUtils.isNotBlank(node_)) {
					checkboxEtcFlag = true;
					if("CheckBoxTableCell".equals(node_)) {
						String field_ObservableValue = (String) c_;
						TableColumn<T,Boolean>	col = new TableColumn<T,Boolean>(head.getFieldName());
						col.setPrefWidth(w_ == null ? defaultWidth : Integer.parseInt((String) w_));

						//col.setCellValueFactory(new PropertyValueFactory<>((String) c_));
						//Object ObjectT = list.get(0).getClass().newInstance();
						
						//Callback<TableColumn<T, Boolean>, TableCell<T, Boolean>> cellFactory = (TableColumn<T, Boolean> p) -> new CheckBoxTableCell();
						/*Callback<Integer, ObservableValue<Boolean>>  callback = (param) -> {
							return null;
							
						};
						CheckBoxTableCell.forTableColumn(callback);
						
						col.setCellFactory(CheckBoxTableCell.forTableColumn((Callback<Integer, ObservableValue<Boolean>>) param -> {

					     return boolean;
					    }));*/
						
						col.setCellFactory(CheckBoxTableCell.forTableColumn((Callback<Integer, ObservableValue<Boolean>>) params -> {
							T t = table.getItems().get(params);	
							BooleanProperty bp;
							try {
								bp = (BooleanProperty) ButiToolClassZz.ReflexRel.reflexTGetFieldObject(t, field_ObservableValue);
							
								bp.addListener((observable, oldvalue, newvalue) -> {//监听事件，值改变，就会触发
									//Object o2 =observable;
									//System.out.println(oldvalue);
									//System.out.println(newvalue);
									return;
								});
								
								return bp;
							} catch (Exception e) {
								e.printStackTrace();
								return null;
							}
							
						}));
						/*col.setOnEditCommit((CellEditEvent<T, Boolean> t) -> {//checkbox无需处理
							Object o = ((T) t.getTableView().getItems().get(t.getTablePosition().getRow()));
							try {
								ButiToolClassZz.ReflexRel.reflexObjectSetFieldVal(o, (String) c_, t.getNewValue());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 

						});*/
						table.getColumns().add(col);
					
					
					
					
					
					}
				}else {
					TableColumn<T,Object> col = new TableColumn<T,Object>(head.getFieldName());
				
					col.setPrefWidth(w_ == null ? defaultWidth : Integer.parseInt((String) w_));
					col.setCellValueFactory(new PropertyValueFactory<>((String) c_));
				
					Callback<TableColumn<T, Object>, TableCell<T, Object>> cellFactory = (TableColumn<T, Object> p) -> new TextFieldTableCell(new TagBase().new MyStringConverter(ButiToolClassZz.ReflexRel.reflexTGetField(list.get(0), (String) c_)));
					
					col.setCellFactory(cellFactory);
					col.setOnEditCommit((CellEditEvent<T, Object> t) -> {
						
						Object o = ((T) t.getTableView().getItems().get(t.getTablePosition().getRow()));
						try {
							ButiToolClassZz.ReflexRel.reflexObjectSetFieldVal(o, (String) c_, t.getNewValue());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 

					});
					table.getColumns().add(col);
				
				
				}
				i++;
			}
			
			
				

			

			return table;
		}

		
		
		/**
		 * Desc:get Fields by fieldsHead[]
		 * 
		 * @author jld.zhangzhou
		 * @datetime 2020-04-23 13:20:19
		 * @modify_record:
		 * @param fieldsHead
		 * @return
		 */
		public static Map<String, Object> getFieldsByfieldsHead(String[] fieldsHead) {
			Map<String, Object> map = new HashMap();
			for (String head : fieldsHead) {
				String c_ = (String) RegexpTool.getContent4LR(head, "_\\$c_", "_");
				map.put(c_, null);
			}

			return map;
		}
		
		/**
		 * Desc:get fields's text value by  ObservableList<Node> and fieldsHead
		 * @author jld.zhangzhou
		 * @datetime 2020-04-23 21:09:16
		 * @modify_record:
		 * @param fieldsHead
		 * @param nodes
		 * @return
		 */
		public static Map<String, Object> getFieldsByfieldsHeadAndVal(TableHeadFields fieldsHead, ObservableList<Node> nodes) {
			Map<String, Object> map = new HashMap();
			for (TableHeadFields head : fieldsHead.getTableHeadFields()) {
				String c_ = head.getField();
				
				if(head.isNoAddFalg()) {
					continue;
				}
				
				map.put(c_, null);
			}

			
			for(Node node : nodes) {
				if(node instanceof TextField) {
					TextField t = (TextField) node;
					if(map.containsKey(t.getId())) {
						map.put(t.getId(), t.getText());
					}
					
					//t.clear();
				}
			}
			
			return map;
		}
		
		/**
		 * Desc:delete text of  T. eg:T=TextField
		 * @author jld.zhangzhou
		 * @datetime 2020-04-23 17:58:20
		 * @modify_record:
		 * @param textFields
		 */
		public static<T> void delTextOfTextFieldOrT(List<T> textFields) {
			for(T t : textFields) {

				/**
				 * T=TextField
				 */
				if(t instanceof TextField) {
					TextField tft = (TextField) t;
					tft.clear();
				}
				
			}
		}
		
/*		public  Class<T> getTClass()
	    {
	        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	        return tClass;
	    }*/
		
		public static<T> TableView<T> tablePage(Integer pageIndex, String getCommonResultMethod, CommonRequest request, 
				 List<TableHeadFields> cols, int defaultWidth, Boolean editAble) {
			((PageCommonRequest) request).setPageNo(pageIndex + 1);
			PageCommonResult comR;
			try {
				comR = (PageCommonResult) ButiToolClassZz.ReflexRel.reflexMethod(getCommonResultMethod.substring(getCommonResultMethod.lastIndexOf(".")+1), 
						getCommonResultMethod.substring(0,getCommonResultMethod.lastIndexOf(".")), request);
				TableView<T> table = TagTool.TableTool.initTableOld(cols, (List<T>) comR.getBody(), defaultWidth, editAble);
				//table.setAccessibleText(String.format("totalPages_%s_,totalRecords_%s_,", comR.getTotalPages(),comR.getTotalRecords()));
				return table;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} 			
			return null;
			
		}
		
		/**
		 * Desc:分页表格，获取分页的数据行
		 * @author jld.zhangzhou
		 * @datetime 2020-05-06 23:26:48
		 * @modify_record:
		 * @param pageIndex
		 * @param getCommonResultMethod
		 * @param request
		 * @param cols
		 * @param defaultWidth
		 * @param editAble
		 * @return
		 */
		public static<T> CommonResult tablePageCommonResult(Integer pageIndex, String getCommonResultMethod, CommonRequest request, 
				 List<TableHeadFields> cols, int defaultWidth, Boolean editAble) {
			((PageCommonRequest) request).setPageNo(pageIndex + 1);
			CommonResult comR;
			try {
				comR = (CommonResult) ButiToolClassZz.ReflexRel.reflexMethod(getCommonResultMethod.substring(getCommonResultMethod.lastIndexOf(".")+1), 
						getCommonResultMethod.substring(0,getCommonResultMethod.lastIndexOf(".")), request);
				//return TagTool.TableTool.initTableOld(cols, (List<T>) comR.getBody(), defaultWidth, editAble);
				return comR;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} 			
			return null;
			
		}

		/**
		 * Desc:分页表格，获取Pagination
		 * @author jld.zhangzhou
		 * @datetime 2020-05-06 23:27:36
		 * @modify_record:
		 * @param indicatorCount
		 * @param getCommonResultMethod
		 * @param request
		 * @param cols
		 * @param tableWidth
		 * @param defaultColumnWidth
		 * @param editAble
		 * @return
		 */
		
		public static <T> Pagination tablePagePagination(int indicatorCount, String getCommonResultMethod,CommonRequest request, List<TableHeadFields> cols, 
				int defaultColumnWidth,Boolean editAble) {

			PageCommonResult comR;

			//comR = (PageCommonResult) tablePageCommonResult(defaultWidth, getCommonResultMethod, request, cols, defaultWidth, editAble);
			//TableView<T> t = new TableView<T>();t.setAccessibleText("a");
			Pagination pagination = new TagBase().new PaginationButi();
			
			//tv = loginTable;
			pagination.setStyle("-fx-font-size:16");
			pagination.setMaxPageIndicatorCount(indicatorCount <= 0 ? ((PageCommonRequest) request).getPageSize() : indicatorCount);
			pagination.setPageFactory((Integer thePageIndex) -> {
				return tablePagePaginationFactory(thePageIndex, request, getCommonResultMethod, cols, editAble, pagination);
				//TableView<T> table = tablePage(thePageIndex, getCommonResultMethod, request, cols, defaultColumnWidth, editAble);
				//pagination.setPageCount(1);
				//return table;
				
							});
			return pagination;

		}
		
		/**
		 * Desc:分页表格,获取PageFactory Node
		 * @author jld.zhangzhou
		 * @datetime 2020-05-07 17:25:19
		 * @modify_record:
		 * @param thePageIndex
		 * @param request
		 * @param getCommonResultMethod
		 * @param cols
		 * @param editAble
		 * @param pagination
		 * @return
		 */
		public static<T>  TableView<T> tablePagePaginationFactory(int thePageIndex, CommonRequest request, String getCommonResultMethod, List<TableHeadFields> cols,
				Boolean editAble, Pagination pagination) {

			((PageCommonRequest) request).setPageNo(thePageIndex + 1);
			PageCommonResult pcomR;
			try {
				pcomR = (PageCommonResult) ButiToolClassZz.ReflexRel.reflexMethod(getCommonResultMethod.substring(getCommonResultMethod.lastIndexOf(".")+1), 
						getCommonResultMethod.substring(0,getCommonResultMethod.lastIndexOf(".")), request);
				TableView<T> table = TagTool.TableTool.initTableOld(cols, (List<T>) pcomR.getBody(), defaultWidth, editAble);
				
				pagination.setPageCount((int) pcomR.getTotalPages());
				
				/**
				 * 设置事实变更的总数
				 */
				Label lab = (Label) pagination.lookup("#table_sum_lab");
				if(lab != null) {
					lab.setText(String.format("  总数:%s", pcomR.getTotalRecords()));
				}
				
				pagination.setAccessibleText(String.format("totalPages_%s_,totalRecords_%s_,", pcomR.getTotalPages(),pcomR.getTotalRecords()));
				return table;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} 			
			return null;

		}
		
		/**
		 * Desc:分页表格,通过PagePagination 获取tableview<T>
		 * @author jld.zhangzhou
		 * @datetime 2020-05-07 17:27:24
		 * @modify_record:
		 * @param pagi
		 * @return
		 */
		public static<T>  TableView<T> tablePagePaginationGetTable(Pagination pagination){
			
			return ((StackPane) pagination.getChildrenUnmodifiable().get(0)).getChildren().size() == 0 ? (TableView<T>) ((StackPane) pagination.getChildrenUnmodifiable().get(1)).getChildren().get(0)
					: (TableView<T>) ((StackPane) pagination.getChildrenUnmodifiable().get(0)).getChildren().get(0);
		}
		

		/**
		 * Desc:传入paginationButi ，对function冲击
		 * 
		 *remrk:这是一个函数式接口非常buti的一个应用。通过传入funtion，从而把代码段传入，。
		 *     总用时每隔20毫秒冲击一次function，直到函数返回ture，或者冲击大于100次，也就是2秒.
		 *     其实最开始的实现仅仅是注释@@1部分的代码，后来发现其中变化的只要try{}部分的逻辑，索性把它做成函数式接口，用户可以
		 *     实现function，之后调用冲击函数asycfuctionFire，
		 * @author jld.zhangzhou
		 * @datetime 2020-05-14 17:22:52
		 * @modify_record:
		 * @param f
		 * @param p
		 * @throws InterruptedException
		 */
		public static void asycPaginationButiOper(Function<TagBase.PaginationButi, Boolean> function, TagBase.PaginationButi paginationButi) throws InterruptedException {
			ThreadPollTool.asycfuctionFire(function, paginationButi, 100, 20L);
			
			/*@@1
			 * int i = 0;
			while (true) {
				System.out.println("i:"+i);
				try {
					TableView<PyProcess> tab = TagTool.TableTool.tablePagePaginationGetTable(p);
					if (tab.getItems().size() > 0) {
						tab.getSelectionModel().selectedItemProperty().addListener(// 选中某一行
								new ChangeListener<PyProcess>() {
									@Override
									public void changed(ObservableValue<? extends PyProcess> observableValue,
											PyProcess oldItem, PyProcess newItem) {
										System.out.println("i:"+newItem.getProcessCode());
									}
								});
						break;

					}
					if(function.apply(paginationButi)) {
						break;
					}
					
				} catch (Exception e) {
					// 继续获取
					Thread.sleep(20);
					if(i>100) {
						break;
					}
				}
				i ++;

			}*/

		}
		
		/**
		 * Desc:公用的，传入一个实现的mouseEvent事件，通过PaginationButi 冲击，
		 * @author jld.zhangzhou
		 * @datetime 2020-05-14 18:13:06
		 * @modify_record:
		 * @param listener
		 * @param paginationButi
		 * @throws InterruptedException
		 */
		public static<T> void startPyMainTableClickRow(EventHandler<? super MouseEvent> mouseEvent, TagBase.PaginationButi paginationButi) throws InterruptedException {
			//ThreadPollTool.asycfuctionFire(function, paginationButi, 100, 20L);
			
			TagTool.TableTool.asycPaginationButiOper((TagBase.PaginationButi p1) -> {
				TableView<T> tab = TagTool.TableTool.tablePagePaginationGetTable(p1); 
				//Node n  = tab.getRowFactory().call(tab);
				if (tab.getItems().size() > 0) {
					//tab.getSelectionModel().selectedItemProperty().addListener(listener);//选择行改变事件
					tab.setOnMouseClicked(mouseEvent);
					return true;
				}
				return false;

			}, paginationButi);

		}
		

	}
	
	/**
	 * 
	 * <note> Desc：警告提示类
	 * 
	 * @author jld.zhangzhou
	 * @email idiot_jillidan@163.com;
	 * @re be willing to communicate
	 * @refactor for jld
	 * @datetime 2020-04-23 16:18:38
	 * @location mobile base 3th,BeiJing version 1.0
	 * 
	 * @REVISIONS: Version Date Author Location Description
	 *             ------------------------------------------------------------------------------------------------------
	 *             1.0 2020-04-23 16:18:38 jld.zhangzhou mobile base 3th,BeiJing
	 *             1.create the class </note>
	 */
	public static class AlertPrompt {

		public static void alertMsg(String title, String msg) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
            alert.titleProperty().set(title);
            alert.headerTextProperty().set(msg);
            alert.showAndWait();

		}
		
		/**
		 * Desc:提示主题框
		 * @author jld.zhangzhou
		 * @datetime 2020-04-29 11:00:27
		 * @modify_record:
		 * @param msg
		 */
		public static void alertMsg_prompt(String msg) {
			//alertEnum.
			alertMsg( Constants.AlertPromptEnum.PROMPT.getTitle(), msg);
		
		}
		
		/**
		 * Desc:警告主题框
		 * @author jld.zhangzhou
		 * @datetime 2020-04-29 11:00:41
		 * @modify_record:
		 * @param msg
		 */
		public static void alertMsg_warn(String msg) {
			alertMsg( Constants.AlertPromptEnum.WARN.getTitle(), msg);
		
		}
		
		/**
		 * Desc:异常主题框
		 * @author jld.zhangzhou
		 * @datetime 2020-04-29 11:18:18
		 * @modify_record:
		 * @param msg
		 */
		public static void alertMsg_exception(String msg) {
			alertMsg( Constants.AlertPromptEnum.EXCEPTION.getTitle(), msg);
		}
		
		/**
		 * Desc:错误主题框
		 * @author jld.zhangzhou
		 * @datetime 2020-04-29 11:00:41
		 * @modify_record:
		 * @param msg
		 */
		public static void alertMsg_error(String msg) {
			alertMsg( Constants.AlertPromptEnum.ERROR.getTitle(), msg);
		
		}
	}
	
	/**
	 * Desc:给TabPane 的tab 添加select改变事件(关于函数value中又传参问题？？？？？）
	 * 
	 * @author jld.zhangzhou
	 * @datetime 2020-04-15 21:16:13
	 * @modify_record:
	 * @param tabp
	 * @param value
	 */
	public static void tabsSetSelectChangeEvent(TabPane tabp, EventHandler<Event> value) {
		MainFxmlView a = new MainFxmlView();
		for (Tab tab : tabp.getTabs()) {
			Tab t = tab;
			tab.setOnSelectionChanged(value);// 添加select被改变事件。
		}
		
	}


	
}
