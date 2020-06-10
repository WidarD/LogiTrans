package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.pdf.osha_generator;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static sample.database.configuration.*;

public class osha_window_controller extends osha_generator {
    @FXML
    private ImageView back_icon;
    @FXML
    private JFXButton generate_button;
    @FXML
    private DatePicker data_pick_field;
    @FXML
    private ComboBox<String> accepted_osha;
    @FXML
    private ComboBox<String> worker_choice;
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> list =getTable();

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

        worker_choice.getItems().removeAll(worker_choice.getItems());
        worker_choice.setItems(list);
        accepted_osha.getItems().removeAll(accepted_osha.getItems());
        accepted_osha.getItems().add("Tak");
        accepted_osha.getItems().add("Nie");
        generate_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            osha_generator osha_generator_usage = new osha_generator();
            LocalDate date = data_pick_field.getValue();
            String string_date = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            //String name = worker_choice.getValue();
            try {
                osha_generator_usage.generate_pdf(worker_choice.getValue(), string_date);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    public static Connection get_database_connection() throws SQLException, ClassNotFoundException
    {
        return DriverManager.getConnection(DBURL, USER, PASSWORD);
    }
    public ObservableList<String> getTable() throws SQLException, ClassNotFoundException {
        Connection conn = get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select name, surname From worker";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ObservableList<String> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(rst.getString("name") + " " + rst.getString("surname"));

            }
        return list;
    }}
