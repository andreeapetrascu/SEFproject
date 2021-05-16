package org.example.project.Controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.project.Actions.User;
import org.example.project.Services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WritersController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    public ListView<User> WritersList;
    @FXML
    public Text userBox;
    @FXML
    private Button BackToClientMain;
    @FXML
    private Button Select;

    private static List<User> writers = new ArrayList<>();
    private static User client;
    private User selectedWriter;

    public User getSelectedWriter() {
        return selectedWriter;
    }


    public void initData(User c) throws Exception {
        this.client = c;
        this.userBox.setText("Hello, " + client.getUsername() + "!");
        updateWritersList();
    }

    public static User getClient() {
        return client;
    }

    private void updateWritersList() throws Exception {
        writers.removeAll(writers);
        List<User> b = new ArrayList<>();
        b = UserService.getWriters();
        writers = b;
        WritersList.getItems().addAll(writers);
    }

    public void handleBackToClientMainButton() throws Exception {

        User us=WritersController.getClient();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("clientmain.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        ClientmainController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) BackToClientMain.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }

    public void handleSelectButton() throws Exception {

        User us=WritersController.getClient();
        User br= WritersList.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("writerBooks.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        WriterBooksController control = loader.getController();
        control.initData(us,br);
        Stage stage1 = (Stage) Select.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            UserService.loadUsersFromFile();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }

    }


}

