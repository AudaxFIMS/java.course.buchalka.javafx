package com.example.javafx.challange.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
	private SimpleStringProperty firstName = new SimpleStringProperty("");
	private SimpleStringProperty lastName = new SimpleStringProperty("");
	private SimpleStringProperty phoneNumber = new SimpleStringProperty("");
	private SimpleStringProperty notes = new SimpleStringProperty("");
	
	public Contact(String firstName, String lastName, String phone, String notes) {
		this.firstName.set(firstName);
		this.lastName.set(lastName);
		this.phoneNumber.set(phone);
		this.notes.set(notes);
	}
	
	public Contact() {
	}
	
	@Override
	public String toString() {
		return 	"Contact: \n" +
				" - Firstname: " + getFirstName() + "\n" +
				" - Lastname: " + getLastName() + "\n" +
				" - Phone number: " + getPhoneNumber() + "\n" +
				" - Notes: " + getNotes();
	}
	
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public void setPhoneNumber(String phone) {
		this.phoneNumber.set(phone);
	}
	
	public void setNotes(String notes) {
		this.notes.set(notes);
	}
	
	public String getFirstName() {
		return firstName.get();
	}
	
	public SimpleStringProperty firstNameProperty() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName.get();
	}
	
	public SimpleStringProperty lastNameProperty() {
		return lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber.get();
	}
	
	public SimpleStringProperty phoneNumberProperty() {
		return phoneNumber;
	}
	
	public String getNotes() {
		return notes.get();
	}
	
	public SimpleStringProperty notesProperty() {
		return notes;
	}
}
