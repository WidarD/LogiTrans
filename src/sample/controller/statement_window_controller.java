package sample.controller;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import sample.pdf.statement_generator;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static sample.database.configuration.*;

public class statement_window_controller extends statement_generator {

    @FXML
    private JFXButton generate_button;

    @FXML
    private ImageView back_icon;

    @FXML
    private ComboBox<String> worker_pick_field;

    @FXML
    private ComboBox<String> counterpart_pick_field;

    @FXML
    private ComboBox<String> accepted_pick_field;

    @FXML
    private JFXTextField number_declaration;

    @FXML
    private DatePicker data_pick_field;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> list_of_workers = getTable_Workers();
        ObservableList<String> list_of_counterparties = getTable_Counterparties();
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
        worker_pick_field.getItems().removeAll(worker_pick_field.getItems());
        worker_pick_field.setItems(list_of_workers);
        counterpart_pick_field.getItems().removeAll(counterpart_pick_field.getItems());
        counterpart_pick_field.setItems(list_of_counterparties);
        accepted_pick_field.getItems().removeAll(accepted_pick_field.getItems());
        accepted_pick_field.getItems().add("Tak");
        accepted_pick_field.getItems().add("Nie");
        generate_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            statement_generator statement_generator_usage = new statement_generator();
            LocalDate data = data_pick_field.getValue();
            String string_date = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String name = worker_pick_field.getValue();
            String counterparty = counterpart_pick_field.getValue();
            String route = "WU-WU-A";
            String number = number_declaration.getText();
            try {
                statement_generator_usage.generate_pdf(string_date, name, counterparty, route, number);
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
        public ObservableList<String> getTable_Workers() throws SQLException, ClassNotFoundException {
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
    public ObservableList<String> getTable_Counterparties() throws SQLException, ClassNotFoundException {
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
