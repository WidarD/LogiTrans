package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.database.route_database_handler;

import java.io.IOException;
import java.sql.SQLException;

public class update_route_controller extends route_window__controller {
    @FXML
    private JFXButton update_route;
    @FXML
    private TextField update_c1;
    @FXML
    private TextField update_c2;
    @FXML
    private TextField update_km;
    @FXML

    int id;

    void initialize() throws SQLException, ClassNotFoundException {


        update_route.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            String ld= update_c1.getText();;
            String uld = update_c2.getText();
            String km = update_km.getText();
            int km_int = Integer.parseInt(km);

            route_database_handler app = new route_database_handler();
            try {
                app.insert_route(ld,uld,km_int);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            update_route.getScene().getWindow().hide();
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

    }

   public void write_value(String value1, String value2, int km) throws SQLException, ClassNotFoundException {

        String distance = String.valueOf(km);
        update_c1.setText(value1);
        update_c2.setText(value2);
        update_km.setText(distance);
    }



}
