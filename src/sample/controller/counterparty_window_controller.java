package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.database.counterparty_database_handler;

import java.io.IOException;

public class counterparty_window_controller  {
    @FXML
    private ImageView back_icon;
    @FXML
    private JFXButton add_cp_button;
    @FXML
    private TableView table;
    @FXML
    private TableColumn name_list;
    @FXML
    private TableColumn address_list;
    @FXML
    private TableColumn tax_number_list;
    @FXML
    private TableColumn phone_number_list;
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
        add_cp_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            add_cp_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/add_counterparty_window.fxml"));
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
        counterparty_database_handler counterparty_database_handler = new counterparty_database_handler();
        table.setItems(counterparty_database_handler.get_all_counterparties());
    }

}
