package com.example.javafx.challange;

import com.example.javafx.challange.datamodel.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class ChallangeContactController {
	@FXML
	private TextField firstNameField;
	
	@FXML
	private TextField lastNameField;

	@FXML
	private TextField phoneNumberField;

	@FXML
	private TextField notesField;
	
	public Contact newContact() {
		return new Contact(firstNameField.getText(),
							lastNameField.getText(),
							phoneNumberField.getText(),
							notesField.getText());
	}
	
	public void editContact(Contact contact) {
		firstNameField.setText(contact.getFirstName());
		lastNameField.setText(contact.getLastName());
		phoneNumberField.setText(contact.getPhoneNumber());
		notesField.setText(contact.getNotes());
	}
	
	public void updateContact(Contact contact) {
		contact.setFirstName(firstNameField.getText());
		contact.setLastName(lastNameField.getText());
		contact.setPhoneNumber(phoneNumberField.getText());
		contact.setNotes(notesField.getText());
	}
}