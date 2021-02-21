package org.db.REST.DB.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UnhandledExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(UnhandledException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String userExceptionHandler(UnhandledException ex) {
        return ex.getMessage();
    }

}
