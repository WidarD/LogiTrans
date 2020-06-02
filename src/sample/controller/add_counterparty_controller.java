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
import sample.database.counterparty_database_handler;

import java.io.IOException;
import java.sql.SQLException;

public class add_counterparty_controller {
    @FXML
    private JFXButton add_counterparty;
    @FXML
    private TextField name_input;
    @FXML
    private TextField address_input;
    @FXML
    private TextField tax_number_input;
    @FXML
    private TextField phone_number_input;
    @FXML
    private ImageView back_icon;
    @FXML
    void initialize() {
        add_counterparty.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            String name = name_input.getText();
            String address = address_input.getText();
            String tax_number = tax_number_input.getText();
            String phone_number = phone_number_input.getText();
            counterparty_database_handler app = new counterparty_database_handler();
            try {
                app.insert_counterparty(name,address,tax_number,phone_number);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
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

}}
