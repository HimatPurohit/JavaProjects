package sample;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class Controller {
    private Task<ObservableList<String>> task;
    private Service<ObservableList<String>> service;

    @FXML
    private ListView listView;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;

    public void initialize() {
//        task = new Task<ObservableList<String>>() {
//            @Override
//            protected ObservableList<String> call() throws Exception {
////                Thread.sleep(1000);
////                final ObservableList<String> employees = FXCollections.observableArrayList("Tim", "Bob", "John", "Jane", "Mary", "jack");
//
//                // To display progress when data is fetched from background
//                String[] names={"Tim", "Bob", "John", "Jane", "Mary", "jack"};
//                ObservableList<String> employees = FXCollections.observableArrayList();
//
//                for (int i=0;i< names.length;++i){
//                    employees.add(names[i]);
//                    updateMessage("Added name "+names[i]+" to the list");
//                    updateProgress(i+1, names.length);
//                    Thread.sleep(200);
//                }
//                updateMessage("Name adding completed!");
//
//
//                // Not a good practice to update UI like this, as if data changes the task will need to be updated
////                Platform.runLater(new Runnable() {
////                    @Override
////                    public void run() {
////                        listView.setItems(employees);
////                    }
////                });
//                return employees;
//            }
//        };

        // use data binding
        // Always keep data and task as separate entity
//        progressBar.progressProperty().bind(task.progressProperty());
//        progressLabel.textProperty().bind(task.messageProperty());
//        listView.itemsProperty().bind(task.valueProperty());


        // Using Service to do the same task as above
        service = new EmployeeService();
        progressBar.progressProperty().bind(service.progressProperty());
        progressLabel.textProperty().bind(service.messageProperty());
        listView.itemsProperty().bind(service.valueProperty());


//        service.setOnRunning(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent workerStateEvent) {
//                progressBar.setVisible(true);
//                progressLabel.setVisible(true);
//            }
//        });
//        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent workerStateEvent) {
//                progressBar.setVisible(false);
//                progressLabel.setVisible(false);
//            }
//        });
//
//        // Default state before service runs for 1st time
//        progressBar.setVisible(false);
//        progressLabel.setVisible(false);

        // all of the above can be done by binding the properties to service state
        progressBar.visibleProperty().bind(service.runningProperty());
        progressLabel.visibleProperty().bind(service.runningProperty());
    }

    @FXML
    public void buttonPressed() {
//        new Thread(task).start();
        if (service.getState() == Service.State.SUCCEEDED) {
            service.reset();
            service.start();
        } else if (service.getState() == Service.State.READY) {
            service.start();
        }
    }
}
