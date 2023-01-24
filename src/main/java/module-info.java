module com.example.planner {
    requires javafx.controls;
    requires javafx.fxml;
    requires tornadofx.controls;
    requires java.sql;
    requires javafx.media;
    requires java.desktop;
    requires AnimateFX;


    opens com.example.planner to javafx.fxml;
    exports com.example.planner;
}