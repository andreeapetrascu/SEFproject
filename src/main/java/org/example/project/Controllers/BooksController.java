package org.example.project.Controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.project.Actions.Book;
import org.example.project.Actions.User;
import org.example.project.Services.BookService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BooksController implements Initializable {
    @FXML
    public AnchorPane rootPane2;
    @FXML
    public Label userLabel;
    @FXML
    public Text registrationMessage;
    @FXML
    public Button backToMainButton;
    @FXML
    public ListView<Book> booksList;
    @FXML
    public Button addBookButton;
    @FXML
    public Button editBookButton;
    @FXML
    public Button deleteBookButton;

    @FXML
    public ImageView selectedBookImg;

    private static User writer;



    private static Book selectedBook;
    private static List<Book> books = new ArrayList<>();

    private void updateBookList() throws Exception {
        booksList.getItems().clear();
        books.removeAll(books);
        List<Book> p = new ArrayList<>();
        p = BookService.getBooks(writer.getUsername());
        books = p;
        booksList.getItems().addAll(books);
    }
    public static List<Book> getBooks() {
        return books;
    }
    public void selectedBook(Book book) throws Exception {
        selectedBook=book;
        updateBookList();
        updateGUI();

    }

    public void listViewselectedBook(){
        if (booksList.getSelectionModel().getSelectedItem()!=null) {
            selectedBook = booksList.getSelectionModel().getSelectedItem();
            updateGUI();
        }
    }
    private void updateGUI()
    {
        selectedBookImg.setImage(new Image(selectedBook.getImageUrl()));
    }
    public BooksController(){

    }

    public void initData(User br) throws Exception{
        this.writer = br;
        this.userLabel.setText("Hello, " + writer.getUsername() + "!");
        updateBookList();

    }

    public void handleBackToMain() throws IOException {
        User us=BooksController.getWriter();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("writermain.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        WritermainController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) backToMainButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }

    public void handleAddBookAction() throws  IOException {
        User us=BooksController.getWriter();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("addBook.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        AddBookController control = loader.getController();
        control.initialize(us);
        Stage stage1 = (Stage) addBookButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }

    public void handleEditBookAction() throws Exception {
        User us=BooksController.getWriter();
        Book p = BooksController.getselectedBook();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("editBook.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        EditBookController control = loader.getController();
        control.initData(us,p);
        Stage stage1 = (Stage) editBookButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();

    }

    public static Book getselectedBook() {
        return selectedBook;
    }

    public void handleDeleteBookAction() throws Exception {
        User us=BooksController.getWriter();
        Book p = BooksController.getselectedBook();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("deleteBook.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        DeleteBookController control = loader.getController();
        control.initData(us,p);
        Stage stage1 = (Stage) deleteBookButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();

    }

    public static User getWriter() {
        return writer;
    }

    public static void setWriter(User writer) {
        BooksController.writer = writer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            BookService.loadBookFromFile();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }

    }
}

