package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.datamodel.TodoData;
import sample.datamodel.TodoItem;

import java.time.LocalDate;

public class TodoItemDialog {
    @FXML
    private TextField shortDescriptionTextField;
    @FXML
    private TextArea detailsTextArea;
    @FXML
    private DatePicker deadlineDatePicker;

    public TodoItem processResults(){
        String shortDescription= shortDescriptionTextField.getText();
        String details= detailsTextArea.getText();
        LocalDate deadline= deadlineDatePicker.getValue();

        TodoItem todoItem=new TodoItem(shortDescription,details,deadline);
        TodoData.getInstance().addTodoItem(todoItem);
        return todoItem;
    }
}
