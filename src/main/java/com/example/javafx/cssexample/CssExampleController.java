package com.example.javafx.cssexample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class CssExampleController {
	@FXML
	private Label transformedLabel;
	
	@FXML
	private GridPane gridPane;
	
	@FXML
	public void onMouseEnteredLabel(MouseEvent mouseEvent) {
		transformedLabel.setScaleX(2.0);
		transformedLabel.setScaleY(2.0);
	}
	
	@FXML
	public void onMouseExitedLabel(MouseEvent mouseEvent) {
		transformedLabel.setScaleX(1.0);
		transformedLabel.setScaleY(1.0);
	}
	
	@FXML
	public void openFileChooser(ActionEvent actionEvent) {
		FileChooser chooser = new FileChooser();
		
		chooser.showOpenDialog(gridPane.getScene().getWindow());
	}
}