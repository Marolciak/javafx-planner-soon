package com.example.planner;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class BudzikController implements Initializable {


    @FXML
    private Button bWylaczAlarm;
    @FXML
    private Pane mainPane;
    @FXML
    public javafx.scene.control.Label lDataAlarmu;
    @FXML
    public javafx.scene.control.Label lGodzinaAlarmu;
    @FXML
    public javafx.scene.control.Label lTytulAlarmu;

    private Boolean weryfikacjaAnimacji = true;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle ) {
        //new animatefx.animation.BounceIn(bWylaczAlarm).play();
        try {
            ConnectionMysql.wyswietlRekordBazaAlarm();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        lTytulAlarmu.setText(ConnectionMysql.tytulDoAlarmuObecnego);
        lGodzinaAlarmu.setText(ConnectionMysql.dataNajwczesniejszegoAlarmu.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        lDataAlarmu.setText(ConnectionMysql.dataNajwczesniejszegoAlarmu.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        //lDataAlarmu.setText(ConnectionMysql.dataNajwczesniejszegoAlarmu.getYear() + "-"+ ConnectionMysql.dataNajwczesniejszegoAlarmu.getMonthValue() + "-" + ConnectionMysql.dataNajwczesniejszegoAlarmu.getDayOfMonth());
        //lGodzinaAlarmu.setText(ConnectionMysql.dataNajwczesniejszegoAlarmu.getHour() + ":" +ConnectionMysql.dataNajwczesniejszegoAlarmu.getMinute() + ":" + ConnectionMysql.dataNajwczesniejszegoAlarmu.getSecond());
        ActionListener timerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        new animatefx.animation.Shake(bWylaczAlarm).play();
                        new animatefx.animation.Bounce(mainPane).play();

                    }
                })
            ;}
        };
        Timer timer = new Timer(2000, timerListener);
        // to make sure it doesn't wait one second at the start
        timer.setInitialDelay(0);
        timer.start();
    }
    public void wylaczenieAlarmu(javafx.event.ActionEvent event) {
        if(event.getSource() == bWylaczAlarm)
        {
            weryfikacjaAnimacji = false;
            HelloController.muzykaStop();
            Stage stage = (Stage) bWylaczAlarm.getScene().getWindow();
            stage.close();

        }
    }
}
