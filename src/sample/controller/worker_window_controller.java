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
import sample.database.worker_database_handler;
import sample.model.worker_model;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

import static sample.database.configuration.*;

public class worker_window_controller {
    @FXML
    private ImageView back_icon;
    @FXML
    private JFXButton add_w_button;
    @FXML
    private JFXButton delate_w_button;
    @FXML
    private JFXButton update_w_button;
    @FXML
    private TableView<worker_model> table;
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
        update_w_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            update_w_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/update_worker_window.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String name = null;
            String surname = null;
            String id = null;
            String phone = null;
            Date dl = null;
            Date sb = null;
            Date dc = null;
            Boolean ohs = null;
            String tractor = null;
            String trailer = null;
            LocalDate driving_license = null;
            LocalDate sanitary_book = null;
            LocalDate driver_card = null;
            Boolean yes = null;
            Boolean OHS = null;

            if (table.getSelectionModel().getSelectedItem() != null) {

                worker_model cp = table.getSelectionModel().getSelectedItem();
                name = cp.name;
                surname = cp.surname;
                id = cp.ID_number;
                phone = cp.phone_number;
                dl = cp.driving_license;
                sb = cp.sanitary_book;
                dc = cp.driver_card;
                driving_license= dl.toLocalDate();
                sanitary_book = sb.toLocalDate();
                driver_card = dc.toLocalDate();
                yes = cp.OHS;
                tractor = cp.registration_number_ST;
                trailer = cp.registration_number_TU;
            }
            update_worker_controller updateController = loader.getController();
            if (yes) {
                OHS = true;
            }
            else {
                OHS = false;
            }
            try {
                updateController.write_value(name,surname,id,phone,driving_license, sanitary_book, driver_card,OHS,tractor,trailer);

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

            try {
                delateRow();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        delate_w_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                delateRow();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                getTable();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }
    public void getTable() throws SQLException, ClassNotFoundException {

        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "SELECT worker.*, semi_trailer.*, tractor_unit.* FROM worker LEFT JOIN semi_trailer ON (worker.id_semi_trailer=semi_trailer.idSemi_trailer) LEFT JOIN tractor_unit ON (worker.id_tractor_unit=tractor_unit.idTractor_unit)";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ObservableList<worker_model> list  = FXCollections.observableArrayList();

        while (rst.next()) {
            list.add(new worker_model(rst.getInt("idWorker"), rst.getString("name"), rst.getString("surname"), rst.getString("ID_number"), rst.getString("phone_number"), rst.getDate("driving_license"), rst.getDate("sanitary_book"), rst.getDate("driver_card"), rst.getBoolean("OHS"), rst.getString("tractor_unit.registration_number"), rst.getString("semi_trailer.registration_number")));
        }

        name_list.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname_list.setCellValueFactory(new PropertyValueFactory<>("surname"));
        ID_list.setCellValueFactory(new PropertyValueFactory<>("ID_number"));
        phone_number_list.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        driving_license_list.setCellValueFactory(new PropertyValueFactory<>("driving_license"));
        sanitary_book_list.setCellValueFactory(new PropertyValueFactory<>("sanitary_book"));
        driver_card_list.setCellValueFactory(new PropertyValueFactory<>("driver_card"));
        OHS_list.setCellValueFactory(new PropertyValueFactory<>("OHS"));

        tractor_list.setCellValueFactory(new PropertyValueFactory<>("registration_number_TU"));
        trailer_list.setCellValueFactory(new PropertyValueFactory<>("registration_number_ST"));
        table.setItems(list);


    }

    public void delateRow() throws SQLException, ClassNotFoundException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            worker_model worker = table.getSelectionModel().getSelectedItem();
            String ID = worker.ID_number;

            Connection conn=get_database_connection();
            Statement stm;
            stm = conn.createStatement();
            String sql = "SELECT idWorker FROM worker WHERE ID_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ID);

            int id =0;
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                id = rs.getInt("idWorker");
            }


            try {
                worker_database_handler app = new worker_database_handler();
                app.delete_worker(id);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection get_database_connection() throws SQLException, ClassNotFoundException
    {
        return DriverManager.getConnection(DBURL, USER, PASSWORD);
    }
}
