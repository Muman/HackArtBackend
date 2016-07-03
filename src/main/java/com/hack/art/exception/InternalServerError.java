package com.hack.art.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ROLO on 18.02.2016.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerError extends RuntimeException{

    public InternalServerError(){
    }

    public InternalServerError(Throwable th){
        super(th);
    }

    public InternalServerError(String msg){
        super(msg);
    }

    public InternalServerError(String msg, Throwable th){
        super(msg, th);
    }
}
