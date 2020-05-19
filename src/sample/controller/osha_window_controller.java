package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.pdf.osha_generator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class osha_window_controller extends osha_generator {
    @FXML
    private ImageView back_icon;
    @FXML
    private JFXButton generate_button;
    @FXML
    private DatePicker data_pick_field;

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
        generate_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            osha_generator osha_generator_usage = new osha_generator();
            LocalDate date = data_pick_field.getValue();
            String string_date = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            try {
                osha_generator_usage.generate_pdf("Jan Kowalski", string_date);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }}

