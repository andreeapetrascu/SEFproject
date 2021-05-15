package project.Actions;

import project.Services.UserService;

import java.util.Objects;
import java.util.Random;

public class Order {

    private String clientUsername;
    private Book book;
    private int orderNo;
    private String status;

    public Order(){

    }
    public Order(String clientUsername, Book book, int orderNo, String status) {
        this.clientUsername = clientUsername;
        this.book = book;
        this.orderNo = orderNo;
        this.status = status;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public Book getBook() {return book;};

    public void setBook(Book book) {this.book = book;};

    public int getOrderNo() { return orderNo;}

    public void setOrderNo(int orderNo) { this.orderNo = orderNo;};

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderNo == order.orderNo &&
                Objects.equals(clientUsername, order.clientUsername) &&
                Objects.equals(book, order.book);

    }

    public int hashCode() {
        return Objects.hash(clientUsername, book, orderNo);
    }

    @Override
    public String toString() {
        try {
            return "Order no: " + this.orderNo + ", Book: " + this.book.getBookName() + ", Price: " + this.book.getPrice() + ", Writer: " + this.book.getWriterName() + ", Client: " + UserService.getUser(this.clientUsername).getName() + ", Status: " + this.status;
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}