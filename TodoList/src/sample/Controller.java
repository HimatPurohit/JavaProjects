package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import sample.datamodel.TodoData;
import sample.datamodel.TodoItem;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;


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
    @FXML
    private ContextMenu listContextMenu;
    @FXML
    private ToggleButton filterToggleButton;

    private FilteredList<TodoItem> filteredList;
    private Predicate<TodoItem> wantAllItems;
    private Predicate<TodoItem> wantTodaysItems;

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

        listContextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                deleteItem(item);
            }
        });

        listContextMenu.getItems().addAll(deleteMenuItem);
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

        wantAllItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem item) {
                return true;
            }
        };

        wantTodaysItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem item) {
                return item.getDeadline().equals(LocalDate.now());
            }
        };
        filteredList = new FilteredList<TodoItem>(TodoData.getInstance().getTodoItems(), wantAllItems);
//        filteredList = new FilteredList<TodoItem>(TodoData.getInstance().getTodoItems(),
//                new Predicate<TodoItem>() {
//                    @Override
//                    public boolean test(TodoItem item) {
//                        // returns true to show all item
//                        return true;
//                    }
//                });
//        SortedList<TodoItem> sortedList = new SortedList<TodoItem>(TodoData.getInstance().getTodoItems(),
//                new Comparator<TodoItem>() {
//                    @Override
//                    public int compare(TodoItem o1, TodoItem o2) {
//                        return o1.getDeadline().compareTo(o2.getDeadline());
//                    }
//                });
        SortedList<TodoItem> sortedList = new SortedList<TodoItem>(filteredList,
                new Comparator<TodoItem>() {
                    @Override
                    public int compare(TodoItem o1, TodoItem o2) {
                        return o1.getDeadline().compareTo(o2.getDeadline());
                    }
                });

        //Used for the hard coded data
//        todoListView.getItems().setAll(todoItems);
//        todoListView.setItems(TodoData.getInstance().getTodoItems());
        todoListView.setItems(sortedList);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();


        //This changes how list cells are displayed that is alternate colors and all
        todoListView.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {
            @Override
            public ListCell<TodoItem> call(ListView<TodoItem> todoItemListView) {
                ListCell<TodoItem> listCell = new ListCell<TodoItem>() {
                    @Override
                    protected void updateItem(TodoItem todoItem, boolean b) {
                        super.updateItem(todoItem, b);
                        if (b) {
                            setText(null);
                        } else {
                            setText(todoItem.getShortDescription());
                            if (todoItem.getDeadline().isEqual(LocalDate.now())) {
                                setTextFill(Color.RED);
                            } else if (todoItem.getDeadline().equals(LocalDate.now().plusDays(1))) {
                                setTextFill(Color.BROWN);
                            } else if (todoItem.getDeadline().isAfter(LocalDate.now())) {
                                setTextFill(Color.GREEN);
                            } else if (todoItem.getDeadline().isBefore(LocalDate.now())) {
                                setTextFill(Color.DARKGREY);
                            }
                        }
                    }
                };
                listCell.emptyProperty().addListener((observableValue, wasEmpty, isEmpty) -> {
                    if (isEmpty) {
                        listCell.setContextMenu(null);
                    } else {
                        listCell.setContextMenu(listContextMenu);
                    }
                });

                return listCell;
            }
        });

    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        TodoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (keyEvent.getCode().equals(KeyCode.DELETE)) {
                deleteItem(selectedItem);
            }
        }
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
        }
//        else {
//            System.out.println("Cancel Pressed");
//        }
    }

    public void handleFilterButton() {
//        if (filterToggleButton.isSelected()) {
//            filteredList.setPredicate(new Predicate<TodoItem>() {
//                @Override
//                public boolean test(TodoItem item) {
//                    return (item.getDeadline().equals(LocalDate.now()));
//                }
//            });
//        } else {
//            filteredList.setPredicate(new Predicate<TodoItem>() {
//                @Override
//                public boolean test(TodoItem item) {
//                    //returns true to display all items
//                    return true;
//                }
//            });
//        }

        TodoItem selectedItem=todoListView.getSelectionModel().getSelectedItem();
        if (filterToggleButton.isSelected()) {
            filteredList.setPredicate(wantTodaysItems);
            if (filteredList.isEmpty()){
                todoListDetails.clear();
                deadlineLabel.setText("");
            }else if (filteredList.contains(selectedItem)){
                todoListView.getSelectionModel().select(selectedItem);
            }else {
                todoListView.getSelectionModel().selectFirst();
            }
        } else {
            filteredList.setPredicate(wantAllItems);
            todoListView.getSelectionModel().select(selectedItem);
        }
    }

    public void deleteItem(TodoItem item) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Todo Item");
        alert.setHeaderText("Delete item: " + item.getShortDescription());
        alert.setContentText("Are you sure? Press OK to confirm, or Cancel to Back out.");
        Optional<ButtonType> button = alert.showAndWait();
        if (button.isPresent() && button.get() == ButtonType.OK) {
            TodoData.getInstance().deleteTodoItem(item);
        }
    }

    public void handleExit(){
        Platform.exit();
    }

}
