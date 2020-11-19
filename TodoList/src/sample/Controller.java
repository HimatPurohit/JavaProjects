package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import sample.datamodel.TodoItem;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea todoListDetails;

    public void initialize() {
        TodoItem item1 = new TodoItem("Mail birthday card", "Buy a 25th birthday card for the boy",
                LocalDate.of(2020, Month.DECEMBER, 14));
        TodoItem item2 = new TodoItem("Appointment at doctors", "See your doctor for kidney stone",
                LocalDate.of(2020, Month.DECEMBER, 2));
        TodoItem item3 = new TodoItem("Anniversary Surprise",
                "Dont forget to surprise your partner on Anniversary",
                LocalDate.of(2020, Month.NOVEMBER, 28));
        TodoItem item4 = new TodoItem("Buy new Phone", "Pay for the phone you wish to buy",
                LocalDate.of(2020, Month.NOVEMBER, 14));

        todoItems = new ArrayList<>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void handleClickListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        StringBuilder stringBuilder=new StringBuilder(item.getDetails());
        stringBuilder.append("\n\n\n\nDue: ");
        stringBuilder.append(item.getDeadline());
        todoListDetails.setText(stringBuilder.toString());
    }
}
