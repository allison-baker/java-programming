module com.almariebaker.assignment12 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.almariebaker.assignment12 to javafx.fxml;
    exports com.almariebaker.assignment12;
}