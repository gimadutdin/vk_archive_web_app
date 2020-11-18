package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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


    ////////////////////////////////////////
    @FXML
    private TableView<?> friend_table;

    @FXML
    private TableColumn<?, ?> column_fio;

    @FXML
    private TableColumn<?, ?> column_number;



}

