package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class work_schedule_window_controller {
    @FXML
    private ImageView back_icon;
    @FXML
    void initialize() {
        back_icon.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            back_icon.getScene().getWindow().hide();
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
