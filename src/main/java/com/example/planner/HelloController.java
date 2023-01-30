package com.example.planner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class HelloController  extends Application implements Initializable {

    @FXML
    private Button bGlowna;
    @FXML
    private Button bDodajZadanie;
    @FXML
    private Button bUstawBudzik;
    @FXML
    private Button bUstawienia;
    @FXML
    private TextField pTytul;
    @FXML
    private TextField pLokalizacja;
    @FXML
    private ColorPicker pKolor;
    @FXML
    private  tornadofx.control.DateTimePicker pDataOd;
    @FXML
    private  tornadofx.control.DateTimePicker pDataDo;
    @FXML
    private Button bWyjscie;

    @FXML
    private Button bDodajZadanieFormularz;
    @FXML
    public Label lTytulBaza;
    @FXML
    private Label lLokalizacjaBaza;
    @FXML
    private Label lDataOdBaza;
    @FXML
    private Label lDataDoBaza;
    @FXML
    private static Pane panelZadania;
    @FXML
    public  Pane panelDodajZadanie;
    @FXML
    public Pane panelUstawBudzik;
    @FXML
    private  ChoiceBox muzykaAlarm;
    @FXML
    private tornadofx.control.DateTimePicker dataAlarm;
    @FXML
    private Button bUstawAlarm;
    @FXML
    private TextField pTytulAlarmu;
    @FXML
    private Label lMuzykaDostepneAlarmy;
    @FXML
    private Label lTytulDostepneAlarmy;
    @FXML
    private Label lDataDostepneAlarmy;
    @FXML
    private Button bStrzalkaPrawo;
    @FXML
    private Button bStrzalkaLewo;
    @FXML
    private Label lDzienDostepneAlarmy;
    @FXML
    private Button bUsuniecieAlarmu;

    @FXML
    public static AnchorPane mainPane;
    @FXML
    public Pane panelDostepneAlarmy;
    @FXML
    public Pane panelGlowny;

    @FXML
    private javafx.scene.image.ImageView zBudzik;
    @FXML
    private Label lIloscAlarmow;
    @FXML
    private Label lIloscAlarmowDzisiaj;
    @FXML
    public static  Label lIdAlarmuObecnego;

    @FXML
    private Label lAktualnaData;
    @FXML
    private Label lAktualnaGodzina;
    @FXML
    private Label lAktualnyDzienTygodnia;

    @FXML
    private Label lAktualnaData1;
    @FXML
    private Label lAktualnaGodzina1;
    @FXML
    private Label lAktualnyDzienTygodnia1;

    @FXML
    private Label lIloscZadanDzisiaj;
    @FXML
    private Label lIloscAlarmowDzisiaj2;
    @FXML
    public Label lAktywnyCytat;
    @FXML
    public Label lAktywnyAutor;
    @FXML
    public Button bNastepnyCytat;
    int ss, mm,hh,dd,MM,y;
    int ss2, mm2,hh2,dd2,MM2,y2;
    private final String[] items = {"Squid Game" , "Huawei Tune Living","Iphone 13"};

    public static int path;
    static MediaPlayer mediaPlayer;
    public static String path2;
    public static String dzienTygodniaAlarm;
    public static Boolean weryfikacjaAlarmu = false;

    public static int licznikAlarmy = 0;
    public static int licznikCytaty = 0;
    
    public static String napisDoWymagania;
    public LocalDateTime dataAlarmu;
    public LocalDateTime obecnyCzas;
    public String pomDzien;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //ConnectionMysql.wyswietlRekordBazaAlarm();
        //panelDodajZadanie.setVisible(false);
        panelUstawBudzik.setVisible(false);
        muzykaAlarm.getItems().addAll(items);
        //muzykaAlarm.setValue("Iphone 13");
        dataAlarm.setDateTimeValue(null);
        pTytulAlarmu.setText(null);
        ConnectionMysql.wyswietlRekordBazaZadanie();
        try {
            wyswietlDostepneCytaty();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        try {
            ConnectionMysql.wyswietlRekordBazaAlarm();
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

        ActionListener timerListener = new ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //javaFX operations should go here
                        LocalDateTime dates = LocalDateTime.now();
                        dates.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        try {
                            wyswietlDostepneAlarmy();
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }

                        try {
                            muzykaDoAlarmu();
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                        //time.setText(dates.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                        int ys = dates.getYear();
                        int ms = dates.getMonthValue();
                        int ds = dates.getDayOfMonth();
                        int mn = dates.getMinute();
                        int hr = dates.getHour();
                        int sc = dates.getSecond();


                        lAktualnaData.setText(dates.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        lAktualnaGodzina.setText(dates.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                        lAktualnaData1.setText(dates.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        lAktualnaGodzina1.setText(dates.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                        pomDzien = String.valueOf(dates.getDayOfWeek());
                        zamianaDniTygodniaNaPolskie(pomDzien);
                        lAktualnyDzienTygodnia.setText(pomDzien);
                        lAktualnyDzienTygodnia1.setText(pomDzien);



                        /*y = dataAlarm.getDateTimeValue().getYear();
                        MM = dataAlarm.getDateTimeValue().getMonthValue();
                        dd = dataAlarm.getDateTimeValue().getDayOfMonth();
                        hh = dataAlarm.getDateTimeValue().getHour();
                        mm = dataAlarm.getDateTimeValue().getMinute();
                        ss = dataAlarm.getDateTimeValue().getSecond();*/

                        y = ConnectionMysql.dataNajwczesniejszegoAlarmu.getYear();
                        MM = ConnectionMysql.dataNajwczesniejszegoAlarmu.getMonthValue();
                        dd = ConnectionMysql.dataNajwczesniejszegoAlarmu.getDayOfMonth();
                        hh = ConnectionMysql.dataNajwczesniejszegoAlarmu.getHour();
                        if( ConnectionMysql.dataNajwczesniejszegoAlarmu.getMinute() != 0)
                            mm = ConnectionMysql.dataNajwczesniejszegoAlarmu.getMinute() - 1;
                        else
                        {
                            mm = 59;
                            hh = hh-1;
                        }

                        ss = 59;


                        try{
                            y2 = ConnectionMysql.dataNajwczesniejszegoAlarmu.getYear();
                            MM2 = ConnectionMysql.dataNajwczesniejszegoAlarmu.getMonthValue();
                            dd2 = ConnectionMysql.dataNajwczesniejszegoAlarmu.getDayOfMonth();
                            hh2 = ConnectionMysql.dataNajwczesniejszegoAlarmu.getHour();
                            if( ConnectionMysql.dataNajwczesniejszegoAlarmu.getMinute() != 0)
                                mm2 = ConnectionMysql.dataNajwczesniejszegoAlarmu.getMinute() - 1;
                            else
                            {
                                mm2 = 59;
                                hh2 = hh2-1;
                            }

                            ss2 = 59;
                        }catch (Exception e){
                            System.out.println("brak najnowszego alarmu");
                        }

                        System.out.println(ys + " " + ms + " " + ds + " " + hr + " " + mn + " " + sc + " " + y + " " + MM + " " +  dd + " " + hh + " " + mm + " " + ss);

                        if (mn == mm && hr == hh && ds == dd && ys == y && ms == MM && sc == ss)
                        {
                            System.out.println(mn + " " + mm + " " + hr + " " + hh);
                            System.out.print("Matched 1");
                            muzykaStart();
                            ConnectionMysql.wyswietlRekordNajwczesniejszyAlarm();
                        }
                        else if (mn == mm2 && hr == hh2 && ds == dd2 && ys == y2 && ms == MM2 && sc == ss2)
                        {
                            System.out.print("Matched ");
                            try {
                                muzykaDoAlarmu();
                            } catch (FileNotFoundException ex) {
                                throw new RuntimeException(ex);
                            }
                            muzykaStart();

                        }


                    }
                });



            }
        };

        Timer timer = new Timer(1000, timerListener);
        timer.setInitialDelay(0);
        timer.start();


    }

    public  void wyslanieAlarmuDoBazy() throws FileNotFoundException, ParseException {
        muzykaDoAlarmu();


            try {
                ConnectionMysql.wyswietlRekordBazaAlarm();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

            //ConnectionMysql.wyswietlRekordNajwczesniejszyAlarm();
            ConnectionMysql.usuniecieAlarmowZBazy();
            try
            {
                dzienTygodniaAlarm = String.valueOf(dataAlarm.getDateTimeValue().getDayOfWeek());
            }catch (Exception e)
            {
                System.out.println("blad dzien tygodnia");
            }
            zamianaDniTygodniaNaPolskie(dzienTygodniaAlarm);
            ConnectionMysql.dodajAlarmDoBazy(pTytulAlarmu.getText() , (String) muzykaAlarm.getValue(),dataAlarm.getDateTimeValue(), dzienTygodniaAlarm);
            pTytulAlarmu.setText("");
            muzykaAlarm.setValue("");
            System.out.println(dzienTygodniaAlarm);

    }

    @FXML
    public  void usuniecieAlarmuBaza(ActionEvent event) throws ParseException {
        if(event.getSource() == bUsuniecieAlarmu)
        {
            System.out.println("usunales budzik");
            ConnectionMysql.usuniecieAlarmu();
            ConnectionMysql.wyswietlRekordBazaAlarm();
        }
    }

    @FXML
    public  void handleClicks(ActionEvent event) throws ParseException, FileNotFoundException {
        if (event.getSource() == bGlowna)
        {
            //panelDodajZadanie.setVisible(false);
            panelGlowny.setVisible(true);
            panelUstawBudzik.setVisible(false);
            //panelZadania.setVisible(true);
            wyswietlDostepneCytaty();
        }
        if (event.getSource() == bDodajZadanie)
        {
            //panelDodajZadanie.setVisible(true);
            panelUstawBudzik.setVisible(false);
            panelGlowny.setVisible(false);
           //panelZadania.setVisible(false);
        }
        if (event.getSource() == bUstawBudzik)
        {
            //panelDodajZadanie.setVisible(false);
            panelUstawBudzik.setVisible(true);
            panelGlowny.setVisible(false);
            ConnectionMysql.wyswietlRekordBazaAlarm();
            wyswietlDostepneAlarmy();
        }
        if (event.getSource() == bUstawienia)
        {
            //panelDodajZadanie.setVisible(false);
            panelGlowny.setVisible(false);
            panelUstawBudzik.setVisible(false);
        }
    }


    private void wyswietlDostepneAlarmy() throws ParseException {
        ConnectionMysql.wyswietlRekordBazaAlarm();
        //lIdAlarmuObecnego.setText(ConnectionMysql.tablicaId[licznikAlarmy]);
        lDataDostepneAlarmy.setText(ConnectionMysql.tablicaData[licznikAlarmy]);
        lMuzykaDostepneAlarmy.setText(ConnectionMysql.tablicaMuzyka[licznikAlarmy]);
        lTytulDostepneAlarmy.setText(ConnectionMysql.tablicaTytul[licznikAlarmy]);
        lDzienDostepneAlarmy.setText(ConnectionMysql.tablicaDzien[licznikAlarmy]);
        lIloscAlarmow.setText(String.valueOf(ConnectionMysql.iloscAlarmowBaza));
        lIloscAlarmowDzisiaj.setText(String.valueOf(ConnectionMysql.iloscAlarmowBazaDzisiaj));
        lIloscAlarmowDzisiaj2.setText(String.valueOf(ConnectionMysql.iloscAlarmowBazaDzisiaj));
        lIloscZadanDzisiaj.setText(String.valueOf(ConnectionMysql.iloscZadanBaza));

    }

    @FXML
    public  void zmienienieDostepnychAlarmowPrawo(ActionEvent event) throws ParseException {
        if (event.getSource() == bStrzalkaPrawo)
        {
            ConnectionMysql.wyswietlRekordBazaAlarm();
            System.out.println("kliknales przycisk");
            if(ConnectionMysql.iloscAlarmowBaza > licznikAlarmy +1)
                licznikAlarmy++;

            wyswietlDostepneAlarmy();
            //System.out.println(lIdAlarmuObecnego.getText());

        }

    }

    @FXML
    public  void zmienienieDostepnychAlarmowLewo(ActionEvent event) throws ParseException {
        if (event.getSource() == bStrzalkaLewo)
        {
            System.out.println("kliknales przycisk");
            if(licznikAlarmy > 0)
                licznikAlarmy--;
            ConnectionMysql.wyswietlRekordBazaAlarm();
            wyswietlDostepneAlarmy();

            //System.out.println(lIdAlarmuObecnego.getText());

        }

    }
    private void wyswietlDostepneCytaty() throws ParseException {
        ConnectionMysql.wyswietlRekordBazaCytat();
        //lIdAlarmuObecnego.setText(ConnectionMysql.tablicaId[licznikAlarmy]);
        lAktywnyCytat.setText(ConnectionMysql.tablicaCytat[licznikCytaty]);
        lAktywnyAutor.setText(ConnectionMysql.tablicaAutor[licznikCytaty]);

    }

    @FXML
    public  void zmienienieDostepnychCytatowPrawo(ActionEvent event) throws ParseException {
        if (event.getSource() == bNastepnyCytat)
        {
            ConnectionMysql.wyswietlRekordBazaCytat();
            System.out.println("kliknales przycisk");
            if(ConnectionMysql.iloscCytatów > licznikCytaty + 1)
                licznikCytaty++;
            else if(ConnectionMysql.iloscCytatów >= licznikCytaty)
                licznikCytaty = 0;


            wyswietlDostepneCytaty();
            //System.out.println(lIdAlarmuObecnego.getText());

        }

    }



    @FXML
    public  void muzykaDoAlarmu() throws FileNotFoundException {
        try {
            String wartoscAlarmu = (String) ConnectionMysql.muzykaBazaAlarm;

            System.out.println(wartoscAlarmu);

            switch (wartoscAlarmu) {
                case "Squid Game":
                    path = 1;
                    break;
                case "Huawei Tune Living":
                    path = 2;
                    break;
                case "Iphone 13":
                    path = 3;
                    break;
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void wymaganiaDoDodaniaBudzika(ActionEvent event) throws FileNotFoundException, ParseException {
        if(event.getSource() == bUstawAlarm)
        {
            try {
                obecnyCzas = LocalDateTime.now();
                obecnyCzas.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                dataAlarmu = dataAlarm.getDateTimeValue();
                dataAlarmu.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }catch (Exception e)
            {

            }


            if(muzykaAlarm.getValue() == null)
            {
                napisDoWymagania = "Pole muzyka jest puste.";
                zaladujScene("wymaganieAlarm.fxml");
                //AlarmController.napisOstrzezeniaAlarm.setText("test");
                System.out.println("wybierz muzyke");

            }
            else if(dataAlarm.getDateTimeValue() == null)
            {
                napisDoWymagania = " Pole data jest puste.";
                zaladujScene("wymaganieAlarm.fxml");

                //AlarmController.napisOstrzezeniaAlarm.setText("test1");
            }
            else if(pTytulAlarmu.getText() == null)
            {
                napisDoWymagania = " Pole tytuł jest puste.";
                zaladujScene("wymaganieAlarm.fxml");
            }

            else if(pTytulAlarmu.getLength() >  20)
            {
                napisDoWymagania = "Tytuł nie może przekraczać 20 znaków.";
                zaladujScene("wymaganieAlarm.fxml");


            }
            else if(obecnyCzas.compareTo(dataAlarmu) > 0)
            {
                napisDoWymagania = "Budzik nie może zostać ustawiony na przeszłość!";
                zaladujScene("wymaganieAlarm.fxml");


            }
            else
            {
                wyslanieAlarmuDoBazy();
                muzykaAlarm.setValue(null);
                dataAlarm.setDateTimeValue(null);
                pTytulAlarmu.setText(null);
            }

        }
    }

    public void zamianaDniTygodniaNaPolskie(String dzienTygodnia)
    {
        switch (dzienTygodnia)
        {
            case "MONDAY":
                dzienTygodniaAlarm  = "Poniedziałek";
                pomDzien = "Poniedziałek";
                break;
            case "TUESDAY":
                dzienTygodniaAlarm  = "Wtorek";
                pomDzien  = "Wtorek";
                break;
            case "WEDNESDAY":
                dzienTygodniaAlarm  = "Środa";
                pomDzien  = "Środa";
                break;
            case "THURSDAY":
                dzienTygodniaAlarm  = "Czwartek";
                pomDzien  = "Czwartek";
                break;
            case "FRIDAY":
                dzienTygodniaAlarm  = "Piątek";
                pomDzien  = "Piątek";
                break;
            case "SATURDAY":
                dzienTygodniaAlarm  = "Sobota";
                pomDzien  = "Sobota";
                break;
            case "SUNDAY":
                dzienTygodniaAlarm  = "Niedziela";
                pomDzien  = "Niedziela";
                break;



        }
    }

    public  void muzykaStart() {


        if(path == 1){
            path2 = "C:\\Users\\marci\\PycharmProjects\\pythonProject3\\Squid_Game.mp3";
        }
        else if(path == 2){
            path2 = "C:\\Users\\marci\\PycharmProjects\\pythonProject3\\Huawei_Tune_Living.mp3";
        }
        else if(path == 3){
            path2 = "C:\\Users\\marci\\PycharmProjects\\pythonProject3\\iPhone_13_.mp3";
        }
        if(path != 0 )
            zaladujScene("budzik.fxml");

        Media h = new Media(Paths.get(path2).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
    }

    public static void muzykaStop() {
        mediaPlayer.stop();
    }
    @FXML
    private  void dodajZadanie(ActionEvent event)
    {
        if(event.getSource() == bDodajZadanieFormularz)
        {

            String wartoscKolor = String.valueOf(pKolor.getValue());

            //ConnectionMysql.dodajZadanieDoBazy(pTytul.getText() ,pLokalizacja.getText(), (Color) Paint.valueOf(wartoscKolor),pDataOd.getDateTimeValue(),pDataDo.getDateTimeValue());
            ConnectionMysql.wyswietlRekordBazaZadanie();
            lTytulBaza.setText(ConnectionMysql.tytulRekord);
            lLokalizacjaBaza.setText(ConnectionMysql.lokalizacjaRekord);
            lDataOdBaza.setText(ConnectionMysql.dataOdRekord);
            lDataDoBaza.setText(ConnectionMysql.dataDoRekord);
            panelZadania.setBackground(Background.fill(Paint.valueOf(ConnectionMysql.kolorRekord)));


        }
    }
    public void handleClose(ActionEvent event){
        if (event.getSource() == bWyjscie){
            System.exit(1);
        }
    }

    private void zaladujScene(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage stage) throws Exception {
        launch();
    }
}