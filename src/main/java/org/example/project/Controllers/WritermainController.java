package org.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.project.Actions.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WritermainController implements Initializable {
    @FXML
    public AnchorPane rootPane2;
    @FXML
    public Text userBox;
    @FXML
    private Label usernameLabel;

    @FXML
    public Button logoutButton;

    @FXML
    public Button viewBooksButton;

    @FXML
    public Button viewOrdersButton;
    private static User writer;


    public WritermainController()
    {

    }
    public void initData(User wr){
        this.writer = wr;
        this.usernameLabel.setText("Hello, " + writer.getUsername() + "!");
    }

    public static User getWriter() {
        return writer;
    }

    public static void setWriter(User writer) {
        WritermainController.writer = writer;
    }

    public void handleLogOutAction() throws IOException {
        Stage stage = (Stage) rootPane2.getScene().getWindow();
        Stage prevStage = (Stage) logoutButton.getScene().getWindow();
        Parent root = (Parent) FXMLLoader.load(this.getClass().getClassLoader().getResource("start.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleViewBooksAction() throws Exception
    {
        User us=WritermainController.getWriter();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("book.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        BooksController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) viewBooksButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }

    public void handleViewOrdersAction() throws Exception{
        User us=WritermainController.getWriter();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("WriterOrders.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        OrdersWriterController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) viewBooksButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
