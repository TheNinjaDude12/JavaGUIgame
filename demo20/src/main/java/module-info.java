module com.example.demo20 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens com.example.demo20 to javafx.fxml;
    exports com.example.demo20;
}