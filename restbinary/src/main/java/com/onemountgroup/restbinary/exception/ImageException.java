package com.onemountgroup.restbinary.exception;

import org.springframework.http.HttpStatus;

public class ImageException extends RuntimeException {

    public HttpStatus status;

    public ImageException(String message, Throwable cause, HttpStatus status) {
        super(message, cause, false, false);
        this.status = status;
    }

    public ImageException(String message, HttpStatus status) {
        super(message, null, false, false);
        this.status = status;
      }

    public ImageException(String message) {
        super(message, null, false, false);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public ImageException(String message, Throwable cause) {
        super(message, cause, false, false);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
