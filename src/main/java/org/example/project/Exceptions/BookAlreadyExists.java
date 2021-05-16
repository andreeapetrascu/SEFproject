package org.example.project.Exceptions;

public class BookAlreadyExists extends Exception {
    private String bookName;

    public BookAlreadyExists(String bookName) {
        super(String.format("An identical product called '%s' already exists!", bookName));
        this.bookName = bookName;
    }

    public String getBookName() {
        return this.bookName;
    }
}
