package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;


public class Controller {
    @FXML
    private Label label1;
    @FXML
    private Label label2;

    @FXML
    private Button Button4;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button Open;
    @FXML
    private WebView webView;

    public void initialize() {
        label1.setScaleX(2.0);
        label1.setScaleY(2.0);

        Button4.setEffect(new DropShadow());
    }

    public void handleMouseEnter() {
        label2.setScaleX(2.0);
        label2.setScaleY(2.0);
    }

    public void handleMouseExit() {
        label2.setScaleX(1.0);
        label2.setScaleY(1.0);
    }

    public void handleClick() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text","*.txt"),
                new FileChooser.ExtensionFilter("Pdf","*.pdf"),
                new FileChooser.ExtensionFilter("All Files","*.*")
        );
        // null means any number of times the button can be clicked without interaction with FileChooser
//        fileChooser.showOpenDialog(null);

//        File file = fileChooser.showOpenDialog(gridPane.getScene().getWindow());
        List<File> file = fileChooser.showOpenMultipleDialog(gridPane.getScene().getWindow());
//        File file = fileChooser.showSaveDialog(gridPane.getScene().getWindow());
        if (file != null) {
            for (File currentFile: file) {
                System.out.println(currentFile.getPath());
            }
//            System.out.println(file.getPath());
//            Open.setText(file.getName());
        } else {
            System.out.println("Chooser was cancelled");
            Open.setText("Open...");
        }
    }

    public void handleLinkClick(){
//        try {
//            Desktop.getDesktop().browse(new URI("http://www.google.com"));
//        } catch (IOException e){
//            e.printStackTrace();
//        }catch (URISyntaxException e){
//            e.printStackTrace();
//        }

        WebEngine webEngine=webView.getEngine();
        webEngine.load("https://www.google.com");
    }
}
