package org.db.REST.DB.exception;

public class UnhandledException extends RuntimeException {
    public UnhandledException() {
        super("Some trouble");
    }
}
