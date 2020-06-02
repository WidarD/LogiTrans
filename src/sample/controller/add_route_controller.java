package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.database.route_database_handler;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;


public class add_route_controller {
    @FXML
    private ImageView back_icon;
    @FXML
    private JFXButton add_route;
    @FXML
    private TableView table;
    @FXML
    private DatePicker loading_pick;
    @FXML
    private DatePicker unloading_pick;
    @FXML
    private TextField loading_hour;
    @FXML
    private TextField unloading_hour;
    @FXML
    void initialize() {
    back_icon.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
        back_icon.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/counterparty_window.fxml"));
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
            LocalDate ld= loading_pick.getValue();
            LocalDate uld = unloading_pick.getValue();
            String lh = loading_hour.getText();
            String ulh = unloading_hour.getText();
            Date loading_date = Date.valueOf(ld);
            Date unloading_date =Date.valueOf(uld);
            Time tl = Time.valueOf(lh);
            Time tul = Time.valueOf(ulh);
            route_database_handler app = new route_database_handler();
            try {
                app.insert_route(loading_date,tl,unloading_date,tul);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
}}
