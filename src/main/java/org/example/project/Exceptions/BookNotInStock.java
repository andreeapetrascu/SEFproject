package org.example.project.Exceptions;

public class BookNotInStock extends Exception {

    public BookNotInStock() {
        super(String.format("The book is out of stock!"));
    }

}
