package com.example.javafx.challange;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChallangeApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(ChallangeApplication.class.getResource("chellange.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 600, 480);
		stage.setTitle("My contacts");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}