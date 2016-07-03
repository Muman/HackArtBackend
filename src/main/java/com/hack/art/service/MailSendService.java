package com.hack.art.service;

import java.io.File;

/**
 * Created by ROLO on 02.04.2016.
 */
public interface MailSendService{

    void sendMail(String email, String subject, String body, File... attachment);
}
