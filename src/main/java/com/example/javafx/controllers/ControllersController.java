package com.example.javafx.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.nio.channels.InterruptedByTimeoutException;

public class ControllersController {
	@FXML
	private TextField nameField;
	@FXML
	private Button helloButton;
	@FXML
	private Button byeButton;
	@FXML
	private CheckBox ourCheckBox;
	@FXML
	private Label ourProcessingLabel;
	
	@FXML
	public void initialize() {
		helloButton.setDisable(true);
		byeButton.setDisable(true);
		
	}
	@FXML
	public void onButtonClicked(ActionEvent e) {
		if(e.getSource().equals(helloButton)) {
			System.out.println("Hello, " + nameField.getText());
		} else if(e.getSource().equals(byeButton)) {
			System.out.println("Bye, " + nameField.getText());
		}
		
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
					System.out.println("I'm going to sleep on: " + s);
					Thread.sleep(10000);
					// {{ Code for execute updates on UI after complete execution upper
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
							System.out.println("I'm going to update on: " + s);
							ourProcessingLabel.setText("We finished background process!");
						}
					});
					// }} --------------------------------------------------------------
				} catch (InterruptedException e) {
					// Some action
				}
			}
		};
		
		new Thread(task).start();
		
		if (ourCheckBox.isSelected()) {
			nameField.clear();
		}
		handleKeyReleased();
	}
	
	@FXML
	public void handleKeyReleased() {
		String text = nameField.getText();
		boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
		helloButton.setDisable(disableButtons);
		byeButton.setDisable(disableButtons);
	}
	
	@FXML
	public void handleChange() {
		System.out.println("The checkbox is " + (ourCheckBox.isSelected() ? "checked" : "not checked"));
	}
}
