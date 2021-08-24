package com.example.javafx.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllersApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(ControllersApplication.class.getResource("controllers.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 600, 480);
		stage.setTitle("Hello world application!");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}