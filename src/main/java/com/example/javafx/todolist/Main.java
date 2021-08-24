package com.example.javafx.todolist;

import com.example.javafx.todolist.datamodel.TodoData;
import com.example.javafx.todolist.util.ResourceFileHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(ResourceFileHelper.getResource(getClass(), "main-window.fxml"));
		Scene scene = new Scene(root, 600, 480);
		stage.setTitle("TODO List");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void stop() throws Exception {
		TodoData.getInstance().storeTodoItems();
	}
	
	@Override
	public void init() throws Exception {
		TodoData.getInstance().loadTodoItems();
	}
}
