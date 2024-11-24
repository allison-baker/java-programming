module com.almariebaker.assignment13 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;

    opens com.almariebaker.assignment13 to javafx.fxml;
    exports com.almariebaker.assignment13;
}