package sample.datamodel;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class TodoData {
    private static TodoData instance = new TodoData();
    private static String filename = "TodoListItems.txt";

    private List<TodoItem> todoItems;
    private DateTimeFormatter dateTimeFormatter;

    public static TodoData getInstance() {
        return instance;
    }

    private TodoData() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }


    // Used to set toDoItems for first time, for loading into TodoListItems.txt file.
//    public void setTodoItems(List<TodoItem> todoItems) {
//        this.todoItems = todoItems;
//    }

    public void loadTodoItems() throws IOException {
        todoItems = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader bufferedReader = Files.newBufferedReader(path);

        String input;
        try {
            while ((input = bufferedReader.readLine()) != null) {
                String[] itemPieces = input.split("\t");
                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                LocalDate deadline = LocalDate.parse(itemPieces[2], dateTimeFormatter);

                TodoItem item = new TodoItem(shortDescription, details, deadline);
                todoItems.add(item);
            }

        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    public void storeTodoitems() throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bufferedWriter = Files.newBufferedWriter(path);

        try {
            Iterator<TodoItem> itemIterator = todoItems.iterator();
            while (itemIterator.hasNext()) {
                TodoItem item = itemIterator.next();
                bufferedWriter.write(String.format("%s\t%s\t%s",
                        item.getShortDescription(), item.getDetails(),
                        item.getDeadline().format(dateTimeFormatter)));
                bufferedWriter.newLine();
            }
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }
}
