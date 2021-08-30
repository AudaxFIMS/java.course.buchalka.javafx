package com.example.javafx.cssexample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CssExampleController {
	@FXML
	private Label transformedLabel;
	
	@FXML
	private GridPane gridPane;
	
	@FXML
	WebView webView;
	
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
	
	@FXML
	public void handleHLClick(ActionEvent actionEvent) {
		/*System.out.println("The link was clicked!");
		try {
			Desktop.getDesktop().browse(new URI("http://www.javafx.com"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}*/
		WebEngine webEngine = webView.getEngine();
		
		webEngine.load("http://www.javafx.com");
	}
	
	
}