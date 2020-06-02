package sample.controller;

import com.itextpdf.text.DocumentException;
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
import sample.pdf.schedule_generator;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static sample.database.configuration.DBURL;
import static sample.database.configuration.PASSWORD;
import static sample.database.configuration.USER;

public class work_schedule_window_controller extends schedule_generator {
    @FXML
    private JFXButton generate_button;

    @FXML
    private ImageView back_icon;

    @FXML
    private ComboBox<String> pick_worker;

    @FXML
    private ComboBox<String> pick_counterparty;

    @FXML
    private ComboBox<?> pick_route;

    @FXML
    private DatePicker data_pick_field;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> list_of_workers = getTable_Workers();
        ObservableList<String> list_of_counterparties = getTable_Counterparties();
        pick_worker.getItems().removeAll(pick_worker.getItems());
        pick_worker.setItems(list_of_workers);
        pick_counterparty.getItems().removeAll(pick_counterparty.getItems());
        pick_counterparty.setItems(list_of_counterparties);
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
            schedule_generator schedule_generator_usage = new schedule_generator();
            LocalDate data = data_pick_field.getValue();
            String string_date = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String name = pick_worker.getValue();
            String counterparty = pick_counterparty.getValue();
            String route = "WU-WU-A";
            try {
                schedule_generator_usage.generate_pdf(string_date, name, counterparty, route);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });
    }
    public static Connection get_database_connection() throws SQLException, ClassNotFoundException
    {
        return DriverManager.getConnection(DBURL, USER, PASSWORD);
    }
    private ObservableList<String> getTable_Workers() throws SQLException, ClassNotFoundException {
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
    }
    private ObservableList<String> getTable_Counterparties() throws SQLException, ClassNotFoundException {
        Connection conn = get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select name From counterparty";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ObservableList<String> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(rst.getString("name") );

        }

        return list;
    }

}
