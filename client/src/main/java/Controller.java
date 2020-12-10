import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;

public class Controller {

    @FXML
    private TextField date;

    @FXML
    private Button photo_button;

    @FXML
    private TextField study;

    @FXML
    private TextField city;

    @FXML
    private Button search_button;

    @FXML
    private ImageView avatar;

    @FXML
    private TextField FIO;

    @FXML
    private TextField friends;

    @FXML
    private TextField search_row;

    public void onSearchClick(ActionEvent actionEvent) throws IOException {
        String urlUserInfo = String.format("http://localhost:8080/user-info/%s", search_row.getText());
        HashMap<String, String> map = new HashMap<String, String>();

        Document docUserInfo = Jsoup.connect(urlUserInfo)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .ignoreContentType(true)
                .data(map)
                .get();
        System.out.println(docUserInfo.text());

        String json = docUserInfo.text();
        JSONObject jsonObject = new JSONObject(json);
        FIO.setText(jsonObject.getString("fio"));
        city.setText(jsonObject.getString("city"));
        date.setText(jsonObject.getString("birthday"));
        study.setText(jsonObject.getString("study_place"));
    }

    @FXML
    void friend_button(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("friendss.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void photo_button(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zxc.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


////////////////////////////////////////


