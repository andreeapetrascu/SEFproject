package org.example.project.Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.project.Actions.Book;
import org.example.project.Actions.User;
import org.example.project.Services.BookService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditBookController implements Initializable {

    @FXML
    public AnchorPane rootPane2;
    @FXML
    public Label userLabel;
    @FXML
    public Text editMessage;
    @FXML
    public Button cancelButton;
    @FXML
    public Button saveChanges;
    @FXML
    public TextField bookNameField;
    @FXML
    public TextField quantityField;
    @FXML
    public TextField priceField;
    @FXML
    public TextField numProd;
    @FXML
    public ImageView bookImage;
    @FXML
    public Button changeImgButton;


    public static User getWriter() {
        return writer;
    }

    private static User writer;
    private static Book book;
    private String path;

    public EditBookController()
    {

    }

    public void initData(User wr, Book p) throws Exception{
        this.writer = wr;
        this.userLabel.setText("Hello, " + writer.getUsername() + "!");
        this.book = p;
        this.quantityField.setText(book.getStringQuantity());
        this.numProd.setText(book.getStringNoOfItems());
        this.priceField.setText(book.getStringPrice());
        this.bookNameField.setText(book.getBookName());
        bookImage.setImage(new Image(book.getImageUrl()));
        setPath("def_pic.jpg");

    }

    public void handleChangeImgButton()
    {
        Stage stage = (Stage) changeImgButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg");
        fileChooser.getExtensionFilters().add(imageFilter);
        File imageFile = fileChooser.showOpenDialog(stage);
        if (imageFile.isFile()){
            setPath(imageFile.toURI().toString());
            bookImage.setImage(new Image(imageFile.toURI().toString()));
        }
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }


    public void handleCancelAction() throws IOException, Exception {
        User us=EditBookController.getWriter();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("book.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        BooksController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) cancelButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }

    public void handleSaveChangesAction(){
        if (fieldsReadyToSubmit()) {

            int quant = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            int number = Integer.parseInt(numProd.getText());
            String imageUrl = getPath();
            String name = bookNameField.getText();
            BookService.editBook(book,name,imageUrl,quant,number,price);
            this.editMessage.setText("Changes has been successfully saved!");


        }
    }

    private boolean fieldsReadyToSubmit()
    {
        if (bookNameField.getText() == null || bookNameField.getText().trim().isEmpty() || quantityField.getText()==null || quantityField.getText().trim().isEmpty() || priceField.getText() == null || priceField.getText().trim().isEmpty())
            return false;
        return true;
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

