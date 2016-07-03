package com.hack.art.handler;

import com.hack.art.util.LogUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by ROLO on 18.02.2016.
 */
@ControllerAdvice
public class ExceptionHandlerUtil {

    private Logger logger = Logger.getLogger(ExceptionHandlerUtil.class);

    @Autowired
    private LogUtil log;

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Void> hadleSQLException(HttpServletRequest rq, Exception e) {
        log.error(e, rq.getRequestURL().toString());
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleInternalException(HttpServletRequest rq, Exception e){
        log.error(e, rq.getRequestURL().toString());
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
