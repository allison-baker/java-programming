module com.almariebaker.assignment14 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.almariebaker.assignment14 to javafx.fxml;
    exports com.almariebaker.assignment14;
}