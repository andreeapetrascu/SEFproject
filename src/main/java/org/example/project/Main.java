package org.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.project.Services.UserService;

import java.util.Objects;

public class Main extends Application {

    public Main() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        UserService.loadUsersFromFile();
        Parent root = (Parent)FXMLLoader.load(Objects.requireNonNull(this.getClass().getClassLoader().getResource("start.fxml")));
        primaryStage.setTitle("PUB");
        primaryStage.setScene(new Scene(root, 525.0D, 448.0D));
        primaryStage.show();

    }
}

