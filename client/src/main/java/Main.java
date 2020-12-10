import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {


    static protected Stage primaryStage;
    static AnchorPane rootLayout;
    protected BorderPane pane;
    static protected Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("main start");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent content = loader.load();
        primaryStage.setTitle("VK_ARCHIVE");
        primaryStage.setScene(new Scene(content, 711, 526));
        primaryStage.show();
        System.out.println("main end");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
