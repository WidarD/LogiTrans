package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.database.worker_database_handler;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

import static sample.database.configuration.*;

public class update_worker_controller {

    @FXML
    private JFXButton update_worker;
    @FXML
    private TextField name_update;
    @FXML
    private TextField surname_update;
    @FXML
    private TextField id_update;
    @FXML
    private TextField phone_number_update;
    @FXML
    private DatePicker driving_license_update;
    @FXML
    private DatePicker sanitary_book_update;
    @FXML
    private DatePicker driver_card_update;
    @FXML
    private CheckBox ohs_yes;
    @FXML
    private JFXComboBox tractor_update;
    @FXML
    private JFXComboBox trailer_update;
    @FXML

    void initialize() throws SQLException, ClassNotFoundException {

        update_worker.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            String name = name_update.getText();
            String surname = surname_update.getText();
            String ID_number = id_update.getText();
            String phone_number = phone_number_update.getText();
            LocalDate dl= driving_license_update.getValue();
            LocalDate sb = sanitary_book_update.getValue();
            LocalDate dc = driver_card_update.getValue();
            Boolean yes = ohs_yes.isSelected();
            Boolean OHS;
            Date driving_license = Date.valueOf(dl);
            Date sanitary_book =Date.valueOf(sb);
            Date driver_card = Date.valueOf(dc);



            if (yes) {
                OHS = true;
            }
            else {
                OHS = false;
            }
            int f_key_tractor = 0;
            int f_key_trailer = 0;
            try {
                f_key_tractor = getIdTractor_unit();
                f_key_trailer = getIdTrailer_unit();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            ObservableList<String> ID  = FXCollections.observableArrayList();
            try {
                Connection conn2=get_database_connection();
                Statement stm2;
                stm2 = conn2.createStatement();
                String sql2 = "Select ID_number From worker";
                ResultSet rst2 = stm2.executeQuery(sql2);

                while(rst2.next()){
                    ID.add(rst2.getString("ID_number"));
                }

            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }


            if(ID.contains(ID_number))
            {
                String message = "Błąd dodawania\n"
                        + "Ten pesel już istnieje";
                JOptionPane.showMessageDialog(new JFrame(), message, "Błąd!",
                        JOptionPane.ERROR_MESSAGE);
            }else
            {
                worker_database_handler app = new worker_database_handler();
                try {
                    app.insert_worker(name, surname, ID_number, phone_number, driving_license, sanitary_book, driver_card, OHS, f_key_tractor, f_key_trailer);
                } catch (SQLException ee) {
                    ee.printStackTrace();
                }
            }

            update_worker.getScene().getWindow().hide();
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

    }

    public void write_value(String value1, String value2, String value3, String value4, LocalDate value5, LocalDate value6, LocalDate value7, boolean value8, String value9, String value10  ) throws SQLException, ClassNotFoundException {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();

        String st = "Select registration_number From semi_trailer";
        ResultSet rst_st = stm.executeQuery(st);
        ObservableList<String> trailer  = FXCollections.observableArrayList();
        while(rst_st.next()){
            trailer.add(rst_st.getString("registration_number"));
        }
        trailer_update.setItems(trailer);

        String sql = "Select registration_number From tractor_unit";
        ResultSet rst = stm.executeQuery(sql);
        ObservableList<String> tractor  = FXCollections.observableArrayList();
        while(rst.next()){
            tractor.add(rst.getString("registration_number"));
        }
        tractor_update.setItems(tractor);

        tractor_update.setEditable(true);
        trailer_update.setEditable(true);


        name_update.setText(value1);
        surname_update.setText(value2);
        id_update.setText(value3);
        phone_number_update.setText(value4);
        driving_license_update.setValue(value5);
        sanitary_book_update.setValue(value6);
        driver_card_update.setValue(value7);
        ohs_yes.setSelected(value8);
        tractor_update.setValue(value9);
        trailer_update.setValue(value10);
    }
    public static Connection get_database_connection() throws SQLException, ClassNotFoundException
    {
        return DriverManager.getConnection(DBURL, USER, PASSWORD);
    }

    public int getIdTractor_unit() throws SQLException, ClassNotFoundException {


        Object tractor_sel = tractor_update.getSelectionModel().getSelectedItem();
        String r_tractor = tractor_sel.toString();

        Connection conn2=get_database_connection();
        Statement stm2;
        stm2 = conn2.createStatement();
        String sql2 = "SELECT idTractor_unit FROM tractor_unit WHERE registration_number = ?";
        PreparedStatement pstmt = conn2.prepareStatement(sql2);
        pstmt.setString(1, r_tractor);

        int id =0;

        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            id = rs.getInt("idTractor_unit");
        }return id;

    }

    public int getIdTrailer_unit() throws SQLException, ClassNotFoundException {


        Object trailer_sel = trailer_update.getSelectionModel().getSelectedItem();
        String r_trailer = trailer_sel.toString();

        Connection conn2=get_database_connection();
        Statement stm2;
        stm2 = conn2.createStatement();
        String sql2 = "SELECT idSemi_trailer FROM semi_trailer WHERE registration_number = ?";
        PreparedStatement pstmt = conn2.prepareStatement(sql2);
        pstmt.setString(1, r_trailer);

        int id =0;

        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            id = rs.getInt("idSemi_trailer");
        }return id;

    }

}
