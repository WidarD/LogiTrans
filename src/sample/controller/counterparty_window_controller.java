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
import sample.database.counterparty_database_handler;
import sample.model.counterparty_model;

import java.io.IOException;
import java.sql.*;

import static sample.database.configuration.*;

public class counterparty_window_controller  {
    @FXML
    private ImageView back_icon;
    @FXML
    private JFXButton add_cp_button;
    @FXML
    private JFXButton update_cp_button;
    @FXML
    private JFXButton delate_cp_button;
    @FXML
    private TableView<counterparty_model> table;
    @FXML
    private TableColumn name_list;
    @FXML
    private TableColumn address_list;
    @FXML
    private TableColumn tax_number_list;
    @FXML
    private TableColumn phone_number_list;
    @FXML

    void initialize() throws SQLException, ClassNotFoundException {

        getTable();

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

        delate_cp_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            delateRow();
            try {
                getTable();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        update_cp_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            update_cp_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/update_counterparty_window.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String name = null;
            String address = null;
            String tax_number = null;
            String phone_number = null;
            if (table.getSelectionModel().getSelectedItem() != null) {

                counterparty_model cp = table.getSelectionModel().getSelectedItem();
                name = cp.name;
                address = cp.address;
                tax_number = cp.tax_number;
                phone_number = cp.phone_number;

            }
            update_counterparty_controller updateController = loader.getController();

            try {
                updateController.write_value(name,address,tax_number,phone_number);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("LogiTrans");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();

            delateRow();

        });
    }

    public static Connection get_database_connection() throws SQLException, ClassNotFoundException
    {
        return DriverManager.getConnection(DBURL, USER, PASSWORD);
    }

    public void delateRow(){
        if (table.getSelectionModel().getSelectedItem() != null) {
            counterparty_model cp = table.getSelectionModel().getSelectedItem();
            String er = cp.tax_number;
            try {
                counterparty_database_handler app = new counterparty_database_handler();
                app.delete_counetrparty(er);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void getTable() throws SQLException, ClassNotFoundException {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select idCounterparty, name, address, tax_number, phone_number From counterparty";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ObservableList<counterparty_model> list  = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new counterparty_model(rst.getInt("idCounterparty"),rst.getString("name"), rst.getString("address"), rst.getString("tax_number"), rst.getString("phone_number")));
        }
        name_list.setCellValueFactory(new PropertyValueFactory<>("name"));
        address_list.setCellValueFactory(new PropertyValueFactory<>("address"));
        tax_number_list.setCellValueFactory(new PropertyValueFactory<>("tax_number"));
        phone_number_list.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        table.setItems(list);
    }
}


