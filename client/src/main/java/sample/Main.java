package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    static protected Stage primaryStage;
    static AnchorPane rootLayout;
    protected BorderPane pane;
    static protected Scene scene;


    @Override

    public void start(Stage primaryStage) throws Exception {
        System.out.println("main start");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("VK_ARCHIVE");
        primaryStage.setScene(new Scene(root, 711, 526));
        primaryStage.show();
        System.out.println("main end");
    }




    public static void main(String[] args) {
        launch(args);
    }
}
