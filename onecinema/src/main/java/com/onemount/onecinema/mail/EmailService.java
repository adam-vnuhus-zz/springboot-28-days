package com.onemount.onecinema.mail;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;


/**
 * Created by Olga on 8/22/2016.
 */
public interface EmailService {
    
    void sendMessageUsingThymeleafTemplate(String to,
                                           String subject,
                                           Map<String, Object> templateModel) 
            throws IOException, MessagingException;
}
