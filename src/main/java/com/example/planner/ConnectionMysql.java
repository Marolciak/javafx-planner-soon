package com.example.planner;
import javafx.scene.paint.Color;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConnectionMysql {

    public static String tytulRekord;
    public static String lokalizacjaRekord;
    public static String kolorRekord;
    public static String dataOdRekord;
    public static String dataDoRekord;

    public static String tytulBazaAlarm;
    public static String muzykaBazaAlarm;
    public static String dataBazaAlarm;
    public static int iloscAlarmowBaza;
    public static int iloscAlarmowBazaDzisiaj;
    public  static boolean czyIstniejeRekord;

    public static String dataNajwczesniejszyAlarm;
    public static LocalDateTime dataNajwczesniejszegoAlarmu;
    public  static String doFormatowania;
    public static String tytulDoAlarmuObecnego;
    public static String muzykaDoAlarmuObecnego;
    public static String[] tablicaTytul = new String[150];
    public static String[] tablicaMuzyka = new String[150];

    public static String[] tablicaData = new String[150];

    public static String[] tablicaDzien = new String[150];

    public static String[] tablicaId = new String[150];

    public static String[] tablicaCytat = new String[150];

    public static String[] tablicaAutor = new String[150];

    public static String ikonaBazaSpotkanie;

    public static int iloscZadanBaza;
    public static int iloscCytatów;





    public static void dodajZadanieDoBazy(String tytul, String lokalizacja, Color kolor, LocalDateTime DataOd, LocalDateTime DataDo) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/planner", "root", "");
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate("INSERT INTO `zadania` VALUES (NULL,'" + tytul  +"' , '" + lokalizacja + "' , '" + kolor + "' ,'" + DataOd + "' , '" + DataDo + "' )");

            con.close();
        } catch (Exception evt) {
            System.out.println(evt);
        }

    }

    public static void dodajAlarmDoBazy(String tytul, String muzyka, LocalDateTime data , String dzienTygodnia) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/planner", "root", "");
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate("INSERT INTO `alarmy` VALUES (NULL,'" + tytul  +"' , '" + muzyka + "' , '" + data + "' , '" + dzienTygodnia + "' )");
            con.close();
        } catch (Exception evt) {
            System.out.println(evt);
        }

    }

    public static void dodajSpotkanieDoBazy(String tytul , String osoba , LocalDateTime data , String lokalizacja , String ikona)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/planner", "root", "");
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate("INSERT INTO `spotkania` VALUES (NULL,'" + tytul  +"' , '" + osoba + "' , '" + lokalizacja + "' , '" + ikona + "', '" + data + "' )");
            con.close();
        } catch (Exception evt) {
            System.out.println(evt);
        }
    }

    public static void wyswietlRekordBazaSpotkanie()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/planner", "root", "");
            PreparedStatement st = con.prepareStatement("select ikona from spotkania order by data asc");
            ResultSet rs = st.executeQuery();
            rs.next();
            ikonaBazaSpotkanie = rs.getString(1);
            System.out.println(ikonaBazaSpotkanie);


            con.close();
        } catch (Exception evt) {
            System.out.println(evt);
        }
    }

    public static void wyswietlRekordBazaZadanie(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/planner" , "root" , "");
            PreparedStatement st = con.prepareStatement("select * from zadania where id = 1");
            ResultSet rs = st.executeQuery();
            rs.next();
            tytulRekord =  String.valueOf(rs.getString(2));
            lokalizacjaRekord = String.valueOf(rs.getString(3));
            kolorRekord = String.valueOf(rs.getString(4));
            dataOdRekord = String.valueOf(rs.getString(5));
            dataDoRekord = String.valueOf(rs.getString(6));
            //System.out.println(1);
            System.out.println(tytulRekord);
            System.out.println(lokalizacjaRekord);
            System.out.println(kolorRekord);
            System.out.println(dataOdRekord);
            System.out.println(dataDoRekord);

            PreparedStatement st2 = con.prepareStatement("select COUNT(id) as count  from zadania where SUBSTRING(DataOd,1,10) = CURRENT_DATE");
            ResultSet rs2 = st2.executeQuery();
            rs2.next();
            iloscZadanBaza = rs2.getInt(1);

            /*Statement st2 = con.createStatement();
            ResultSet rs2 = st.executeQuery("SELECT COUNT(id) as count from alarmy");
            rs2.next();*/
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int licznikIdAlarm = 1;
    public static void wyswietlRekordNajwczesniejszyAlarm()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/planner" , "root" , "");
            PreparedStatement st3 = con.prepareStatement("select * from alarmy where data > CURRENT_TIMESTAMP order by data asc limit 1;");
            //PreparedStatement st3 = con.prepareStatement("select * from alarmy order by data asc;");
            ResultSet rs3 = st3.executeQuery();
            doFormatowania = "2030-10-10 10:10:10";
            if(rs3.next())
            {
                tytulDoAlarmuObecnego = rs3.getString(2);
                muzykaDoAlarmuObecnego = rs3.getString(3);
                dataNajwczesniejszyAlarm = rs3.getString(4);
                //muzykaBazaAlarm = rs3.getString(5);
                //System.out.println(dataNajwczesniejszyAlarm);

                //doFormatowania = dataNajwczesniejszyAlarm;

            }


            try{
                DateTimeFormatter patternFormatowania = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                dataNajwczesniejszegoAlarmu = LocalDateTime.parse(dataNajwczesniejszyAlarm,patternFormatowania);
            }catch (Exception e){
                System.out.println("brak najnowszego alarmu");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void wyswietlRekordBazaCytat()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/planner" , "root" , "");
            PreparedStatement st = con.prepareStatement("select * from cytaty ;");
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next())
            {
                tablicaCytat[i] = rs.getString(2);
                tablicaAutor[i] = rs.getString(3);
                i++;
            }

            PreparedStatement st2 = con.prepareStatement("select COUNT(id) from cytaty ;");
            ResultSet rs2 = st2.executeQuery();
            rs2.next();
            iloscCytatów = rs2.getInt(1);

        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void wyswietlRekordBazaAlarm() throws ParseException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/planner" , "root" , "");
            PreparedStatement st = con.prepareStatement("select * from alarmy  order by data asc;");
            ResultSet rs = st.executeQuery();
            PreparedStatement sts = con.prepareStatement("select * from alarmy order by data asc;");
            ResultSet rss = sts.executeQuery();
            rss.next();
            muzykaBazaAlarm = rss.getString(3);


            int i = 0;
            while(rs.next())
            {
                tablicaId[i] = String.valueOf(rs.getString(1));
                tablicaTytul[i] = rs.getString(2);
                tablicaMuzyka[i] = String.valueOf(rs.getString(3));
                tablicaData[i] = String.valueOf(rs.getString(4));
                tablicaDzien[i] = rs.getString(5);
                i++;
            }





            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery("SELECT COUNT(id) as count from alarmy");
            rs2.next();
            iloscAlarmowBaza = rs2.getInt(1);

            Statement stp = con.createStatement();
            ResultSet rsp = stp.executeQuery("select COUNT(id) as count  from alarmy where SUBSTRING(data,1,10) = CURRENT_DATE;");
            rsp.next();
            iloscAlarmowBazaDzisiaj = rsp.getInt(1);



            //PreparedStatement st3 = con.prepareStatement("select * from alarmy where data > CURRENT_TIMESTAMP order by data asc limit 1;");
            PreparedStatement st3 = con.prepareStatement("select * from alarmy where data > CURRENT_TIMESTAMP order by data asc limit 1;");
            //PreparedStatement st3 = con.prepareStatement("select * from alarmy order by data asc;");
            ResultSet rs3 = st3.executeQuery();
            doFormatowania = "2030-10-10 10:10:10";
            if(rs3.next())
            {

                tytulDoAlarmuObecnego = rs3.getString(2);
                muzykaDoAlarmuObecnego = rs3.getString(3);
                dataNajwczesniejszyAlarm = rs3.getString(4);
                //muzykaBazaAlarm = rs3.getString(5);
                //System.out.println(dataNajwczesniejszyAlarm);

                //doFormatowania = dataNajwczesniejszyAlarm;

            }


            try{
                DateTimeFormatter patternFormatowania = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                dataNajwczesniejszegoAlarmu = LocalDateTime.parse(dataNajwczesniejszyAlarm,patternFormatowania);
            }catch (Exception e){
                System.out.println("brak najnowszego alarmu");
            }



            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void usuniecieAlarmowZBazy(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/planner", "root", "");
            PreparedStatement st = con.prepareStatement("DELETE FROM `alarmy` WHERE data < CURRENT_TIMESTAMP;");
            st.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void usuniecieAlarmu() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/planner", "root", "");
            //PreparedStatement st = con.prepareStatement("delete from alarmy where tytul = "  + " " + " " );
            wyswietlRekordBazaAlarm();
            //PreparedStatement st = con.prepareStatement("\"INSERT INTO `alarmy` VALUES (NULL,'\" + tytul  +\"' , '\" + muzyka + \"' , '\" + data + \"' , '\" + dzienTygodnia + \"' )\"")
            //PreparedStatement st = con.prepareStatement("delete from alarmy where tytul = '" + tablicaTytul[licznikTablicyAlarmy] + "' and muzyka =  '" + tablicaMuzyka[licznikTablicyAlarmy] + "' and data =  '" +
                //    tablicaData[licznikTablicyAlarmy] + "' ");
            PreparedStatement st = con.prepareStatement("delete from alarmy where id = '" + tablicaId[HelloController.licznikAlarmy] + "'   ;");

            st.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

        public static void main(String[] args) throws ParseException {
        wyswietlRekordBazaSpotkanie();

    }


}






