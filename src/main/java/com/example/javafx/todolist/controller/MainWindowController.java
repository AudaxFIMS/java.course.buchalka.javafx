package com.example.javafx.todolist.controller;

import com.example.javafx.todolist.datamodel.TodoData;
import com.example.javafx.todolist.datamodel.TodoItem;
import com.example.javafx.todolist.util.ResourceFileHelper;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

public class MainWindowController {
	@FXML
	private ListView todoListView;
	
	@FXML
	private TextArea itemDetailsText;
	
	@FXML
	private Label deadlineLabel;
	
	@FXML
	private BorderPane mainBorderPane;
	
	@FXML
	private ContextMenu listContextMenu;
	
	@FXML
	private ToggleButton filterToggleButton;
	
	public void initialize() {
		listContextMenu = new ContextMenu();
		
		MenuItem deleteMenuItems = new MenuItem("Delete");
		deleteMenuItems.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				TodoItem item = (TodoItem) todoListView.getSelectionModel().getSelectedItem();
				deleteItem(item);
			}
		});
		
		listContextMenu.getItems().add(deleteMenuItems);
		
		todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
			@Override
			public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem todoItem, TodoItem t1) {
				handleClickListView();
			}
		});
		refreshTodoItemList();
		refreshCellSettings();
		todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		todoListView.getSelectionModel().selectFirst();
	}
	
	private void refreshCellSettings() {
		todoListView.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {
			@Override
			public ListCell<TodoItem> call(ListView<TodoItem> todoItemListView) {
				ListCell<TodoItem> cell = new ListCell<TodoItem>() {
					@Override
					protected void updateItem(TodoItem item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setText(null);
						} else {
							setText(item.getShortDescription());
							if (item.getDeadline().isBefore(LocalDate.now()) || item.getDeadline().isEqual(LocalDate.now())) {
								setTextFill(Color.RED);
								
							}
						}
					}
				};
				
				cell.emptyProperty().addListener(
						(obs, wasEmpty, isNowEmpty) -> {
							if (isNowEmpty) {
								cell.setContextMenu(null);
							} else {
								cell.setContextMenu(listContextMenu);
							}
						}
				);
				return cell;
			}
		});
	}
	
	@FXML
	public void handleKeyPresed(KeyEvent keyEvent) {
		TodoItem item = (TodoItem) todoListView.getSelectionModel().getSelectedItem();
		if (item != null) {
			if (keyEvent.getCode().equals(KeyCode.DELETE)) {
				deleteItem(item);
			}
		}
	}
	
	@FXML
	public void handleFilterButton() {
		TodoItem item = (TodoItem) todoListView.getSelectionModel().getSelectedItem();
		refreshTodoItemList();
		refreshCellSettings();
		if (todoListView.getItems().contains(item)) {
			todoListView.getSelectionModel().select(item);
		} else {
			todoListView.getSelectionModel().selectFirst();
		}
	}
	
	private void deleteItem(TodoItem item) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Delete TODO item");
		alert.setHeaderText("Delete item: " + item.getShortDescription());
		alert.setContentText("Are you sure? Press OK to confirm, or Cancel to Back out");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.OK) {
			TodoData.getInstance().deleteTodoItem(item);
		}
	}
	
	private void refreshTodoItemList() {
		FilteredList<TodoItem> items;
		
		items = filterToggleButton.isSelected() ?
				new FilteredList<>(TodoData.getInstance().getTodoItems(), new Predicate<TodoItem>() {
					@Override
					public boolean test(TodoItem todoItem) {
						return !todoItem.getDeadline().isBefore(LocalDate.now());
					}
				}):
				new FilteredList<>(TodoData.getInstance().getTodoItems());
		
		SortedList<TodoItem> sortedTodoItems = new SortedList<>(items, new Comparator<TodoItem>() {
			@Override
			public int compare(TodoItem o1, TodoItem o2) {
				return o1.getDeadline().compareTo(o2.getDeadline());
			}
		});
		
		todoListView.setItems(sortedTodoItems);
	}
	
	@FXML
	public void showNewItemDialog() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainBorderPane.getScene().getWindow());
		dialog.setTitle("Add new TODO item");
		dialog.setHeaderText("Use this dialog to create a new TODO item");
		
		FXMLLoader fmxLoader = new FXMLLoader();

		try {
			fmxLoader.setLocation(ResourceFileHelper.getResource(getClass(), "todo-item-dialog.fxml"));
			dialog.getDialogPane().setContent(fmxLoader.load());
		} catch (IOException e) {
			System.out.println("Couldn't load the dialog");
			e.printStackTrace();
			return;
		}
		
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		
		Optional<ButtonType> result = dialog.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.OK) {
			DialogController controller = fmxLoader.getController();
			TodoItem item = controller.processResult();
			refreshTodoItemList();
			refreshCellSettings();
			todoListView.getSelectionModel().select(item);
		}
	}
	
	public void handleClickListView() {
		TodoItem item = (TodoItem) todoListView.getSelectionModel().getSelectedItem();
		
		if (item != null) {
			itemDetailsText.setText("Selected item details: " + item.getDetails());
			
			DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM-dd-YYYY");
			
			deadlineLabel.setText(df.format(item.getDeadline()));
		}
	}
	
	@FXML
	public void handleExit() {
		Platform.exit();
	}
}
