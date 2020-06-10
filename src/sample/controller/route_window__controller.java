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
import sample.database.route_database_handler;
import sample.model.route_model;

import java.io.IOException;
import java.sql.*;

import static sample.database.configuration.*;

public class route_window__controller {
    @FXML
    private ImageView back_icon;
    @FXML
    private JFXButton add_r_button;
    @FXML
    private JFXButton update_r_button;
    @FXML
    private JFXButton delate_r_button;
    @FXML
    private TableView<route_model> table;
    @FXML
    private TableColumn city_start_list;
    @FXML
    private TableColumn city_stop_list;
    @FXML
    private TableColumn km_list;

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
        add_r_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            add_r_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/add_route_window.fxml"));
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

        update_r_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            update_r_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/update_route_window.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String city1 = null;
            String city2 = null;
            int distance = 0;
            if (table.getSelectionModel().getSelectedItem() != null) {

                route_model cp = table.getSelectionModel().getSelectedItem();
                city1 = cp.city_start;
                city2 = cp.city_stop;
                distance = cp.km;
            }
            update_route_controller updateController = loader.getController();

            try {
                updateController.write_value(city1, city2, distance);

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

        delate_r_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
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

    public static Connection get_database_connection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(DBURL, USER, PASSWORD);
    }

    public void getTable() throws SQLException, ClassNotFoundException {

        Connection conn = get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select idRoute, city_start, city_stop, km From route";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ObservableList<route_model> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new route_model(rst.getInt("idRoute"), rst.getString("city_start"), rst.getString("city_stop"), rst.getInt("km")));
        }

        city_start_list.setCellValueFactory(new PropertyValueFactory<>("city_start"));
        city_stop_list.setCellValueFactory(new PropertyValueFactory<>("city_stop"));
        km_list.setCellValueFactory(new PropertyValueFactory<>("km"));
        table.setItems(list);
    }


    public void delateRow() throws SQLException, ClassNotFoundException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            route_model cp = table.getSelectionModel().getSelectedItem();
            String city1 = cp.city_start;
            String city2 = cp.city_stop;

            Connection conn = get_database_connection();
            Statement stm;
            stm = conn.createStatement();
            String sql = "SELECT idRoute FROM route WHERE city_start = ? AND city_stop = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, city1);
            pstmt.setString(2, city2);

            int id = 0;

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idRoute");
            }


            try {
                route_database_handler app = new route_database_handler();
                app.delete_route(id);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}




