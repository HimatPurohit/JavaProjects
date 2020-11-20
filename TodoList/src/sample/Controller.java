package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import sample.datamodel.TodoData;
import sample.datamodel.TodoItem;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea todoListDetails;
    @FXML
    private Label deadlineLabel;

    public void initialize() {

        //Used to generate TodoListItems.txt for the first time. After Genearying we are loading the list from the file.

//        TodoItem item1 = new TodoItem("Mail birthday card", "Buy a 25th birthday card for the boy",
//                LocalDate.of(2020, Month.DECEMBER, 14));
//        TodoItem item2 = new TodoItem("Appointment at doctors", "See your doctor for kidney stone",
//                LocalDate.of(2020, Month.DECEMBER, 2));
//        TodoItem item3 = new TodoItem("Anniversary Surprise",
//                "Don't forget to surprise your partner on Anniversary",
//                LocalDate.of(2020, Month.NOVEMBER, 28));
//        TodoItem item4 = new TodoItem("Buy new Phone", "Pay for the phone you wish to buy",
//                LocalDate.of(2020, Month.NOVEMBER, 14));
//
//        todoItems = new ArrayList<>();
//        todoItems.add(item1);
//        todoItems.add(item2);
//        todoItems.add(item3);
//        todoItems.add(item4);
//
//        TodoData.getInstance().setTodoItems(todoItems);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem oldValue, TodoItem newValue) {
                if (newValue != null) {
                    TodoItem item=todoListView.getSelectionModel().getSelectedItem();
                    todoListDetails.setText(item.getDetails());
                    DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    deadlineLabel.setText(dateTimeFormatter.format(item.getDeadline()));
                }
            }
        });

        //Used for the hard coded data
//        todoListView.getItems().setAll(todoItems);
        todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

//    public void handleClickListView() {
//        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
//        todoListDetails.setText(item.getDetails());
//        deadlineLabel.setText(item.getDeadline().toString());
//    }


}
