package com.onemount.onecinema.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import javax.mail.MessagingException;

import com.onemount.onecinema.request.EmailRequest;
import com.onemount.onecinema.service.EmailService;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api")
public class EmailController {
    private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    EmailService emailService;

    @Operation(summary = "Sent emails to confirm order")
    @PostMapping(value = "/emails")
    public @ResponseBody ResponseEntity<String> sendSimpleEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendSimpleEmail(emailRequest.getEmail(), "Order Confirmation!!!",
                    "Thanks for your recent order: \n" + "Your film: " + emailRequest.getFilmTitle() + "\n" + "Date: "
                            + emailRequest.getDate() + "\n");
        } catch (MailException mailException) {
            LOG.error("Error while sending out email..{}", mailException.getStackTrace());
            LOG.error("Error while sending out email..{}", mailException.fillInStackTrace());
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
        return ResponseEntity.ok().body("Please check your inbox!!!");
    }
}
