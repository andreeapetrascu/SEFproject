package org.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.project.Actions.User;
import org.example.project.Exceptions.BookNotInStock;
import org.example.project.Actions.Book;
import org.example.project.Services.OrdersService;
import org.example.project.Services.BookService;

import java.util.ArrayList;
import java.util.List;

public class WriterBooksController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    public ListView<Book> BooksList;
    @FXML
    public Text userBox;
    @FXML
    private Button Order;
    @FXML
    private Button BackToWriters;
    @FXML
    private ImageView SelectedBookImage;
    @FXML
    private Text orderMessage;
    private static User client;
    public void initData(User c, User selWriter) throws Exception {
        this.client = c;
        this.selectedWriter = selWriter;
        this.userBox.setText(client.getUsername());
        updateWriterBooksList();
        OrdersService.loadOrdersFromFile();
    }

    public static User getClient() {
        return client;
    }

    public void handleBackWritersButton() throws Exception {

        User us=WriterBooksController.getClient();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("writers.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        WritersController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) BackToWriters.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }

    public User getSelectedWriter() {
        return selectedWriter;
    }

    private User selectedWriter;

    private static List<Book> writerBooks = new ArrayList<>();

    private void updateWriterBooksList() throws Exception {
        BooksList.getItems().clear();
        writerBooks.removeAll(writerBooks);
        List<Book> p = new ArrayList<>();
        p = BookService.getBooks(selectedWriter.getUsername());
        writerBooks = p;
        BooksList.getItems().addAll(writerBooks);
    }

    public Book getSelectedProd() {
        return selectedProd;
    }

    private Book selectedProd;

    public void listViewSelectedBook(){
        selectedProd = BooksList.getSelectionModel().getSelectedItem();
        updateGUI();
    }
    private void updateGUI()
    {
        SelectedBookImage.setImage(new Image(selectedProd.getImageUrl()));
        this.orderMessage.setText("");
    }

    public void handleOrderAction() throws Exception {
        OrdersService.loadOrdersFromFile();
        if (BooksList.getSelectionModel().getSelectedItem()!=null)
        {
            try {
                BookService.updateNumberOfItems(this.getSelectedProd());
                OrdersService.addOrder(this.getClient().getUsername(), this.getSelectedProd());
                this.orderMessage.setText("You successfully ordered the book");
            } catch (BookNotInStock var2){
                this.orderMessage.setText(var2.getMessage());
            }


        }
        updateWriterBooksList();



    }
}

