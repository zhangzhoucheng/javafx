package com.zz.test.javafxmvn.commontag;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import org.junit.Test;

import com.zz.test.javafxmvn.common.entity.PyProcess;
import com.zz.test.javafxmvn.commontool.threadtool.ButiToolClassZz;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
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

	
	@Test
	public void test() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		int a = 13;
		String a1="14";
		Integer a2 =3;
		
		System.out.println(a2.getClass().getTypeName());
		System.out.println(a1.getClass().getTypeName());
		PyProcess p = new PyProcess();
		Object  oo = p.getClass().newInstance();
		Field theField = p.getClass().getDeclaredField("processCode");
		theField.setAccessible(true);//设置可见
		String o1="1";
		Object o = theField.get(oo);
		System.out.println(oo.getClass().getTypeName());

	}
}
