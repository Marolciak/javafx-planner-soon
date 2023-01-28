package com.example.planner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AlarmController  implements Initializable {
    @FXML
    private Label napisOstrzezeniaAlarm = new Label();
    @FXML
    private Button bZakceptowaniaOstrzezeniaAlarmu;





    public void wylaczenieOkna(javafx.event.ActionEvent event) {
        if(event.getSource() == bZakceptowaniaOstrzezeniaAlarmu )
        {
            Stage stage = (Stage) bZakceptowaniaOstrzezeniaAlarmu.getScene().getWindow();
            stage.close();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(HelloController.napisDoWymagania);
        napisOstrzezeniaAlarm.setText(HelloController.napisDoWymagania);
    }
}
