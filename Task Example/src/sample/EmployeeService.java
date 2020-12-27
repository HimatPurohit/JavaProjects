package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class EmployeeService extends Service<ObservableList<String>> {
    // Recommended way for JavaFx

    @Override
    protected Task<ObservableList<String>> createTask() {
        return new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                String[] names={"Tim", "Bob", "John", "Jane", "Mary", "jack"};
                ObservableList<String> employees = FXCollections.observableArrayList();

                for (int i=0;i< names.length;++i){
                    employees.add(names[i]);
                    updateMessage("Added name "+names[i]+" to the list");
                    updateProgress(i+1, names.length);
                    Thread.sleep(200);
                }
                updateMessage("Name adding completed!");
                return employees;
            }
        };
    }
}
