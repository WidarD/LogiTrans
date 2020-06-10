package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.database.route_database_handler;

import java.io.IOException;
import java.sql.SQLException;


public class add_route_controller {
    @FXML
    private ImageView back_icon;
    @FXML
    private JFXButton add_route;
    @FXML
    private TextField loading_pick;
    @FXML
    private TextField unloading_pick;
    @FXML
    private TextField km_pick;
    @FXML
    void initialize() {
    back_icon.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
        back_icon.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/route_window.fxml"));
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
        add_route.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            String ld= loading_pick.getText();;
            String uld = unloading_pick.getText();

            String km = km_pick.getText();
            int km_int = Integer.parseInt(km);
            route_database_handler app = new route_database_handler();
            try {
                app.insert_route(ld,uld,km_int);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }



        });
}


}
