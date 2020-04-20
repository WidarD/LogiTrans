package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class choice_window_controller {
    @FXML
    private JFXButton workers_button;

    @FXML
    private JFXButton counetrparties_button;

    @FXML
    private JFXButton route_button;

    @FXML
    private JFXButton schedule_button;

    @FXML
    private JFXButton osha_button;

    @FXML
    private JFXButton statement_button;
    @FXML
    void initialize() {
        workers_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            workers_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/worker_window.fxml"));
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
        counetrparties_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            counetrparties_button.getScene().getWindow().hide();
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
        route_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            route_button.getScene().getWindow().hide();
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
        schedule_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            schedule_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/work_schedule_window.fxml"));
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
         osha_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            workers_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/osha_window.fxml"));
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
         statement_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
             workers_button.getScene().getWindow().hide();
             FXMLLoader loader = new FXMLLoader();
             loader.setLocation(getClass().getResource("/sample/view/statement_window.fxml"));
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

