package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class main_window_controller {

    @FXML
    private JFXButton start_button;
    @FXML
    private ImageView icon_down;
    @FXML
    void initialize()
    {
        start_button.setStyle("-fx-font-size:34;" + "-fx-background-color: #4ba2c6");
        start_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
        start_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/choice_window.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();

            Stage primaryStage = new Stage();
            primaryStage.setTitle("LogiTrans");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        });
    }

}
