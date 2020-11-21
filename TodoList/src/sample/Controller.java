package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import sample.datamodel.TodoData;
import sample.datamodel.TodoItem;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


public class Controller {
    //    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea todoListDetails;
    @FXML
    private Label deadlineLabel;
    @FXML
    private BorderPane mainWindowBorderPane;

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


        // This Listener is replacement of handleClickListView()
        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem oldValue, TodoItem newValue) {
                if (newValue != null) {
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    todoListDetails.setText(item.getDetails());
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    deadlineLabel.setText(dateTimeFormatter.format(item.getDeadline()));
                }
            }
        });

        //Used for the hard coded data
//        todoListView.getItems().setAll(todoItems);
        todoListView.setItems(TodoData.getInstance().getTodoItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

//    public void handleClickListView() {
//        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
//        todoListDetails.setText(item.getDetails());
//        deadlineLabel.setText(item.getDeadline().toString());
//    }

    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindowBorderPane.getScene().getWindow());

        dialog.setTitle("Add new ToDo item");
        dialog.setHeaderText("Use this dialog to add New ToDo Item in the list");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));

        try {
//            Parent root = FXMLLoader.load(getClass().getResource("todoItemDialog.fxml"));
//            dialog.getDialogPane().setContent(root);
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load Dialog");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> showAndWait = dialog.showAndWait();
        if (showAndWait.isPresent() && showAndWait.get() == ButtonType.OK) {
            TodoItemDialog todoItemDialog = fxmlLoader.getController();
            TodoItem newItem = todoItemDialog.processResults();
//            todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
            todoListView.getSelectionModel().select(newItem);
//            System.out.println("OK Pressed");

            //This changes how list cells are displayed that is alternate colrs and all
            todoListView.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {
                @Override
                public ListCell<TodoItem> call(ListView<TodoItem> todoItemListView) {
                    ListCell<TodoItem> listCell=new ListCell<TodoItem>(){
                        @Override
                        protected void updateItem(TodoItem todoItem, boolean b) {
                            super.updateItem(todoItem, b);
                            if (b){
                                setText(null);
                            }else {
                                setText(todoItem.getShortDescription());
                                if (todoItem.getDeadline().isEqual(LocalDate.now())){
                                    setTextFill(Color.RED);
                                }
                            }
                        }
                    };
                    return listCell;
                }
            });
        }
//        else {
//            System.out.println("Cancel Pressed");
//        }
    }

}
