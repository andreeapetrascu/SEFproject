package org.example.project.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import org.example.project.Actions.User;
import org.example.project.Exceptions.BookAlreadyExists;
import org.example.project.Services.BookService;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

    @FXML
    public AnchorPane rootPane2;
    @FXML
    public Label userLabel;
    @FXML
    public Text registrationMessage;
    @FXML
    public Button cancelButton;
    @FXML
    public Button addButton;
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
    public Button selectImageButton;

    @FXML
    public Text addBookMessage;

    @FXML
    public Button resetImageButton;

    private static User writer;
    private String path;

    public AddBookController(){

    }

    public void handleResetAction(){
        bookImage.setImage(new Image("def_pic.jpg"));
        setPath("def_pic.jpg");
    }

    public void handleSelectImage(){

        Stage stage = (Stage) selectImageButton.getScene().getWindow();
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


    public void handleAddAction(){
        if (fieldsReadyToSubmit()) {
            try {
                int quant = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                int number = Integer.parseInt(numProd.getText());
                String imageUrl = getPath();
                BookService.addBook(this.bookNameField.getText(), imageUrl, quant, price, number);
                this.addBookMessage.setText("Book has been successfully added!");
            } catch (BookAlreadyExists var2) {
                this.addBookMessage.setText(var2.getMessage());
            }
        }

    }
    private boolean fieldsReadyToSubmit()
    {
        if (bookNameField.getText() == null || bookNameField.getText().trim().isEmpty() || quantityField.getText()==null || quantityField.getText().trim().isEmpty() || priceField.getText() == null || priceField.getText().trim().isEmpty())
            return false;
        return true;
    }

    public void handleCancelAction() throws IOException, Exception {
        User us=AddBookController.getWriter();
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

    public static User getWriter() {
        return writer;
    }

    public static void setWriter(User writer) {
        AddBookController.writer = writer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)

    {

        priceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("\\d*(\\.\\d*)?")){
                    priceField.setText(oldValue);
                }
            }
        });

        quantityField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("\\d*?")){
                    quantityField.setText(oldValue);
                }
            }
        });

        numProd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("\\d*?")){
                    numProd.setText(oldValue);
                }
            }
        });
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void initialize(User us) {
        this.setWriter(us);
        try {
            BookService.loadBookFromFile();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
        bookImage.setImage(new Image("def_pic.jpg"));
        setPath("def_pic.jpg");
    }
}

