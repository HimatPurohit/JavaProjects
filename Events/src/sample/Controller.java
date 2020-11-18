package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField nameField;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;
    @FXML
    private Label outputLabel;
    @FXML
    private CheckBox clearCheckBox;

//    public void sayHello(){
//        System.out.println("Hello "+nameField.getText());
//        outputLabel.setText("Hello "+nameField.getText());
//    }

    public void initialize() {
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }

    public void sayHello(ActionEvent e) {
//        System.out.println("Hello "+nameField.getText());
        if (e.getSource().equals(helloButton)) {
            outputLabel.setText("Hello " + nameField.getText().trim());
        } else if (e.getSource().equals(byeButton)) {
            outputLabel.setText("Bye " + nameField.getText().trim());
        }
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Platform.isFxApplicationThread()?"UIThread":"Background Thread");
                    Thread.sleep(10000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(Platform.isFxApplicationThread()?"UIThread":"Background Thread");
                            outputLabel.setText("Runnable executed after 10sec");
                        }
                    });
                } catch (InterruptedException e1) {

                }
            }
        };

        new Thread(task).start();

        if (clearCheckBox.isSelected()) {
            nameField.clear();
            helloButton.setDisable(true);
            byeButton.setDisable(true);
        }
    }

    public void onKeyReleased() {
        String text = nameField.getText();
        boolean textEmpty = text.isEmpty() || text.trim().isEmpty();
        helloButton.setDisable(textEmpty);
        byeButton.setDisable(textEmpty);
    }

    public void handleChange() {
        System.out.println("CheckBox is " + (clearCheckBox.isSelected() ? "Selected" : "not Selected"));
    }

}
