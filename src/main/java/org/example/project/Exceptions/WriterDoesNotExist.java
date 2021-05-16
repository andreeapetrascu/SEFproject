package org.example.project.Exceptions;

public class WriterDoesNotExist extends Exception {
        private String name;

    public WriterDoesNotExist(String name) {
            super(String.format("A wirter with the username '%s' does not exist", name));
            this.name = name;
        }

        public String getUsername() {
            return this.name;
        }
}
