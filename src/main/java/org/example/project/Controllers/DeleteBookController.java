package org.example.project.Controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.project.Actions.Book;
import javafx.scene.control.Button;
import org.example.project.Actions.User;
import org.example.project.Services.BookService;



public class DeleteBookController {

    @FXML
    public AnchorPane root;
    @FXML
    public Text text;

    @FXML
    public Button yesButton;
    @FXML
    public Button back;
    @FXML
    public Button noButton;

    public DeleteBookController(){

    }
    private static User writer;
    private static Book book;

    public static User getWriter() {
        return writer;
    }

    public void initData(User us, Book p)
    {
        this.writer = us;
        this.text.setText("Hello, " + writer.getUsername() + "!");
        this.book = p;
    }

    public void handleYesButton() throws Exception {
        BookService.deleteBook(book);

    }

    public void handleNoButton() throws Exception {
        User us=DeleteBookController.getWriter();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("book.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        BooksController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) noButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }

    public void handleBackButton() throws Exception {
        User us=DeleteBookController.getWriter();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("book.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        BooksController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) back.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }

}

