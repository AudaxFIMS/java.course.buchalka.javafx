package com.example.javafx.todolist.controller;

import com.example.javafx.todolist.datamodel.TodoData;
import com.example.javafx.todolist.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {
	@FXML
	private TextField shortDescription;
	@FXML
	private TextArea details;
	@FXML
	private DatePicker deadline;
	
	public void initialize() {
		deadline.setValue(LocalDate.now());
	}
	
	public TodoItem processResult() {
		return TodoData.getInstance().addTodoItem(
				shortDescription.getText().trim(),
				details.getText().trim(),
				deadline.getValue()
		);
	}
}
