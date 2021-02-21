package org.db.REST.DB.exception;


public class ExistedLoginException extends RuntimeException {
    public ExistedLoginException() {
        super("That login already exist");
    }
}
