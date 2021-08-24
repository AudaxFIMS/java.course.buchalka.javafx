package com.example.javafx.samples;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
	@FXML
	private Label welcomeText;
	@FXML
	private Label secondText;
	
	private int counter = 0;
	
	@FXML
	protected void onHelloButtonClick() {
		welcomeText.setText("Welcome to JavaFX Application!");
		counter++;
		secondText.setText("Click number - " + counter);
	}
}