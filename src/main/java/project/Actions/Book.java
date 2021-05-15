package project.Actions;

//import javafx.scene.image.Image;

import java.util.Objects;

public class Book {

    private String writerName;
    private String bookName;
    private String writerUsername;
    private String imageUrl;
    private int quantity;
    private double price;
    private String noPages;
    private int noOfItems;

    public Book(){

    }
    public Book(String writerName, String bookName, String writerUsername, String imageUrl, int quantity, double price, String noPages, int noOfItems) {
        this.writerName = writerName;
        this.bookName = bookName;
        this.writerUsername = writerUsername;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.price = price;
        this.noPages=noPages;
        this.noOfItems=noOfItems;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public String getStringNoOfItems() {
        return Integer.toString(noOfItems);
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getWriterUsername() {
        return writerUsername;
    }

    public void setWriterUsername(String writerUsername) {
        this.writerUsername = writerUsername;
    }

    public String getImageUrl() {
        return imageUrl;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }
    public String getStringQuantity() {
        return Integer.toString(quantity);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public double getPrice() {
        return price;
    }

    public String getStringPrice() {
        return Double.toString(price);
    }

    public String getnoPages() {
        return noPages;
    }

    public void setnoPages(String noPages) {
        this.noPages = noPages;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return quantity == book.quantity &&
                Double.compare(book.price, price) == 0 &&
                Objects.equals(writerName, book.writerName) &&
                Objects.equals(bookName, book.bookName) &&
                Objects.equals(writerUsername, book.writerUsername) &&
                Objects.equals(imageUrl, book.imageUrl) &&
                Objects.equals(noPages, book.noPages);
    }

    public int hashCode() {
        return Objects.hash(writerName, bookName, writerUsername, imageUrl, quantity, price, noPages);
    }

    @Override
    public String toString() {
        return bookName + ", " + quantity + noPages + ", Books on stock: " + noOfItems + ", "  + this.price + "lei";
    }
}
