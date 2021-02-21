package org.db.REST.DB.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Could not find user with the following id: " + id);
    }
}
