module com.example.javafx {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;
	//----------------------------------------------------------------
	requires org.controlsfx.controls;
	requires com.dlsc.formsfx;
	requires validatorfx;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.bootstrapfx.core;
	requires eu.hansolo.tilesfx;
	//----------------------------------------------------------------
	exports com.example.javafx.samples;
	opens com.example.javafx.samples to javafx.fxml;
	//----------------------------------------------------------------
	exports com.example.javafx.controllers;
	opens com.example.javafx.controllers to javafx.fxml;
	//----------------------------------------------------------------
	exports com.example.javafx.cssexample;
	opens com.example.javafx.cssexample to javafx.fxml;
	//----------------------------------------------------------------
	exports com.example.javafx.todolist;
	opens com.example.javafx.todolist to javafx.fxml;
	//----------------------------------------------------------------
	exports com.example.javafx.todolist.controller;
	opens com.example.javafx.todolist.controller to javafx.fxml;
	
}