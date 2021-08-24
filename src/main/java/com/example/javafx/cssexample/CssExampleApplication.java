package com.example.javafx.cssexample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CssExampleApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(CssExampleApplication.class.getResource("css-example-view.fxml"));
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		Scene scene = new Scene(fxmlLoader.load(), 600, 480);
		stage.setTitle("CSS example application!");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}