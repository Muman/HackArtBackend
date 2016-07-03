package com.hack.art.exception;

/**
 * Created by ROLO on 02.04.2016.
 */
public class MailSendException extends RuntimeException {

    public MailSendException() {
    }

    public MailSendException(String message) {
        super(message);
    }

    public MailSendException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailSendException(Throwable cause) {
        super(cause);
    }

}
