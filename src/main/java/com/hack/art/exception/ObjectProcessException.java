package com.hack.art.exception;

/**
 * Created by ROLO on 26.04.2016.
 */
public class ObjectProcessException extends Exception{

    public ObjectProcessException() {
    }

    public ObjectProcessException(String message) {
        super(message);
    }

    public ObjectProcessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectProcessException(Throwable cause) {
        super(cause);
    }

    public ObjectProcessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
