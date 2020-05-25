package sample.controller;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class statement_window_controller extends statement_generator {

    @FXML
    private JFXButton generate_button;

    @FXML
    private ImageView back_icon;

    @FXML
    private ComboBox<?> worker_pick_field;

    @FXML
    private ComboBox<?> counterpart_pick_field;

    @FXML
    private ComboBox<?> accepted_pick_field;

    @FXML
    private JFXTextField number_declaration;

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
        });;
        generate_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            statement_generator statement_generator_usage = new statement_generator();
            LocalDate data = data_pick_field.getValue();
            String string_date = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String name = "Jan Kowalski";
            String counterparty = "Amazon";
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
}
