package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller
{
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Hello");
    }
}
