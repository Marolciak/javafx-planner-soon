package com.example.planner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class HomeController implements Initializable {

    @FXML
    private  Button bKontynuuj;
    @FXML
    private AnchorPane mainPane;


    private void zaladujScene(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Planner");
            stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            //new animatefx.animation.SlideInDown(mainPane).play();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void kliknieciePrzycisku(javafx.event.ActionEvent mouseEvent) throws FileNotFoundException {
        if (mouseEvent.getSource() == bKontynuuj) {
            Stage stage = (Stage) bKontynuuj.getScene().getWindow();
            stage.close();
            zaladujScene("main.fxml");

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            ConnectionMysql.usuniecieAlarmowZBazy();
            ConnectionMysql.wyswietlRekordNajwczesniejszyAlarm();
    }
}
