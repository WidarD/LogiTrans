package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Font.loadFont(getClass().getResourceAsStream("resources/font/Cyberpunk.ttf"), 32);
        Parent root = FXMLLoader.load(getClass().getResource("view/main_window.fxml"));
        primaryStage.setTitle("LogiTrans");
        primaryStage.setResizable(false);
        Image applicationIcon = new Image(getClass().getResourceAsStream("assets/icon_van.png"));
        primaryStage.getIcons().add(applicationIcon);
        primaryStage.setScene(new Scene(root, 800, 500));
        //Font.loadFont(getClass().getResourceAsStream("resources/fontstyle.css"),50);
        root.getStylesheets().add(getClass().getResource("resources/fontstyle.css").toExternalForm());
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
