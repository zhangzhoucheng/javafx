package com.zz.test.javafxmvn.commontag;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.sun.javafx.scene.control.skin.LabeledText;
import com.sun.javafx.scene.control.skin.PaginationSkin;
import com.zz.test.javafxmvn.common.entity.PyProcess;
import com.zz.test.javafxmvn.commontool.RegexpTool;
import com.zz.test.javafxmvn.commontool.threadtool.ButiToolClassZz;

import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Skin;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

public  class TagBase {
	class CustomTab extends Tab {

	}

	public class EditingCell<T> extends TableCell<T, Object> {

		private TextField textField;

		public EditingCell(){
			
		}
		
		public EditingCell(T t) {
		}

		@Override
		public void startEdit() {
			if (!isEmpty()) {
				super.startEdit();
				createTextField();
				setText(null);
				setGraphic(textField);
				textField.selectAll();
			}
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();
String a = getItem() + "" ;
			setText(getItem() + "" );
			setGraphic(null);
		}

		@Override
		public void updateItem(Object item, boolean empty) {
			super.updateItem(item, empty);

			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				if (isEditing()) {
					if (textField != null) {
						textField.setText(getString());
					}
					setText(null);
					setGraphic(textField);
				} else {
					setText(getString());
					setGraphic(null);
				}
			}
		}

		private void createTextField() {
			textField = new TextField(getString());
			textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
			textField.focusedProperty()
					.addListener((ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) -> {
						if (!arg2) {
							commitEdit(textField.getText());
						}
					});
		}

		private String getString() {
			return getItem() == null ? "" : getItem().toString();
		}
	}
	
	/**
	 * 
	 * <note>
	 * Desc： 自己实现的stringConverter类
	 * @author jld.zhangzhou
	 * @email idiot_jillidan@163.com;
	 * @re be willing to communicate
	 * @refactor for jld
	 * @datetime 2020-04-28 00:11:06
	 * @location mobile base 3th,BeiJing 
	 * version  1.0
	 *  
	 * @REVISIONS: 
	 * Version 	        Date 		         Author             Location                   Description          
	 * ------------------------------------------------------------------------------------------------------  
	 * 1.0 		  2020-04-28 00:11:06    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
	 * </note>
	 */
	public class MyStringConverter extends StringConverter<Object>{

		private Field field;
		public MyStringConverter() {
			
		}
		
		public MyStringConverter(Field field) {
			this.field = field;
		}

		@Override
		public String toString(Object object) {
			if(object == null) {
				return "";
			}
			return object + "";
		}

		@Override
		public Object fromString(String string) {
			return ButiToolClassZz.ReflexRel.reflexTypeParseFromValToField(string, field);
		}



		public Field getField() {
			return field;
		}

		public void setField(Field field) {
			this.field = field;
		}

	
		
		
	}

	/**
	 * 
	 * <note>
	 * Desc： 针对Pagination 没有选择跳转问题，进行了代码相关方法的重构补充
	 * @author jld.zhangzhou
	 * @email idiot_jillidan@163.com;
	 * @re be willing to communicate
	 * @refactor for jld
	 * @datetime 2020-05-09 17:05:15
	 * @location mobile base 3th,BeiJing 
	 * version  1.0
	 *  
	 * @REVISIONS: 
	 * Version 	        Date 		         Author             Location                   Description          
	 * ------------------------------------------------------------------------------------------------------  
	 * 1.0 		  2020-05-09 17:05:15    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
	 * </note>
	 */
	public class PaginationButi extends Pagination{
		public PaginationButi() {
			super();
		}
		/*public PaginationButi(int pageCount, int pageIndex) {
	       super(pageCount,pageIndex);
	    }*/

		
		/**
		 * 非常精美的重构
		 */
	   @Override protected Skin<?> createDefaultSkin() {
		   	System.out.println(1);
		    Skin<?> skin = new PaginationSkinButi(this);
			//丰富pagination 下方样式，加上跳转页。
			StackPane ps=	(StackPane)this.getChildrenUnmodifiable().get(2);
			HBox b =(HBox)(((StackPane)this.getChildrenUnmodifiable().get(2)).getChildren().get(0));
			//((HBox)(((StackPane)thePagination.getChildrenUnmodifiable().get(2)).getChildren().get(0))).getChildren().add(e)
			
			if(true) {
				TextField tf = new TextField();
				tf.setPrefWidth(50);
				tf.setPromptText("页码");
				/*tf.setOnInputMethodTextChanged((InputMethodEvent e) -> {
					
				});*/
				tf.textProperty().addListener((ObservableValue<? extends String> observable, String oldt, String newt) -> {
					System.out.println(newt);
				});
				Button bt = new Button("跳转");
				bt.setOnMouseClicked((MouseEvent e) -> {
					if(e.getClickCount() == 1) {
						if(StringUtils.isNotBlank(tf.getText())) {
							if(RegexpTool.isMatch(tf.getText(), "[1-9][0-9]{0,}")) {
								//this.getPageFactory().call(Integer.parseInt(tf.getText()));
						  this.setCurrentPageIndex(Integer.parseInt(tf.getText()) - 1);//本身当前页这个属性加了触发事件，来查询该页码对应内容。
							}
						}
						
					}
				});
				System.out.println("acc:"+this.getAccessibleText());
				Label lab = new Label(String.format("  总数:%s", RegexpTool.getContent4LR(this.getAccessibleText(), "totalRecords_", "_")));
				lab.setId("table_sum_lab");
				b.getChildren().addAll(tf, bt, lab);
			}
			
			// Skin<?> skin = new PaginationSkin(this);
	        return skin;
	    }
	}
	

	@Test
	public void test() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		int a = 13;
		RegexpTool.isMatch("3", "[1-9][0-9]{0,}");
		

	}
}
