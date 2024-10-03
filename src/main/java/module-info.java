module com.pcverksted{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;

    opens com.pcverksted.application to javafx.fxml, javafx.graphics;
    exports com.pcverksted.application;
}
