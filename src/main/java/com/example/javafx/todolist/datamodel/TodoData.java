package com.example.javafx.todolist.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TodoData {
	private static TodoData instance = new TodoData();
	private static String fileName = "TodoListItems.txt";
	
	private ObservableList<TodoItem> todoItems;
	private DateTimeFormatter formatter;
	
	public static TodoData getInstance() {
		return instance;
	}
	
	
	
	private TodoData() {
		formatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy");
	}
	
	public ObservableList<TodoItem> getTodoItems() {
		return todoItems;
	}
	
	public void loadTodoItems() throws IOException {
		todoItems = FXCollections.observableArrayList();
		Path path = Paths.get(fileName);
		
		BufferedReader br = Files.newBufferedReader(path);
		
		String input;
		
		try {
			while ((input = br.readLine()) != null) {
				String[] itemPieces = input.split("\t");
				
				String shortDescription = itemPieces[0];
				String details = itemPieces[1];
				String deadlineString = itemPieces[2];
				
				LocalDate deadline = LocalDate.parse(deadlineString, formatter);
				
				addTodoItem(shortDescription, details, deadline);
			}
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}
	
	public TodoItem addTodoItem(String shortDescription, String details, LocalDate deadline) {
		TodoItem todoItem = new TodoItem(shortDescription, details, deadline);
		todoItems.add(todoItem);
		return todoItem;
	}
	
	public void storeTodoItems() throws IOException {
		Path path = Paths.get(fileName);
		
		BufferedWriter bw = Files.newBufferedWriter(path);
		
		try {
			todoItems.stream().forEach(val -> {
						try {
							bw.write(String.format("%s\t%s\t%s", val.getShortDescription(), val.getDetails(),val.getDeadline().format(formatter)));
							bw.newLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
			);
		} finally {
			if (bw != null) {
				bw.close();
			}
		}
	}
	
	public void deleteTodoItem(TodoItem item) {
		todoItems.remove(item);
	}
}
