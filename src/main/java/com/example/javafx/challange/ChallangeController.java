package com.example.javafx.challange;

import com.example.javafx.challange.datamodel.Contact;
import com.example.javafx.challange.datamodel.ContactData;
import com.example.javafx.todolist.datamodel.TodoItem;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class ChallangeController {
	@FXML
	private BorderPane mainPanel;
	
	private ContactData contactData;
	
	@FXML
	private TableView<Contact> contactsTable;
	
	@FXML
	private ContextMenu listContextMenu;
	
	public void initialize() {
		listContextMenu = new ContextMenu();
		
		contactData = new ContactData();
		contactData.loadContacts();
		contactsTable.setItems(contactData.getContacts());
		
		MenuItem deleteMenuItems = new MenuItem("Delete");
		deleteMenuItems.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
					deleteContact();
				}
		});
		listContextMenu.getItems().add(deleteMenuItems);
		
		setViewRowSettings();
	}
	
	public void setViewRowSettings(){
		contactsTable.setRowFactory(new Callback<TableView<Contact>, TableRow<Contact>>() {
			@Override
			public TableRow<Contact> call(TableView<Contact> contactTableView) {
				final TableRow<Contact> row = new TableRow<>();
				// only display context menu for non-empty rows:
				row.contextMenuProperty().bind(
						Bindings.when(row.emptyProperty())
								.then((ContextMenu) null)
								.otherwise(listContextMenu));
				return row;
			}
		});
	}
	
	@FXML
	public void showAddContactDialog(ActionEvent actionEvent) {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainPanel.getScene().getWindow());
		dialog.setTitle("Add new contact");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contactdialog.fxml"));
		
		try {
			dialog.getDialogPane().setContent(fxmlLoader.load());
		} catch (IOException e) {
			System.out.println("Couldn't load dialog");
			e.printStackTrace();
			return;
		}
		
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		
		Optional<ButtonType> result = dialog.showAndWait();
		
		if(result.isPresent() && result.get() == ButtonType.OK) {
			ChallangeContactController contactController = fxmlLoader.getController();
			contactData.addContact(contactController.newContact());
			contactData.saveContacts();
		}
	}
	
	@FXML
	public void showEditContactDialog(ActionEvent actionEvent) {
		Contact contact = contactsTable.getSelectionModel().getSelectedItem();
		
		if (contact == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initOwner(mainPanel.getScene().getWindow());
			alert.setTitle("No contact selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select the contact you want to edit!");
			alert.showAndWait();
			return;
		}
		
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainPanel.getScene().getWindow());
		dialog.setTitle("Edit contact");
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contactdialog.fxml"));
		
		try {
			dialog.getDialogPane().setContent(fxmlLoader.load());
		} catch (IOException e) {
			System.out.println("Couldn't load dialog");
			e.printStackTrace();
			return;
		}
		
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		
		ChallangeContactController contactController = fxmlLoader.getController();
		
		contactController.editContact(contact);
		
		Optional<ButtonType> result = dialog.showAndWait();
		
		if(result.isPresent() && result.get() == ButtonType.OK) {
			contactController.updateContact(contact);
			contactData.saveContacts();
		}
	}
	
	@FXML
	public void deleteContact() {
		Contact contact = (Contact) contactsTable.getSelectionModel().getSelectedItem();
		
		if (contact == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initOwner(mainPanel.getScene().getWindow());
			alert.setTitle("No contact selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select the contact you want to delete!");
			alert.showAndWait();
			return;
		}
		
		if (contact != null) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(mainPanel.getScene().getWindow());
			alert.setTitle("Contact deletion!");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure what you want to delete contact?!\n\n" +
					contact.toString());
			
			alert.getDialogPane().getButtonTypes().remove(ButtonType.OK);
			
			alert.getDialogPane().getButtonTypes().add(ButtonType.YES);
			alert.getDialogPane().getButtonTypes().add(ButtonType.NO);
			
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.isPresent() && result.get() == ButtonType.YES) {
				contactData.deleteContact(contactsTable.getSelectionModel().getSelectedItem());
				contactData.saveContacts();
				
			}
		}
	}
}