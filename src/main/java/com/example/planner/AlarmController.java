package com.example.planner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.awt.*;

public class AlarmController {
    @FXML
    public static Label napisOstrzezeniaAlarm;
    @FXML
    private Button bZakceptowaniaOstrzezeniaAlarmu;

    public void wylaczenieOkna(javafx.event.ActionEvent event) {
        if(event.getSource() == bZakceptowaniaOstrzezeniaAlarmu )
        {
            Stage stage = (Stage) bZakceptowaniaOstrzezeniaAlarmu.getScene().getWindow();
            stage.close();

        }
    }

}
