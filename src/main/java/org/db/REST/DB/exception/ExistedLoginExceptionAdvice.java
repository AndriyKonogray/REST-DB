package org.db.REST.DB.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExistedLoginExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(ExistedLoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String userExceptionHandler(UnhandledException ex) {
        return ex.getMessage();
    }

}
