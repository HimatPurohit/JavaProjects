package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

//    public void sayHello(){
//        System.out.println("Hello "+nameField.getText());
//        outputLabel.setText("Hello "+nameField.getText());
//    }

    /**
     *
     * @param e is used to define Actionevent
     */
    public void sayHello(ActionEvent e){
//        System.out.println("Hello "+nameField.getText());
        if (e.getSource().equals(helloButton)){
            outputLabel.setText("Hello "+nameField.getText());
        }else if (e.getSource().equals(byeButton)){
            outputLabel.setText("Bye "+nameField.getText());
        }
    }

}
