package org.example.project.Exceptions;

public class WrongPassword extends Exception {
    public WrongPassword() {
        super(String.format("The password is incorrect"));
    }
}
