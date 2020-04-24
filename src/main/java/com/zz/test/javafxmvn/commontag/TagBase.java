package com.zz.test.javafxmvn.commontag;


import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

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

			setText((String) getItem());
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

}
