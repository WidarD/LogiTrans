package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class main_window_controller {

    @FXML
    private JFXButton start_button;
    @FXML
    private ImageView icon_down;
    @FXML
    void initialize()
    {
        start_button.setStyle("-fx-font-size:34;" + "-fx-background-color: #4ba2c6");
    }

}
