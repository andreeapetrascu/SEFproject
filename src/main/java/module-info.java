module PUB {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.io;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens org.example.project to javafx.fxml;
    opens org.example.project.Controllers to javafx.fxml;
    exports org.example.project;
    exports org.example.project.Actions;
    exports org.example.project.Controllers;

}