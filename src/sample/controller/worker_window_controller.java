package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.model.worker_model;

import java.io.IOException;
import java.sql.*;

import static sample.database.configuration.*;

public class worker_window_controller {
    @FXML
    private ImageView back_icon;
    @FXML
    private JFXButton add_w_button;
    @FXML
    private TableView table;
    @FXML
    private TableColumn name_list;
    @FXML
    private TableColumn surname_list;
    @FXML
    private TableColumn ID_list;
    @FXML
    private TableColumn phone_number_list;
    @FXML
    private TableColumn driving_license_list;
    @FXML
    private TableColumn sanitary_book_list;
    @FXML
    private TableColumn driver_card_list;
    @FXML
    private TableColumn OHS_list;
    @FXML
    private TableColumn trailer_list;
    @FXML
    private TableColumn tractor_list;
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
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
        add_w_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            add_w_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/add_worker_window.fxml"));
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
        String sql = "Select * From worker";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ObservableList<worker_model> list  = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new worker_model(rst.getInt("idWorker"), rst.getString("name"), rst.getString("surname"), rst.getString("ID_number"), rst.getString("phone_number"), rst.getDate("driving_license"), rst.getDate("sanitary_book"), rst.getDate("driver_card"), rst.getBoolean("OHS")/*, rst.getInt("id_semi_trailer"), rst.getInt("id_tractor_unit")*/));

        }

        name_list.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname_list.setCellValueFactory(new PropertyValueFactory<>("surname"));
        ID_list.setCellValueFactory(new PropertyValueFactory<>("ID_number"));
        phone_number_list.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        driving_license_list.setCellValueFactory(new PropertyValueFactory<>("driving_license"));
        sanitary_book_list.setCellValueFactory(new PropertyValueFactory<>("sanitary_book"));
        driver_card_list.setCellValueFactory(new PropertyValueFactory<>("driver_card"));
        OHS_list.setCellValueFactory(new PropertyValueFactory<>("OHS"));
        //trailer_list.setCellValueFactory(new PropertyValueFactory<>("id_semi_trailer"));
        //tractor_list.setCellValueFactory(new PropertyValueFactory<>("id_tractor_unit"));
        table.setItems(list);


    }
    public static Connection get_database_connection() throws SQLException, ClassNotFoundException
    {
        return DriverManager.getConnection(DBURL, USER, PASSWORD);
    }
}
