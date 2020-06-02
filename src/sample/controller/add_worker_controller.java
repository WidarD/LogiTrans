package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.database.worker_database_handler;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

import static sample.database.configuration.*;

public class add_worker_controller {
    @FXML
    private JFXButton add_worker;
    @FXML
    private ImageView back_icon;
    @FXML
    private TextField name_input;
    @FXML
    private TextField surname_input;
    @FXML
    private TextField id_input;
    @FXML
    private TextField phone_number_input;
    @FXML
    private DatePicker driving_license_date;
    @FXML
    private DatePicker sanitary_book_date;
    @FXML
    private DatePicker driver_card_date;
    @FXML
    private CheckBox ohs_yes;
    @FXML
    private CheckBox ohs_no;
    @FXML
    private ComboBox tractor_pick;
    @FXML
    private ComboBox trailer_pick;
    @FXML

    void initialize() throws SQLException, ClassNotFoundException {
        add_worker.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            String name = name_input.getText();
            String surname = surname_input.getText();
            String ID_number = id_input.getText();
            String phone_number = phone_number_input.getText();
            LocalDate dl= driving_license_date.getValue();
            LocalDate sb = sanitary_book_date.getValue();
            LocalDate dc = driver_card_date.getValue();
            Boolean yes = ohs_yes.isSelected();
            Boolean no = ohs_yes.isSelected();
            Boolean OHS = false;
            Date driving_license = Date.valueOf(dl);
            Date sanitary_book =Date.valueOf(sb);
            Date driver_card = Date.valueOf(dc);

            if (yes) {
                OHS = true;
            }
            else if (no){
                OHS = false;
            }

            worker_database_handler app = new worker_database_handler();
            try {
                app.insert_worker(name,surname,ID_number,phone_number,driving_license, sanitary_book, driver_card, OHS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        back_icon.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            back_icon.getScene().getWindow().hide();
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
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select registration_number From tractor_unit";
        ResultSet rst = stm.executeQuery(sql);
        ObservableList<String> tractor  = FXCollections.observableArrayList();
        while(rst.next()){
            tractor.add(rst.getString("registration_number"));
        }
        tractor_pick.setItems(tractor);

        String st = "Select registration_number From semi_trailer";
        ResultSet rst_st = stm.executeQuery(st);
        ObservableList<String> trailer  = FXCollections.observableArrayList();
        while(rst_st.next()){
            trailer.add(rst_st.getString("registration_number"));
        }
        trailer_pick.setItems(trailer);
    }
    public static Connection get_database_connection() throws SQLException, ClassNotFoundException
    {
        return DriverManager.getConnection(DBURL, USER, PASSWORD);
    }
}
