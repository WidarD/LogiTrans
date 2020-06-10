package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.database.counterparty_database_handler;

import java.io.IOException;
import java.sql.SQLException;

public class update_counterparty_controller extends counterparty_window_controller {
    @FXML
    private JFXButton update_counterparty;
    @FXML
    private TextField name_update;
    @FXML
    private TextField address_update;
    @FXML
    private TextField tax_number_update;
    @FXML
    private TextField phone_number_update;
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        update_counterparty.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            String name = name_update.getText();
            String address = address_update.getText();
            String tax = tax_number_update.getText();
            String phone = phone_number_update.getText();
            counterparty_database_handler app = new counterparty_database_handler();
            try {
                app.insert_counterparty(name,address,tax,phone);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            update_counterparty.getScene().getWindow().hide();
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
    }

    public void write_value(String value1, String value2, String value3, String value4) throws SQLException, ClassNotFoundException {

        name_update.setText(value1);
        address_update.setText(value2);
        tax_number_update.setText(value3);
        phone_number_update.setText(value4);
    }

}
